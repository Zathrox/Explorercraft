package com.zathrox.explorercraft.common.world.feature;

import com.mojang.datafixers.Dynamic;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class WildLeekFeature extends Feature<NoFeatureConfig> {

    public WildLeekFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49889_1_) {
        super(p_i49889_1_);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        BlockState blockstate = ExplorerBlocks.LEEK_WILD.getDefaultState();
        int i = 0;

        for(int j = 0; j < 64; ++j) {
            BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (worldIn.isAirBlock(blockpos) && blockpos.getY() > 100 && blockpos.getY() < 255 && blockstate.isValidPosition(worldIn, blockpos)) {
                worldIn.setBlockState(blockpos, blockstate, 2);
                ++i;
            }
        }

        return i > 0;
    }
}
