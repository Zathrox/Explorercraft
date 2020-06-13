package com.zathrox.explorercraft.core.registry;

import com.zathrox.explorercraft.common.world.biome.*;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.config.BiomeConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeContainer;
import net.minecraft.world.gen.layer.LayerUtil;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ExplorerBiomes {

    public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES, Explorercraft.MOD_ID);

    public static final RegistryObject<Biome> FORESTED_MOUNTAIN = BIOMES.register("forested_mountain", () -> new ForestedMountainsBiome());
    public static final RegistryObject<Biome> BAMBOO_GROVE = BIOMES.register("bamboo_grove", () -> new BambooGroveBiome());
    public static final RegistryObject<Biome> SNOWDONIA = BIOMES.register("snowdonia", () -> new SnowdoniaBiome());
    public static final RegistryObject<Biome> FUNGAL_FOREST = BIOMES.register("fungal_forest", () -> new FungalForestBiome());
    public static final RegistryObject<Biome> CHERRY_GROVE = BIOMES.register("cherry_grove", () -> new CherryGroveBiome());

    public static void registerBiomes() {
        addBiomesToManager(BAMBOO_GROVE.get(), BiomeConfig.bambooForestWeight.get(), false, BiomeConfig.spawnBambooForest.get(), BiomeManager.BiomeType.COOL, BiomeDictionary.Type.DENSE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.WET);
        addBiomesToManager(FORESTED_MOUNTAIN.get(), BiomeConfig.forestedMountainWeight.get(), false, BiomeConfig.spawnForestedMountain.get(), BiomeManager.BiomeType.WARM, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.FOREST);
        addBiomesToManager(SNOWDONIA.get(),  BiomeConfig.snowdoniaWeight.get(), false, BiomeConfig.spawnSnowdonia.get(), BiomeManager.BiomeType.COOL, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.WET);
        addBiomesToManager(FUNGAL_FOREST.get(), BiomeConfig.fungalForestWeight.get(), false, BiomeConfig.spawnFungalForest.get(), BiomeManager.BiomeType.WARM, BiomeDictionary.Type.WET, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.RARE);
        BiomeDictionary.addTypes(CHERRY_GROVE.get(), BiomeDictionary.Type.DENSE, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.WET);
        //BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(CHERRY_GROVE.get(), 10));
    }

    public static void addBiomesToManager(Biome biome, int weight, Boolean village, Boolean allowed, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... types) {

        if (allowed)
        {
            BiomeDictionary.addTypes(biome, types);
            BiomeManager.addSpawnBiome(biome);
            BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
        }
    }
}
