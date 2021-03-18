package dev.driscollcreations.explorercraft.bamboogrove.setup;

import dev.driscollcreations.explorercraft.bamboogrove.blocks.*;
import dev.driscollcreations.explorercraft.bamboogrove.blocks.trees.BambooTree;
import dev.driscollcreations.explorercraft.bamboogrove.blocks.trees.CherryTree;
import dev.driscollcreations.explorercraft.bamboogrove.blocks.trees.MapleTree;
import dev.driscollcreations.explorercraft.util.BlockUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.extensions.IForgeBlock;
import net.minecraftforge.fml.RegistryObject;

public class BambooGroveBlocks {

    //== Bamboo related Blocks
    public static final RegistryObject<Block> BAMBOO_LEAVES = BlockUtils.register("bamboo_leaves", () -> BlockUtils.createLeavesBlock());
    public static final RegistryObject<BambooLogBlock> BAMBOO_LOG = BlockUtils.register("bamboo_log",
            () -> new BambooLogBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2, 10).sound(SoundType.BAMBOO)));
    public static final RegistryObject<Block> BAMBOO_BUTTON = BlockUtils.register("bamboo_button", () -> BlockUtils.createWoodButton());
    public static final RegistryObject<Block> BAMBOO_DOOR = BlockUtils.register("bamboo_door", () -> BlockUtils.createWoodDoor());
    public static final RegistryObject<Block> BAMBOO_FENCE = BlockUtils.register("bamboo_fence", () -> BlockUtils.createWoodFence());
    public static final RegistryObject<Block> BAMBOO_FENCE_GATE = BlockUtils.register("bamboo_fence_gate", () -> BlockUtils.createWoodFenceGate());
    public static final RegistryObject<Block> BAMBOO_PLANKS = BlockUtils.register("bamboo_planks", () -> BlockUtils.createWoodBlock());
    public static final RegistryObject<Block> BAMBOO_PLANKS_VERTICAL = BlockUtils.register("bamboo_planks_vertical", () -> BlockUtils.createWoodBlock());
    public static final RegistryObject<Block> BAMBOO_PRESSURE_PLATE = BlockUtils.register("bamboo_pressure_plate", () -> BlockUtils.createWoodPressurePlate());
    public static final RegistryObject<Block> BAMBOO_SAPLING = BlockUtils.register("bamboo_sapling",
            () -> new SaplingBlock(new BambooTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final RegistryObject<Block> POTTED_BAMBOO_SAPLING = BlockUtils.registerNoItem("potted_bamboo_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BAMBOO_SAPLING::get, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()));
    public static final RegistryObject<Block> BAMBOO_SLAB = BlockUtils.register("bamboo_slab", () -> BlockUtils.createWoodSlab());
    public static final RegistryObject<Block> BAMBOO_STAIRS = BlockUtils.register("bamboo_stairs",
            () -> new StairsBlock(() -> BAMBOO_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(BAMBOO_PLANKS.get())));
    public static final RegistryObject<Block> BAMBOO_TRAPDOOR = BlockUtils.register("bamboo_trapdoor", () -> BlockUtils.createWoodTrapdoor());

    //== Cherry Tree
    public static final RegistryObject<Block> CHERRY_LEAVES = BlockUtils.register("cherry_leaves", () -> BlockUtils.createLeavesBlock());
    public static final RegistryObject<RotatedPillarBlock> CHERRY_LOG = BlockUtils.register("cherry_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CHERRY_BUTTON = BlockUtils.register("cherry_button", () -> BlockUtils.createWoodButton());
    public static final RegistryObject<Block> CHERRY_DOOR = BlockUtils.register("cherry_door", () -> BlockUtils.createWoodDoor());
    public static final RegistryObject<Block> CHERRY_FENCE = BlockUtils.register("cherry_fence", () -> BlockUtils.createWoodFence());
    public static final RegistryObject<Block> CHERRY_FENCE_GATE = BlockUtils.register("cherry_fence_gate", () -> BlockUtils.createWoodFenceGate());
    public static final RegistryObject<Block> CHERRY_PLANKS = BlockUtils.register("cherry_planks", () -> BlockUtils.createWoodBlock());
    public static final RegistryObject<Block> CHERRY_PRESSURE_PLATE = BlockUtils.register("cherry_pressure_plate", () -> BlockUtils.createWoodPressurePlate());
    public static final RegistryObject<Block> CHERRY_SAPLING = BlockUtils.register("cherry_sapling",
            () -> new SaplingBlock(new CherryTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final RegistryObject<Block> POTTED_CHERRY_SAPLING = BlockUtils.registerNoItem("potted_cherry_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CHERRY_SAPLING::get, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()));
    public static final RegistryObject<Block> CHERRY_SLAB = BlockUtils.register("cherry_slab", () -> BlockUtils.createWoodSlab());
    public static final RegistryObject<Block> CHERRY_STAIRS = BlockUtils.register("cherry_stairs",
            () -> new StairsBlock(() -> CHERRY_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(CHERRY_PLANKS.get())));
    public static final RegistryObject<Block> CHERRY_TRAPDOOR = BlockUtils.register("cherry_trapdoor", () -> BlockUtils.createWoodTrapdoor());
    public static final RegistryObject<RotatedPillarBlock> CHERRY_WOOD = BlockUtils.register("cherry_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> CHERRY_STRIPPED_LOG = BlockUtils.register("cherry_stripped_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> CHERRY_STRIPPED_WOOD = BlockUtils.register("cherry_stripped_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2, 10).sound(SoundType.WOOD)));

    //== Maple Tree
    public static final RegistryObject<Block> MAPLE_LEAVES = BlockUtils.register("maple_leaves", () -> BlockUtils.createLeavesBlock());
    public static final RegistryObject<RotatedPillarBlock> MAPLE_LOG = BlockUtils.register("maple_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MAPLE_BUTTON = BlockUtils.register("maple_button", () -> BlockUtils.createWoodButton());
    public static final RegistryObject<Block> MAPLE_DOOR = BlockUtils.register("maple_door", () -> BlockUtils.createWoodDoor());
    public static final RegistryObject<Block> MAPLE_FENCE = BlockUtils.register("maple_fence", () -> BlockUtils.createWoodFence());
    public static final RegistryObject<Block> MAPLE_FENCE_GATE = BlockUtils.register("maple_fence_gate", () -> BlockUtils.createWoodFenceGate());
    public static final RegistryObject<Block> MAPLE_PLANKS = BlockUtils.register("maple_planks", () -> BlockUtils.createWoodBlock());
    public static final RegistryObject<Block> MAPLE_PRESSURE_PLATE = BlockUtils.register("maple_pressure_plate", () -> BlockUtils.createWoodPressurePlate());
    public static final RegistryObject<Block> MAPLE_SAPLING = BlockUtils.register("maple_sapling",
            () -> new SaplingBlock(new MapleTree(), AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().zeroHardnessAndResistance().sound(SoundType.PLANT)));
    public static final RegistryObject<Block> POTTED_MAPLE_SAPLING = BlockUtils.registerNoItem("potted_maple_sapling",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, MAPLE_SAPLING::get, AbstractBlock.Properties.create(Material.MISCELLANEOUS).zeroHardnessAndResistance()));
    public static final RegistryObject<Block> MAPLE_SLAB = BlockUtils.register("maple_slab", () -> BlockUtils.createWoodSlab());
    public static final RegistryObject<Block> MAPLE_STAIRS = BlockUtils.register("maple_stairs",
            () -> new StairsBlock(() -> MAPLE_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(MAPLE_PLANKS.get())));
    public static final RegistryObject<Block> MAPLE_TRAPDOOR = BlockUtils.register("maple_trapdoor", () -> BlockUtils.createWoodTrapdoor());
    public static final RegistryObject<RotatedPillarBlock> MAPLE_WOOD = BlockUtils.register("maple_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> MAPLE_STRIPPED_LOG = BlockUtils.register("maple_stripped_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2, 10).sound(SoundType.WOOD)));
    public static final RegistryObject<RotatedPillarBlock> MAPLE_STRIPPED_WOOD = BlockUtils.register("maple_stripped_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(2, 10).sound(SoundType.WOOD)));

    //== Rice, Jade
    public static final RegistryObject<Block> JADE_ORE = BlockUtils.register("jade_ore", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).harvestLevel(2).sound(SoundType.STONE)));
    public static final RegistryObject<Block> JADE_BLOCK = BlockUtils.register("jade_block", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3, 10).sound(SoundType.METAL)));

    public static final RegistryObject<Block> TATAMI = BlockUtils.register("tatami", () -> new TatamiBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> TATAMI_HALF = BlockUtils.register("tatami_half", () -> new TatamiHalfBlock(AbstractBlock.Properties.create(Material.WOOD).hardnessAndResistance(0.2F, 0.3F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> RICE_STRAW_BLOCK = BlockUtils.register("rice_straw_block", () -> new HayBlock(AbstractBlock.Properties.create(Material.ORGANIC, MaterialColor.YELLOW).hardnessAndResistance(0.5F).sound(SoundType.PLANT)));
    public static final RegistryObject<Block> RICE_BASE = BlockUtils.registerNoItem("rice_base", () -> new RiceBaseBlock(AbstractBlock.Properties.create(Material.OCEAN_PLANT).doesNotBlockMovement().sound(SoundType.WET_GRASS)));
    public static final RegistryObject<Block> RICE_TOP = BlockUtils.registerNoItem("rice_top", () -> new RiceBlock(AbstractBlock.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().sound(SoundType.WET_GRASS)));

    public static void register() {}
}
