package com.zathrox.explorercraft.common.world.feature;

import com.mojang.datafixers.Dynamic;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class SlimeBlockFeature extends Feature<NoFeatureConfig> {

    private static final Direction[] DIRECTIONS = Direction.values();
    public SlimeBlockFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49889_1_) {
        super(p_i49889_1_);
    }

    @Override
    public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random randomIn, BlockPos pos, NoFeatureConfig config) {

        ChunkPos chunkpos = new ChunkPos(pos);
        boolean flag = SharedSeedRandom.seedSlimeChunk(chunkpos.x, chunkpos.z, world.getSeed(), 987234911L).nextInt(10) == 0;
        if (flag && pos.getY() < 40 && randomIn.nextInt(5) == 0) {
            for(int i = 0; i < 64; ++i) {
                BlockPos blockpos = pos.add(randomIn.nextInt(8) - randomIn.nextInt(8), randomIn.nextInt(2) - randomIn.nextInt(2), randomIn.nextInt(8) - randomIn.nextInt(8));
                if(getStoneVariant(world, blockpos) && world.isAirBlock(blockpos.down())) {
                    world.setBlockState(blockpos, Blocks.SLIME_BLOCK.getDefaultState(), 2);
                }
            }
            for(int j = 0; j < 100; ++j) {
                BlockPos blockpos = pos.add(randomIn.nextInt(8) - randomIn.nextInt(8), randomIn.nextInt(2) - randomIn.nextInt(2), randomIn.nextInt(8) - randomIn.nextInt(8));
                if(getStoneVariant(world, blockpos) && world.isAirBlock(blockpos.up()) ) {
                    world.setBlockState(blockpos, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 2);
                }
            }
            return true;
        }
        return false;

    }

    public boolean getStoneVariant(IWorld world, BlockPos pos) {
        return world.getBlockState(pos) == Blocks.STONE.getDefaultState() ||
                world.getBlockState(pos) == Blocks.ANDESITE.getDefaultState() ||
                world.getBlockState(pos) == Blocks.GRANITE.getDefaultState() ||
                world.getBlockState(pos) == Blocks.DIORITE.getDefaultState() ||
                world.getBlockState(pos) == ExplorerBlocks.MARBLE.getDefaultState() ||
                world.getBlockState(pos) == ExplorerBlocks.BASALT.getDefaultState() ||
                world.getBlockState(pos) == ExplorerBlocks.SLATE.getDefaultState();
    }

}
