package dev.driscollcreations.explorercraft.vanillatweaks.tileentities;

import dev.driscollcreations.explorercraft.setup.ExplorerTileEntities;
import dev.driscollcreations.explorercraft.vanillatweaks.blocks.SleepingBagBlock;
import net.minecraft.item.DyeColor;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SleepingBagTileEntity extends TileEntity {

    private DyeColor color;

    public SleepingBagTileEntity() {
        super(ExplorerTileEntities.SLEEPING_BAG.get());
    }

    public SleepingBagTileEntity(DyeColor colorIn) {
        this();
        this.setColor(colorIn);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 11, this.getUpdateTag());
    }

    @OnlyIn(Dist.CLIENT)
    public DyeColor getColor() {
        if (this.color == null) {
            this.color = ((SleepingBagBlock) this.getBlockState().getBlock()).getColor();
        }
        return this.color;
    }

    public void setColor(DyeColor color) {
        this.color = color;
    }
}
