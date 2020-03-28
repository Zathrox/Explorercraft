package com.zathrox.explorercraft.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zathrox.explorercraft.common.world.ExplorerFeature;
import com.zathrox.explorercraft.core.proxy.ClientProxy;
import com.zathrox.explorercraft.core.proxy.CommonProxy;
import com.zathrox.explorercraft.core.proxy.ServerProxy;
import com.zathrox.explorercraft.core.registry.ExplorerBiomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

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

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void featureRegistery(final RegistryEvent.Register<Feature<?>> event)
		{
			ExplorerFeature.featureRegistering(event);
		}
		
		@SubscribeEvent
		public static void surfacebuilderRegistery(final RegistryEvent.Register<SurfaceBuilder<?>> event)
		{
			ExplorerFeature.surfacebuilderRegistering(event);
		}
	}
}
