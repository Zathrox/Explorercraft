//package dev.driscollcreations.explorercraft.vanillatweaks.world.feature;
//
//import dev.driscollcreations.explorercraft.Explorercraft;
//import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.core.BlockPos;
//import net.minecraft.core.Direction;
//import net.minecraft.tags.BlockTags;
//import net.minecraft.util.Direction;
//import net.minecraft.util.SharedSeedRandom;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.ChunkPos;
//import net.minecraft.util.math.Math;
//import net.minecraft.world.ISeedReader;
//import net.minecraft.world.LevelAccessor;
//import net.minecraft.world.gen.ChunkGenerator;
//import net.minecraft.world.gen.Heightmap;
//import net.minecraft.world.gen.feature.Feature;
//import net.minecraft.world.gen.feature.NoneFeatureConfiguration;
//import net.minecraft.world.gen.feature.OreFeatureConfig;
//import net.minecraft.world.gen.feature.template.RuleTest;
//import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.level.levelgen.feature.Feature;
//import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
//import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
//import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
//import org.apache.logging.log4j.Level;
//
//import java.util.BitSet;
//import java.util.Random;
//
//public class SlimeBlockFeature extends Feature<NoneFeatureConfiguration> {
//
//    public SlimeBlockFeature() {
//        super(NoneFeatureConfiguration.CODEC);
//    }
//
//    public static final RuleTest NATURAL_STONE = new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD);
//
//    @Override
//    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos position, NoneFeatureConfiguration config) {
//
//
//        ChunkPos chunkpos = new ChunkPos(position);
//        boolean flag = SharedSeedRandom.seedSlimeChunk(chunkpos.x, chunkpos.z, world.getSeed(), 987234911L).nextInt(10) == 0;
//        if (random.nextInt(10) == 0 && flag) {
//            float f = random.nextFloat() * (float)Math.PI;
//            float f1 = (float)33 / 8.0F;
//            int i = Math.ceil(((float)33 / 16.0F * 2.0F + 1.0F) / 2.0F);
//            double d0 = (double)position.getX() + Math.sin(f) * (double)f1;
//            double d1 = (double)position.getX() - Math.sin(f) * (double)f1;
//            double d2 = (double)position.getZ() + Math.cos(f) * (double)f1;
//            double d3 = (double)position.getZ() - Math.cos(f) * (double)f1;
//            int j = 2;
//            double d4 = (position.getY() + random.nextInt(3) - 2);
//            double d5 = (position.getY() + random.nextInt(3) - 2);
//            int k = position.getX() - Math.ceil(f1) - i;
//            int l = position.getY() - 2 - i;
//            int i1 = position.getZ() - Math.ceil(f1) - i;
//            int j1 = 2 * (Math.ceil(f1) + i);
//            int k1 = 2 * (2 + i);
//
//            for(int l1 = k; l1 <= k + j1; ++l1) {
//                for(int i2 = i1; i2 <= i1 + j1; ++i2) {
//                    if (l <= world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, l1, i2)) {
//                        return this.doPlace(world, random, d0, d1, d2, d3, d4, d5, k, l, i1, j1, k1);
//                    }
//                }
//            }
//        }
//
//        return false;
//    }
//
//    protected boolean doPlace(LevelAccessor world, Random random, double p_207803_4_, double p_207803_6_, double p_207803_8_, double p_207803_10_, double p_207803_12_, double p_207803_14_, int p_207803_16_, int p_207803_17_, int p_207803_18_, int p_207803_19_, int p_207803_20_) {
//        int i = 0;
//        BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
//        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
//        int j = 33;
//        double[] adouble = new double[j * 4];
//
//        for(int k = 0; k < j; ++k) {
//            float f = (float)k / (float)j;
//            double d0 = Math.lerp(f, p_207803_4_, p_207803_6_);
//            double d2 = Math.lerp(f, p_207803_12_, p_207803_14_);
//            double d4 = Math.lerp(f, p_207803_8_, p_207803_10_);
//            double d6 = random.nextDouble() * (double)j / 16.0D;
//            double d7 = ((double)(Math.sin((float)Math.PI * f) + 1.0F) * d6 + 1.0D) / 2.0D;
//            adouble[k * 4 + 0] = d0;
//            adouble[k * 4 + 1] = d2;
//            adouble[k * 4 + 2] = d4;
//            adouble[k * 4 + 3] = d7;
//        }
//
//        for(int i3 = 0; i3 < j - 1; ++i3) {
//            if (!(adouble[i3 * 4 + 3] <= 0.0D)) {
//                for(int k3 = i3 + 1; k3 < j; ++k3) {
//                    if (!(adouble[k3 * 4 + 3] <= 0.0D)) {
//                        double d12 = adouble[i3 * 4 + 0] - adouble[k3 * 4 + 0];
//                        double d13 = adouble[i3 * 4 + 1] - adouble[k3 * 4 + 1];
//                        double d14 = adouble[i3 * 4 + 2] - adouble[k3 * 4 + 2];
//                        double d15 = adouble[i3 * 4 + 3] - adouble[k3 * 4 + 3];
//                        if (d15 * d15 > d12 * d12 + d13 * d13 + d14 * d14) {
//                            if (d15 > 0.0D) {
//                                adouble[k3 * 4 + 3] = -1.0D;
//                            } else {
//                                adouble[i3 * 4 + 3] = -1.0D;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        for(int j3 = 0; j3 < j; ++j3) {
//            double d11 = adouble[j3 * 4 + 3];
//            if (!(d11 < 0.0D)) {
//                double d1 = adouble[j3 * 4 + 0];
//                double d3 = adouble[j3 * 4 + 1];
//                double d5 = adouble[j3 * 4 + 2];
//                double l = Math.max(Math.floor(d1 - d11), p_207803_16_);
//                double l3 = Math.max(Math.floor(d3 - d11), p_207803_17_);
//                double i1 = Math.max(Math.floor(d5 - d11), p_207803_18_);
//                double j1 = Math.max(Math.floor(d1 + d11), l);
//                double k1 = Math.max(Math.floor(d3 + d11), l3);
//                double l1 = Math.max(Math.floor(d5 + d11), i1);
//
//                for(int i2 = l; i2 <= j1; ++i2) {
//                    double d8 = ((double)i2 + 0.5D - d1) / d11;
//                    if (d8 * d8 < 1.0D) {
//                        for(int j2 = l3; j2 <= k1; ++j2) {
//                            double d9 = ((double)j2 + 0.5D - d3) / d11;
//                            if (d8 * d8 + d9 * d9 < 1.0D) {
//                                for(int k2 = i1; k2 <= l1; ++k2) {
//                                    double d10 = ((double)k2 + 0.5D - d5) / d11;
//                                    if (d8 * d8 + d9 * d9 + d10 * d10 < 1.0D) {
//                                        int l2 = i2 - p_207803_16_ + (j2 - p_207803_17_) * p_207803_19_ + (k2 - p_207803_18_) * p_207803_19_ * p_207803_20_;
//                                        if (!bitset.get(l2)) {
//                                            bitset.set(l2);
//                                            blockpos$mutable.set(i2, j2, k2);
//                                            for(Direction direction : Direction.Plane.HORIZONTAL) {
//                                                BlockPos offset = blockpos$mutable.relative(direction);
//                                                if (NATURAL_STONE.test(world.getBlockState(blockpos$mutable), random) && world.isEmptyBlock(offset)) {
//                                                    world.setBlock(blockpos$mutable, getBlock(random), 2);
//                                                    ++i;
//                                                }
//                                            }
//                                            for(Direction direction : Direction.Plane.VERTICAL) {
//                                                BlockPos offset = blockpos$mutable.relative(direction);
//                                                if (NATURAL_STONE.test(world.getBlockState(blockpos$mutable), random) && world.isEmptyBlock(offset)) {
//                                                    world.setBlock(blockpos$mutable, getBlock(random), 2);
//                                                    ++i;
//                                                }
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return i > 0;
//    }
//
//
//    public BlockState getBlock(Random random) {
//        if (random.nextInt(15) == 0) {
//            return Blocks.SLIME_BLOCK.defaultBlockState();
//        } else if (random.nextInt(6) == 1) {
//            return VanillaTweaksBlocks.DISSOLVED_STONE.get().defaultBlockState();
//        } else {
//            return VanillaTweaksBlocks.SLIMEY_STONE.get().defaultBlockState();
//        }
//    }
//
//}
