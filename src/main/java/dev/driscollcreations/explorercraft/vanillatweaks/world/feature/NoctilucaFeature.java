package dev.driscollcreations.explorercraft.vanillatweaks.world.feature;

import com.mojang.serialization.Codec;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.NoctilucaBlock;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;

import java.util.Random;

public class NoctilucaFeature extends Feature<CountConfiguration> {

    private static final Random randomAge = new Random();

    public NoctilucaFeature(Codec<CountConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CountConfiguration> config) {
        Random random = config.random();
        WorldGenLevel worldIn = config.level();
        BlockPos blockPos = config.origin();
        int configCount = config.config().count().sample(random);

        int count = 0;

        for(int i = 0; i < configCount; ++i) {
            int x = random.nextInt(8) - random.nextInt(8);
            int z = random.nextInt(8) - random.nextInt(8);
            int y = worldIn.getHeight(Heightmap.Types.OCEAN_FLOOR, blockPos.getX() + x, blockPos.getZ() + z);
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
