"""Blocky Font Builder v2.4 — build cache-safe regular + monospaced TTFs.

Double-click this .py file on Windows. Put Blocky.zip beside it.
Generated in the same folder:
    Blocky.ttf
    Blocky_monospace.ttf
    Blocky_preview.png
    Blocky_monospace_preview.png

The internal font FAMILY NAMES include a fingerprint of the glyphs, so the
Windows Font Viewer and IntelliJ cannot mistake a new build for an older one.
The output FILE NAMES are intentionally stable.

Dependencies: py -m pip install pillow fonttools

Optional CLI: py Blocky_Font_Builder_v2.4.py path/to/Blocky.zip
"""

from __future__ import annotations

from hashlib import sha256
from io import BytesIO
from pathlib import Path
import re
import sys
import zipfile

PIXEL_UNITS = 100
UNITS_PER_EM = 1000
LETTER_SPACING_PX = 1
SPACE_WIDTH_PX = 3
MONO_CELL_WIDTH_PX = 6

# Keeps a separate installed font family for each new glyph revision.
# Set False only after you finish editing and want a stable installed font name.
CACHE_SAFE_FONT_NAMES = True

# The author's uppercase-style question mark has priority.
EXTRA_GLYPHS = {
    "._question_.png": "?",
    "._backslash_.png": "\\",
    "._colon_.png": ":",
    "._exclaimation_.png": "!",  # Preserve original spelling.
    "._slash_.png": "/",
    "._double-quote_.png": '"',
    "._left-arrow-bracket_.png": "<",
    "._right-arrow-bracket_.png": ">",
    "._single-quote_.png": "'",
    "._star_.png": "*",
}


def character_for_file(filename: str) -> str | None:
    if filename in EXTRA_GLYPHS:
        return EXTRA_GLYPHS[filename]
    match = re.fullmatch(r"_(.)_\.png", filename, flags=re.IGNORECASE)
    if match:
        ch = match.group(1)
        if ch.isdigit() or (ch.isalpha() and ch.isupper()) or not ch.isalnum():
            return ch
    match = re.fullmatch(r"(.)_\.png", filename, flags=re.IGNORECASE)
    if match and match.group(1).isalpha() and match.group(1).islower():
        return match.group(1)
    return None


def choose_zip(folder: Path) -> Path:
    zips = sorted((p for p in folder.iterdir() if p.is_file() and p.suffix.lower() == '.zip'),
                  key=lambda p: p.name.lower())
    # A folder can contain both an old Blocky.zip and a newer revision.
    # Never silently choose an old archive if more than one exists.
    if len(zips) == 1:
        return zips[0]
    if not zips:
        raise FileNotFoundError('No .zip file found next to the builder. Put Blocky.zip there.')
    try:
        from tkinter import Tk, filedialog
        root = Tk()
        root.withdraw()
        root.attributes('-topmost', True)
        name = filedialog.askopenfilename(title='Choose glyph ZIP', initialdir=str(folder),
                                           filetypes=[('ZIP archives', '*.zip')], parent=root)
        root.destroy()
    except Exception as exc:
        raise RuntimeError('Multiple ZIPs found; rename the correct one Blocky.zip.') from exc
    if not name:
        raise RuntimeError('No ZIP selected.')
    return Path(name)


def read_glyphs(source_zip: Path):
    from PIL import Image
    glyphs = {}
    skipped = []
    if not zipfile.is_zipfile(source_zip):
        raise ValueError(f'Not a ZIP archive: {source_zip}')
    with zipfile.ZipFile(source_zip) as archive:
        for member in archive.namelist():
            if not member.lower().endswith('.png'):
                continue
            filename = Path(member).name
            char = character_for_file(filename)
            if char is None:
                skipped.append(filename)
                continue
            if char in glyphs:
                raise ValueError(f'Duplicate character {char!r} from {filename}')
            with archive.open(member) as png:
                glyphs[char] = Image.open(BytesIO(png.read())).convert('RGBA')
    if not glyphs:
        raise ValueError('No recognized glyph images found.')
    return glyphs, skipped


