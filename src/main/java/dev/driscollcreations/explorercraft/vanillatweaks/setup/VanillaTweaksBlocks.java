package dev.driscollcreations.explorercraft.vanillatweaks.setup;

import dev.driscollcreations.explorercraft.util.BlockUtils;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.SleepingBagBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.DyeColor;
import net.minecraft.state.properties.BedPart;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class VanillaTweaksBlocks {

    public static final RegistryObject<Block> SLIMEY_STONE = BlockUtils.register("slimey_stone", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.5F, 6.0F).harvestLevel(2).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DISSOLVED_STONE = BlockUtils.register("dissolved_stone", () -> new SlimeBlock(AbstractBlock.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> LEATHER_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_leather", createSleepingBag(DyeColor.BROWN));
    public static final RegistryObject<Block> WHITE_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_white", createSleepingBag(DyeColor.WHITE));
    public static final RegistryObject<Block> ORANGE_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_orange", createSleepingBag(DyeColor.ORANGE));
    public static final RegistryObject<Block> MAGENTA_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_magenta", createSleepingBag(DyeColor.MAGENTA));
    public static final RegistryObject<Block> LIGHT_BLUE_SLEEPING_BAG 	= BlockUtils.register("sleeping_bag_light_blue", createSleepingBag(DyeColor.LIGHT_BLUE));
    public static final RegistryObject<Block> YELLOW_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_yellow", createSleepingBag(DyeColor.YELLOW));
    public static final RegistryObject<Block> LIME_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_lime", createSleepingBag(DyeColor.LIME));
    public static final RegistryObject<Block> PINK_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_pink", createSleepingBag(DyeColor.PINK));
    public static final RegistryObject<Block> GRAY_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_gray", createSleepingBag(DyeColor.GRAY));
    public static final RegistryObject<Block> LIGHT_GRAY_SLEEPING_BAG 	= BlockUtils.register("sleeping_bag_light_gray", createSleepingBag(DyeColor.LIGHT_GRAY));
    public static final RegistryObject<Block> CYAN_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_cyan", createSleepingBag(DyeColor.CYAN));
    public static final RegistryObject<Block> PURPLE_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_purple", createSleepingBag(DyeColor.PURPLE));
    public static final RegistryObject<Block> BLUE_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_blue", createSleepingBag(DyeColor.BLUE));
    public static final RegistryObject<Block> BROWN_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_brown", createSleepingBag(DyeColor.BROWN));
    public static final RegistryObject<Block> GREEN_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_green", createSleepingBag(DyeColor.GREEN));
    public static final RegistryObject<Block> RED_SLEEPING_BAG 			= BlockUtils.register("sleeping_bag_red", createSleepingBag(DyeColor.RED));
    public static final RegistryObject<Block> BLACK_SLEEPING_BAG 		= BlockUtils.register("sleeping_bag_black", createSleepingBag(DyeColor.BLACK));

    public static void register() {}

    private static Supplier<Block> createSleepingBag(DyeColor color) {
        return () -> new SleepingBagBlock(color, AbstractBlock.Properties.of(Material.WOOL, (state) -> {
            return state.getValue(BedBlock.PART) == BedPart.FOOT ? color.getMaterialColor() : MaterialColor.WOOL;
        }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission());
    }
}
