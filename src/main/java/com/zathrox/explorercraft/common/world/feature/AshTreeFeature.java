package com.zathrox.explorercraft.common.world.feature;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class AshTreeFeature extends AbstractTreeFeature<NoFeatureConfig> {
    private static final BlockState ASH_LOG = ExplorerBlocks.ASH_LOG.getDefaultState();
    private static final BlockState ASH_LEAVES = ExplorerBlocks.ASH_LEAVES.getDefaultState();
    private Random rand;

    public AshTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn, boolean doBlockNotifyIn) {
        super(configFactoryIn, doBlockNotifyIn);
    }

    private void crossSection(IWorldGenerationReader worldIn, BlockPos pos, float p_208529_3_, MutableBoundingBox p_208529_4_, Set<BlockPos> changedBlocks) {
        int i = (int)((double)p_208529_3_ + 0.618D);

        for(int j = -i; j <= i; ++j) {
            for(int k = -i; k <= i; ++k) {
                if (Math.pow((double)Math.abs(j) + 0.5D, 2.0D) + Math.pow((double)Math.abs(k) + 0.5D, 2.0D) <= (double)(p_208529_3_ * p_208529_3_)) {
                    BlockPos blockpos = pos.add(j, 0, k);
                    if (isAirOrLeaves(worldIn, blockpos)) {
                        this.setLogState(changedBlocks, worldIn, blockpos, ASH_LEAVES, p_208529_4_);
                    }
                }
            }
        }

    }

    private float treeShape(int heightLimit, int y) {
        if ((float)y < (float)heightLimit * 0.3F) {
            return -1.0F;
        } else {
            float f = (float)heightLimit / 2.0F;
            float f1 = f - (float)y;
            float f2 = MathHelper.sqrt(f * f - f1 * f1);
            if (f1 == 0.0F) {
                f2 = f;
            } else if (Math.abs(f1) >= f) {
                return 0.0F;
            }

            return f2 * 0.5F;
        }
    }

    private float foliageShape(int y) {
        if (y >= 0 && y < 5) {
            return y != 0 && y != 4 ? 3.0F : 2.0F;
        } else {
            return -1.0F;
        }
    }

    private void foliageCluster(IWorldGenerationReader worldIn, BlockPos pos, MutableBoundingBox p_202393_3_, Set<BlockPos> changedBlocks) {
        for(int i = 0; i < 5; ++i) {
            this.crossSection(worldIn, pos.up(i), this.foliageShape(i), p_202393_3_, changedBlocks);
        }

    }

    private int makeLimb(Set<BlockPos> p_208523_1_, IWorldGenerationReader worldIn, BlockPos p_208523_3_, BlockPos p_208523_4_, boolean p_208523_5_, MutableBoundingBox p_208523_6_) {
        if (!p_208523_5_ && Objects.equals(p_208523_3_, p_208523_4_)) {
            return -1;
        } else {
            BlockPos blockpos = p_208523_4_.add(-p_208523_3_.getX(), -p_208523_3_.getY(), -p_208523_3_.getZ());
            int i = this.getGreatestDistance(blockpos);
            float f = (float)blockpos.getX() / (float)i;
            float f1 = (float)blockpos.getY() / (float)i;
            float f2 = (float)blockpos.getZ() / (float)i;

            for(int j = 0; j <= i; ++j) {
                BlockPos blockpos1 = p_208523_3_.add((double)(0.5F + (float)j * f), (double)(0.5F + (float)j * f1), (double)(0.5F + (float)j * f2));
                if (p_208523_5_) {
                    this.setLogState(p_208523_1_, worldIn, blockpos1, ASH_LOG.with(LogBlock.AXIS, this.getLoxAxis(p_208523_3_, blockpos1)), p_208523_6_);
                } else if (!func_214587_a(worldIn, blockpos1)) {
                    return j;
                }
            }

            return -1;
        }
    }

    /**
     * Returns the absolute greatest distance in the BlockPos object.
     */
    private int getGreatestDistance(BlockPos posIn) {
        int i = MathHelper.abs(posIn.getX());
        int j = MathHelper.abs(posIn.getY());
        int k = MathHelper.abs(posIn.getZ());
        if (k > i && k > j) {
            return k;
        } else {
            return j > i ? j : i;
        }
    }

    private Direction.Axis getLoxAxis(BlockPos p_197170_1_, BlockPos p_197170_2_) {
        Direction.Axis direction$axis = Direction.Axis.Y;
        int i = Math.abs(p_197170_2_.getX() - p_197170_1_.getX());
        int j = Math.abs(p_197170_2_.getZ() - p_197170_1_.getZ());
        int k = Math.max(i, j);
        if (k > 0) {
            if (i == k) {
                direction$axis = Direction.Axis.X;
            } else if (j == k) {
                direction$axis = Direction.Axis.Z;
            }
        }

        return direction$axis;
    }

    private void makeFoliage(IWorldGenerationReader worldIn, int p_208525_2_, BlockPos pos, List<FoliageCoordinates> p_208525_4_, MutableBoundingBox p_208525_5_, Set<BlockPos> changedBlocks) {
        for(AshTreeFeature.FoliageCoordinates ashtreefeature$foliagecoordinates : p_208525_4_) {
            if (this.trimBranches(p_208525_2_, ashtreefeature$foliagecoordinates.getBranchBase() - pos.getY())) {
                this.foliageCluster(worldIn, ashtreefeature$foliagecoordinates, p_208525_5_, changedBlocks);
            }
        }

    }

    private boolean trimBranches(int p_208522_1_, int p_208522_2_) {
        return (double)p_208522_2_ >= (double)p_208522_1_ * 0.2D;
    }

    private void makeTrunk(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, BlockPos pillarPos, int height, MutableBoundingBox p_208526_5_) {
        this.makeLimb(changedBlocks, worldIn, pillarPos, pillarPos.up(height), true, p_208526_5_);
        this.makeLimb(changedBlocks, worldIn, pillarPos.east(), pillarPos.east().up(height), true, p_208526_5_);
        this.makeLimb(changedBlocks, worldIn, pillarPos.east().south(), pillarPos.east().south().up(height), true, p_208526_5_);
        this.makeLimb(changedBlocks, worldIn, pillarPos.south(), pillarPos.south().up(height), true, p_208526_5_);
    }

    private void makeTrunk2(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, BlockPos pillarPos, int height, MutableBoundingBox p_208526_5_, Random rand) {

        this.makeLimb(changedBlocks, worldIn, pillarPos.north(), pillarPos.north().up(rand.nextInt(height)+1), true, p_208526_5_);
        this.makeLimb(changedBlocks, worldIn, pillarPos.east().north(), pillarPos.east().north().up(rand.nextInt(height)+1), true, p_208526_5_);
        this.makeLimb(changedBlocks, worldIn, pillarPos.east(2), pillarPos.east(2).up(rand.nextInt(height)+1), true, p_208526_5_);
        this.makeLimb(changedBlocks, worldIn, pillarPos.east(2).south(), pillarPos.east(2).south().up(rand.nextInt(height)+1), true, p_208526_5_);
        this.makeLimb(changedBlocks, worldIn, pillarPos.west(), pillarPos.west().up(rand.nextInt(height)+1), true, p_208526_5_);
        this.makeLimb(changedBlocks, worldIn, pillarPos.west().south(), pillarPos.west().south().up(rand.nextInt(height)+1), true, p_208526_5_);
        this.makeLimb(changedBlocks, worldIn, pillarPos.south(2), pillarPos.south(2).up(rand.nextInt(height)+1), true, p_208526_5_);
        this.makeLimb(changedBlocks, worldIn, pillarPos.south(2).east(), pillarPos.south(2).east().up(rand.nextInt(height)+1), true, p_208526_5_);


    }

    private void makeBranches(Set<BlockPos> p_208524_1_, IWorldGenerationReader p_208524_2_, int p_208524_3_, BlockPos p_208524_4_, List<AshTreeFeature.FoliageCoordinates> p_208524_5_, MutableBoundingBox p_208524_6_) {

        for(AshTreeFeature.FoliageCoordinates ashtreefeature$foliagecoordinates : p_208524_5_) {
            int i = ashtreefeature$foliagecoordinates.getBranchBase();
            BlockPos blockpos = new BlockPos(p_208524_4_.getX(), i, p_208524_4_.getZ());
            if (!blockpos.equals(ashtreefeature$foliagecoordinates) && this.trimBranches(p_208524_3_, i - p_208524_4_.getY())) {
                this.makeLimb(p_208524_1_, p_208524_2_, blockpos, ashtreefeature$foliagecoordinates, true, p_208524_6_);
            }
        }

    }

    public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox boundsIn) {
        Random random = new Random(rand.nextLong());
        int i = this.checkLocation(changedBlocks, worldIn, position, 20 + random.nextInt(12), boundsIn);
        int m = this.checkLocation(changedBlocks, worldIn, position, 4, boundsIn);
        if (i == -1 || m == -1) {
            return false;
        } else {
            BlockPos dirtPost = position.down();
            this.setDirtAt(worldIn, dirtPost, position);
            this.setDirtAt(worldIn, dirtPost.east(), position);
            this.setDirtAt(worldIn, dirtPost.south(), position);
            this.setDirtAt(worldIn, dirtPost.south().east(), position);
            int j = (int)((double)i * 0.618D);
            if (j >= i) {
                j = i - 1;
            }

            double d0 = 1.0D;
            int k = (int)(1.382D + Math.pow(1.0D * (double)i / 13.0D, 2.0D));
            if (k < 1) {
                k = 1;
            }

            int l = position.getY() + j;
            int i1 = i - 5;
            List<AshTreeFeature.FoliageCoordinates> list = Lists.newArrayList();
            list.add(new AshTreeFeature.FoliageCoordinates(position.up(i1), l));

            for(; i1 >= 0; --i1) {
                float f = this.treeShape(i, i1);
                if (!(f < 0.0F)) {
                    for(int j1 = 0; j1 < k; ++j1) {
                        double d1 = 1.0D;
                        double d2 = 1.0D * (double)f * ((double)random.nextFloat() + 0.328D);
                        double d3 = (double)(random.nextFloat() * 2.0F) * Math.PI;
                        double d4 = d2 * Math.sin(d3) + 0.5D;
                        double d5 = d2 * Math.cos(d3) + 0.5D;
                        BlockPos blockpos = position.add(d4, (double)(i1 - 1), d5);
                        BlockPos blockpos1 = blockpos.up(5);
                        if (this.makeLimb(changedBlocks, worldIn, blockpos, blockpos1, false, boundsIn) == -1) {
                            int k1 = position.getX() - blockpos.getX();
                            int l1 = position.getZ() - blockpos.getZ();
                            double d6 = (double)blockpos.getY() - Math.sqrt((double)(k1 * k1 + l1 * l1)) * 0.681D;
                            int i2 = d6 > (double)l ? l : (int)d6;
                            BlockPos blockpos2 = new BlockPos(position.getX(), i2, position.getZ());
                            if (this.makeLimb(changedBlocks, worldIn, blockpos2, blockpos, false, boundsIn) == -1) {
                                list.add(new AshTreeFeature.FoliageCoordinates(blockpos, blockpos2.getY()));
                            }
                        }
                    }
                }
            }



            this.makeFoliage(worldIn, i, position, list, boundsIn, changedBlocks);
            this.makeTrunk(changedBlocks, worldIn, position, j, boundsIn);
            this.makeTrunk2(changedBlocks, worldIn, position, m, boundsIn, rand);
            this.makeBranches(changedBlocks, worldIn, i, position, list, boundsIn);
            return true;
        }
    }

    private int checkLocation(Set<BlockPos> p_208528_1_, IWorldGenerationReader worldIn, BlockPos position, int height, MutableBoundingBox p_208528_5_) {

        BlockPos worldHeight = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE, position);
        if (worldHeight.getY() <= 100) {
            return -1;
        }
        BlockPos down = position.down();
        int l2 = 1;
        for (int i3 = down.getX()- l2; i3 <= down.getX()+1 + l2; ++i3)
        {
            for (int k1 = down.getZ()- l2; k1 <= down.getZ()+1 + l2; ++k1)
            {
                BlockPos soilPos = new BlockPos(i3, down.getY(), k1);
                if (!isSoilOrFarm(worldIn, soilPos, getSapling())) {
                    return -1;
                }
            }
        }
        if (!isSoilOrFarm(worldIn, position.down(), getSapling()))
        {
            return -1;
        } else {
            int i = this.makeLimb(p_208528_1_, worldIn, position, position.up(height - 1), false, p_208528_5_);
            if (i == -1) {
                return height;
            } else {
                return i < 6 ? -1 : i;
            }
        }
    }

    static class FoliageCoordinates extends BlockPos {
        private final int branchBase;

        public FoliageCoordinates(BlockPos pos, int p_i45635_2_) {
            super(pos.getX(), pos.getY(), pos.getZ());
            this.branchBase = p_i45635_2_;
        }

        public int getBranchBase() {
            return this.branchBase;
        }
    }
}