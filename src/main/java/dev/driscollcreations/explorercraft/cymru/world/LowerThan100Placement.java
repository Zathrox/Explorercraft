package dev.driscollcreations.explorercraft.cymru.world;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.configurations.HeightmapConfiguration;
import net.minecraft.world.level.levelgen.placement.DecorationContext;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

import java.util.Random;
import java.util.stream.Stream;

public class LowerThan100Placement extends FeatureDecorator<HeightmapConfiguration> {
    public LowerThan100Placement(Codec<HeightmapConfiguration> p_70747_) {
        super(p_70747_);
    }

    /**
     * Applies this decorator to the given position, and returns a stream of (potentially multiple) possible decorated
     * positions.
     */
    public Stream<BlockPos> getPositions(DecorationContext p_162193_, Random p_162194_, HeightmapConfiguration p_162195_, BlockPos p_162196_) {
        int i = p_162196_.getX();
        int j = p_162196_.getZ();
        int k = p_162193_.getHeight(p_162195_.heightmap, i, j);
        return k < 100 ? Stream.of(new BlockPos(i, k, j)) : Stream.of();
    }
}