package com.zathrox.explorercraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EmissiveLogBlock extends RotatedPillarBlock {

    private final MaterialColor verticalColor;

    public EmissiveLogBlock(MaterialColor verticalColorIn, Block.Properties properties) {
        super(properties);
        this.verticalColor = verticalColorIn;
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
