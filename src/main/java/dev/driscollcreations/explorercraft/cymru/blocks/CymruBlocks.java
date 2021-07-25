package dev.driscollcreations.explorercraft.cymru.blocks;

import dev.driscollcreations.explorercraft.bamboogrove.blocks.RiceBlock;
import dev.driscollcreations.explorercraft.bamboogrove.blocks.trees.BambooTree;
import dev.driscollcreations.explorercraft.cymru.blocks.trees.AshTree;
import dev.driscollcreations.explorercraft.util.BlockUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;

public class CymruBlocks {

    public static final AbstractBlock.Properties stoneBlockProps = AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).strength(1.5F, 6.0F);

    //=========== SLATE BLOCKS ============//
    public static final RegistryObject<Block> SLATE                        = BlockUtils.register("slate", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_POLISHED               = BlockUtils.register("slate_polished", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_BRICKS                 = BlockUtils.register("slate_bricks", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_MOSSY                  = BlockUtils.register("slate_mossy", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_TILE                   = BlockUtils.register("slate_tile", () -> new RotatedPillarBlock(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_CHISELED               = BlockUtils.register("slate_chiseled", () -> new Block(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_PILLAR                 = BlockUtils.register("slate_pillar", () -> new RotatedPillarBlock(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_WELSH                  = BlockUtils.register("slate_welsh", () -> new Block(stoneBlockProps));

    //=========== SLATE DECOR ============//
    public static final RegistryObject<Block> SLATE_SLAB                       = BlockUtils.register("slate_slab", () -> new SlabBlock(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_BRICK_SLAB                 = BlockUtils.register("slate_brick_slab", () -> new SlabBlock(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_MOSSY_SLAB                 = BlockUtils.register("slate_mossy_slab", () -> new SlabBlock(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_POLISHED_SLAB              = BlockUtils.register("slate_polished_slab", () -> new SlabBlock(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_TILE_SLAB                  = BlockUtils.register("slate_tile_slab", () -> new SlabBlock(stoneBlockProps));

    public static final RegistryObject<Block> SLATE_STAIRS                         = BlockUtils.register("slate_stairs", () -> new StairsBlock(SLATE.get()::defaultBlockState, stoneBlockProps));
    public static final RegistryObject<Block> SLATE_BRICK_STAIRS                   = BlockUtils.register("slate_brick_stairs", () -> new StairsBlock(SLATE_BRICKS.get()::defaultBlockState, stoneBlockProps));
    public static final RegistryObject<Block> SLATE_MOSSY_STAIRS                   = BlockUtils.register("slate_mossy_stairs", () -> new StairsBlock(SLATE_MOSSY.get()::defaultBlockState, stoneBlockProps));
    public static final RegistryObject<Block> SLATE_POLISHED_STAIRS                = BlockUtils.register("slate_polished_stairs", () -> new StairsBlock(SLATE_POLISHED.get()::defaultBlockState, stoneBlockProps));
    public static final RegistryObject<Block> SLATE_TILE_STAIRS                    = BlockUtils.register("slate_tile_stairs", () -> new StairsBlock(SLATE_TILE.get()::defaultBlockState, stoneBlockProps));

    public static final RegistryObject<Block> SLATE_WALL                           = BlockUtils.register("slate_wall", () -> new WallBlock(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_BRICK_WALL                     = BlockUtils.register("slate_brick_wall", () -> new WallBlock(stoneBlockProps));
    public static final RegistryObject<Block> SLATE_MOSSY_WALL                     = BlockUtils.register("slate_mossy_wall", () -> new WallBlock(stoneBlockProps));

    public static final RegistryObject<Block> DRAGON_HEART = BlockUtils.register("dragon_heart", () -> new DragonHeartBlock(AbstractBlock.Properties.of(Material.EGG, MaterialColor.COLOR_RED).strength(3.0F, 9.0F).lightLevel((light) -> 1).noOcclusion()));


    public static final RegistryObject<Block> DAFFODIL = BlockUtils.register("daffodil", () -> new FlowerBlock(Effects.DIG_SPEED, 6, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> LEEK_WILD = BlockUtils.register("leek_wild", () -> new FlowerBlock(Effects.NIGHT_VISION, 6, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> LEEKS = BlockUtils.registerNoItem("leeks", () -> new CropsBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().sound(SoundType.CROP)));

    public static final RegistryObject<Block> POTTED_WILD_LEEK = BlockUtils.registerNoItem("potted_wild_leek",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, LEEK_WILD::get, AbstractBlock.Properties.of(Material.DECORATION).instabreak()));

    public static final RegistryObject<Block> POTTED_DAFFODIL = BlockUtils.registerNoItem("potted_daffodil",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, LEEK_WILD::get, AbstractBlock.Properties.of(Material.DECORATION).instabreak()));

    public static final RegistryObject<Block> ASH_SAPLING = BlockUtils.register("ash_sapling",
            () -> new SaplingBlock(new AshTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

    public static void register() {}

}
