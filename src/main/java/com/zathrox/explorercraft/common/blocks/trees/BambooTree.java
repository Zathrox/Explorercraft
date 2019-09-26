package com.zathrox.explorercraft.common.blocks.trees;

import com.zathrox.explorercraft.common.world.feature.BambooTreeFeature;
import com.zathrox.explorercraft.common.world.feature.KwanzanCherryTreeFeature;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.BirchTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class BambooTree extends Tree {

    public BambooTree() {
    }

    @Nullable
    protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random p_196936_1_) {
        return new BambooTreeFeature(NoFeatureConfig::deserialize, true, true);
    }
}