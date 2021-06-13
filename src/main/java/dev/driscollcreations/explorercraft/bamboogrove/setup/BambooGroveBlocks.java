package dev.driscollcreations.explorercraft.bamboogrove.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.blocks.*;
import dev.driscollcreations.explorercraft.bamboogrove.blocks.trees.BambooTree;
import dev.driscollcreations.explorercraft.bamboogrove.blocks.trees.CherryTree;
import dev.driscollcreations.explorercraft.bamboogrove.blocks.trees.MapleTree;
import dev.driscollcreations.explorercraft.setup.ExplorerSigns;
import dev.driscollcreations.explorercraft.util.BlockUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class BambooGroveBlocks {

    public static final WoodType BAMBOO_WOODTYPE = WoodType.create(new ResourceLocation(Explorercraft.MOD_ID, "bamboo").toString());
    public static final WoodType CHERRY_WOODTYPE = WoodType.create(new ResourceLocation(Explorercraft.MOD_ID, "cherry").toString());
    public static final WoodType CHERRY_BLOSSOM_WOODTYPE = WoodType.create(new ResourceLocation(Explorercraft.MOD_ID, "cherry_blossom").toString());
    public static final WoodType MAPLE_WOODTYPE = WoodType.create(new ResourceLocation(Explorercraft.MOD_ID, "maple").toString());

    //== Bamboo related Blocks
    public static final RegistryObject<Block> BAMBOO_LEAVES = BlockUtils.register("bamboo_leaves", () -> BlockUtils.createLeavesBlock());
    public static final RegistryObject<BambooLogBlock> BAMBOO_LOG = BlockUtils.register("bamboo_log",
            () -> new BambooLogBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2, 10).sound(SoundType.BAMBOO)));
    public static final RegistryObject<Block> BAMBOO_BUTTON = BlockUtils.register("bamboo_button", () -> BlockUtils.createWoodButton());
    public static final RegistryObject<Block> BAMBOO_DOOR = BlockUtils.register("bamboo_door", () -> BlockUtils.createWoodDoor());
    public static final RegistryObject<Block> BAMBOO_FENCE = BlockUtils.register("bamboo_fence", () -> BlockUtils.createWoodFence());
    public static final RegistryObject<Block> BAMBOO_FENCE_GATE = BlockUtils.register("bamboo_fence_gate", () -> BlockUtils.createWoodFenceGate());
    public static final RegistryObject<Block> BAMBOO_PLANKS = BlockUtils.register("bamboo_planks", () -> BlockUtils.createWoodBlock());
    public static final RegistryObject<Block> BAMBOO_PLANKS_VERTICAL = BlockUtils.register("bamboo_planks_vertical", () -> BlockUtils.createWoodBlock());
    public static final RegistryObject<Block> BAMBOO_PRESSURE_PLATE = BlockUtils.register("bamboo_pressure_plate", () -> BlockUtils.createWoodPressurePlate());
    public static final RegistryObject<Block> BAMBOO_SAPLING = BlockUtils.register("bamboo_sapling",
            () -> new SaplingBlock(new BambooTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> POTTED_BAMBOO_SAPLING = BlockUtils.registerNoItem("potted_bamboo_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BAMBOO_SAPLING::get, AbstractBlock.Properties.of(Material.DECORATION).instabreak()));
    public static final RegistryObject<Block> BAMBOO_SLAB = BlockUtils.register("bamboo_slab", () -> BlockUtils.createWoodSlab());
    public static final RegistryObject<Block> BAMBOO_STAIRS = BlockUtils.register("bamboo_stairs",
            () -> new StairsBlock(() -> BAMBOO_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(BAMBOO_PLANKS.get())));
    public static final RegistryObject<Block> BAMBOO_TRAPDOOR = BlockUtils.register("bamboo_trapdoor", () -> BlockUtils.createWoodTrapdoor());
    public static final RegistryObject<Block> BAMBOO_PANEL = BlockUtils.register("bamboo_panel", () -> BlockUtils.createPanel());
    public static final RegistryObject<ExplorerSigns.CustomStandingSignBlock> BAMBOO_STANDING_SIGN = BlockUtils.registerNoItem("bamboo_sign", () -> new ExplorerSigns.CustomStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BAMBOO_WOODTYPE));
    public static final RegistryObject<ExplorerSigns.CustomWallSignBlock> BAMBOO_WALL_SIGN = BlockUtils.registerNoItem("bamboo_wall_sign", () -> new ExplorerSigns.CustomWallSignBlock(AbstractBlock.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), BAMBOO_WOODTYPE));

    //== Cherry Tree
    public static final RegistryObject<Block> CHERRY_LEAVES = BlockUtils.register("cherry_leaves", () -> BlockUtils.createLeavesBlock());
    public static final RegistryObject<RotatedPillarBlock> CHERRY_LOG = BlockUtils.register("cherry_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_BUTTON = BlockUtils.register("cherry_button", () -> BlockUtils.createWoodButton());
    public static final RegistryObject<Block> CHERRY_DOOR = BlockUtils.register("cherry_door", () -> BlockUtils.createWoodDoor());
    public static final RegistryObject<Block> CHERRY_FENCE = BlockUtils.register("cherry_fence", () -> BlockUtils.createWoodFence());
    public static final RegistryObject<Block> CHERRY_FENCE_GATE = BlockUtils.register("cherry_fence_gate", () -> BlockUtils.createWoodFenceGate());
    public static final RegistryObject<Block> CHERRY_PLANKS = BlockUtils.register("cherry_planks", () -> BlockUtils.createWoodBlock());
    public static final RegistryObject<Block> CHERRY_PRESSURE_PLATE = BlockUtils.register("cherry_pressure_plate", () -> BlockUtils.createWoodPressurePlate());
    public static final RegistryObject<Block> CHERRY_SAPLING = BlockUtils.register("cherry_sapling",
            () -> new SaplingBlock(new CherryTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> POTTED_CHERRY_SAPLING = BlockUtils.registerNoItem("potted_cherry_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CHERRY_SAPLING::get, AbstractBlock.Properties.of(Material.DECORATION).instabreak()));
    public static final RegistryObject<Block> CHERRY_SLAB = BlockUtils.register("cherry_slab", () -> BlockUtils.createWoodSlab());
    public static final RegistryObject<Block> CHERRY_STAIRS = BlockUtils.register("cherry_stairs",
            () -> new StairsBlock(() -> CHERRY_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(CHERRY_PLANKS.get())));
    public static final RegistryObject<Block> CHERRY_TRAPDOOR = BlockUtils.register("cherry_trapdoor", () -> BlockUtils.createWoodTrapdoor());
    public static final RegistryObject<RotatedPillarBlock> CHERRY_WOOD = BlockUtils.register("cherry_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> CHERRY_STRIPPED_LOG = BlockUtils.register("cherry_stripped_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> CHERRY_STRIPPED_WOOD = BlockUtils.register("cherry_stripped_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_PANEL = BlockUtils.register("cherry_panel", () -> BlockUtils.createPanel());
    public static final RegistryObject<ExplorerSigns.CustomStandingSignBlock> CHERRY_STANDING_SIGN = BlockUtils.registerNoItem("cherry_sign", () -> new ExplorerSigns.CustomStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), CHERRY_WOODTYPE));
    public static final RegistryObject<ExplorerSigns.CustomWallSignBlock> CHERRY_WALL_SIGN = BlockUtils.registerNoItem("cherry_wall_sign", () -> new ExplorerSigns.CustomWallSignBlock(AbstractBlock.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), CHERRY_WOODTYPE));

    //== Cherry Blossom Blocks
    public static final RegistryObject<Block> CHERRY_BLOSSOM_BUTTON = BlockUtils.register("cherry_blossom_button", () -> BlockUtils.createWoodButton());
    public static final RegistryObject<Block> CHERRY_BLOSSOM_DOOR = BlockUtils.register("cherry_blossom_door", () -> BlockUtils.createWoodDoor());
    public static final RegistryObject<Block> CHERRY_BLOSSOM_FENCE = BlockUtils.register("cherry_blossom_fence", () -> BlockUtils.createWoodFence());
    public static final RegistryObject<Block> CHERRY_BLOSSOM_FENCE_GATE = BlockUtils.register("cherry_blossom_fence_gate", () -> BlockUtils.createWoodFenceGate());
    public static final RegistryObject<Block> CHERRY_BLOSSOM_PLANKS = BlockUtils.register("cherry_blossom_planks", () -> BlockUtils.createWoodBlock());
    public static final RegistryObject<Block> CHERRY_BLOSSOM_PRESSURE_PLATE = BlockUtils.register("cherry_blossom_pressure_plate", () -> BlockUtils.createWoodPressurePlate());
    public static final RegistryObject<Block> CHERRY_BLOSSOM_SLAB = BlockUtils.register("cherry_blossom_slab", () -> BlockUtils.createWoodSlab());
    public static final RegistryObject<Block> CHERRY_BLOSSOM_STAIRS = BlockUtils.register("cherry_blossom_stairs",
            () -> new StairsBlock(() -> CHERRY_BLOSSOM_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(CHERRY_BLOSSOM_PLANKS.get())));
    public static final RegistryObject<Block> CHERRY_BLOSSOM_TRAPDOOR = BlockUtils.register("cherry_blossom_trapdoor", () -> BlockUtils.createWoodTrapdoor());
    public static final RegistryObject<ExplorerSigns.CustomStandingSignBlock> CHERRY_BLOSSOM_STANDING_SIGN = BlockUtils.registerNoItem("cherry_blossom_sign", () -> new ExplorerSigns.CustomStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), CHERRY_BLOSSOM_WOODTYPE));
    public static final RegistryObject<ExplorerSigns.CustomWallSignBlock> CHERRY_BLOSSOM_WALL_SIGN = BlockUtils.registerNoItem("cherry_blossom_wall_sign", () -> new ExplorerSigns.CustomWallSignBlock(AbstractBlock.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), CHERRY_BLOSSOM_WOODTYPE));

    //== Maple Tree
    public static final RegistryObject<Block> MAPLE_LEAVES = BlockUtils.register("maple_leaves", () -> BlockUtils.createLeavesBlock());
    public static final RegistryObject<RotatedPillarBlock> MAPLE_LOG = BlockUtils.register("maple_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MAPLE_BUTTON = BlockUtils.register("maple_button", () -> BlockUtils.createWoodButton());
    public static final RegistryObject<Block> MAPLE_DOOR = BlockUtils.register("maple_door", () -> BlockUtils.createWoodDoor());
    public static final RegistryObject<Block> MAPLE_FENCE = BlockUtils.register("maple_fence", () -> BlockUtils.createWoodFence());
    public static final RegistryObject<Block> MAPLE_FENCE_GATE = BlockUtils.register("maple_fence_gate", () -> BlockUtils.createWoodFenceGate());
    public static final RegistryObject<Block> MAPLE_PLANKS = BlockUtils.register("maple_planks", () -> BlockUtils.createWoodBlock());
    public static final RegistryObject<Block> MAPLE_PRESSURE_PLATE = BlockUtils.register("maple_pressure_plate", () -> BlockUtils.createWoodPressurePlate());
    public static final RegistryObject<Block> MAPLE_SAPLING = BlockUtils.register("maple_sapling",
            () -> new SaplingBlock(new MapleTree(), AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> POTTED_MAPLE_SAPLING = BlockUtils.registerNoItem("potted_maple_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, MAPLE_SAPLING::get, AbstractBlock.Properties.of(Material.DECORATION).instabreak()));
    public static final RegistryObject<Block> MAPLE_SLAB = BlockUtils.register("maple_slab", () -> BlockUtils.createWoodSlab());
    public static final RegistryObject<Block> MAPLE_STAIRS = BlockUtils.register("maple_stairs",
            () -> new StairsBlock(() -> MAPLE_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(MAPLE_PLANKS.get())));
    public static final RegistryObject<Block> MAPLE_TRAPDOOR = BlockUtils.register("maple_trapdoor", () -> BlockUtils.createWoodTrapdoor());
    public static final RegistryObject<RotatedPillarBlock> MAPLE_WOOD = BlockUtils.register("maple_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> MAPLE_STRIPPED_LOG = BlockUtils.register("maple_stripped_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> MAPLE_STRIPPED_WOOD = BlockUtils.register("maple_stripped_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<ExplorerSigns.CustomStandingSignBlock> MAPLE_STANDING_SIGN = BlockUtils.registerNoItem("maple_sign", () -> new ExplorerSigns.CustomStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), MAPLE_WOODTYPE));
    public static final RegistryObject<ExplorerSigns.CustomWallSignBlock> MAPLE_WALL_SIGN = BlockUtils.registerNoItem("maple_wall_sign", () -> new ExplorerSigns.CustomWallSignBlock(AbstractBlock.Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), MAPLE_WOODTYPE));

    //== Rice, Jade
    public static final RegistryObject<Block> JADE_ORE = BlockUtils.register("jade_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(3, 10).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final RegistryObject<Block> JADE_BLOCK = BlockUtils.register("jade_block", () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(3, 10).sound(SoundType.METAL)));

    public static final RegistryObject<Block> TATAMI = BlockUtils.register("tatami", () -> new TatamiBlock(AbstractBlock.Properties.of(Material.WOOD).strength(0.2F, 0.3F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> TATAMI_HALF = BlockUtils.register("tatami_half", () -> new TatamiHalfBlock(AbstractBlock.Properties.of(Material.WOOD).strength(0.2F, 0.3F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> RICE_STRAW_BLOCK = BlockUtils.register("rice_straw_block", () -> new HayBlock(AbstractBlock.Properties.of(Material.GRASS, MaterialColor.COLOR_YELLOW).strength(0.5F).sound(SoundType.GRASS)));
    public static final RegistryObject<Block> RICE_BASE = BlockUtils.registerNoItem("rice_base", () -> new RiceBaseBlock(AbstractBlock.Properties.of(Material.WATER_PLANT).noCollission().sound(SoundType.WET_GRASS)));
    public static final RegistryObject<Block> RICE_TOP = BlockUtils.registerNoItem("rice_top", () -> new RiceBlock(AbstractBlock.Properties.of(Material.PLANT).noCollission().randomTicks().sound(SoundType.WET_GRASS)));

    public static void register() {}
}
