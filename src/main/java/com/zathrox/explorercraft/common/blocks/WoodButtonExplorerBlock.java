package com.zathrox.explorercraft.common.blocks;

import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class WoodButtonExplorerBlock extends AbstractButtonBlock {

    public WoodButtonExplorerBlock(Properties p_i48291_1_) {
        super(true, p_i48291_1_);
    }

    @Override
    protected SoundEvent getSoundEvent(boolean p_196369_1_) {
        return p_196369_1_ ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF;
    }
}