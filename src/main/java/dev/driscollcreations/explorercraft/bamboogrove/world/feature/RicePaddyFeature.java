package dev.driscollcreations.explorercraft.bamboogrove.world.feature;

import dev.driscollcreations.explorercraft.bamboogrove.blocks.RiceBlock;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Material;

import java.util.ArrayList;
import java.util.Random;

public class RicePaddyFeature extends Feature<NoneFeatureConfiguration> {

    private final Block block;

    private static final Random randomAge = new Random();
    /** Water block state. */
    private static final BlockState water = Blocks.WATER.defaultBlockState();
    /** Rice base block state. */
    private static final BlockState base = BambooGroveBlocks.RICE_BASE.get().defaultBlockState();

    public RicePaddyFeature() {
        super(NoneFeatureConfiguration.CODEC);
        block = Blocks.WATER;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {

        Random random = pContext.random();
        WorldGenLevel world = pContext.level();
        BlockPos position = pContext.origin();
        
        for (position = position.offset(-8, 0, -8); position.getY() > 5 && world.isEmptyBlock(position); position = position.below())
        {

        }

        if (position.getY() <= 4)
        {
            return false;
        }
        else
        {
            position = position.below(4);
            boolean[] aboolean = new boolean[2048];
            int i = random.nextInt(4) + 4;

            for (int j = 0; j < i; ++j)
            {
                double d0 = random.nextDouble() * 6.0D + 3.0D;
                double d1 = random.nextDouble() * 4.0D + 2.0D;
                double d2 = random.nextDouble() * 6.0D + 3.0D;
                double d3 = random.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = random.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int l = 1; l < 15; ++l)
                {
                    for (int i1 = 1; i1 < 15; ++i1)
                    {
                        for (int j1 = 1; j1 < 7; ++j1)
                        {
                            double d6 = ((double)l - d3) / (d0 / 2.0D);
                            double d7 = ((double)j1 - d4) / (d1 / 2.0D);
                            double d8 = ((double)i1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D)
                            {
                                aboolean[(l * 16 + i1) * 8 + j1] = true;
                            }
                        }
                    }
                }
            }

            for (int k1 = 0; k1 < 16; ++k1)
            {
                for (int l2 = 0; l2 < 16; ++l2)
                {
                    for (int k = 0; k < 8; ++k)
                    {
                        boolean flag = !aboolean[(k1 * 16 + l2) * 8 + k] && (k1 < 15 && aboolean[((k1 + 1) * 16 + l2) * 8 + k] || k1 > 0 && aboolean[((k1 - 1) * 16 + l2) * 8 + k] || l2 < 15 && aboolean[(k1 * 16 + l2 + 1) * 8 + k] || l2 > 0 && aboolean[(k1 * 16 + (l2 - 1)) * 8 + k] || k < 7 && aboolean[(k1 * 16 + l2) * 8 + k + 1] || k > 0 && aboolean[(k1 * 16 + l2) * 8 + (k - 1)]);

                        if (flag)
                        {
                            Material material = world.getBlockState(position.offset(k1, k, l2)).getMaterial();

                            if (k >= 4 && material.isLiquid())
                            {
                                return false;
                            }

                            if (k < 4 && !material.isSolid() && world.getBlockState(position.offset(k1, k, l2)).getBlock() != block)
                            {
                                return false;
                            }
                        }
                    }
                }
            }

            // Begin rice lake generation using vanilla loops
            ArrayList<BlockPos> possibles = new ArrayList<BlockPos>();

            for (int l1 = 0; l1 < 16; ++l1) {

                for (int i3 = 0; i3 < 16; ++i3) {

                    for (int i4 = 0; i4 < 5; ++i4) {

                        if (aboolean[(l1 * 16 + i3) * 8 + i4]) {

                            BlockPos target = position.offset(l1, i4, i3);

                            BlockPos down = target;
                            BlockState state = world.getBlockState(down);
                            boolean isSoil = state.getBlock().canSustainPlant(state, world, down, Direction.UP, (SaplingBlock) Blocks.OAK_SAPLING);

                            if (i4 >= 4) {

                                if (world.getBlockState(target) != BambooGroveBlocks.RICE_TOP.get().defaultBlockState() && world.getBlockState(target) != base && world.getBlockState(target) != BambooGroveBlocks.BAMBOO_LOG.get().defaultBlockState()) {

                                    if (world.getBlockState(target.above()).getBlock() != BambooGroveBlocks.BAMBOO_LOG.get() && isSoil == false ) {
                                        world.setBlock(target, Blocks.AIR.defaultBlockState(), 2);
                                    }

                                }

                            } else if (world.getBlockState(target.above()).getBlock() == BambooGroveBlocks.BAMBOO_LOG.get() && isSoil ) {
                                world.setBlock(target, Blocks.DIRT.defaultBlockState(), 2);

                            } else {
                                world.setBlock(target, Blocks.WATER.defaultBlockState(), 2);
                                Block below = world.getBlockState(target.below()).getBlock();
                                if (below == Blocks.DIRT || below == Blocks.GRASS || below == Blocks.SAND) {
                                    possibles.add(target);
                                }
                            }
                        }
                    }
                }
            }

            int riceCount = 0;
            int riceMax = random.nextInt(6) + 1;

            while (riceCount <= riceMax && possibles.size() > 0) {

                BlockPos rice = possibles.remove(random.nextInt(possibles.size()));

                if (world.canSeeSkyFromBelowWater(rice.above()) && world.isEmptyBlock(rice.above())) {

                    BlockState top = BambooGroveBlocks.RICE_TOP.get().defaultBlockState().setValue(RiceBlock.AGE, randomAge.nextInt(6));
                    world.setBlock(rice, base, 2);
                    world.setBlock(rice.above(), top, 3);
                    //System.out.println(top);
                    riceCount++;
                }
            }

            if (block.defaultBlockState().getMaterial() == Material.LAVA)
            {
                for (int j2 = 0; j2 < 16; ++j2)
                {
                    for (int k3 = 0; k3 < 16; ++k3)
                    {
                        for (int k4 = 0; k4 < 8; ++k4)
                        {
                            boolean flag1 = !aboolean[(j2 * 16 + k3) * 8 + k4] && (j2 < 15 && aboolean[((j2 + 1) * 16 + k3) * 8 + k4] || j2 > 0 && aboolean[((j2 - 1) * 16 + k3) * 8 + k4] || k3 < 15 && aboolean[(j2 * 16 + k3 + 1) * 8 + k4] || k3 > 0 && aboolean[(j2 * 16 + (k3 - 1)) * 8 + k4] || k4 < 7 && aboolean[(j2 * 16 + k3) * 8 + k4 + 1] || k4 > 0 && aboolean[(j2 * 16 + k3) * 8 + (k4 - 1)]);

                            if (flag1 && (k4 < 4 || random.nextInt(2) != 0) && world.getBlockState(position.offset(j2, k4, k3)).getMaterial().isSolid())
                            {
                                world.setBlock(position.offset(j2, k4, k3), Blocks.STONE.defaultBlockState(), 2);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}