def glyph_fingerprint(glyphs: dict, monospace: bool):
    h = sha256()
    h.update(f'v2.4/mono={monospace}/cell={MONO_CELL_WIDTH_PX if monospace else "variable"}/spacing={LETTER_SPACING_PX}'.encode('ascii'))
    for char, image in sorted(glyphs.items(), key=lambda item: ord(item[0])):
        h.update(char.encode('utf-8'))
        h.update(f'{image.width}x{image.height};'.encode('ascii'))
        h.update(image.tobytes())
    return h.hexdigest()[:6].upper()


def visible_pixels(image):
    rgba = image.convert('RGBA')
    width, height = rgba.size
    filled = []
    for y in range(height):
        for x in range(width):
            r, g, b, a = rgba.getpixel((x, y))
            if a >= 128 and r + g + b < 600:
                filled.append((x, y))
    return filled, height


def make_glyph(pixels, image_height: int, padding: int = 0, horizontal_scale: float = 1.0):
    from fontTools.pens.ttGlyphPen import TTGlyphPen
    pen = TTGlyphPen(None)
    if not pixels:
        return pen.glyph()
    left_edge = min(x for x, _ in pixels)
    for x, y in pixels:
        x0 = round((x - left_edge) * PIXEL_UNITS * horizontal_scale) + padding
        x1 = round((x - left_edge + 1) * PIXEL_UNITS * horizontal_scale) + padding
        y0 = (image_height - 1 - y) * PIXEL_UNITS
        y1 = y0 + PIXEL_UNITS
        if x1 <= x0:
            raise ValueError('Monospace cell too narrow: increase MONO_CELL_WIDTH_PX.')
        pen.moveTo((x0, y0))
        pen.lineTo((x1, y0))
        pen.lineTo((x1, y1))
        pen.lineTo((x0, y1))
        pen.closePath()
    return pen.glyph()


