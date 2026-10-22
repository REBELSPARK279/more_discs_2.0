package net.rebelspark.generators.AI;

import net.rebelspark.more_discs_rebelspark.rarityHandlers.DiscRarityTreeMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//CREATED BY AI, MINOR CHANGES BY ME

/**
 * Development-only generator for More Discs rarity-scroller textures.
 * Run main() from IntelliJ with the More Discs project root as working directory.
 *
 * - All discs occupy exactly DISC_TICKS in their scroller.
 * - Danny (and other non-Mythic animations) use only their first source frame.
 * - Animated Mythic discs sample their timeline into DISC_TICKS / MYTHIC_FRAME_TICKS
 *   frames; static Mythic placeholders work normally without any .mcmeta.
 * - Mixed 16x16 / 32x32 / 64x64 textures retain the highest resolution in each group.
 * - Existing item/model JSON files are never overwritten.
 */
public final class ScrollerGen {
    private static final String MOD_ID = "more_discs_rebelspark";
    private static final int DISC_TICKS = 10;
    // 2 = five sampled Mythic frames, 2 ticks each; 1 = ten, 1 tick each.
    private static final int MYTHIC_FRAME_TICKS = 2;

    private static final Path ASSETS = Path.of("src", "main", "resources", "assets", MOD_ID);
    private static final Path TEXTURES = ASSETS.resolve("textures/item");
    private static final Path SCROLLER_TEXTURES = ASSETS.resolve("textures/item/scroller");
    private static final Path MODELS = ASSETS.resolve("models/item");
    private static final Path ITEMS = ASSETS.resolve("items");

    private static final Pattern FRAME_TIME_PATTERN =
            Pattern.compile("\\\"frametime\\\"\\s*:\\s*(\\d+)");
    private static final Pattern FRAME_INDEX_PATTERN =
            Pattern.compile("\\\"index\\\"\\s*:\\s*(\\d+)");
    private static final Pattern TIME_PATTERN =
            Pattern.compile("\\\"time\\\"\\s*:\\s*(\\d+)");
    private static final Pattern FRAMES_PATTERN =
            Pattern.compile("\\\"frames\\\"\\s*:\\s*\\[");
    private static final Pattern FRAME_ELEMENT_PATTERN =
            Pattern.compile("\\{[^{}]*}|\\d+");

    private ScrollerGen() {
    }

    private record SourceFrame(int index, int ticks) {
    }

    private record DiscTexture(BufferedImage pixels, int frameSize, List<SourceFrame> timeline) {
        int totalTicks() {
            return timeline.stream().mapToInt(SourceFrame::ticks).sum();
        }

        // Find which original animation frame is visible at the given source tick.
        int frameAtTick(long tick) {
            long elapsed = 0;
            for (SourceFrame frame : timeline) {
                elapsed += frame.ticks();
                if (tick < elapsed) {
                    return frame.index();
                }
            }
            return timeline.getLast().index();
        }
    }

    private record ScrollerFrame(DiscTexture source, int sourceIndex, int ticks) {
    }

