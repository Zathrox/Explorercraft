package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.world.BambooGroveBiome;
import dev.driscollcreations.explorercraft.config.BambooGroveConfig;
import dev.driscollcreations.explorercraft.config.CymruConfig;
import dev.driscollcreations.explorercraft.config.VanillaTweaksConfig;
import dev.driscollcreations.explorercraft.cymru.world.SnowdoniaBiome;
import net.minecraft.data.worldgen.biome.VanillaBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class ExplorerBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Explorercraft.MOD_ID);

    public static final RegistryObject<Biome> BAMBOO_GROVE = BIOMES.register("bamboo_grove", BambooGroveBiome::makeBambooGrove);
    public static final RegistryObject<Biome> FORESTED_MOUNTAIN = BIOMES.register("forested_mountain", () -> VanillaBiomes.forestBiome(0.1F, 1.3F));
    public static final RegistryObject<Biome> SNOWDONIA = BIOMES.register("snowdonia", SnowdoniaBiome::makeSnowdoniaBiome);

    @Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void setupBiomes(final FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                setupBiome(BAMBOO_GROVE.get(), BambooGroveConfig.spawnBambooGroves.get(), BambooGroveConfig.bambooGroveRarity.get(), BiomeManager.BiomeType.WARM, HOT, WET, DENSE, JUNGLE, OVERWORLD);
                setupBiome(FORESTED_MOUNTAIN.get(), VanillaTweaksConfig.spawnForestedMountain.get(), VanillaTweaksConfig.forestedMountainRarity.get(), BiomeManager.BiomeType.WARM, MOUNTAIN, FOREST, OVERWORLD);
                setupBiome(SNOWDONIA.get(), CymruConfig.spawnSnowdonia.get(), CymruConfig.snowdoniaRarity.get(), BiomeManager.BiomeType.COOL, MOUNTAIN, FOREST, RARE, OVERWORLD);
            });
        }

        private static void setupBiome(final Biome biome, Boolean allowed, final int weight, final BiomeManager.BiomeType biomeType, final BiomeDictionary.Type... types) {
            if (allowed) {
                BiomeDictionary.addTypes(key(biome), types);
                BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(key(biome), weight));
            }

        }

        private static ResourceKey<Biome> key(final Biome biome) {
            return ResourceKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome), "Biome registry name was null"));
        }
    }

}
