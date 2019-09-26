package com.zathrox.explorercraft.core.registry;

import com.zathrox.explorercraft.common.world.biome.BiomeBambooForest;
import com.zathrox.explorercraft.common.world.biome.BiomeForestedMountains;
import com.zathrox.explorercraft.common.world.biome.BiomeSnowdonia;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.config.BiomeConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Explorercraft.MOD_ID)
public class ExplorerBiomes {

    @ObjectHolder("bamboo_forest")
    public static final Biome BAMBOO_FOREST = null;
    @ObjectHolder("forested_mountain")
    public static final Biome FORESTED_MOUNTAIN = null;
    @ObjectHolder("snowdonia")
    public static final Biome SNOWDONIA = null;

    @Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static abstract class Registration {

        @SubscribeEvent
        public static void onRegister(final RegistryEvent.Register<Biome> event) {
            IForgeRegistry<Biome> registry = event.getRegistry();

            registry.register(new BiomeBambooForest().setRegistryName(new ResourceLocation(Explorercraft.MOD_ID, "bamboo_forest")));
            registry.register(new BiomeForestedMountains().setRegistryName(new ResourceLocation(Explorercraft.MOD_ID, "forested_mountain")));
            registry.register(new BiomeSnowdonia().setRegistryName(new ResourceLocation(Explorercraft.MOD_ID, "snowdonia")));

        }

    }

    public static void registerBiomes() {
        addBiomesToManager(BAMBOO_FOREST, "bamboo_forest", BiomeConfig.bambooForestWeight.get(), false, BiomeConfig.spawnBambooForest.get(), BiomeManager.BiomeType.COOL, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.WET);
        addBiomesToManager(FORESTED_MOUNTAIN, "forested_mountain", BiomeConfig.forestedMountainWeight.get(), false, BiomeConfig.spawnForestedMountain.get(), BiomeManager.BiomeType.WARM, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.FOREST);
        addBiomesToManager(SNOWDONIA, "snowdonia", BiomeConfig.snowdoniaWeight.get(), false, BiomeConfig.spawnSnowdonia.get(), BiomeManager.BiomeType.COOL, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.WET);
    }

    public static void addBiomesToManager(Biome biome, String name, int weight, Boolean village, Boolean allowed, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... types) {

        if (allowed)
        {
            BiomeDictionary.addTypes(biome, types);
            BiomeManager.addSpawnBiome(biome);
            BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));

        }
    }
}
