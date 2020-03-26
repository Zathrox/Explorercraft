package com.zathrox.explorercraft.core;

import com.zathrox.explorercraft.core.proxy.ClientProxy;
import com.zathrox.explorercraft.core.proxy.CommonProxy;
import com.zathrox.explorercraft.core.proxy.ServerProxy;
import com.zathrox.explorercraft.core.registry.ExplorerBiomes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Explorercraft.MOD_ID)
public class Explorercraft {

    public static final String MOD_ID = "explorercraft";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public static Explorercraft instance;
    public static CommonProxy proxy;


    public Explorercraft() {

        instance = this;
        proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);


    }

}
