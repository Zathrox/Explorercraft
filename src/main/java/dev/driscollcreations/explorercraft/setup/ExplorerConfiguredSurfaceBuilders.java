//package dev.driscollcreations.explorercraft.setup;
//
//import ResourceKey;
//import dev.driscollcreations.explorercraft.Explorercraft;
//import net.minecraft.core.Registry;
//import net.minecraft.data.BuiltinRegistries;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.levelgen.surfacebuilders.ConfiguredSurfaceBuilder;
//import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
//import net.minecraftforge.event.RegistryEvent;
//import net.minecraftforge.eventbus.api.EventPriority;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//public class ExplorerConfiguredSurfaceBuilders {
//
//    public static ResourceKey<ConfiguredSurfaceBuilder<?>> TEST = key("test");
//    public static ResourceKey<ConfiguredSurfaceBuilder<?>> BAMB00_GROVE = key("bamboo_grove");
//
//    private static ResourceKey<ConfiguredSurfaceBuilder<?>> key(final String name) {
//        return ResourceKey.create(Registry.CONFIGURED_SURFACE_BUILDER_REGISTRY, new ResourceLocation(Explorercraft.MOD_ID, name));
//    }
//
//    @Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//    public static class RegistrationHandler {
//        // Ensure this is run after the SurfaceBuilder DeferredRegister in ModSurfaceBuilders
//        @SubscribeEvent(priority = EventPriority.LOW)
//        public static void register(final RegistryEvent.Register<SurfaceBuilder<?>> event) {
//            register(TEST,
//                    ExplorerSurfaceBuilders.LOGGING_DEFAULT.get()
//                            .configured(new SurfaceBuilderConfig(Blocks.RED_SAND.defaultBlockState(), Blocks.BRICKS.defaultBlockState(), Blocks.GRAVEL.defaultBlockState()))
//            );
//            register(BAMB00_GROVE,
//                    ExplorerSurfaceBuilders.BAMBOO_GROVE_LOG.get()
//                            .configured(SurfaceBuilder.CONFIG_OCEAN_SAND)
//            );
//        }
//
//        private static void register(final ResourceKey<ConfiguredSurfaceBuilder<?>> key, final ConfiguredSurfaceBuilder<?> configuredSurfaceBuilder) {
//            Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, key.location(), configuredSurfaceBuilder);
//        }
//    }
//}