    public static void main(String[] args) throws IOException {
        if (MYTHIC_FRAME_TICKS <= 0 || DISC_TICKS % MYTHIC_FRAME_TICKS != 0) {
            throw new IllegalArgumentException("MYTHIC_FRAME_TICKS must divide " + DISC_TICKS);
        }

        DiscRarityTreeMap.init();
        Map<String, List<String>> groups = new LinkedHashMap<>();
        for (String group : List.of("common", "uncommon", "rare", "epic", "legendary", "mythic", "trader")) {
            groups.put(group, new ArrayList<>());
        }

        for (var entry : DiscRarityTreeMap.rarityMap.entrySet()) {
            String group = switch (entry.getValue()) {
                case VILLAGER, WANDER_GEN, WANDER_RARE -> "trader";
                default -> entry.getValue().name().toLowerCase(Locale.ROOT);
            };
            List<String> discs = groups.get(group);
            if (discs == null) {
                throw new IllegalArgumentException("Unhandled rarity group: " + entry.getValue());
            }
            discs.add(entry.getKey());
        }

        // Preflight ALL disc textures and metadata before changing output files.
        Map<String, DiscTexture> sources = new LinkedHashMap<>();
        List<String> missing = new ArrayList<>();
        for (List<String> discs : groups.values()) {
            for (String disc : discs) {
                Path textureFile = TEXTURES.resolve(disc + "_music_disc.png");
                if (!Files.isRegularFile(textureFile)) {
                    missing.add(textureFile.toString());
                } else {
                    sources.put(disc, loadTexture(textureFile));
                }
            }
        }
        if (!missing.isEmpty()) {
            throw new IOException("Missing " + missing.size() + " disc texture(s):\n  "
                    + String.join("\n  ", missing)
                    + "\nRun from the More Discs project root, and check filenames.");
        }

        Files.createDirectories(SCROLLER_TEXTURES);
        Files.createDirectories(MODELS);
        Files.createDirectories(ITEMS);

        for (var entry : groups.entrySet()) {
            String group = entry.getKey();
            List<String> discs = entry.getValue();
            if (discs.isEmpty()) {
                System.out.println("Skipping empty group: " + group);
                continue;
            }

            int outputFrameSize = 0;
            List<ScrollerFrame> frames = new ArrayList<>();
            for (String disc : discs) {
                DiscTexture source = sources.get(disc);
                outputFrameSize = Math.max(outputFrameSize, source.frameSize());

                // Preserve equal total scroller time per disc. Mythic animations get
                // multiple sampled frames; all other animated discs (including Danny)
                // remain single-frame previews in their scrollers.
                if (group.equals("mythic") && source.timeline().size() > 1) {
                    int count = DISC_TICKS / MYTHIC_FRAME_TICKS;
                    long totalSourceTicks = source.totalTicks();
                    for (int i = 0; i < count; i++) {
                        // Sample the middle of each evenly spaced time slice.
                        long tick = (2L * i + 1) * totalSourceTicks / (2L * count);
                        frames.add(new ScrollerFrame(source, source.frameAtTick(tick), MYTHIC_FRAME_TICKS));
                    }
                } else {
                    frames.add(new ScrollerFrame(source, source.timeline().getFirst().index(), DISC_TICKS));
                }
            }

            BufferedImage strip = new BufferedImage(
                    outputFrameSize, outputFrameSize * frames.size(), BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < frames.size(); i++) {
                blitNearest(strip, i * outputFrameSize, outputFrameSize, frames.get(i));
            }

            String name = group + "_scroller";
            Path png = SCROLLER_TEXTURES.resolve(name + ".png");
            if (!ImageIO.write(strip, "PNG", png.toFile())) {
                throw new IOException("No PNG writer available for " + png);
            }
            Files.writeString(SCROLLER_TEXTURES.resolve(name + ".png.mcmeta"),
                    scrollerMetadata(frames), StandardCharsets.UTF_8);

            // Only create missing JSON files; preserve edits made in 2.0.
            Path model = MODELS.resolve(name + ".json");
            if (Files.notExists(model)) {
                Files.writeString(model, """
                        {
                          "parent": "minecraft:item/generated",
                          "textures": {
                            "layer0": "%s:item/scroller/%s"
                          }
                        }
                        """.formatted(MOD_ID, name), StandardCharsets.UTF_8);
            }
            Path item = ITEMS.resolve(name + ".json");
            if (Files.notExists(item)) {
                Files.writeString(item, """
                        {
                          "model": {
                            "type": "minecraft:model",
                            "model": "%s:item/%s"
                          }
                        }
                        """.formatted(MOD_ID, name), StandardCharsets.UTF_8);
            }

            System.out.printf("Generated %-19s %2d discs, %2d frames, %dx%d pixels%n",
                    name, discs.size(), frames.size(), strip.getWidth(), strip.getHeight());
        }
        System.out.println("Finished. Each disc occupies " + DISC_TICKS + " ticks in its scroller.");
    }

    private static void blitNearest(BufferedImage output, int top, int targetSize, ScrollerFrame frame) {
        BufferedImage input = frame.source().pixels();
        int inputSize = frame.source().frameSize();
        int inputTop = frame.sourceIndex() * inputSize;
        for (int y = 0; y < targetSize; y++) {
            int sy = inputTop + y * inputSize / targetSize;
            for (int x = 0; x < targetSize; x++) {
                int sx = x * inputSize / targetSize;
                output.setRGB(x, top + y, input.getRGB(sx, sy));
            }
        }
    }

