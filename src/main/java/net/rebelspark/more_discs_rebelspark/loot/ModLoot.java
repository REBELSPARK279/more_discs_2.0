package net.rebelspark.more_discs_rebelspark.loot;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.rebelspark.more_discs_rebelspark.item.ModItems;

public class ModLoot {

    private static ResourceKey<LootTable> vanillaLoot(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                Identifier.withDefaultNamespace(path));
    }

    private static final ResourceKey<LootTable> AMETHYST = vanillaLoot("blocks/amethyst_cluster");
    private static final ResourceKey<LootTable> BLAZE = vanillaLoot("entities/blaze");
    private static final ResourceKey<LootTable> CHERRY_LEAVES = vanillaLoot("blocks/cherry_leaves");
    private static final ResourceKey<LootTable> CHORUS_PLANT = vanillaLoot("blocks/chorus_plant");
    private static final ResourceKey<LootTable> CREEPER = vanillaLoot("entities/creeper");
    private static final ResourceKey<LootTable> DRAGON = vanillaLoot("entities/ender_dragon");
    private static final ResourceKey<LootTable> ENDERMAN = vanillaLoot("entities/enderman");
    private static final ResourceKey<LootTable> GHAST = vanillaLoot("entities/ghast");
    private static final ResourceKey<LootTable> GRASS_BLOCK = vanillaLoot("blocks/grass_block");
    private static final ResourceKey<LootTable> ICE = vanillaLoot("blocks/ice");
    private static final ResourceKey<LootTable> IRON_GOLEM = vanillaLoot("entities/iron_golem");
    private static final ResourceKey<LootTable> JUNGLE_LEAVES = vanillaLoot("blocks/jungle_leaves");
    private static final ResourceKey<LootTable> MAGMA_CUBE = vanillaLoot("entities/magma_cube");
    private static final ResourceKey<LootTable> MOOSHROOM = vanillaLoot("entities/mooshroom");
    private static final ResourceKey<LootTable> QUARTZ_ORE = vanillaLoot("blocks/nether_quartz_ore");
    private static final ResourceKey<LootTable> SEA_LANTERN = vanillaLoot("blocks/sea_lantern");
    private static final ResourceKey<LootTable> SHULKER = vanillaLoot("entities/shulker");
    private static final ResourceKey<LootTable> SKELETON = vanillaLoot("entities/skeleton");
    private static final ResourceKey<LootTable> SLIME = vanillaLoot("entities/slime");
    private static final ResourceKey<LootTable> SPIDER = vanillaLoot("entities/spider");
    private static final ResourceKey<LootTable> STONE = vanillaLoot("blocks/stone");
    private static final ResourceKey<LootTable> STRAY = vanillaLoot("entities/stray");
    private static final ResourceKey<LootTable> ZOMBIE = vanillaLoot("entities/zombie");

    public static void modifyLootTables() {

        LootTableEvents.MODIFY.register((key, builder, source, reg) -> {


            // AERIE
            if (source.isBuiltin() && BuiltInLootTables.END_CITY_TREASURE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.AERIE))
                        .when(LootItemRandomChanceCondition.randomChance(0.04f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.PILLAGER_OUTPOST.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.AERIE))
                        .when(LootItemRandomChanceCondition.randomChance(0.11f));
                builder.withPool(loot);
            }


            // ALPHA
            if (source.isBuiltin() && ZOMBIE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.ALPHA))
                        .when(LootItemRandomChanceCondition.randomChance(0.005f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && CREEPER.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.ALPHA))
                        .when(LootItemRandomChanceCondition.randomChance(0.005f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && SKELETON.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.ALPHA))
                        .when(LootItemRandomChanceCondition.randomChance(0.005f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && SPIDER.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.ALPHA))
                        .when(LootItemRandomChanceCondition.randomChance(0.005f));
                builder.withPool(loot);
            }


            // ARIA MATH
            if (source.isBuiltin() && BLAZE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.ARIA_MATH))
                        .when(LootItemRandomChanceCondition.randomChance(0.04f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && MAGMA_CUBE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.ARIA_MATH))
                        .when(LootItemRandomChanceCondition.randomChance(0.015f));
                builder.withPool(loot);
            }


            // BALLAD OF THE CATS
            if (source.isBuiltin() && GHAST.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BALLAD_OF_THE_CATS))
                        .when(LootItemRandomChanceCondition.randomChance(0.04f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && QUARTZ_ORE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BALLAD_OF_THE_CATS))
                        .when(LootItemRandomChanceCondition.randomChance(0.01f));
                builder.withPool(loot);
            }


            // BEGINNING 2
            if (source.isBuiltin() && CHORUS_PLANT.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BEGINNING2))
                        .when(LootItemRandomChanceCondition.randomChance(0.005f));
                builder.withPool(loot);
            }


            // BIOME FEST
            if (source.isBuiltin() && MOOSHROOM.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BIOME_FEST))
                        .when(LootItemRandomChanceCondition.randomChance(0.015f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.WOODLAND_MANSION.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BIOME_FEST))
                        .when(LootItemRandomChanceCondition.randomChance(0.165f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.FISHING_TREASURE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BIOME_FEST))
                        .when(LootItemRandomChanceCondition.randomChance(0.03f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.TRIAL_CHAMBERS_CORRIDOR_POT.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.BIOME_FEST))
                        .when(LootItemRandomChanceCondition.randomChance(0.025f));
                builder.withPool(loot);
            }


            // CHRIS
            if (source.isBuiltin() && BuiltInLootTables.IGLOO_CHEST.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.CHRIS))
                        .when(LootItemRandomChanceCondition.randomChance(0.28f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && ICE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.CHRIS))
                        .when(LootItemRandomChanceCondition.randomChance(0.008f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && STRAY.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.CHRIS))
                        .when(LootItemRandomChanceCondition.randomChance(0.026f));
                builder.withPool(loot);
            }


            // CLARK
            if (source.isBuiltin() && BuiltInLootTables.SHEEP.yellow().equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.CLARK))
                        .when(LootItemRandomChanceCondition.randomChance(0.03f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.NETHER_BRIDGE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.CLARK))
                        .when(LootItemRandomChanceCondition.randomChance(0.15f));
                builder.withPool(loot);
            }


            // CONCRETE HALLS
            if (source.isBuiltin() && BuiltInLootTables.ABANDONED_MINESHAFT.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.CONCRETE_HALLS))
                        .when(LootItemRandomChanceCondition.randomChance(0.175f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.BASTION_OTHER.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.CONCRETE_HALLS))
                        .when(LootItemRandomChanceCondition.randomChance(0.225f));
                builder.withPool(loot);
            }


            // DANNY
            if (source.isBuiltin() && BuiltInLootTables.ANCIENT_CITY.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.DANNY))
                        .when(LootItemRandomChanceCondition.randomChance(0.012f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.STRONGHOLD_CROSSING.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.DANNY))
                        .when(LootItemRandomChanceCondition.randomChance(0.0052f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.END_CITY_TREASURE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.DANNY))
                        .when(LootItemRandomChanceCondition.randomChance(0.015f));
                builder.withPool(loot);
            }


            // DOG
            if (source.isBuiltin() && BuiltInLootTables.WOODLAND_MANSION.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.DOG))
                        .when(LootItemRandomChanceCondition.randomChance(0.0279f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && SKELETON.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.DOG_FRAG))
                        .when(LootItemRandomChanceCondition.randomChance(0.01f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.SIMPLE_DUNGEON.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.DOG))
                        .when(LootItemRandomChanceCondition.randomChance(0.035f));
                builder.withPool(loot);
            }


            // FIREBUGS
            if (source.isBuiltin() && SLIME.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.FIREBUGS))
                        .when(LootItemRandomChanceCondition.randomChance(0.02f));
                builder.withPool(loot);
            }


            // FLAKE
            if (source.isBuiltin() && BuiltInLootTables.DESERT_WELL_ARCHAEOLOGY.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.FLAKE))
                        .when(LootItemRandomChanceCondition.randomChance(0.1f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.FLAKE))
                        .when(LootItemRandomChanceCondition.randomChance(0.1f));
                builder.withPool(loot);
            }


            // FLOATING DREAM
            if (source.isBuiltin() && BuiltInLootTables.END_CITY_TREASURE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.FLOATING_DREAM))
                        .when(LootItemRandomChanceCondition.randomChance(0.05f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && GHAST.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.FLOATING_DREAM))
                        .when(LootItemRandomChanceCondition.randomChance(0.03f));
                builder.withPool(loot);
            }


            // HAGGSTROM
            if (source.isBuiltin() && BuiltInLootTables.VILLAGE_FISHER.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.HAGGSTROM))
                        .when(LootItemRandomChanceCondition.randomChance(0.125f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && CHERRY_LEAVES.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.HAGGSTROM))
                        .when(LootItemRandomChanceCondition.randomChance(0.00075f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.JUNGLE_TEMPLE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.HAGGSTROM))
                        .when(LootItemRandomChanceCondition.randomChance(0.0562f));
                builder.withPool(loot);
            }


            // INFINITE AMETHYST
            if (source.isBuiltin() && AMETHYST.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.INFINITE_AMETHYST))
                        .when(LootItemRandomChanceCondition.randomChance(0.006f));
                builder.withPool(loot);
            }


            // LABYRINTHINE
            if (source.isBuiltin() && BuiltInLootTables.NETHER_BRIDGE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.LABYRINTHINE))
                        .when(LootItemRandomChanceCondition.randomChance(0.015f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.ABANDONED_MINESHAFT.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.LABYRINTHINE))
                        .when(LootItemRandomChanceCondition.randomChance(0.02f));
                builder.withPool(loot);
            }


            // LEFT TO BLOOM
            if (source.isBuiltin() && CHERRY_LEAVES.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.LEFT_TO_BLOOM))
                        .when(LootItemRandomChanceCondition.randomChance(0.0001f));
                builder.withPool(loot);
            }


            // MICE ON VENUS
            if (source.isBuiltin() && BuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.MICE_ON_VENUS))
                        .when(LootItemRandomChanceCondition.randomChance(0.035f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.DESERT_WELL_ARCHAEOLOGY.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.MICE_ON_VENUS))
                        .when(LootItemRandomChanceCondition.randomChance(0.042f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.BURIED_TREASURE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.MICE_ON_VENUS))
                        .when(LootItemRandomChanceCondition.randomChance(0.025f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.RUINED_PORTAL.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.MICE_ON_VENUS))
                        .when(LootItemRandomChanceCondition.randomChance(0.03f));
                builder.withPool(loot);
            }


            // MINECRAFT
            if (source.isBuiltin() && BuiltInLootTables.SIMPLE_DUNGEON.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.MINECRAFT))
                        .when(LootItemRandomChanceCondition.randomChance(0.01f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && GRASS_BLOCK.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.MINECRAFT_FRAG))
                        .when(LootItemRandomChanceCondition.randomChance(0.00075f));
                builder.withPool(loot);
            }


            // MOOG CITY 2
            if (source.isBuiltin() && BuiltInLootTables.ANCIENT_CITY.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.MOOG_CITY2_FRAG))
                        .when(LootItemRandomChanceCondition.randomChance(0.025f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && STONE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.MOOG_CITY2_FRAG))
                        .when(LootItemRandomChanceCondition.randomChance(0.00065f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && IRON_GOLEM.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.MOOG_CITY2))
                        .when(LootItemRandomChanceCondition.randomChance(0.0005f));
                builder.withPool(loot);
            }


            // ONE MORE DAY
            if (source.isBuiltin() && BuiltInLootTables.SHIPWRECK_TREASURE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.ONE_MORE_DAY))
                        .when(LootItemRandomChanceCondition.randomChance(0.04f));
                builder.withPool(loot);
            }


            // OXYGENE
            if (source.isBuiltin() && BuiltInLootTables.BURIED_TREASURE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.OXYGENE))
                        .when(LootItemRandomChanceCondition.randomChance(0.225f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.SHIPWRECK_TREASURE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.OXYGENE))
                        .when(LootItemRandomChanceCondition.randomChance(0.33f));
                builder.withPool(loot);
            }


            // STAND TALL
            if (source.isBuiltin() && ENDERMAN.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.STAND_TALL))
                        .when(LootItemRandomChanceCondition.randomChance(0.02f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && JUNGLE_LEAVES.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.STAND_TALL))
                        .when(LootItemRandomChanceCondition.randomChance(0.0025f));
                builder.withPool(loot);
            }


            // SUBWOOFER LULLABY
            if (source.isBuiltin() && BuiltInLootTables.SHEEP.pink().equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.SUBWOOFER_LULLABY))
                        .when(LootItemRandomChanceCondition.randomChance(0.03f));
                builder.withPool(loot);
            }


            // TASWELL
            if (source.isBuiltin() && DRAGON.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.TASWELL))
                        .when(LootItemRandomChanceCondition.randomChance(1.0f));
                builder.withPool(loot);
            }


            // THE END
            if (source.isBuiltin() && DRAGON.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.THE_END_FRAG))
                        .when(LootItemRandomChanceCondition.randomChance(0.1f));
                builder.withPool(loot);
            }


            // WET HANDS
            if (source.isBuiltin() && SEA_LANTERN.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.WET_HANDS))
                        .when(LootItemRandomChanceCondition.randomChance(0.027f));
                builder.withPool(loot);
            }

            if (source.isBuiltin() && BuiltInLootTables.FISHING_TREASURE.equals(key)) {
                LootPool.Builder loot = LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.WET_HANDS))
                        .when(LootItemRandomChanceCondition.randomChance(0.046f));
                builder.withPool(loot);
            }
        });
    }
}