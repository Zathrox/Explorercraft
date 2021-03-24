package dev.driscollcreations.explorercraft.vanillatweaks.world.feature;

import com.mojang.serialization.Codec;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.NoctilucaBlock;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpreadConfig;

import java.util.Random;

public class NoctilucaFeature extends Feature<FeatureSpreadConfig> {

    private static final Random randomAge = new Random();

    public NoctilucaFeature(Codec<FeatureSpreadConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, FeatureSpreadConfig config) {
        int count = 0;
        int configCount = config.count().sample(random);

        for(int i = 0; i < configCount; ++i) {
            int x = random.nextInt(8) - random.nextInt(8);
            int z = random.nextInt(8) - random.nextInt(8);
            int y = worldIn.getHeight(Heightmap.Type.OCEAN_FLOOR, blockPos.getX() + x, blockPos.getZ() + z);
            BlockPos water = new BlockPos(blockPos.getX() + x, y, blockPos.getZ() + z);
            BlockState noctiluca = VanillaTweaksBlocks.NOCTILUCAS.get().defaultBlockState().setValue(NoctilucaBlock.AGE, 0 + randomAge.nextInt(8));
            if (worldIn.getBlockState(water).is(Blocks.WATER) && noctiluca.canSurvive(worldIn, water)) {
                worldIn.setBlock(water, noctiluca, 2);
                ++count;
            }
        }

        return count > 0;
    }
}
