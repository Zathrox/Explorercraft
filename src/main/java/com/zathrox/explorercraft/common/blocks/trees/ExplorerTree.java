package com.zathrox.explorercraft.common.blocks.trees;

import com.zathrox.explorercraft.common.world.feature.AbstractTreeFeature2;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class ExplorerTree {
    @Nullable
    public abstract AbstractTreeFeature2<NoFeatureConfig> getTreeFeature(Random random);

    public boolean spawn(ServerWorld worldIn, BlockPos pos, BlockState blockUnder, Random random) {
        AbstractTreeFeature2<NoFeatureConfig> abstracttreefeature = this.getTreeFeature(random);
        if (abstracttreefeature == null) {
            return false;
        } else {
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
            if (abstracttreefeature.place(worldIn, worldIn.getChunkProvider().getChunkGenerator(), random, pos, IFeatureConfig.NO_FEATURE_CONFIG)) {
                return true;
            } else {
                worldIn.setBlockState(pos, blockUnder, 4);
                return false;
            }
        }
    }
}