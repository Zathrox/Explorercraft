package dev.driscollcreations.explorercraft.cymru.world;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.HeightmapBasedPlacement;
import net.minecraft.world.gen.placement.IPlacementConfig;

import java.util.Random;
import java.util.stream.Stream;

public abstract class LowerThan100Placement<DC extends IPlacementConfig> extends HeightmapBasedPlacement<DC> {

    public LowerThan100Placement(Codec<DC> codec) {
        super(codec);
    }

    public Stream<BlockPos> getPositions(WorldDecoratingHelper decoratingHelper, Random random, DC dc, BlockPos blockPos) {
        int i = blockPos.getX();
        int j = blockPos.getZ();
        int k = decoratingHelper.getHeight(this.type(dc), i, j);
        return k < 100 ? Stream.of(new BlockPos(i, k, j)) : Stream.of();
    }
}