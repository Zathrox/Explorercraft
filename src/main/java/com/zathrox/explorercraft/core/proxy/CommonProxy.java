package com.zathrox.explorercraft.core.proxy;

import com.zathrox.explorercraft.common.entity.EnderreeperEntity;
import com.zathrox.explorercraft.common.world.OreGeneration;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.config.Config;
import com.zathrox.explorercraft.core.events.ExplorerPlayerEvents;
import com.zathrox.explorercraft.core.registry.ExplorerBiomes;
import com.zathrox.explorercraft.core.registry.ExplorerEntities;
import com.zathrox.explorercraft.core.util.ExplorerVanillaCompat;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

public class CommonProxy {

    public CommonProxy() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.common_config);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);

        Config.loadConfig(Config.common_config, FMLPaths.CONFIGDIR.get().resolve("explorercraft-common.toml").toString());

        MinecraftForge.EVENT_BUS.register(new ExplorerPlayerEvents());
    }

    protected void preInit(FMLCommonSetupEvent event) {
        Explorercraft.LOGGER.debug("CommonProxy preInit method");

        OreGeneration.setup();
        ExplorerVanillaCompat.setup();
    }

    protected void init(InterModEnqueueEvent event) {
        Explorercraft.LOGGER.debug("CommonProxy init method");
    }

    protected void postInit(InterModProcessEvent event) {
        Explorercraft.LOGGER.debug("CommonProxy postInit method");

        //EntityOvergrownSkeleton.addSpawn();
        //EnderreeperEntity.addSpawn();
        ExplorerEntities.registerEntityWorldSpawns();
        ExplorerBiomes.registerBiomes();
    }
}