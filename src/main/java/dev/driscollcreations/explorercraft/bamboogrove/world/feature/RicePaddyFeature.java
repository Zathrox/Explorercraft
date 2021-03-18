package dev.driscollcreations.explorercraft.bamboogrove.world.feature;

import dev.driscollcreations.explorercraft.bamboogrove.blocks.RiceBlock;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.ArrayList;
import java.util.Random;

public class RicePaddyFeature extends Feature<NoFeatureConfig> {

    private final Block block;

    private static final Random randomAge = new Random();
    /** Water block state. */
    private static final BlockState water = Blocks.WATER.getDefaultState();
    /** Rice base block state. */
    private static final BlockState base = BambooGroveBlocks.RICE_BASE.get().getDefaultState();

    public RicePaddyFeature() {
        super(NoFeatureConfig.CODEC);
        block = Blocks.WATER;
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos position, NoFeatureConfig config) {

        for (position = position.add(-8, 0, -8); position.getY() > 5 && world.isAirBlock(position); position = position.down())
        {

        }

        if (position.getY() <= 4)
        {
            return false;
        }
        else
        {
            position = position.down(4);
            boolean[] aboolean = new boolean[2048];
            int i = rand.nextInt(4) + 4;

            for (int j = 0; j < i; ++j)
            {
                double d0 = rand.nextDouble() * 6.0D + 3.0D;
                double d1 = rand.nextDouble() * 4.0D + 2.0D;
                double d2 = rand.nextDouble() * 6.0D + 3.0D;
                double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

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
                            Material material = world.getBlockState(position.add(k1, k, l2)).getMaterial();

                            if (k >= 4 && material.isLiquid())
                            {
                                return false;
                            }

                            if (k < 4 && !material.isSolid() && world.getBlockState(position.add(k1, k, l2)).getBlock() != block)
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

                            BlockPos target = position.add(l1, i4, i3);

                            BlockPos down = target;
                            BlockState state = world.getBlockState(down);
                            boolean isSoil = state.getBlock().canSustainPlant(state, world, down, Direction.UP, (net.minecraft.block.SaplingBlock) Blocks.OAK_SAPLING);

                            if (i4 >= 4) {

                                if (world.getBlockState(target) != BambooGroveBlocks.RICE_TOP.get().getDefaultState() && world.getBlockState(target) != base && world.getBlockState(target) != BambooGroveBlocks.BAMBOO_LOG.get().getDefaultState()) {

                                    if (world.getBlockState(target.up()).getBlock() != BambooGroveBlocks.BAMBOO_LOG.get() && isSoil == false ) {
                                        world.setBlockState(target, Blocks.AIR.getDefaultState(), 2);
                                    }

                                }

                            } else if (world.getBlockState(target.up()).getBlock() == BambooGroveBlocks.BAMBOO_LOG.get() && isSoil ) {
                                world.setBlockState(target, Blocks.DIRT.getDefaultState(), 2);

                            } else {
                                world.setBlockState(target, Blocks.WATER.getDefaultState(), 2);
                                Block below = world.getBlockState(target.down()).getBlock();
                                if (below == Blocks.DIRT || below == Blocks.GRASS || below == Blocks.SAND) {
                                    possibles.add(target);
                                }
                            }
                        }
                    }
                }
            }

            int riceCount = 0;
            int riceMax = rand.nextInt(6) + 1;

            while (riceCount <= riceMax && possibles.size() > 0) {

                BlockPos rice = possibles.remove(rand.nextInt(possibles.size()));

                if (world.canBlockSeeSky(rice.up()) && world.isAirBlock(rice.up())) {

                    BlockState top = BambooGroveBlocks.RICE_TOP.get().getDefaultState().with(RiceBlock.AGE, 0 + randomAge.nextInt(6));
                    world.setBlockState(rice, base, 2);
                    world.setBlockState(rice.up(), top, 3);
                    //System.out.println(top);
                    riceCount++;
                }
            }

            if (block.getDefaultState().getMaterial() == Material.LAVA)
            {
                for (int j2 = 0; j2 < 16; ++j2)
                {
                    for (int k3 = 0; k3 < 16; ++k3)
                    {
                        for (int k4 = 0; k4 < 8; ++k4)
                        {
                            boolean flag1 = !aboolean[(j2 * 16 + k3) * 8 + k4] && (j2 < 15 && aboolean[((j2 + 1) * 16 + k3) * 8 + k4] || j2 > 0 && aboolean[((j2 - 1) * 16 + k3) * 8 + k4] || k3 < 15 && aboolean[(j2 * 16 + k3 + 1) * 8 + k4] || k3 > 0 && aboolean[(j2 * 16 + (k3 - 1)) * 8 + k4] || k4 < 7 && aboolean[(j2 * 16 + k3) * 8 + k4 + 1] || k4 > 0 && aboolean[(j2 * 16 + k3) * 8 + (k4 - 1)]);

                            if (flag1 && (k4 < 4 || rand.nextInt(2) != 0) && world.getBlockState(position.add(j2, k4, k3)).getMaterial().isSolid())
                            {
                                world.setBlockState(position.add(j2, k4, k3), Blocks.STONE.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }


}