def build_one_font(glyphs: dict, path: Path, monospace: bool) -> str:
    from fontTools.fontBuilder import FontBuilder
    from fontTools.pens.ttGlyphPen import TTGlyphPen
    from fontTools.ttLib.tables.O_S_2f_2 import Panose

    if MONO_CELL_WIDTH_PX < 2:
        raise ValueError('MONO_CELL_WIDTH_PX must be at least 2.')

    glyph_order = ['.notdef', 'space']
    cmap = {32: 'space'}
    outlines = {'.notdef': TTGlyphPen(None).glyph(), 'space': TTGlyphPen(None).glyph()}
    cell = MONO_CELL_WIDTH_PX * PIXEL_UNITS
    metrics = {'.notdef': (cell if monospace else 6 * PIXEL_UNITS, 0),
               'space': (cell if monospace else SPACE_WIDTH_PX * PIXEL_UNITS, 0)}
    for char, image in sorted(glyphs.items(), key=lambda it: ord(it[0])):
        filled, height = visible_pixels(image)
        width = max(x for x, y in filled) - min(x for x, y in filled) + 1 if filled else 0
        padding = 0
        scale = 1.0
        if monospace:
            # Keep at least one blank pixel per cell; fit wide characters.
            natural_width = width * PIXEL_UNITS
            max_ink = (MONO_CELL_WIDTH_PX - 1) * PIXEL_UNITS
            scale = min(1.0, max_ink / natural_width) if natural_width else 1.0
            rendered_width = round(natural_width * scale)
            padding = ((cell - rendered_width) // (2 * PIXEL_UNITS)) * PIXEL_UNITS
            advance = cell
            bearing = padding if filled else 0
        else:
            advance = (width + LETTER_SPACING_PX) * PIXEL_UNITS if filled else SPACE_WIDTH_PX * PIXEL_UNITS
            bearing = 0
        name = f'uni{ord(char):04X}'
        glyph_order.append(name)
        cmap[ord(char)] = name
        outlines[name] = make_glyph(filled, height, padding, scale)
        metrics[name] = (advance, bearing)

    fingerprint = glyph_fingerprint(glyphs, monospace)
    if monospace:
        base = f'Blocky Mono {MONO_CELL_WIDTH_PX}px'
        ps_base = f'BlockyMono{MONO_CELL_WIDTH_PX}px'
    else:
        base = 'Blocky'
        ps_base = 'Blocky'
    suffix = f' {fingerprint}' if CACHE_SAFE_FONT_NAMES else ''
    family = base + suffix
    ps_name = ps_base + (f'-{fingerprint}' if CACHE_SAFE_FONT_NAMES else '') + '-Regular'

    ascent = max(900, max(img.height for img in glyphs.values()) * PIXEL_UNITS)
    fb = FontBuilder(UNITS_PER_EM, isTTF=True)
    fb.setupGlyphOrder(glyph_order)
    fb.setupCharacterMap(cmap)
    fb.setupGlyf(outlines)
    fb.setupHorizontalMetrics(metrics)
    fb.setupHorizontalHeader(ascent=ascent, descent=-100)
    fb.setupNameTable({
        'familyName': family,
        'styleName': 'Regular',
        'uniqueFontIdentifier': f'{family} Regular 0.24',
        'fullName': f'{family} Regular',
        'psName': ps_name,
        'version': 'Version 0.24',
    })
    settings = dict(sTypoAscender=ascent, sTypoDescender=-100,
                    usWinAscent=ascent, usWinDescent=100,
                    sxHeight=500, sCapHeight=700)
    if monospace:
        panose = Panose()
        panose.bFamilyType = 2
        panose.bProportion = 9
        settings['panose'] = panose
    fb.setupOS2(**settings)
    fb.setupPost(isFixedPitch=1 if monospace else 0)
    fb.setupMaxp()
    fb.save(str(path))
    return family


def make_preview(path: Path, output: Path, family: str):
    from PIL import Image, ImageDraw, ImageFont
    font = ImageFont.truetype(str(path), 36)
    # These strings include ASCII, numbers and optional signs; unsupported glyphs
    # will display blank until the user adds them.
    lines = [
        family,
        'ABCDEFGHIJKLMNOPQRSTUVWXYZ',
        'abcdefghijklmnopqrstuvwxyz',
        '0123456789  ! ? : ; . , - _',
        'The quick brown fox jumps over the lazy dog.',
        'public static void main(String[] args)',
    ]
    canvas = Image.new('RGB', (1350, 390), '#ffffff')
    painter = ImageDraw.Draw(canvas)
    for i, line in enumerate(lines):
        painter.text((20, 18 + 59*i), line, font=font, fill='#111111')
    canvas.save(output)


def notify(title: str, message: str, error: bool = False):
    try:
        from tkinter import Tk, messagebox
        root = Tk()
        root.withdraw()
        root.attributes('-topmost', True)
        if error:
            messagebox.showerror(title, message, parent=root)
        else:
            messagebox.showinfo(title, message, parent=root)
        root.destroy()
    except Exception:
        print(f'\n{title}: {message}')


def main():
    folder = Path(__file__).resolve().parent
    try:
        if len(sys.argv) > 2:
            raise ValueError('Provide at most one ZIP path.')
        archive = Path(sys.argv[1]).resolve() if len(sys.argv) == 2 else choose_zip(folder)
        glyphs, skipped = read_glyphs(archive)
        regular_path = folder / 'Blocky.ttf'
        mono_path = folder / 'Blocky_monospace.ttf'
        reg_family = build_one_font(glyphs, regular_path, False)
        mono_family = build_one_font(glyphs, mono_path, True)
        make_preview(regular_path, folder / 'Blocky_preview.png', reg_family)
        make_preview(mono_path, folder / 'Blocky_monospace_preview.png', mono_family)
        print(f'Source: {archive.name}')
        print(f'Regular family: {reg_family} ({regular_path.name})')
        print(f'Monospace family: {mono_family} ({mono_path.name})')
        print(f'Generated 2 TTFs and 2 PNG previews, {len(glyphs)} glyphs + space')
        if skipped:
            print('Unmapped PNGs:', ', '.join(skipped))
        message = (f'Both fonts rebuilt from {archive.name}.\n\n'
                   f'Regular: {reg_family}\nMonospace: {mono_family}\n\n'
                   'New builds have unique internal names; install these fonts and select the NEW family in Paint 3D / IntelliJ.')
        if skipped:
            message += f'\n\n{len(skipped)} unrecognized PNG(s); see console.'
        notify('Blocky Builder v2.4 — Done', message)
        return 0
    except Exception as exc:
        import traceback
        traceback.print_exc()
        notify('Blocky Builder v2.4 — Error', str(exc) + '\n\nRequired libraries: py -m pip install pillow fonttools', True)
        return 1


if __name__ == '__main__':
    sys.exit(main())
