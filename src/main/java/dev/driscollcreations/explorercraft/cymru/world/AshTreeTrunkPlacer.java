package dev.driscollcreations.explorercraft.cymru.world;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class AshTreeTrunkPlacer  extends AbstractTrunkPlacer {
    public static final Codec<AshTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((p_236883_0_) -> {
        return trunkPlacerParts(p_236883_0_).apply(p_236883_0_, AshTreeTrunkPlacer::new);
    });

    public AshTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    protected TrunkPlacerType<?> type() {
        return TrunkPlacerType.DARK_OAK_TRUNK_PLACER;
    }


    public List<FoliagePlacer.Foliage> placeTrunk(IWorldGenerationReader reader, Random random, int freeHeight, BlockPos blockPos, Set<BlockPos> blockPosSet, MutableBoundingBox boundingBox, BaseTreeFeatureConfig featureConfig) {

        int j = 20 + random.nextInt(12);
        int k = MathHelper.floor((double)j * 0.618D);
        if (!featureConfig.fromSapling) {
            BlockPos dirtPos = blockPos.below();
            setDirtAt(reader, dirtPos);
            setDirtAt(reader, dirtPos.east());
            setDirtAt(reader, dirtPos.south());
            setDirtAt(reader, dirtPos.south().east());
        }

        int l = Math.min(1, MathHelper.floor(1.382D + Math.pow(1.0D * (double)j / 13.0D, 2.0D)));
        int i1 = blockPos.getY() + k;
        int j1 = j;
        List<Foliage> list = Lists.newArrayList();
        list.add(new Foliage(blockPos.above(j1 - 5), i1));

        for(; j1 >= 0; --j1) {
            float f = this.treeShape(j, j1);
            if (!(f < 0.0F)) {
                for(int k1 = 0; k1 < l; ++k1) {
                    double d1 = 1.0D;
                    double d2 = 1.0D * (double)f * ((double)random.nextFloat() + 0.328D);
                    double d3 = (double)(random.nextFloat() * 2.0F) * Math.PI;
                    double d4 = d2 * Math.sin(d3) + 0.5D;
                    double d5 = d2 * Math.cos(d3) + 0.5D;
                    BlockPos blockpos = blockPos.offset(d4, (double)(j1 - 1), d5);
                    BlockPos blockpos1 = blockpos.above(5);
                    if (this.makeLimb(reader, random, blockpos, blockpos1, false, blockPosSet, boundingBox, featureConfig)) {
                        int l1 = blockPos.getX() - blockpos.getX();
                        int i2 = blockPos.getZ() - blockpos.getZ();
                        double d6 = (double)blockpos.getY() - Math.sqrt((double)(l1 * l1 + i2 * i2)) * 0.381D;
                        int j2 = d6 > (double)i1 ? i1 : (int)d6;
                        BlockPos blockpos2 = new BlockPos(blockPos.getX(), j2, blockPos.getZ());
                        if (this.makeLimb(reader, random, blockpos2, blockpos, false, blockPosSet, boundingBox, featureConfig)) {
                            list.add(new Foliage(blockpos, blockpos2.getY()));
                        }
                    }
                }
            }
        }

        int x = blockPos.getX();
        int y = blockPos.getY();
        int z = blockPos.getZ();
        for(int i2 = 0; i2 < j; ++i2) {
            int j2 = y + i2;
            BlockPos trunkPos = new BlockPos(x, j2, z);
            if (TreeFeature.isAirOrLeaves(reader, trunkPos)) {
                placeLog(reader, random, trunkPos, blockPosSet, boundingBox, featureConfig);
                placeLog(reader, random, trunkPos.east(), blockPosSet, boundingBox, featureConfig);
                placeLog(reader, random, trunkPos.south(), blockPosSet, boundingBox, featureConfig);
                placeLog(reader, random, trunkPos.east().south(), blockPosSet, boundingBox, featureConfig);
            }
        }
        this.makeBranches(reader, random, j, blockPos, list, blockPosSet, boundingBox, featureConfig);
        for(int l2 = -1; l2 <= 2; ++l2) {
            for(int i3 = -1; i3 <= 2; ++i3) {
                if ((l2 < 0 || l2 > 1 || i3 < 0 || i3 > 1)) {
                    if ((l2 != -1 && i3 != -1) || (l2 != 2 && i3 != 2) ) {
                        if ((i3 != -1 && l2 != 2) || (i3 != 2 && l2 != -1)) {
                            int j3 = random.nextInt(4) + 2;
                            for(int k2 = 0; k2 < j3; ++k2) {
                                placeLog(reader, random, new BlockPos(x + l2, y + k2, z + i3), blockPosSet, boundingBox, featureConfig);
                            }
                        }
                    }
                }
            }
        }

        List<FoliagePlacer.Foliage> list1 = Lists.newArrayList();
        for(Foliage fancytrunkplacer$foliage : list) {
            if (this.trimBranches(j, fancytrunkplacer$foliage.getBranchBase() - blockPos.getY())) {
                list1.add(fancytrunkplacer$foliage.attachment);
            }
        }

        return list1;
    }

    private void makeBranches(IWorldGenerationReader p_236886_1_, Random p_236886_2_, int p_236886_3_, BlockPos p_236886_4_, List<Foliage> p_236886_5_, Set<BlockPos> p_236886_6_, MutableBoundingBox p_236886_7_, BaseTreeFeatureConfig p_236886_8_) {
        for(Foliage fancytrunkplacer$foliage : p_236886_5_) {
            int i = fancytrunkplacer$foliage.getBranchBase();
            BlockPos blockpos = new BlockPos(p_236886_4_.getX(), i, p_236886_4_.getZ());
            if (!blockpos.equals(fancytrunkplacer$foliage.attachment.foliagePos()) && this.trimBranches(p_236886_3_, i - p_236886_4_.getY())) {
                this.makeLimb(p_236886_1_, p_236886_2_, blockpos, fancytrunkplacer$foliage.attachment.foliagePos(), true, p_236886_6_, p_236886_7_, p_236886_8_);
            }
        }

    }

    private float treeShape(int p_236890_1_, int p_236890_2_) {
        if ((float)p_236890_2_ < (float)p_236890_1_ * 0.3F) {
            return -1.0F;
        } else {
            float f = (float)p_236890_1_ / 2.0F;
            float f1 = f - (float)p_236890_2_;
            float f2 = MathHelper.sqrt(f * f - f1 * f1);
            if (f1 == 0.0F) {
                f2 = f;
            } else if (Math.abs(f1) >= f) {
                return 0.0F;
            }

            return f2 * 0.5F;
        }
    }

    private boolean trimBranches(int p_236885_1_, int p_236885_2_) {
        return (double)p_236885_2_ >= (double)p_236885_1_ * 0.2D;
    }

    private boolean makeLimb(IWorldGenerationReader p_236887_1_, Random p_236887_2_, BlockPos p_236887_3_, BlockPos p_236887_4_, boolean p_236887_5_, Set<BlockPos> p_236887_6_, MutableBoundingBox p_236887_7_, BaseTreeFeatureConfig p_236887_8_) {
        if (!p_236887_5_ && Objects.equals(p_236887_3_, p_236887_4_)) {
            return true;
        } else {
            BlockPos blockpos = p_236887_4_.offset(-p_236887_3_.getX(), -p_236887_3_.getY(), -p_236887_3_.getZ());
            int i = this.getSteps(blockpos);
            float f = (float)blockpos.getX() / (float)i;
            float f1 = (float)blockpos.getY() / (float)i;
            float f2 = (float)blockpos.getZ() / (float)i;

            for(int j = 0; j <= i; ++j) {
                BlockPos blockpos1 = p_236887_3_.offset((double)(0.5F + (float)j * f), (double)(0.5F + (float)j * f1), (double)(0.5F + (float)j * f2));
                if (p_236887_5_) {
                    setBlock(p_236887_1_, blockpos1, p_236887_8_.trunkProvider.getState(p_236887_2_, blockpos1).setValue(RotatedPillarBlock.AXIS, this.getLogAxis(p_236887_3_, blockpos1)), p_236887_7_);
                    p_236887_6_.add(blockpos1.immutable());
                } else if (!TreeFeature.isFree(p_236887_1_, blockpos1)) {
                    return false;
                }
            }

            return true;
        }
    }

    private int getSteps(BlockPos p_236888_1_) {
        int i = MathHelper.abs(p_236888_1_.getX());
        int j = MathHelper.abs(p_236888_1_.getY());
        int k = MathHelper.abs(p_236888_1_.getZ());
        return Math.max(i, Math.max(j, k));
    }

    private Direction.Axis getLogAxis(BlockPos p_236889_1_, BlockPos p_236889_2_) {
        Direction.Axis direction$axis = Direction.Axis.Y;
        int i = Math.abs(p_236889_2_.getX() - p_236889_1_.getX());
        int j = Math.abs(p_236889_2_.getZ() - p_236889_1_.getZ());
        int k = Math.max(i, j);
        if (k > 0) {
            if (i == k) {
                direction$axis = Direction.Axis.X;
            } else {
                direction$axis = Direction.Axis.Z;
            }
        }

        return direction$axis;
    }

    static class Foliage {
        private final FoliagePlacer.Foliage attachment;
        private final int branchBase;

        public Foliage(BlockPos p_i232055_1_, int p_i232055_2_) {
            this.attachment = new FoliagePlacer.Foliage(p_i232055_1_, 0, false);
            this.branchBase = p_i232055_2_;
        }

        public int getBranchBase() {
            return this.branchBase;
        }
    }
}
