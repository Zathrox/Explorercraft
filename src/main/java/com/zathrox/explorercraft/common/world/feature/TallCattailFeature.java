package com.zathrox.explorercraft.common.world.feature;

import com.mojang.datafixers.Dynamic;
import com.zathrox.explorercraft.common.blocks.TallCattailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.DoublePlantConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GrassFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class TallCattailFeature extends Feature<DoublePlantConfig> {
    public TallCattailFeature(Function<Dynamic<?>, ? extends DoublePlantConfig> p_i49884_1_) {
        super(p_i49884_1_);
    }

    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, DoublePlantConfig config) {
        boolean flag = false;

        for(int i = 0; i < 64; ++i) {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (worldIn.isAirBlock(blockpos) && blockpos.getY() < worldIn.getWorld().getDimension().getHeight() - 2 && config.state.isValidPosition(worldIn, blockpos)) {
                ((TallCattailBlock)config.state.getBlock()).placeAt(worldIn, blockpos, 2);
                flag = true;
            }
        }

        return flag;
    }
}