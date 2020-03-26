package com.zathrox.explorercraft.common.blocks.trees;

import com.zathrox.explorercraft.common.world.feature.AbstractTreeFeature2;
import com.zathrox.explorercraft.common.world.feature.BambooTreeFeature;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class BambooTree extends ExplorerTree {

    public BambooTree() {
    }

    @Nullable
    public AbstractTreeFeature2<NoFeatureConfig> getTreeFeature(Random p_196936_1_) {
        return new BambooTreeFeature(NoFeatureConfig::deserialize, true);
    }
}