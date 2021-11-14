package dev.driscollcreations.explorercraft.cymru.world;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiConsumer;

public class AshTreeTrunkPlacer  extends TrunkPlacer {
    public static final Codec<AshTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((p_236883_0_) -> {
        return trunkPlacerParts(p_236883_0_).apply(p_236883_0_, AshTreeTrunkPlacer::new);
    });

    public AshTreeTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    protected TrunkPlacerType<?> type() {
        return TrunkPlacerType.DARK_OAK_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> consumer, Random random, int freeHeight, BlockPos blockPos, TreeConfiguration featureConfig) {
        int j = 20 + random.nextInt(12);
        int k = (int) Math.floor(j * 0.618D);
        BlockPos dirtPos = blockPos.below();
        setDirtAt(reader, consumer, random, dirtPos, featureConfig);
        setDirtAt(reader, consumer, random, dirtPos.east(), featureConfig);
        setDirtAt(reader, consumer, random, dirtPos.south(), featureConfig);
        setDirtAt(reader, consumer, random, dirtPos.south().east(), featureConfig);

        int l = (int) Math.min(1, Math.floor(1.382D + Math.pow(1.0D * (double)j / 13.0D, 2.0D)));
        int i1 = blockPos.getY() + k;
        int j1 = j;
        List<AshTreeTrunkPlacer.FoliageCoords> list = Lists.newArrayList();
        list.add(new AshTreeTrunkPlacer.FoliageCoords(blockPos.above(j1 - 5), i1));

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
                    if (this.makeLimb(reader, consumer, random, blockpos, blockpos1, false, featureConfig)) {
                        int l1 = blockPos.getX() - blockpos.getX();
                        int i2 = blockPos.getZ() - blockpos.getZ();
                        double d6 = (double)blockpos.getY() - Math.sqrt((double)(l1 * l1 + i2 * i2)) * 0.381D;
                        int j2 = d6 > (double)i1 ? i1 : (int)d6;
                        BlockPos blockpos2 = new BlockPos(blockPos.getX(), j2, blockPos.getZ());
                        if (this.makeLimb(reader, consumer, random, blockpos2, blockpos, false, featureConfig)) {
                            list.add(new AshTreeTrunkPlacer.FoliageCoords(blockpos, blockpos2.getY()));
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
                placeLog(reader, consumer, random, trunkPos, featureConfig);
                placeLog(reader, consumer, random, trunkPos.east(),featureConfig);
                placeLog(reader, consumer, random, trunkPos.south(),featureConfig);
                placeLog(reader, consumer, random, trunkPos.east().south(),featureConfig);
            }
        }
        this.makeBranches(reader, consumer, random, j, blockPos, list, featureConfig);
        for(int l2 = -1; l2 <= 2; ++l2) {
            for(int i3 = -1; i3 <= 2; ++i3) {
                if ((l2 < 0 || l2 > 1 || i3 < 0 || i3 > 1)) {
                    if ((l2 != -1 && i3 != -1) || (l2 != 2 && i3 != 2) ) {
                        if ((i3 != -1 && l2 != 2) || (i3 != 2 && l2 != -1)) {
                            int j3 = random.nextInt(4) + 2;
                            BlockPos dirtPos1 = new BlockPos(x + l2, y, z + i3).below();
                            setDirtAt(reader, consumer, random, dirtPos1, featureConfig);
                            for(int k2 = 0; k2 < j3; ++k2) {
                                placeLog(reader, consumer, random, new BlockPos(x + l2, y + k2, z + i3), featureConfig);
                            }
                        }
                    }
                }
            }
        }

        List<FoliagePlacer.FoliageAttachment> list1 = Lists.newArrayList();
        for(AshTreeTrunkPlacer.FoliageCoords fancytrunkplacer$foliage : list) {
            if (this.trimBranches(j, fancytrunkplacer$foliage.getBranchBase() - blockPos.getY())) {
                list1.add(fancytrunkplacer$foliage.attachment);
            }
        }

        return list1;
    }

    private void makeBranches(LevelSimulatedReader p_161808_, BiConsumer<BlockPos, BlockState> p_161809_, Random p_161810_, int p_161811_, BlockPos p_161812_, List<AshTreeTrunkPlacer.FoliageCoords> p_161813_, TreeConfiguration p_161814_) {
        for(AshTreeTrunkPlacer.FoliageCoords ashtreetrunkplacer$foliagecoords : p_161813_) {
            int i = ashtreetrunkplacer$foliagecoords.getBranchBase();
            BlockPos blockpos = new BlockPos(p_161812_.getX(), i, p_161812_.getZ());
            if (!blockpos.equals(ashtreetrunkplacer$foliagecoords.attachment.pos()) && this.trimBranches(p_161811_, i - p_161812_.getY())) {
                this.makeLimb(p_161808_, p_161809_, p_161810_, blockpos, ashtreetrunkplacer$foliagecoords.attachment.pos(), true, p_161814_);
            }
        }

    }

    private float treeShape(int p_236890_1_, int p_236890_2_) {
        if ((float)p_236890_2_ < (float)p_236890_1_ * 0.3F) {
            return -1.0F;
        } else {
            float f = (float)p_236890_1_ / 2.0F;
            float f1 = f - (float)p_236890_2_;
            float f2 = (float) Math.sqrt(f * f - f1 * f1);
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

    private boolean makeLimb(LevelSimulatedReader p_236887_1_, BiConsumer<BlockPos, BlockState> p_161817_, Random p_236887_2_, BlockPos p_236887_3_, BlockPos p_236887_4_, boolean p_236887_5_, TreeConfiguration p_236887_8_) {
        if (!p_236887_5_ && Objects.equals(p_236887_3_, p_236887_4_)) {
            return true;
        } else {
            BlockPos blockpos = p_236887_4_.offset(-p_236887_3_.getX(), -p_236887_3_.getY(), -p_236887_3_.getZ());
            int i = this.getSteps(blockpos);
            float f = (float)blockpos.getX() / (float)i;
            float f1 = (float)blockpos.getY() / (float)i;
            float f2 = (float)blockpos.getZ() / (float)i;

            for(int j = 0; j <= i; ++j) {
                BlockPos blockpos1 = p_236887_3_.offset(0.5F + (float)j * f, 0.5F + (float)j * f1, 0.5F + (float)j * f2);
                if (p_236887_5_) {
                    TrunkPlacer.placeLog(p_236887_1_, p_161817_, p_236887_2_, blockpos1, p_236887_8_, (p_161826_) -> {
                        return p_161826_.setValue(RotatedPillarBlock.AXIS, this.getLogAxis(p_236887_3_, blockpos1));
                    });
                } else if (!TreeFeature.isFree(p_236887_1_, blockpos1)) {
                    return false;
                }
            }
            return true;
        }
    }

    private int getSteps(BlockPos p_236888_1_) {
        int i = Math.abs(p_236888_1_.getX());
        int j = Math.abs(p_236888_1_.getY());
        int k = Math.abs(p_236888_1_.getZ());
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

    static class FoliageCoords {
        final FoliagePlacer.FoliageAttachment attachment;
        private final int branchBase;

        public FoliageCoords(BlockPos pAttachmentPos, int pBranchBase) {
            this.attachment = new FoliagePlacer.FoliageAttachment(pAttachmentPos, 0, false);
            this.branchBase = pBranchBase;
        }

        public int getBranchBase() {
            return this.branchBase;
        }
    }
}
