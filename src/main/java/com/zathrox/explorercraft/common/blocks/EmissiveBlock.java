package com.zathrox.explorercraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EmissiveBlock extends Block {

    public EmissiveBlock(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isEmissiveRendering(BlockState p_225543_1_) {
        return true;
    }

    @Override
    public boolean needsPostProcessing(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return true;
    }

}
