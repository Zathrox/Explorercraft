package dev.driscollcreations.explorercraft.data.client;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.blocks.BambooLogBlock;
import dev.driscollcreations.explorercraft.bamboogrove.blocks.PanelBlock;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.SleepingBagBlock;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import net.minecraft.block.*;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.*;
import net.minecraft.util.Direction;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class ExplorerBlockStateProvider extends BlockStateProvider {

    public ExplorerBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Explorercraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //===== Vanilla tweaks
        simpleBlock(VanillaTweaksBlocks.SLIMEY_STONE.get());
        simpleBlock(VanillaTweaksBlocks.BLACK_HOLE.get());

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
        panelBlock((PanelBlock) BambooGroveBlocks.BAMBOO_PANEL.get(), modLoc("block/bamboo_panel_bottom"), modLoc("block/bamboo_panel_top"));
        new ConfiguredModel(models().getBuilder("bamboo_sign").texture("particle", "block/bamboo_planks"));
        simpleBlock(BambooGroveBlocks.BAMBOO_STANDING_SIGN.get(), models().getExistingFile(modLoc("block/bamboo_sign")));
        simpleBlock(BambooGroveBlocks.BAMBOO_WALL_SIGN.get(), models().getExistingFile(modLoc("block/bamboo_sign")));


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
        panelBlock((PanelBlock) BambooGroveBlocks.CHERRY_PANEL.get(), modLoc("block/cherry_panel_bottom"), modLoc("block/cherry_panel_top"));
        new ConfiguredModel(models().getBuilder("cherry_sign").texture("particle", "block/cherry_planks"));
        simpleBlock(BambooGroveBlocks.CHERRY_STANDING_SIGN.get(), models().getExistingFile( modLoc("block/cherry_sign")));
        simpleBlock(BambooGroveBlocks.CHERRY_WALL_SIGN.get(), models().getExistingFile( modLoc("block/cherry_sign")));

        //===== Cherry Blossom Blocks
        buttonBlock(BambooGroveBlocks.CHERRY_BLOSSOM_BUTTON.get(),
                models().withExistingParent("cherry_blossom_button", mcLoc("block/button"))
                        .texture("texture", "block/cherry_blossom_planks"),
                models().withExistingParent("cherry_blossom_button_pressed", mcLoc("block/button_pressed"))
                        .texture("texture", "block/cherry_blossom_planks"));
        new ConfiguredModel(models().withExistingParent("cherry_blossom_button_inventory", mcLoc("block/button_inventory")).texture("texture", "block/cherry_blossom_planks"));
        doorBlock((DoorBlock) BambooGroveBlocks.CHERRY_BLOSSOM_DOOR.get(), modLoc("block/cherry_blossom_door_bottom"), modLoc("block/cherry_blossom_door_top"));

        fenceBlock((FenceBlock) BambooGroveBlocks.CHERRY_BLOSSOM_FENCE.get(), modLoc("block/cherry_blossom_planks"));
        fenceGateBlock((FenceGateBlock) BambooGroveBlocks.CHERRY_BLOSSOM_FENCE_GATE.get(), modLoc("block/cherry_blossom_planks"));
        simpleBlock(BambooGroveBlocks.CHERRY_BLOSSOM_PLANKS.get());
        pressurePlateBlock(BambooGroveBlocks.CHERRY_BLOSSOM_PRESSURE_PLATE.get(),
                models().withExistingParent("cherry_blossom_pressure_plate", mcLoc("block/pressure_plate_up"))
                        .texture("texture", "block/cherry_blossom_planks"),
                models().withExistingParent("cherry_blossom_pressure_plate_down", mcLoc("block/pressure_plate_down"))
                        .texture("texture", "block/cherry_blossom_planks"));
        slabBlock((SlabBlock) BambooGroveBlocks.CHERRY_BLOSSOM_SLAB.get(), modLoc("block/cherry_blossom_planks"), modLoc("block/cherry_blossom_planks"));
        stairsBlock((StairsBlock) BambooGroveBlocks.CHERRY_BLOSSOM_STAIRS.get(), modLoc("block/cherry_blossom_planks"));
        trapdoorBlock((TrapDoorBlock) BambooGroveBlocks.CHERRY_BLOSSOM_TRAPDOOR.get(), modLoc("block/cherry_blossom_trapdoor"), true);
        new ConfiguredModel(models().getBuilder("cherry_blossom_sign").texture("particle", "block/cherry_blossom_planks"));
        simpleBlock(BambooGroveBlocks.CHERRY_BLOSSOM_STANDING_SIGN.get(), models().getExistingFile( modLoc("block/cherry_blossom_sign")));
        simpleBlock(BambooGroveBlocks.CHERRY_BLOSSOM_WALL_SIGN.get(), models().getExistingFile( modLoc("block/cherry_blossom_sign")));

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
        new ConfiguredModel(models().getBuilder("maple_sign").texture("particle", "block/maple_planks"));
        simpleBlock(BambooGroveBlocks.MAPLE_STANDING_SIGN.get(), models().getExistingFile( modLoc("block/maple_sign")));
        simpleBlock(BambooGroveBlocks.MAPLE_WALL_SIGN.get(), models().getExistingFile( modLoc("block/maple_sign")));

        //==== MARBLE
        simpleBlock(VanillaTweaksBlocks.MARBLE.get());
        simpleBlock(VanillaTweaksBlocks.MARBLE_POLISHED.get());
        simpleBlock(VanillaTweaksBlocks.MARBLE_BRICKS.get());
        simpleBlock(VanillaTweaksBlocks.MARBLE_MOSSY.get());
        simpleBlock(VanillaTweaksBlocks.MARBLE_CRACKED.get());
        simpleBlock(VanillaTweaksBlocks.MARBLE_CHISELED.get());
        axisBlock((RotatedPillarBlock) VanillaTweaksBlocks.MARBLE_PILLAR.get(), modLoc("block/marble_pillar"), modLoc("block/marble_polished"));

        slabBlock((SlabBlock) VanillaTweaksBlocks.MARBLE_SLAB.get(), modLoc("block/marble"), modLoc("block/marble"));
        slabBlock((SlabBlock) VanillaTweaksBlocks.MARBLE_POLISHED_SLAB.get(), modLoc("block/marble_polished"), modLoc("block/marble_polished"));
        slabBlock((SlabBlock) VanillaTweaksBlocks.MARBLE_BRICK_SLAB.get(), modLoc("block/marble_bricks"), modLoc("block/marble_bricks"));
        slabBlock((SlabBlock) VanillaTweaksBlocks.MARBLE_MOSSY_SLAB.get(), modLoc("block/marble_mossy"), modLoc("block/marble_mossy"));

        stairsBlock((StairsBlock) VanillaTweaksBlocks.MARBLE_STAIRS.get(), modLoc("block/marble"));
        stairsBlock((StairsBlock) VanillaTweaksBlocks.MARBLE_POLISHED_STAIRS.get(), modLoc("block/marble_polished"));
        stairsBlock((StairsBlock) VanillaTweaksBlocks.MARBLE_BRICK_STAIRS.get(), modLoc("block/marble_bricks"));
        stairsBlock((StairsBlock) VanillaTweaksBlocks.MARBLE_MOSSY_STAIRS.get(), modLoc("block/marble_mossy"));

        wallBlock((WallBlock) VanillaTweaksBlocks.MARBLE_WALL.get(), modLoc("block/marble"));
        wallBlock((WallBlock) VanillaTweaksBlocks.MARBLE_BRICK_WALL.get(), modLoc("block/marble_bricks"));
        wallBlock((WallBlock) VanillaTweaksBlocks.MARBLE_MOSSY_WALL.get(), modLoc("block/marble_mossy"));

        //==== BASALT
        simpleBlock(VanillaTweaksBlocks.BASALT.get());
        simpleBlock(VanillaTweaksBlocks.BASALT_POLISHED.get());
        simpleBlock(VanillaTweaksBlocks.BASALT_BRICKS.get());
        simpleBlock(VanillaTweaksBlocks.BASALT_MOSSY.get());
        simpleBlock(VanillaTweaksBlocks.BASALT_CRACKED.get());
        simpleBlock(VanillaTweaksBlocks.BASALT_CHISELED.get());
        axisBlock((RotatedPillarBlock) VanillaTweaksBlocks.BASALT_PILLAR.get(), modLoc("block/basalt_pillar"), modLoc("block/basalt_polished"));
        simpleBlock(VanillaTweaksBlocks.BASALT_COBBLESTONE.get());
        simpleBlock(VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY.get());

        slabBlock((SlabBlock) VanillaTweaksBlocks.BASALT_SLAB.get(), modLoc("block/basalt"), modLoc("block/basalt"));
        slabBlock((SlabBlock) VanillaTweaksBlocks.BASALT_POLISHED_SLAB.get(), modLoc("block/basalt_polished"), modLoc("block/basalt_polished"));
        slabBlock((SlabBlock) VanillaTweaksBlocks.BASALT_BRICK_SLAB.get(), modLoc("block/basalt_bricks"), modLoc("block/basalt_bricks"));
        slabBlock((SlabBlock) VanillaTweaksBlocks.BASALT_MOSSY_SLAB.get(), modLoc("block/basalt_mossy"), modLoc("block/basalt_mossy"));
        slabBlock((SlabBlock) VanillaTweaksBlocks.BASALT_COBBLESTONE_SLAB.get(), modLoc("block/basalt_cobblestone"), modLoc("block/basalt_cobblestone"));
        slabBlock((SlabBlock) VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_SLAB.get(), modLoc("block/basalt_cobblestone_mossy"), modLoc("block/basalt_cobblestone_mossy"));

        stairsBlock((StairsBlock) VanillaTweaksBlocks.BASALT_STAIRS.get(), modLoc("block/basalt"));
        stairsBlock((StairsBlock) VanillaTweaksBlocks.BASALT_POLISHED_STAIRS.get(), modLoc("block/basalt_polished"));
        stairsBlock((StairsBlock) VanillaTweaksBlocks.BASALT_BRICK_STAIRS.get(), modLoc("block/basalt_bricks"));
        stairsBlock((StairsBlock) VanillaTweaksBlocks.BASALT_MOSSY_STAIRS.get(), modLoc("block/basalt_mossy"));
        stairsBlock((StairsBlock) VanillaTweaksBlocks.BASALT_COBBLESTONE_STAIRS.get(), modLoc("block/basalt_cobblestone"));
        stairsBlock((StairsBlock) VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_STAIRS.get(), modLoc("block/basalt_cobblestone_mossy"));

        wallBlock((WallBlock) VanillaTweaksBlocks.BASALT_WALL.get(), modLoc("block/basalt"));
        wallBlock((WallBlock) VanillaTweaksBlocks.BASALT_BRICK_WALL.get(), modLoc("block/basalt_bricks"));
        wallBlock((WallBlock) VanillaTweaksBlocks.BASALT_MOSSY_WALL.get(), modLoc("block/basalt_mossy"));
        wallBlock((WallBlock) VanillaTweaksBlocks.BASALT_COBBLESTONE_WALL.get(), modLoc("block/basalt_cobblestone"));
        wallBlock((WallBlock) VanillaTweaksBlocks.BASALT_COBBLESTONE_MOSSY_WALL.get(), modLoc("block/basalt_cobblestone_mossy"));

        sleepingBagModels();

    }
    public void sleepingBag(String color, ResourceLocation head, ResourceLocation foot) {
        ModelFile headModel = models().getExistingFile(head);
        ModelFile footModel = models().getExistingFile(foot);
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Explorercraft.MOD_ID, "sleeping_bag_"+color));
        getVariantBuilder(block).forAllStatesExcept(state -> {
            int yRot = ((int) state.getValue(SleepingBagBlock.FACING).toYRot());
            return ConfiguredModel.builder().modelFile(state.getValue(SleepingBagBlock.PART) == BedPart.FOOT ? footModel : headModel)
                    .rotationY(yRot)
                    .build();
        });
    }

    public void sleepingBagModels() {
        new ConfiguredModel(models().withExistingParent("block/sleeping_bag/sleeping_bag_foot", mcLoc("block/thin_block"))
                .element()
                .from(0,0,0).to(16, 2, 16)
                .face(Direction.EAST).uvs(8,1,12,1.5f).texture("#sleeping_bag").cullface(Direction.EAST).end()
                .face(Direction.NORTH).uvs(10, 0.5f, 14, 1).texture("#sleeping_bag").cullface(Direction.NORTH).end()
                .face(Direction.WEST).uvs(8, 1.5f, 12, 2).texture("#sleeping_bag").cullface(Direction.WEST).end()
                .face(Direction.UP).uvs(0, 4, 4, 8).texture("#sleeping_bag").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end()
                .face(Direction.DOWN).uvs(4, 4, 8, 8).texture("#sleeping_bag").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end()
                .end());

        new ConfiguredModel(models().withExistingParent("block/sleeping_bag/sleeping_bag_head", mcLoc("block/thin_block"))
                .element()
                .from(0,0,0).to(16, 2, 16)
                .face(Direction.EAST).uvs(12,1.5f,16,2).texture("#sleeping_bag").cullface(Direction.EAST).end()
                .face(Direction.SOUTH).uvs(10, 0.5f, 14, 1).texture("#sleeping_bag").cullface(Direction.SOUTH).end()
                .face(Direction.WEST).uvs(12, 1, 16, 1.5f).texture("#sleeping_bag").cullface(Direction.WEST).end()
                .face(Direction.UP).uvs(0, 0, 4, 4).texture("#sleeping_bag").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end()
                .face(Direction.DOWN).uvs(4, 0, 8, 4).texture("#sleeping_bag").rotation(ModelBuilder.FaceRotation.UPSIDE_DOWN).end()
                .end());

        List<String> sleepingBags = Arrays.asList("black", "blue", "brown", "cyan", "gray", "green", "leather", "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "white", "yellow");
        for (String color : sleepingBags) {
            sleepingBag(color, modLoc("block/sleeping_bag/sleeping_bag_"+color+"_head"), modLoc("block/sleeping_bag/sleeping_bag_"+color+"_foot"));
            new ConfiguredModel(models().withExistingParent("block/sleeping_bag/sleeping_bag_"+color+"_head", modLoc("block/sleeping_bag/sleeping_bag_head")).texture("sleeping_bag", modLoc("block/sleeping_bag/sleeping_bag_"+color)).texture("particle", modLoc("block/sleeping_bag/sleeping_bag_"+color)));
            new ConfiguredModel(models().withExistingParent("block/sleeping_bag/sleeping_bag_"+color+"_foot", modLoc("block/sleeping_bag/sleeping_bag_foot")).texture("sleeping_bag", modLoc("block/sleeping_bag/sleeping_bag_"+color)).texture("particle", modLoc("block/sleeping_bag/sleeping_bag_"+color)));
        }
    }

    @Override
    public void axisBlock(RotatedPillarBlock block, ResourceLocation side, ResourceLocation end) {
        axisBlock(block, models().cubeColumn(name(block), side, end), models().cubeColumnHorizontal(name(block) + "_horizontal", side, end));
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
        SixWayBlock.PROPERTY_BY_DIRECTION.entrySet().forEach(e -> {
            Direction dir = e.getKey();
            if (dir.getAxis().isHorizontal()) {
                builder.part().modelFile(side).rotationY((((int) dir.toYRot()) + 180) % 360).uvLock(true).weight(5).nextModel().modelFile(side2).rotationY((((int) dir.toYRot()) + 180) % 360).uvLock(true).weight(5).addModel()
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
                    Boolean powered = state.getValue(BlockStateProperties.POWERED);
                    if (powered) {
                        return ConfiguredModel.builder()
                                   .modelFile(pressed.apply(state))
                                   .rotationX(state.getValue(BlockStateProperties.ATTACH_FACE).ordinal() * 90)
                                   .rotationY((((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + angleOffset) + (state.getValue(BlockStateProperties.ATTACH_FACE) == AttachFace.CEILING ? 180 : 0)) % 360)
                                   .build();
                    } else {
                        return ConfiguredModel.builder()
                                   .modelFile(normal.apply(state))
                                   .rotationX(state.getValue(BlockStateProperties.ATTACH_FACE).ordinal() * 90)
                                   .rotationY((((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + angleOffset) + (state.getValue(BlockStateProperties.ATTACH_FACE) == AttachFace.CEILING ? 180 : 0)) % 360)
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

    public void panelBlock(PanelBlock block, ResourceLocation bottom, ResourceLocation top) {
        panelBLockInternal(block, block.getRegistryName().toString(), bottom, top);
    }

    private void panelBLockInternal(PanelBlock block, String baseName, ResourceLocation bottom, ResourceLocation top) {
        ModelFile bottomLeft = models().doorBottomLeft(baseName + "_bottom", bottom, top);
        ModelFile topLeft = models().doorTopLeft(baseName + "_top", bottom, top);
        panelBlock(block, bottomLeft, topLeft);
    }

    public void panelBlock(PanelBlock block, ModelFile bottomLeft, ModelFile topLeft) {
        getVariantBuilder(block).forAllStatesExcept(state -> {
            int yRot = ((int) state.getValue(DoorBlock.FACING).toYRot()) + 90;
            yRot %= 360;
            return ConfiguredModel.builder().modelFile(state.getValue(DoorBlock.HALF) == DoubleBlockHalf.LOWER ? (bottomLeft) : (topLeft))
                           .rotationY(yRot)
                           .build();
        });
    }

}
