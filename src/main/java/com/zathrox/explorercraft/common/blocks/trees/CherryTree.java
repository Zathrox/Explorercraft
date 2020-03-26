package com.zathrox.explorercraft.common.blocks.trees;

import com.zathrox.explorercraft.common.world.feature.AbstractTreeFeature2;
import com.zathrox.explorercraft.common.world.feature.KwanzanCherryTreeFeature;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class CherryTree extends ExplorerTree {

    public CherryTree() {
    }

    @Nullable
    public AbstractTreeFeature2<NoFeatureConfig> getTreeFeature(Random p_196936_1_) {
        return new KwanzanCherryTreeFeature(NoFeatureConfig::deserialize);
    }
}