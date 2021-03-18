package dev.driscollcreations.explorercraft.util;

import dev.driscollcreations.explorercraft.setup.ExplorerItemGroups;
import dev.driscollcreations.explorercraft.setup.Registration;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class BlockUtils {

    public static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> ret = registerNoItem(name, block);
        Registration.ITEMS.register(name,() -> new BlockItem(ret.get(), new Item.Properties().group(ExplorerItemGroups.EXPLORERCRAFT)));
        return ret;
    }

    public static Block createWoodBlock() {
        return new Block(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    }

    public static TrapDoorBlock createWoodTrapdoor() {
        return  new TrapDoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid().setAllowsSpawn(BlockUtils::neverAllowSpawn));
    }

    public static SlabBlock createWoodSlab() {
        return new SlabBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    }

    public static WoodButtonBlock createWoodButton() {
        return new WoodButtonBlock(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
    }

    public static PressurePlateBlock createWoodPressurePlate() {
        return new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
    }

    public static DoorBlock createWoodDoor() {
        return new DoorBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid());
    }

    public static FenceBlock createWoodFence() {
        return new FenceBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    }

    public static FenceGateBlock createWoodFenceGate() {
        return new FenceGateBlock(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
    }

    public static LeavesBlock createLeavesBlock() {
        return new LeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().setAllowsSpawn(BlockUtils::allowsSpawnOnLeaves).setSuffocates(BlockUtils::isntSolid).setBlocksVision(BlockUtils::isntSolid));
    }

    public static Boolean allowsSpawnOnLeaves(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return entity == EntityType.OCELOT || entity == EntityType.PARROT;
    }

    public static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    public static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return false;
    }

    public static RotatedPillarBlock createLogBlock(MaterialColor topColor, MaterialColor barkColor) {
        return new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, (state) -> {
            return state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor;
        }).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
    }

}
