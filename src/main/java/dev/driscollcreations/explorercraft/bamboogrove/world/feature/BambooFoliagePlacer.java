package dev.driscollcreations.explorercraft.bamboogrove.world.feature;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.driscollcreations.explorercraft.setup.ExplorerFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.Random;
import java.util.function.BiConsumer;

public class BambooFoliagePlacer extends FoliagePlacer {

    public static final Codec<BambooFoliagePlacer> CODEC = RecordCodecBuilder.create((p_68427_) -> {
        return blobParts(p_68427_).apply(p_68427_, BambooFoliagePlacer::new);
    });

    protected final int height;

    protected static <P extends BambooFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, IntProvider, IntProvider, Integer> blobParts(RecordCodecBuilder.Instance<P> p_68414_) {
        return foliagePlacerParts(p_68414_).and(Codec.intRange(0, 16).fieldOf("height").forGetter((p_68412_) -> {
            return p_68412_.height;
        }));
    }

    public BambooFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    protected FoliagePlacerType<?> type() {
        return ExplorerFeature.BAMBOO_FOLIAGE_TYPE.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, TreeConfiguration pConfig, int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {
        for (int i = pOffset; i >= pOffset - pFoliageHeight; --i) {
            int j = Math.max(pFoliageRadius + pAttachment.radiusOffset() - 1 / 2, 0);
            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos(), j, i, pAttachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(Random random, int height, TreeConfiguration config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(Random random, int j, int offset, int k, int radius, boolean isDoubleTrunk) {
        return j == radius && k == radius && (random.nextInt(2) == 0 || offset == 0);
    }

//    @Override
//    protected void placeLeavesRow(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, Random pRandom, TreeConfiguration pConfig, BlockPos pPos, int pRange, int pYOffset, boolean pLarge) {
//        super.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pPos, pRange, pYOffset, pLarge);
//    }
//
//    @Override
//    protected void placeLeavesRow(LevelAccessor world, Random random, TreeConfiguration config, BlockPos blockPos, int radius, Set<BlockPos> set, int offset, boolean isDoubleTrunk, MutableBoundingBox boundingBox) {
//        int i = isDoubleTrunk ? 1 : 0;
//        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
//
//        for(int j = -radius; j <= radius + i; ++j) {
//            for(int k = -radius; k <= radius + i; ++k) {
//                if (!this.shouldSkipLocationSigned(random, j, offset, k, radius, isDoubleTrunk)) {
//                    blockpos$mutable.setWithOffset(blockPos, j, offset, k);
//                    if (TreeFeature.validTreePos(world, blockpos$mutable)) {
//                        world.setBlock(blockpos$mutable, config.leavesProvider.getState(random, blockpos$mutable), 19);
//                        config.leavesProvider.getState(random, blockpos$mutable).updateNeighbourShapes((LevelAccessor) world, blockpos$mutable, 3);
//                        boundingBox.expand(new MutableBoundingBox(blockpos$mutable, blockpos$mutable));
//                        set.add(blockpos$mutable.immutable());
//                    }
//                }
//            }
//        }
//
//    }
}