    private static String scrollerMetadata(List<ScrollerFrame> frames) {
        boolean allTenTicks = frames.stream().allMatch(frame -> frame.ticks() == DISC_TICKS);
        if (allTenTicks) {
            // Keep legacy output identical for non-animated scrollers (and for
            // Mythic until your full Mythic animations are available).
            return """
                    {
                      "animation": {
                        "frametime": %d
                      }
                    }
                    """.formatted(DISC_TICKS);
        }

        StringBuilder json = new StringBuilder("{\n  \"animation\": {\n    \"frametime\": 1,\n    \"frames\": [\n");
        for (int i = 0; i < frames.size(); i++) {
            json.append("      {\"index\": ").append(i)
                    .append(", \"time\": ").append(frames.get(i).ticks()).append('}');
            json.append(i == frames.size() - 1 ? "\n" : ",\n");
        }
        return json.append("    ]\n  }\n}\n").toString();
    }

    private static DiscTexture loadTexture(Path pngFile) throws IOException {
        BufferedImage image = ImageIO.read(pngFile.toFile());
        if (image == null) {
            throw new IOException("Invalid PNG: " + pngFile);
        }
        int size = image.getWidth();
        if (size <= 0 || image.getHeight() < size || image.getHeight() % size != 0) {
            throw new IOException("Expected a square PNG or vertical square-frame spritesheet: "
                    + pngFile + " (" + size + "x" + image.getHeight() + ")");
        }

        int physicalFrameCount = image.getHeight() / size;
        Path metadataPath = Path.of(pngFile + ".mcmeta");
        List<SourceFrame> timeline = new ArrayList<>();
        int defaultFrameTicks = 1;
        if (Files.isRegularFile(metadataPath)) {
            String json = Files.readString(metadataPath, StandardCharsets.UTF_8);
            Matcher timeMatcher = FRAME_TIME_PATTERN.matcher(json);
            if (timeMatcher.find()) {
                defaultFrameTicks = positiveInt(timeMatcher.group(1), metadataPath);
            }
            Matcher framesMatcher = FRAMES_PATTERN.matcher(json);
            if (framesMatcher.find()) {
                int begin = framesMatcher.end();
                int end = json.indexOf(']', begin);
                if (end < 0) {
                    throw new IOException("Malformed animation.frames: " + metadataPath);
                }
                Matcher elementMatcher = FRAME_ELEMENT_PATTERN.matcher(json.substring(begin, end));
                while (elementMatcher.find()) {
                    String element = elementMatcher.group();
                    int index;
                    int ticks = defaultFrameTicks;
                    if (element.startsWith("{")) {
                        Matcher indexMatcher = FRAME_INDEX_PATTERN.matcher(element);
                        if (!indexMatcher.find()) {
                            throw new IOException("Animation frame is missing 'index': " + metadataPath);
                        }
                        index = Integer.parseInt(indexMatcher.group(1));
                        Matcher customTime = TIME_PATTERN.matcher(element);
                        if (customTime.find()) {
                            ticks = positiveInt(customTime.group(1), metadataPath);
                        }
                    } else {
                        index = Integer.parseInt(element);
                    }
                    validateFrameIndex(index, physicalFrameCount, metadataPath);
                    timeline.add(new SourceFrame(index, ticks));
                }
                if (timeline.isEmpty()) {
                    throw new IOException("Empty animation.frames list: " + metadataPath);
                }
            }
        } else if (physicalFrameCount > 1) {
            System.out.println("Note: " + pngFile.getFileName()
                    + " has " + physicalFrameCount + " stacked frames but no .mcmeta."
                    + " Using the frames in top-to-bottom order for scroller previews.");
        }

        if (timeline.isEmpty()) {
            for (int index = 0; index < physicalFrameCount; index++) {
                timeline.add(new SourceFrame(index, defaultFrameTicks));
            }
        }
        return new DiscTexture(image, size, List.copyOf(timeline));
    }

    private static int positiveInt(String text, Path file) throws IOException {
        int value;
        try {
            value = Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new IOException("Bad animation frame time in " + file, e);
        }
        if (value <= 0) {
            throw new IOException("Frame time must be positive in " + file);
        }
        return value;
    }

    private static void validateFrameIndex(int index, int frameCount, Path file) throws IOException {
        if (index < 0 || index >= frameCount) {
            throw new IOException("Frame index " + index + " out of range 0.." + (frameCount - 1)
                    + " in " + file);
        }
    }
}
