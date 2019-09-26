package com.zathrox.explorercraft.core;

import com.zathrox.explorercraft.common.entity.EnderreeperEntity;
import com.zathrox.explorercraft.common.world.OreGeneration;
import com.zathrox.explorercraft.core.proxy.*;
import com.zathrox.explorercraft.core.registry.ExplorerBiomes;
import com.zathrox.explorercraft.core.util.ExplorerVanillaCompat;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Explorercraft.MOD_ID)
public class Explorercraft
{

    public static final String MOD_ID = "explorercraft";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public static Explorercraft instance;
    public static CommonProxy proxy;


    public Explorercraft() {

        instance = this;
        proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
    }

}
