package dev.driscollcreations.explorercraft.vanillatweaks.setup;

import dev.driscollcreations.explorercraft.util.BlockUtils;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.BlackHoleBlock;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.NoctilucaBlock;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.SleepingBagBlock;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Supplier;

public class VanillaTweaksBlocks {

    public static final BlockBehaviour.Properties stoneBlockProps = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F, 6.0F);

    public static final RegistryObject<Block> SLIMEY_STONE              = BlockUtils.register("slimey_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final RegistryObject<Block> DISSOLVED_STONE           = BlockUtils.register("dissolved_stone", () -> new SlimeBlock(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion()));

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

    public static final RegistryObject<Block> NOCTILUCAS = BlockUtils.registerNoItem("noctilucas", () -> new NoctilucaBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().sound(SoundType.WET_GRASS)));

    //=========== MARBLE BLOCKS ============//
    public static final RegistryObject<Block> MARBLE            = BlockUtils.register("marble", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_POLISHED   = BlockUtils.register("marble_polished", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_BRICKS     = BlockUtils.register("marble_bricks", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_MOSSY      = BlockUtils.register("marble_mossy", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_CRACKED    = BlockUtils.register("marble_cracked", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_CHISELED   = BlockUtils.register("marble_chiseled", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_PILLAR     = BlockUtils.register("marble_pillar", () -> new RotatedPillarBlock(stoneBlockProps));

    //=========== MARBLE DECOR ============//
    public static final RegistryObject<Block> MARBLE_SLAB               = BlockUtils.register("marble_slab", () -> new SlabBlock(stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_BRICK_SLAB         = BlockUtils.register("marble_brick_slab", () -> new SlabBlock(stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_MOSSY_SLAB         = BlockUtils.register("marble_mossy_slab", () -> new SlabBlock(stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_POLISHED_SLAB      = BlockUtils.register("marble_polished_slab", () -> new SlabBlock(stoneBlockProps));

    public static final RegistryObject<Block> MARBLE_STAIRS             = BlockUtils.register("marble_stairs", () -> new StairBlock(MARBLE.get()::defaultBlockState, stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_BRICK_STAIRS       = BlockUtils.register("marble_brick_stairs", () -> new StairBlock(MARBLE_BRICKS.get()::defaultBlockState, stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_MOSSY_STAIRS       = BlockUtils.register("marble_mossy_stairs", () -> new StairBlock(MARBLE_MOSSY.get()::defaultBlockState, stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_POLISHED_STAIRS    = BlockUtils.register("marble_polished_stairs", () -> new StairBlock(MARBLE_POLISHED.get()::defaultBlockState, stoneBlockProps));

    public static final RegistryObject<Block> MARBLE_WALL               = BlockUtils.register("marble_wall", () -> new WallBlock(stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_BRICK_WALL         = BlockUtils.register("marble_brick_wall", () -> new WallBlock(stoneBlockProps));
    public static final RegistryObject<Block> MARBLE_MOSSY_WALL         = BlockUtils.register("marble_mossy_wall", () -> new WallBlock(stoneBlockProps));

    //=========== BASALT BLOCKS ============//
    public static final RegistryObject<Block> BASALT                        = BlockUtils.register("basalt", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_POLISHED               = BlockUtils.register("basalt_polished", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_BRICKS                 = BlockUtils.register("basalt_bricks", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_MOSSY                  = BlockUtils.register("basalt_mossy", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_CRACKED                = BlockUtils.register("basalt_cracked", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_CHISELED               = BlockUtils.register("basalt_chiseled", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_PILLAR                 = BlockUtils.register("basalt_pillar", () -> new RotatedPillarBlock(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_COBBLESTONE            = BlockUtils.register("basalt_cobblestone", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_COBBLESTONE_MOSSY      = BlockUtils.register("basalt_cobblestone_mossy", () -> new Block(stoneBlockProps));

    //=========== BASALT DECOR ============//
    public static final RegistryObject<Block> BASALT_SLAB                       = BlockUtils.register("basalt_slab", () -> new SlabBlock(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_BRICK_SLAB                 = BlockUtils.register("basalt_brick_slab", () -> new SlabBlock(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_MOSSY_SLAB                 = BlockUtils.register("basalt_mossy_slab", () -> new SlabBlock(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_POLISHED_SLAB              = BlockUtils.register("basalt_polished_slab", () -> new SlabBlock(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_COBBLESTONE_SLAB           = BlockUtils.register("basalt_cobblestone_slab", () -> new SlabBlock(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_COBBLESTONE_MOSSY_SLAB     = BlockUtils.register("basalt_cobblestone_mossy_slab", () -> new SlabBlock(stoneBlockProps));

    public static final RegistryObject<Block> BASALT_STAIRS                         = BlockUtils.register("basalt_stairs", () -> new StairBlock(BASALT.get()::defaultBlockState, stoneBlockProps));
    public static final RegistryObject<Block> BASALT_BRICK_STAIRS                   = BlockUtils.register("basalt_brick_stairs", () -> new StairBlock(BASALT_BRICKS.get()::defaultBlockState, stoneBlockProps));
    public static final RegistryObject<Block> BASALT_MOSSY_STAIRS                   = BlockUtils.register("basalt_mossy_stairs", () -> new StairBlock(BASALT_MOSSY.get()::defaultBlockState, stoneBlockProps));
    public static final RegistryObject<Block> BASALT_POLISHED_STAIRS                = BlockUtils.register("basalt_polished_stairs", () -> new StairBlock(BASALT_POLISHED.get()::defaultBlockState, stoneBlockProps));
    public static final RegistryObject<Block> BASALT_COBBLESTONE_STAIRS             = BlockUtils.register("basalt_cobblestone_stairs", () -> new StairBlock(BASALT_COBBLESTONE.get()::defaultBlockState, stoneBlockProps));
    public static final RegistryObject<Block> BASALT_COBBLESTONE_MOSSY_STAIRS       = BlockUtils.register("basalt_cobblestone_mossy_stairs", () -> new StairBlock(BASALT_COBBLESTONE_MOSSY.get()::defaultBlockState, stoneBlockProps));

    public static final RegistryObject<Block> BASALT_WALL                           = BlockUtils.register("basalt_wall", () -> new WallBlock(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_BRICK_WALL                     = BlockUtils.register("basalt_brick_wall", () -> new WallBlock(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_MOSSY_WALL                     = BlockUtils.register("basalt_mossy_wall", () -> new WallBlock(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_COBBLESTONE_WALL               = BlockUtils.register("basalt_cobblestone_wall", () -> new WallBlock(stoneBlockProps));
    public static final RegistryObject<Block> BASALT_COBBLESTONE_MOSSY_WALL         = BlockUtils.register("basalt_cobblestone_mossy_wall", () -> new WallBlock(stoneBlockProps));

    //========= Amethyst and Ruby
    public static final RegistryObject<Block> AMETHYST_ORE = BlockUtils.register("amethyst_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3, 10).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> AMETHYST_BLOCK = BlockUtils.register("amethyst_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3, 10).sound(SoundType.METAL)));

    public static final RegistryObject<Block> RUBY_ORE = BlockUtils.register("ruby_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3, 10).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> RUBY_BLOCK = BlockUtils.register("ruby_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3, 10).sound(SoundType.METAL)));


    //========== Black hole
    public static final RegistryObject<Block> BLACK_HOLE = BlockUtils.register("black_hole", () -> new BlackHoleBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).strength(-1.0F, 3600000.0F).noDrops().noCollission()));

    public static void register() {}

    private static Supplier<Block> createSleepingBag(DyeColor color) {
        return () -> new SleepingBagBlock(color, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
            return state.getValue(BedBlock.PART) == BedPart.FOOT ? color.getMaterialColor() : MaterialColor.WOOL;
        }).sound(SoundType.WOOL).strength(0.2F, 0.3F).noCollission());
    }
}
