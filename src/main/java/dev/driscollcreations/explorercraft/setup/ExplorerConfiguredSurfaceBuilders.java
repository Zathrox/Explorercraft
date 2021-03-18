package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraft.block.Blocks;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ExplorerConfiguredSurfaceBuilders {

    public static RegistryKey<ConfiguredSurfaceBuilder<?>> TEST = key("test");
    public static RegistryKey<ConfiguredSurfaceBuilder<?>> BAMB00_GROVE = key("bamboo_grove");

    private static RegistryKey<ConfiguredSurfaceBuilder<?>> key(final String name) {
        return RegistryKey.getOrCreateKey(Registry.CONFIGURED_SURFACE_BUILDER_KEY, new ResourceLocation(Explorercraft.MOD_ID, name));
    }

    @Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistrationHandler {
        // Ensure this is run after the SurfaceBuilder DeferredRegister in ModSurfaceBuilders
        @SubscribeEvent(priority = EventPriority.LOW)
        public static void register(final RegistryEvent.Register<SurfaceBuilder<?>> event) {
            register(TEST,
                    ExplorerSurfaceBuilders.LOGGING_DEFAULT.get()
                            .func_242929_a(new SurfaceBuilderConfig(Blocks.RED_SAND.getDefaultState(), Blocks.BRICKS.getDefaultState(), Blocks.GRAVEL.getDefaultState()))
            );
            register(BAMB00_GROVE,
                    ExplorerSurfaceBuilders.BAMBOO_GROVE_LOG.get()
                            .func_242929_a(SurfaceBuilder.GRASS_DIRT_SAND_CONFIG)
            );
        }

        private static void register(final RegistryKey<ConfiguredSurfaceBuilder<?>> key, final ConfiguredSurfaceBuilder<?> configuredSurfaceBuilder) {
            Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, key.getLocation(), configuredSurfaceBuilder);
        }
    }
}