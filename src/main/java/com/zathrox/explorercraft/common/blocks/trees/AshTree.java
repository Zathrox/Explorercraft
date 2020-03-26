package com.zathrox.explorercraft.common.blocks.trees;

import com.zathrox.explorercraft.common.world.feature.AbstractTreeFeature2;
import com.zathrox.explorercraft.common.world.feature.AshTreeFeature;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class AshTree extends ExplorerSuperBigTree {
    @Nullable
    public AbstractTreeFeature2<NoFeatureConfig> getTreeFeature(Random random) {
        return null;
    }

    @Nullable
    public AbstractTreeFeature2<NoFeatureConfig> getBigTreeFeature(Random random) {
        return new AshTreeFeature(NoFeatureConfig::deserialize);
    }
}