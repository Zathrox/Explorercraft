package com.zathrox.explorercraft.common.blocks.trees;

import com.zathrox.explorercraft.common.world.feature.AbstractTreeFeature2;
import com.zathrox.explorercraft.common.world.feature.WillowTreeFeature;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class WillowTree extends ExplorerTree {

    public WillowTree() {
    }

    @Nullable
    @Override
    public AbstractTreeFeature2<NoFeatureConfig> getTreeFeature(Random random) {
        return new WillowTreeFeature(NoFeatureConfig::deserialize);
    }
}