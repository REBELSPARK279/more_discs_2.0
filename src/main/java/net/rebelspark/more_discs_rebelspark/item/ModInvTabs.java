package net.rebelspark.more_discs_rebelspark.item;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.rebelspark.more_discs_rebelspark.MoreDiscs;

import java.util.function.Supplier;

public class ModInvTabs {


    private static final ResourceKey<CreativeModeTab> ALL_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MoreDiscs.id("mod_discs"));
    private static final ResourceKey<CreativeModeTab> COMMON_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MoreDiscs.id("common_discs"));
    private static final ResourceKey<CreativeModeTab> UNCOMMON_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MoreDiscs.id("uncommon_discs"));
    private static final ResourceKey<CreativeModeTab> RARE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MoreDiscs.id("rare_discs"));
    private static final ResourceKey<CreativeModeTab> EPIC_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MoreDiscs.id("epic_discs"));
    private static final ResourceKey<CreativeModeTab> LEGENDARY_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MoreDiscs.id("legendary_discs"));
    private static final ResourceKey<CreativeModeTab> MYTHIC_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MoreDiscs.id("mythic_discs"));
    private static final ResourceKey<CreativeModeTab> VILLAGER_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MoreDiscs.id("villager_discs"));
    private static final ResourceKey<CreativeModeTab> WANDY_T_GEN_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MoreDiscs.id("wandy-t_gen_discs"));
    private static final ResourceKey<CreativeModeTab> WANDY_T_RARE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MoreDiscs.id("wandy-t_rare_discs"));

    public static final CreativeModeTab MOD_DISCS = FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MICE_ON_VENUS))
            .title(Component.translatable("creativetab.more_discs_rebelspark.mod_discs")).displayItems(((parameters, output) -> {
                output.accept(ModItems.EMPTY_DISC_CORE);
                output.accept(ModItems.A_FAMILIAR_ROOM);
                output.accept(ModItems.AERIE);
                output.accept(ModItems.ALPHA);
                output.accept(ModItems.ARIA_MATH);
                output.accept(ModItems.AXOLOTL);
                output.accept(ModItems.BACK_ON_DASH);
                output.accept(ModItems.BALLAD_OF_THE_CATS);
                output.accept(ModItems.BEGINNING);
                output.accept(ModItems.BEGINNING2);
                output.accept(ModItems.BIOME_FEST);
                output.accept(ModItems.BLIND_SPOTS);
                output.accept(ModItems.CASTLEMANIA);
                output.accept(ModItems.CASTLEMANIA2);
                output.accept(ModItems.CHRIS);
                output.accept(ModItems.CLARK);
                output.accept(ModItems.CONCRETE_HALLS);
                output.accept(ModItems.DANNY);
                output.accept(ModItems.DEAD_VOXEL);
                output.accept(ModItems.DEATH);
                output.accept(ModItems.DOG);
                output.accept(ModItems.DOOR);
                output.accept(ModItems.DRAGONFISH);
                output.accept(ModItems.DREITON);
                output.accept(ModItems.DROOPY_LIKES_RICOCHET);
                output.accept(ModItems.DROOPY_LIKES_YOUR_FACE);
                output.accept(ModItems.DRY_HANDS);
                output.accept(ModItems.ELEVEN);
                output.accept(ModItems.EQUINOXE);
                output.accept(ModItems.EXCUSE);
                output.accept(ModItems.EXECUTIONER);
                output.accept(ModItems.FIREBUGS);
                output.accept(ModItems.FLAKE);
                output.accept(ModItems.FLOATING_DREAM);
                output.accept(ModItems.FLOATING_TREES);
                output.accept(ModItems.HAGGSTROM);
                output.accept(ModItems.HARDCOREMINECRAFT);
                output.accept(ModItems.HAUNT_MUSKIE);
                output.accept(ModItems.HEADBUG);
                output.accept(ModItems.INFINITE_AMETHYST);
                output.accept(ModItems.INTRO);
                output.accept(ModItems.INVINCIBLE);
                output.accept(ModItems.KEY);
                output.accept(ModItems.KI);
                output.accept(ModItems.KYOTO);
                output.accept(ModItems.LABYRINTHINE);
                output.accept(ModItems.LEFT_TO_BLOOM);
                output.accept(ModItems.LIVING_MICE);
                output.accept(ModItems.MICE_ON_VENUS);
                output.accept(ModItems.MINECRAFT);
                output.accept(ModItems.MOOG_CITY);
                output.accept(ModItems.MOOG_CITY2);
                output.accept(ModItems.MUTATION);
                output.accept(ModItems.ONE_MORE_DAY);
                output.accept(ModItems.OXYGENE);
                output.accept(ModItems.SHUNIJI);
                output.accept(ModItems.STAND_TALL);
                output.accept(ModItems.SUBWOOFER_LULLABY);
                output.accept(ModItems.SWEDEN);
                output.accept(ModItems.TASWELL);
                output.accept(ModItems.THE_END);
                output.accept(ModItems.WARMTH);
                output.accept(ModItems.WENDING);
                output.accept(ModItems.WET_HANDS);
            })).build();

    public static final CreativeModeTab COMMON_DISCS = FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.FLOATING_TREES))
            .title(Component.translatable("creativetab.more_discs_rebelspark.common")).displayItems(((parameters, output) -> {
                output.accept(ModItems.A_FAMILIAR_ROOM);
                output.accept(ModItems.AXOLOTL);
                output.accept(ModItems.DEAD_VOXEL);
                output.accept(ModItems.DRAGONFISH);
                output.accept(ModItems.DROOPY_LIKES_RICOCHET);
                output.accept(ModItems.DROOPY_LIKES_YOUR_FACE);
                output.accept(ModItems.DRY_HANDS);
                output.accept(ModItems.EXCUSE);
                output.accept(ModItems.FLOATING_TREES);
                output.accept(ModItems.KEY);
                output.accept(ModItems.KI);
                output.accept(ModItems.KYOTO);
            })).build();

    public static final CreativeModeTab UNCOMMON_DISCS = FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HAUNT_MUSKIE))
            .title(Component.translatable("creativetab.more_discs_rebelspark.uncommon")).displayItems(((parameters, output) -> {
                output.accept(ModItems.CONCRETE_HALLS);
                output.accept(ModItems.DOOR);
                output.accept(ModItems.EQUINOXE);
                output.accept(ModItems.FLAKE);
                output.accept(ModItems.HAUNT_MUSKIE);
                output.accept(ModItems.LIVING_MICE);
                output.accept(ModItems.MOOG_CITY);
                output.accept(ModItems.MUTATION);
                output.accept(ModItems.SHUNIJI);
                output.accept(ModItems.WENDING);

            })).build();

    public static final CreativeModeTab RARE_DISCS = FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CHRIS))
            .title(Component.translatable("creativetab.more_discs_rebelspark.rare")).displayItems(((parameters, output) -> {
                output.accept(ModItems.ARIA_MATH);
                output.accept(ModItems.CHRIS);
                output.accept(ModItems.CLARK);
                output.accept(ModItems.OXYGENE);
                output.accept(ModItems.STAND_TALL);
                output.accept(ModItems.SUBWOOFER_LULLABY);
                output.accept(ModItems.SWEDEN);
                output.accept(ModItems.WARMTH);
                output.accept(ModItems.WET_HANDS);

            })).build();

    public static final CreativeModeTab EPIC_DISCS = FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HAGGSTROM))
            .title(Component.translatable("creativetab.more_discs_rebelspark.epic")).displayItems(((parameters, output) -> {
                output.accept(ModItems.AERIE);
                output.accept(ModItems.BALLAD_OF_THE_CATS);
                output.accept(ModItems.BEGINNING);
                output.accept(ModItems.BEGINNING2);
                output.accept(ModItems.BIOME_FEST);
                output.accept(ModItems.BLIND_SPOTS);
                output.accept(ModItems.DREITON);
                output.accept(ModItems.FIREBUGS);
                output.accept(ModItems.FLOATING_DREAM);
                output.accept(ModItems.HAGGSTROM);
                output.accept(ModItems.ONE_MORE_DAY);
            })).build();

    public static final CreativeModeTab LEGENDARY_DISCS = FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.INFINITE_AMETHYST))
            .title(Component.translatable("creativetab.more_discs_rebelspark.legendary")).displayItems(((parameters, output) -> {
                output.accept(ModItems.ALPHA);
                output.accept(ModItems.DANNY);
                output.accept(ModItems.DOG);
                output.accept(ModItems.INFINITE_AMETHYST);
                output.accept(ModItems.LABYRINTHINE);
                output.accept(ModItems.LEFT_TO_BLOOM);
                output.accept(ModItems.MICE_ON_VENUS);
                output.accept(ModItems.MINECRAFT);
                output.accept(ModItems.MOOG_CITY2);
                output.accept(ModItems.TASWELL);
            })).build();

    public static final CreativeModeTab MYTHIC_DISCS = FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.THE_END))
            .title(Component.translatable("creativetab.more_discs_rebelspark.mythic")).displayItems(((parameters, output) -> {
                output.accept(ModItems.ELEVEN);
                output.accept(ModItems.INTRO);
                output.accept(ModItems.THE_END);
            })).build();

    public static final CreativeModeTab VILLAGER_DISCS = FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DEATH))
            .title(Component.translatable("creativetab.more_discs_rebelspark.villager")).displayItems(((parameters, output) -> {
                output.accept(ModItems.BACK_ON_DASH);
                output.accept(ModItems.DEATH);
                output.accept(ModItems.HEADBUG);
                output.accept(ModItems.INVINCIBLE);
            })).build();

    public static final CreativeModeTab GEN_WANDER_DISCS = FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CASTLEMANIA))
            .title(Component.translatable("creativetab.more_discs_rebelspark.wandy-t_gen")).displayItems(((parameters, output) -> {
                output.accept(ModItems.CASTLEMANIA);
                output.accept(ModItems.EXECUTIONER);
            })).build();

    public static final CreativeModeTab RARE_WANDER_DISCS = FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CASTLEMANIA2))
            .title(Component.translatable("creativetab.more_discs_rebelspark.wandy-t_rare")).displayItems(((parameters, output) -> {
                output.accept(ModItems.CASTLEMANIA2);
                output.accept(ModItems.HARDCOREMINECRAFT);
            })).build();


    private static final ResourceKey<CreativeModeTab> DEV = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MoreDiscs.id("admin"));
    public static final CreativeModeTab DEV_TAB = FabricCreativeModeTab.builder().icon(() -> new ItemStack(Items.COMMAND_BLOCK))
            .title(Component.translatable("creativetab.more_discs_rebelspark.dev")).displayItems((parameters, output) -> {
                output.accept(ModItems.COLORS_GUIDE);
                output.accept(ModItems.COMMON_SCROLLER);
                output.accept(ModItems.UNCOMMON_SCROLLER);
                output.accept(ModItems.RARE_SCROLLER);
                output.accept(ModItems.EPIC_SCROLLER);
                output.accept(ModItems.LEGENDARY_SCROLLER);
                output.accept(ModItems.MYTHIC_SCROLLER);
                output.accept(ModItems.TRADER_SCROLLER);
            }).build();

    public static void init() {
        MoreDiscs.LOGGER.info("Starting CreativeTabs");
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ALL_TAB_KEY, MOD_DISCS);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, COMMON_TAB_KEY, COMMON_DISCS);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, UNCOMMON_TAB_KEY, UNCOMMON_DISCS);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, RARE_TAB_KEY, RARE_DISCS);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, EPIC_TAB_KEY, EPIC_DISCS);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, LEGENDARY_TAB_KEY, LEGENDARY_DISCS);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MYTHIC_TAB_KEY, MYTHIC_DISCS);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, VILLAGER_TAB_KEY, VILLAGER_DISCS);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, WANDY_T_GEN_TAB_KEY, GEN_WANDER_DISCS);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, WANDY_T_RARE_TAB_KEY, RARE_WANDER_DISCS);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, DEV, DEV_TAB);

    }


}
