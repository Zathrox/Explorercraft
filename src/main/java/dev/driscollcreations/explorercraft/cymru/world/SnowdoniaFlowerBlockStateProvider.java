package dev.driscollcreations.explorercraft.cymru.world;

import com.mojang.serialization.Codec;
import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;

import java.util.Random;

public class SnowdoniaFlowerBlockStateProvider extends SimpleBlockStateProvider {

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

