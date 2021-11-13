package dev.driscollcreations.explorercraft.cymru.world;

import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;

import java.util.Random;

public class SnowdoniaFlowerBlockStateProvider extends SimpleStateProvider {

    public SnowdoniaFlowerBlockStateProvider(BlockState state) {
        super(state);
    }

    protected BlockStateProviderType<?> type() {
        return BlockStateProviderType.SIMPLE_STATE_PROVIDER;
    }

    @Override
    public BlockState getState(Random random, BlockPos pos) {
        return pos.getY() > 100 ? CymruBlocks.DAFFODIL.get().defaultBlockState() : Blocks.LILY_OF_THE_VALLEY.defaultBlockState();
    }
}

