package dev.driscollcreations.explorercraft.bamboogrove.world.feature;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.driscollcreations.explorercraft.setup.ExplorerFeature;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.Random;
import java.util.Set;

public class BambooFoliagePlacer extends FoliagePlacer {

    public static final Codec<BambooFoliagePlacer> CODEC = RecordCodecBuilder.create((p_236742_0_) -> {
        return blobParts(p_236742_0_).apply(p_236742_0_, BambooFoliagePlacer::new);
    });

    protected final int height;

    protected static <P extends BambooFoliagePlacer> Products.P3<RecordCodecBuilder.Mu<P>, FeatureSpread, FeatureSpread, Integer> blobParts(RecordCodecBuilder.Instance<P> p_236740_0_) {
        return foliagePlacerParts(p_236740_0_).and(Codec.intRange(0, 16).fieldOf("height").forGetter((p_236741_0_) -> {
            return p_236741_0_.height;
        }));
    }

    public BambooFoliagePlacer(FeatureSpread radius, FeatureSpread offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    protected FoliagePlacerType<?> type() {
        return ExplorerFeature.BAMBOO_FOLIAGE_TYPE.get();
    }

    @Override
    protected void createFoliage(IWorldGenerationReader worldGenerationReader, Random random, BaseTreeFeatureConfig treeFeatureConfig, int treeHeight, FoliagePlacer.Foliage foliage, int foilageHeight, int radius, Set<BlockPos> set, int offset, MutableBoundingBox boundingBox) {
        for (int i = offset; i >= offset - foilageHeight; --i) {
            int j = Math.max(radius + foliage.radiusOffset() - 1 / 2, 0);
            this.placeLeavesRow(worldGenerationReader, random, treeFeatureConfig, foliage.foliagePos(), j, set, i, foliage.doubleTrunk(), boundingBox);
        }

    }

    @Override
    public int foliageHeight(Random random, int height, BaseTreeFeatureConfig config) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(Random random, int j, int offset, int k, int radius, boolean isDoubleTrunk) {
        return j == radius && k == radius && (random.nextInt(2) == 0 || offset == 0);
    }

    @Override
    protected void placeLeavesRow(IWorldGenerationReader world, Random random, BaseTreeFeatureConfig config, BlockPos blockPos, int radius, Set<BlockPos> set, int offset, boolean isDoubleTrunk, MutableBoundingBox boundingBox) {
        int i = isDoubleTrunk ? 1 : 0;
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int j = -radius; j <= radius + i; ++j) {
            for(int k = -radius; k <= radius + i; ++k) {
                if (!this.shouldSkipLocationSigned(random, j, offset, k, radius, isDoubleTrunk)) {
                    blockpos$mutable.setWithOffset(blockPos, j, offset, k);
                    if (TreeFeature.validTreePos(world, blockpos$mutable)) {
                        world.setBlock(blockpos$mutable, config.leavesProvider.getState(random, blockpos$mutable), 19);
                        config.leavesProvider.getState(random, blockpos$mutable).updateNeighbourShapes((IWorld) world, blockpos$mutable, 3);
                        boundingBox.expand(new MutableBoundingBox(blockpos$mutable, blockpos$mutable));
                        set.add(blockpos$mutable.immutable());
                    }
                }
            }
        }

    }
}
