package com.zathrox.explorercraft.common.tileentity;

import com.zathrox.explorercraft.common.blocks.BlockSleepingBag;
import com.zathrox.explorercraft.core.registry.ExplorerTileEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TileEntitySleepingBag extends TileEntity {
    private DyeColor color;

    public TileEntitySleepingBag() {
        super(ExplorerTileEntity.SLEEPING_BAG);
    }

    public TileEntitySleepingBag(DyeColor colorIn) {
        this();
        this.setColor(colorIn);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 11, this.getUpdateTag());
    }

    @OnlyIn(Dist.CLIENT)
    public DyeColor getColor() {
        if (this.color == null) {
            this.color = ((BlockSleepingBag) this.getBlockState().getBlock()).getColor();
        }

        return this.color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }
}
