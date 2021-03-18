/*
package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ExplorerConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> BambooTree = key("bamboo_tree");

    private static RegistryKey<ConfiguredFeature<?, ?>> key(final String name) {
        return RegistryKey.getOrCreateKey(Registry.CONFIGURED_FEATURE_KEY, new ResourceLocation(Explorercraft.MOD_ID, name));
    }

    @Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {

        @SubscribeEvent()
        public static void register(final RegistryEvent.Register<Feature<?>> event) {

            register(BambooTree, Feature.TREE.withConfiguration(
                    (new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(BambooGroveBlocks.BAMBOO_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(BambooGroveBlocks.BAMBOO_LEAVES.get().getDefaultState()),
                            new BlobFoliagePlacer(FeatureSpread.create(0), FeatureSpread.create(0), 6),
                            new StraightTrunkPlacer(12, 10, 7),
                            new TwoLayerFeature(1, 0, 1)))
                            .setIgnoreVines()
                            .build())

            );
        }

        private static void register(final RegistryKey<ConfiguredFeature<?, ?>> key, final ConfiguredFeature<?, ?> configuredFeature) {
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key.getLocation(), configuredFeature);
        }
    }

}
*/
