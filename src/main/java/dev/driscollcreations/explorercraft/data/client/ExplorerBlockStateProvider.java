package dev.driscollcreations.explorercraft.data.client;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.blocks.BambooLogBlock;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.MultiPartBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class ExplorerBlockStateProvider extends BlockStateProvider {

    public ExplorerBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Explorercraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //===== Vanilla tweaks
        simpleBlock(VanillaTweaksBlocks.SLIMEY_STONE.get());
        //===== Jade Blocks
        simpleBlock(BambooGroveBlocks.JADE_BLOCK.get());
        simpleBlock(BambooGroveBlocks.JADE_ORE.get());

        //===== Rice
        axisBlock((RotatedPillarBlock) BambooGroveBlocks.RICE_STRAW_BLOCK.get());



        //===== Thick Bamboo Blocks
        buttonBlock(BambooGroveBlocks.BAMBOO_BUTTON.get(),
                models().withExistingParent("bamboo_button", mcLoc("block/button"))
                        .texture("texture", "block/bamboo_planks"),
                models().withExistingParent("bamboo_button_pressed", mcLoc("block/button_pressed"))
                        .texture("texture", "block/bamboo_planks"));
        new ConfiguredModel(models().withExistingParent("bamboo_button_inventory", mcLoc("block/button_inventory")).texture("texture", "block/bamboo_planks"));
        doorBlock((DoorBlock) BambooGroveBlocks.BAMBOO_DOOR.get(), modLoc("block/bamboo_door_bottom"), modLoc("block/bamboo_door_top"));
        fenceBlock((FenceBlock) BambooGroveBlocks.BAMBOO_FENCE.get(), modLoc("block/bamboo_planks"));
        fenceGateBlock((FenceGateBlock) BambooGroveBlocks.BAMBOO_FENCE_GATE.get(), modLoc("block/bamboo_planks"));
        simpleBlock(BambooGroveBlocks.BAMBOO_LEAVES.get());
        bambooLogBlock(BambooGroveBlocks.BAMBOO_LOG.get());
        simpleBlock(BambooGroveBlocks.BAMBOO_PLANKS.get());
        simpleBlock(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get());
        pressurePlateBlock(BambooGroveBlocks.BAMBOO_PRESSURE_PLATE.get(),
                models().withExistingParent("bamboo_pressure_plate", mcLoc("block/pressure_plate_up"))
                        .texture("texture", "block/bamboo_planks"),
                models().withExistingParent("bamboo_pressure_plate_down", mcLoc("block/pressure_plate_down"))
                        .texture("texture", "block/bamboo_planks"));
        simpleBlock(BambooGroveBlocks.BAMBOO_SAPLING.get());
        new ConfiguredModel(models().withExistingParent("bamboo_sapling", mcLoc("block/cross")).texture("cross", "block/bamboo_sapling"));
        new ConfiguredModel(models().withExistingParent("potted_bamboo_sapling", mcLoc("block/flower_pot_cross")).texture("plant", "block/bamboo_sapling"));
        simpleBlock(BambooGroveBlocks.POTTED_BAMBOO_SAPLING.get(), models().getBuilder("block/potted_bamboo_sapling"));
        slabBlock((SlabBlock) BambooGroveBlocks.BAMBOO_SLAB.get(), modLoc("block/bamboo_planks"), modLoc("block/bamboo_planks"));
        stairsBlock((StairsBlock) BambooGroveBlocks.BAMBOO_STAIRS.get(), modLoc("block/bamboo_planks"));
        trapdoorBlock((TrapDoorBlock) BambooGroveBlocks.BAMBOO_TRAPDOOR.get(), modLoc("block/bamboo_trapdoor"), true);

        //===== Cherry Tree Blocks
        buttonBlock(BambooGroveBlocks.CHERRY_BUTTON.get(),
                models().withExistingParent("cherry_button", mcLoc("block/button"))
                        .texture("texture", "block/cherry_planks"),
                models().withExistingParent("cherry_button_pressed", mcLoc("block/button_pressed"))
                        .texture("texture", "block/cherry_planks"));
        new ConfiguredModel(models().withExistingParent("cherry_button_inventory", mcLoc("block/button_inventory")).texture("texture", "block/cherry_planks"));
        doorBlock((DoorBlock) BambooGroveBlocks.CHERRY_DOOR.get(), modLoc("block/cherry_door_bottom"), modLoc("block/cherry_door_top"));
        fenceBlock((FenceBlock) BambooGroveBlocks.CHERRY_FENCE.get(), modLoc("block/cherry_planks"));
        fenceGateBlock((FenceGateBlock) BambooGroveBlocks.CHERRY_FENCE_GATE.get(), modLoc("block/cherry_planks"));
        simpleBlock(BambooGroveBlocks.CHERRY_LEAVES.get());
        logBlock(BambooGroveBlocks.CHERRY_LOG.get());
        logBlock(BambooGroveBlocks.CHERRY_STRIPPED_LOG.get());
        simpleBlock(BambooGroveBlocks.CHERRY_PLANKS.get());
        pressurePlateBlock(BambooGroveBlocks.CHERRY_PRESSURE_PLATE.get(),
                models().withExistingParent("cherry_pressure_plate", mcLoc("block/pressure_plate_up"))
                        .texture("texture", "block/cherry_planks"),
                models().withExistingParent("cherry_pressure_plate_down", mcLoc("block/pressure_plate_down"))
                        .texture("texture", "block/cherry_planks"));
        simpleBlock(BambooGroveBlocks.CHERRY_SAPLING.get());
        new ConfiguredModel(models().withExistingParent("cherry_sapling", mcLoc("block/cross")).texture("cross", "block/cherry_sapling"));
        new ConfiguredModel(models().withExistingParent("potted_cherry_sapling", mcLoc("block/flower_pot_cross")).texture("plant", "block/cherry_sapling"));
        simpleBlock(BambooGroveBlocks.POTTED_CHERRY_SAPLING.get(), models().getBuilder("block/potted_cherry_sapling"));
        slabBlock((SlabBlock) BambooGroveBlocks.CHERRY_SLAB.get(), modLoc("block/cherry_planks"), modLoc("block/cherry_planks"));
        stairsBlock((StairsBlock) BambooGroveBlocks.CHERRY_STAIRS.get(), modLoc("block/cherry_planks"));
        trapdoorBlock((TrapDoorBlock) BambooGroveBlocks.CHERRY_TRAPDOOR.get(), modLoc("block/cherry_trapdoor"), true);
        woodBlock(BambooGroveBlocks.CHERRY_WOOD.get(), modLoc("block/cherry_log"));
        woodBlock(BambooGroveBlocks.CHERRY_STRIPPED_WOOD.get(), modLoc("block/cherry_stripped_log"));

        //===== Maple Tree Blocks
        buttonBlock(BambooGroveBlocks.MAPLE_BUTTON.get(),
                models().withExistingParent("maple_button", mcLoc("block/button"))
                        .texture("texture", "block/maple_planks"),
                models().withExistingParent("maple_button_pressed", mcLoc("block/button_pressed"))
                        .texture("texture", "block/maple_planks"));
        new ConfiguredModel(models().withExistingParent("maple_button_inventory", mcLoc("block/button_inventory")).texture("texture", "block/maple_planks"));
        doorBlock((DoorBlock) BambooGroveBlocks.MAPLE_DOOR.get(), modLoc("block/maple_door_bottom"), modLoc("block/maple_door_top"));
        fenceBlock((FenceBlock) BambooGroveBlocks.MAPLE_FENCE.get(), modLoc("block/maple_planks"));
        fenceGateBlock((FenceGateBlock) BambooGroveBlocks.MAPLE_FENCE_GATE.get(), modLoc("block/maple_planks"));
        simpleBlock(BambooGroveBlocks.MAPLE_LEAVES.get());
        logBlock(BambooGroveBlocks.MAPLE_LOG.get());
        logBlock(BambooGroveBlocks.MAPLE_STRIPPED_LOG.get());
        simpleBlock(BambooGroveBlocks.MAPLE_PLANKS.get());
        pressurePlateBlock(BambooGroveBlocks.MAPLE_PRESSURE_PLATE.get(),
                models().withExistingParent("maple_pressure_plate", mcLoc("block/pressure_plate_up"))
                        .texture("texture", "block/maple_planks"),
                models().withExistingParent("maple_pressure_plate_down", mcLoc("block/pressure_plate_down"))
                        .texture("texture", "block/maple_planks"));
        simpleBlock(BambooGroveBlocks.MAPLE_SAPLING.get());
        new ConfiguredModel(models().withExistingParent("maple_sapling", mcLoc("block/cross")).texture("cross", "block/maple_sapling"));
        new ConfiguredModel(models().withExistingParent("potted_maple_sapling", mcLoc("block/flower_pot_cross")).texture("plant", "block/maple_sapling"));
        simpleBlock(BambooGroveBlocks.POTTED_MAPLE_SAPLING.get(), models().getBuilder("block/potted_maple_sapling"));
        slabBlock((SlabBlock) BambooGroveBlocks.MAPLE_SLAB.get(), modLoc("block/maple_planks"), modLoc("block/maple_planks"));
        stairsBlock((StairsBlock) BambooGroveBlocks.MAPLE_STAIRS.get(), modLoc("block/maple_planks"));
        trapdoorBlock((TrapDoorBlock) BambooGroveBlocks.MAPLE_TRAPDOOR.get(), modLoc("block/maple_trapdoor"), true);
        woodBlock(BambooGroveBlocks.MAPLE_WOOD.get(), modLoc("block/maple_log"));
        woodBlock(BambooGroveBlocks.MAPLE_STRIPPED_WOOD.get(), modLoc("block/maple_stripped_log"));

    }

    public void bambooLogBlock(BambooLogBlock block) {
        String baseName = block.getRegistryName().toString();
        String texture = "block/bamboo_log";
        fourWayBlock(block,
                models().withExistingParent(baseName, "explorercraft:block/bamboo")
                        .texture("texture", texture)
                        .texture("end", "block/bamboo_log_top"),
                models().withExistingParent(baseName + "_side", "explorercraft:block/bamboo_branch")
                        .texture("texture", texture),
                models().withExistingParent(baseName + "_side2", "explorercraft:block/bamboo_branch2")
                        .texture("texture", texture));
    }

    public void fourWayBlock(BambooLogBlock block, ModelFile post, ModelFile side, ModelFile side2) {
        MultiPartBlockStateBuilder builder = getMultipartBuilder(block)
                                                     .part().modelFile(post).addModel().end();
        fourWayMultipart(builder, side, side2);
    }

    public void fourWayMultipart(MultiPartBlockStateBuilder builder, ModelFile side, ModelFile side2 ) {
        SixWayBlock.FACING_TO_PROPERTY_MAP.entrySet().forEach(e -> {
            Direction dir = e.getKey();
            if (dir.getAxis().isHorizontal()) {
                builder.part().modelFile(side).rotationY((((int) dir.getHorizontalAngle()) + 180) % 360).uvLock(true).weight(5).nextModel().modelFile(side2).rotationY((((int) dir.getHorizontalAngle()) + 180) % 360).uvLock(true).weight(5).addModel()
                        .condition(e.getValue(), true);
            }
        });
    }

    public void pressurePlateBlock(Block block, ModelFile normal,  ModelFile powered) {
        getVariantBuilder(block)
                .partialState().with(PressurePlateBlock.POWERED, Boolean.FALSE).addModels(new ConfiguredModel(normal))
                .partialState().with(PressurePlateBlock.POWERED, Boolean.TRUE).addModels(new ConfiguredModel(powered));
    }

    public void buttonBlock(Block block, ModelFile normal, ModelFile powered) {
        buttonBlock(block, normal, powered, 180);
    }

    public void buttonBlock(Block block, ModelFile normal, ModelFile powered, int angleOffset) {
        buttonBlock(block, $ -> normal, $ -> powered, angleOffset);
    }

    public void buttonBlock(Block block, Function<BlockState, ModelFile> normal, Function<BlockState, ModelFile> pressed) {
        buttonBlock(block, normal, pressed, 180);
    }

    public void buttonBlock(Block block, Function<BlockState, ModelFile> normal,  Function<BlockState, ModelFile> pressed, int angleOffset) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Boolean powered = state.get(BlockStateProperties.POWERED);
                    if (powered) {
                        return ConfiguredModel.builder()
                                   .modelFile(pressed.apply(state))
                                   .rotationX(state.get(BlockStateProperties.FACE).ordinal() * 90)
                                   .rotationY((((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + angleOffset) + (state.get(BlockStateProperties.FACE) == AttachFace.CEILING ? 180 : 0)) % 360)
                                   .build();
                    } else {
                        return ConfiguredModel.builder()
                                   .modelFile(normal.apply(state))
                                   .rotationX(state.get(BlockStateProperties.FACE).ordinal() * 90)
                                   .rotationY((((int) state.get(BlockStateProperties.HORIZONTAL_FACING).getHorizontalAngle() + angleOffset) + (state.get(BlockStateProperties.FACE) == AttachFace.CEILING ? 180 : 0)) % 360)
                                   .build();
                    }
                });
    }

    public void woodBlock(RotatedPillarBlock block, ResourceLocation texture) {
        ModelFile woodBlockModel = models().cubeColumn(name(block), texture, texture);
        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(woodBlockModel).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(woodBlockModel).rotationX(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(woodBlockModel).rotationX(90).rotationY(90).addModel();
    }

    private String name(Block block) {
        return block.getRegistryName().getPath();
    }

}
