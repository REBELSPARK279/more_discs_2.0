import net.rebelspark.more_discs_rebelspark.rarityHandlers.DiscRarities;
import net.rebelspark.more_discs_rebelspark.rarityHandlers.DiscRarityTreeMap;

void main() {
    Scanner scanner = new Scanner(System.in);
    init();

    IO.print("Generate for ([I] ModItems, [S] ModSounds, [*] All to the Left, [C] CreativeModeTabGen): ");
    String input = scanner.nextLine();
    IllegalArgumentException illegalArg = new IllegalArgumentException("Illegal Argument \"%s\"".formatted(input));
    if (input.length() != 1) {
        throw illegalArg;
    }
    IO.println();
    if (input.equalsIgnoreCase("I")) {
        genModItemsFile();
    } else if (input.equalsIgnoreCase("S")) {
        genModSoundsFile();
    } else if (input.equalsIgnoreCase("*")) {
        IO.println("ModItems.java");
        IO.println();
        genModItemsFile();
        IO.println();
        IO.println("==================================================");
        IO.println("ModSounds.java");
        IO.println();
        genModSoundsFile();
        IO.println();
    } else if (input.equalsIgnoreCase("C")) {
        IO.println("Creative mode tab gen selected");
        IO.print("Generate tab ([D] All discs, [C] Common, [U] Uncommon, [R] Rare, [E] Epic, [L] Legendary, [M] Mythic, [V] Villager exclusive, [G] Generic wander, [W] Rare wander, [*] All): ");
        input = scanner.nextLine();
        if (input.length() != 1) {
            throw illegalArg;
        }
        if (input.equalsIgnoreCase("D")) {
            genCreativeTabMain();
        } else if (input.equalsIgnoreCase("C")) {
            genCreativeTabRarity(DiscRarities.COMMON);
        } else if (input.equalsIgnoreCase("U")) {
            genCreativeTabRarity(DiscRarities.UNCOMMON);
        } else if (input.equalsIgnoreCase("R")) {
            genCreativeTabRarity(DiscRarities.RARE);
        } else if (input.equalsIgnoreCase("E")) {
            genCreativeTabRarity(DiscRarities.EPIC);
        } else if (input.equalsIgnoreCase("L")) {
            genCreativeTabRarity(DiscRarities.LEGENDARY);
        } else if (input.equalsIgnoreCase("M")) {
            genCreativeTabRarity(DiscRarities.MYTHIC);
        } else if (input.equalsIgnoreCase("V")) {
            genCreativeTabRarity(DiscRarities.VILLAGER);
        } else if (input.equalsIgnoreCase("G")) {
            genCreativeTabRarity(DiscRarities.WANDER_GEN);
        } else if (input.equalsIgnoreCase("W")) {
            genCreativeTabRarity(DiscRarities.WANDER_RARE);
        } else if (input.equalsIgnoreCase("*")) {
            for (DiscRarities rarities: DiscRarities.values()) {
                IO.println("%s tab".formatted(rarities));
                genCreativeTabRarity(rarities);
                IO.println();
                IO.println("----------------------------------------");
            }
        } else {
            throw illegalArg;
        }
    } else {
        throw illegalArg;
    }
}

private static final ArrayList<String> itemList = new ArrayList<>();
private static final ArrayList<String> fragList = new ArrayList<>();

private static void init() {
    IO.println("[DEBUG] " + DiscRarityTreeMap.rarityMap.size());
    IO.println("[DEBUG] " + DiscRarityTreeMap.rarityMap.toString());
    for (int i = 0; i < DiscRarityTreeMap.rarityMap.size(); i++) {
        itemList.add(DiscRarityTreeMap.rarityMap.keySet().toArray()[i].toString());
    }
    fragList.add("dog_frag");
    fragList.add("minecraft_frag");
    fragList.add("moog_city2_frag");
    fragList.add("the_end_frag");
}



private static void genModItemsFile() {
    for (String item: itemList) {
        IO.println("public static final Item %1$s = createDisc(\"%2$s_music_disc\", DiscRarityTreeMap.rarityMap.get(\"%2$s\"), ModSounds.%1$s_SOUND);".formatted(item.toUpperCase(), item));
    }
}

private static void genModSoundsFile() {
    for (String item: itemList) {
        IO.println("""
                public static final ResourceKey<JukeboxSong> %1$s_SOUND = ResourceKey.create(Registries.JUKEBOX_SONG, MoreDiscs.id("%2$s"));
                public static final SoundEvent %1$s = Registry.register(BuiltInRegistries.SOUND_EVENT, MoreDiscs.id("%2$s"), SoundEvent.createVariableRangeEvent(MoreDiscs.id("%2$s")));
                """.formatted(item.toUpperCase(), item));
    }
}

private static void genCreativeTabMain() {
    for (String item: itemList) {
        IO.println("""
                output.accept(ModItems.%s);""".formatted(item.toUpperCase()));
    }
    for (String frag: fragList) {
        IO.println("""
                output.accept(ModItems.%s);""".formatted(frag.toUpperCase()));
    }
}

private static void genCreativeTabRarity(DiscRarities rarity) {
    for (String item: itemList) {
        if (DiscRarityTreeMap.rarityMap.get(item) == rarity) {
            IO.println("""
                    output.accept(ModItems.%s);""".formatted(item.toUpperCase()));
        }
    }
}