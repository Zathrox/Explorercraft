//package dev.driscollcreations.explorercraft.cymru.world;
//
//import NopePlacementDecorator;
//import com.mojang.serialization.Codec;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.gen.feature.LevelDecoratingHelper;
//import net.minecraft.world.gen.placement.HeightmapBasedPlacement;
//
//import java.util.Random;
//import java.util.stream.Stream;
//
//public abstract class HigherThan100Placement<DC extends NopePlacementDecorator> extends HeightmapBasedPlacement<DC> {
//
//    public HigherThan100Placement(Codec<DC> codec) {
//        super(codec);
//    }
//
//    public Stream<BlockPos> getPositions(LevelDecoratingHelper decoratingHelper, Random random, DC dc, BlockPos blockPos) {
//        int i = blockPos.getX();
//        int j = blockPos.getZ();
//        int k = decoratingHelper.getHeight(this.type(dc), i, j);
//        return k > 100 ? Stream.of(new BlockPos(i, k, j)) : Stream.of();
//    }
//}