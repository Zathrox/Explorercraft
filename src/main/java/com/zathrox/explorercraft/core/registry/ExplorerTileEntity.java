package com.zathrox.explorercraft.core.registry;

import com.zathrox.explorercraft.common.tileentity.TileEntitySleepingBag;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.util.ExplorerUtil;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Explorercraft.MOD_ID)
public class ExplorerTileEntity {

    public static final TileEntityType<TileEntitySleepingBag> SLEEPING_BAG = ExplorerUtil._null();


}
