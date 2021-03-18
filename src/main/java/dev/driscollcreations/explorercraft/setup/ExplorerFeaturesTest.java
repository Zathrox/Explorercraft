/*
package dev.driscollcreations.explorercraft.setup;

import com.google.common.collect.ImmutableList;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.util.ExplorercraftResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID)
public final class ExplorerFeaturesTest {


    public static final Lazy<BaseTreeFeatureConfig> THICK_BAMBOO_CONFIG = Lazy.of(() -> new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(BambooGroveBlocks.BAMBOO_LOG.get().getDefaultState()),
            new SimpleBlockStateProvider(BambooGroveBlocks.BAMBOO_LEAVES.get().getDefaultState()),
            new BlobFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(0), 3),
            new StraightTrunkPlacer(12, 10, 7),
            new TwoLayerFeature(1, 0, 1))
                                                                                                   .setIgnoreVines()
                                                                                                   .build());

    public static final Lazy<TreeFeature> THICK_BAMBOO_FEATURE = Lazy.of(() -> new TreeFeature(BaseTreeFeatureConfig.CODEC));

    private static boolean configuredFeaturesRegistered = false;
    @SuppressWarnings("WeakerAccess")
    public static final class Configured {
        static final Map<String, Lazy<ConfiguredFeature<?, ?>>> TO_REGISTER = new LinkedHashMap<>();

        public static final Lazy<ConfiguredFeature<?, ?>> THICK_BAMBOO = createLazy("thick_bamboo",
                () -> Feature.RANDOM_SELECTOR
                    .withConfiguration(new MultipleRandomFeatureConfig(
                          ImmutableList.of(
                                  THICK_BAMBOO_FEATURE.get()
                                          .withConfiguration(THICK_BAMBOO_CONFIG.get())
                                          .withChance(0.8F)
                          ),
                            THICK_BAMBOO_FEATURE.get()
                                  .withConfiguration(THICK_BAMBOO_CONFIG.get())
                    ))
                    .withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(8)))
                    .range(128)
                    .chance(2));

        private static Lazy<ConfiguredFeature<?, ?>> createLazy(String name, Supplier<ConfiguredFeature<?, ?>> supplier) {
            if (TO_REGISTER.containsKey(name)) {
                throw new IllegalArgumentException("Configured feature lazy with name '" + name + "' already created");
            }

            Lazy<ConfiguredFeature<?, ?>> lazy = Lazy.of(supplier);
            TO_REGISTER.put(name, lazy);
            return lazy;
        }

        private Configured() {}
    }

    public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
        event.getRegistry().register(THICK_BAMBOO_FEATURE.get().setRegistryName(Explorercraft.getId("thick_bamboo")));
    }

    private static void registerConfiguredFeatures() {
        if (configuredFeaturesRegistered) return;

        configuredFeaturesRegistered = true;

        Configured.TO_REGISTER.forEach((name, cf) -> registerConfiguredFeature(name, cf.get()));
    }

    private static void registerConfiguredFeature(String name, ConfiguredFeature<?, ?> configuredFeature) {
        ExplorercraftResourceLocation id = Explorercraft.getId(name);
        Explorercraft.LOGGER.debug("Register configured feature {}", id);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }

    @SubscribeEvent
    public static void addFeaturesToBiomes(BiomeLoadingEvent biome) {
        // Need to load these as late as possible, or configs won't be loaded
        registerConfiguredFeatures();

        */
/*if (biome.getName() == ExplorerBiomes.BAMBOO_GROVE.get().getRegistryName()) {
            //addThickBamboo(biome);
        }*//*

    }

    private static void addThickBamboo(BiomeLoadingEvent biome) {
        biome.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.THICK_BAMBOO.get());
    }

}
*/
