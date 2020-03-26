package com.zathrox.explorercraft.common.world.feature;

import com.mojang.datafixers.Dynamic;
import com.zathrox.explorercraft.common.blocks.NoctilucaBlock;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.CountConfig;

import java.util.Random;
import java.util.function.Function;

public class NoctilucaFeature extends Feature<CountConfig> {

    private static final Random randomAge = new Random();

    public NoctilucaFeature(Function<Dynamic<?>, ? extends CountConfig> p_i51442_1_) {
        super(p_i51442_1_);
    }

    public boolean place(IWorld worldIn, ChunkGenerator<?> chunkGenerator, Random random, BlockPos blockPos, CountConfig config) {
        int count = 0;

        for(int i = 0; i < config.count; ++i) {
            int x = random.nextInt(8) - random.nextInt(8);
            int z = random.nextInt(8) - random.nextInt(8);
            int y = worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, blockPos.getX() + x, blockPos.getZ() + z);
            BlockPos water = new BlockPos(blockPos.getX() + x, y, blockPos.getZ() + z);
            BlockState noctiluca = (BlockState) ExplorerBlocks.NOCTILUCAS.getDefaultState().with(NoctilucaBlock.AGE, 0 + randomAge.nextInt(8));
            if (worldIn.getBlockState(water).getBlock() == Blocks.WATER && noctiluca.isValidPosition(worldIn, water)) {
                worldIn.setBlockState(water, noctiluca, 2);
                ++count;
            }
        }

        return count > 0;
    }
}
