package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.world.BambooGroveBiome;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

import static net.minecraftforge.common.BiomeDictionary.Type.*;
import static net.minecraftforge.common.BiomeDictionary.Type.OVERWORLD;

public class ExplorerBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Explorercraft.MOD_ID);

    public static final RegistryObject<Biome> BAMBOO_GROVE = BIOMES.register("bamboo_grove", BambooGroveBiome::makeBambooGrove);

    @Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void setupBiomes(final FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                setupBiome(BAMBOO_GROVE.get(), BiomeManager.BiomeType.WARM, 100, HOT, DRY, SANDY, OVERWORLD);
            });
        }

        private static void setupBiome(final Biome biome, final BiomeManager.BiomeType biomeType, final int weight, final BiomeDictionary.Type... types) {
            BiomeDictionary.addTypes(key(biome), types);
            BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(key(biome), weight));
        }

        private static RegistryKey<Biome> key(final Biome biome) {
            return RegistryKey.create(ForgeRegistries.Keys.BIOMES, Objects.requireNonNull(ForgeRegistries.BIOMES.getKey(biome), "Biome registry name was null"));
        }
    }

}
