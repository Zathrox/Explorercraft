package com.zathrox.explorercraft.core.proxy;

import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@OnlyIn(Dist.DEDICATED_SERVER)
public class ServerProxy extends CommonProxy {

    public ServerProxy() {
        super();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
    }

    private void serverSetup(FMLDedicatedServerSetupEvent event) {
        Explorercraft.LOGGER.debug("ServerProxy serverSetup method");
    }
}