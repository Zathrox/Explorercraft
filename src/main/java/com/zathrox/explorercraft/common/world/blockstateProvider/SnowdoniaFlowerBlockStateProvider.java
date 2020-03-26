package com.zathrox.explorercraft.common.world.blockstateProvider;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.blockstateprovider.BlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.BlockStateProviderType;

import java.util.Random;

public class SnowdoniaFlowerBlockStateProvider extends BlockStateProvider {
    private static final BlockState[] field_227402_b_ = new BlockState[]{Blocks.ORANGE_TULIP.getDefaultState(), Blocks.RED_TULIP.getDefaultState(), Blocks.PINK_TULIP.getDefaultState(), Blocks.WHITE_TULIP.getDefaultState()};
    private static final BlockState[] field_227403_c_ = new BlockState[]{Blocks.POPPY.getDefaultState(), Blocks.AZURE_BLUET.getDefaultState(), Blocks.OXEYE_DAISY.getDefaultState(), Blocks.CORNFLOWER.getDefaultState()};

    public SnowdoniaFlowerBlockStateProvider() {
        super(BlockStateProviderType.SIMPLE_STATE_PROVIDER);
    }

    public <T> SnowdoniaFlowerBlockStateProvider(Dynamic<T> p_i225857_1_) {
        this();
    }

    public BlockState getBlockState(Random random, BlockPos pos) {
        return pos.getY() > 100 ? ExplorerBlocks.DAFFODIL.getDefaultState() : Blocks.LILY_OF_THE_VALLEY.getDefaultState();
    }

    public <T> T serialize(DynamicOps<T> p_218175_1_) {
        ImmutableMap.Builder<T, T> builder = ImmutableMap.builder();
        builder.put(p_218175_1_.createString("type"), p_218175_1_.createString(Registry.BLOCK_STATE_PROVIDER_TYPE.getKey(this.blockStateProvider).toString()));
        return (new Dynamic<>(p_218175_1_, p_218175_1_.createMap(builder.build()))).getValue();
    }
}
