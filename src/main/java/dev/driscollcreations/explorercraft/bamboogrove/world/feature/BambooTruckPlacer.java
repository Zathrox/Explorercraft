package dev.driscollcreations.explorercraft.bamboogrove.world.feature;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class BambooTruckPlacer extends TrunkPlacer {
    public static final Codec<BambooTruckPlacer> CODEC = RecordCodecBuilder.create((p_236904_0_) -> {
        return trunkPlacerParts(p_236904_0_).apply(p_236904_0_, BambooTruckPlacer::new);
    });

    public BambooTruckPlacer(int p_i232059_1_, int p_i232059_2_, int p_i232059_3_) {
        super(p_i232059_1_, p_i232059_2_, p_i232059_3_);
    }

    protected TrunkPlacerType<?> type() {
        return TrunkPlacerType.STRAIGHT_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        for(int i = 0; i < pFreeTreeHeight; ++i) {
            placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.above(pFreeTreeHeight), 0, false));
    }
}