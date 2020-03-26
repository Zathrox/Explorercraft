package com.zathrox.explorercraft.common.world;

import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AtHeight100 extends Placement<FrequencyConfig> {

    public AtHeight100(Function<Dynamic<?>, ? extends FrequencyConfig> p_i51383_1_) {
        super(p_i51383_1_);
    }

    public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generatorIn, Random random, FrequencyConfig configIn, BlockPos pos) {
        return IntStream.range(0, configIn.count).mapToObj((p_215048_2_) -> {
            int i = random.nextInt(16) + pos.getX();
            int j = random.nextInt(16) + pos.getZ();
            int k = 64 + random.nextInt(36);
            return new BlockPos(i, k, j);
        });
    }
}
