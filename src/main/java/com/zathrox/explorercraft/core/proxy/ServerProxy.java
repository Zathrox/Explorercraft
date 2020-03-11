package com.zathrox.explorercraft.core.proxy;

import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ServerProxy extends CommonProxy {

    public ServerProxy() {
        super();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
    }

    private void serverSetup(FMLDedicatedServerSetupEvent event) {
        Explorercraft.LOGGER.debug("ServerProxy serverSetup method");
    }
}