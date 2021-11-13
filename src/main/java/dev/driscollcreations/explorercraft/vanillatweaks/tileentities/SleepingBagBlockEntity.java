package dev.driscollcreations.explorercraft.vanillatweaks.tileentities;

import dev.driscollcreations.explorercraft.setup.ExplorerTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SleepingBagBlockEntity extends BlockEntity {

    private DyeColor color;

    public SleepingBagBlockEntity(BlockPos p_155115_, BlockState p_155116_) {
        super(ExplorerTileEntities.SLEEPING_BAG.get(), p_155115_, p_155116_);
        this.color = ((BedBlock)p_155116_.getBlock()).getColor();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return new ClientboundBlockEntityDataPacket(this.worldPosition, 11, this.getUpdateTag());
    }
}
