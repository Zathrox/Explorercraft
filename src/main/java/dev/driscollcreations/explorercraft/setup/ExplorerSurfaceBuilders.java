package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.world.gen.surfacebuilders.LoggingSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;

public class ExplorerSurfaceBuilders {

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> LOGGING_DEFAULT = Registration.SURFACE_BUILDERS.register(
            "logging_default",
            () -> new LoggingSurfaceBuilder<>(() -> SurfaceBuilder.DEFAULT, SurfaceBuilderConfig.CODEC)
    );

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> BAMBOO_GROVE_LOG = Registration.SURFACE_BUILDERS.register(
            "bamboo_grove_log",
            () -> new LoggingSurfaceBuilder<>(() -> SurfaceBuilder.GIANT_TREE_TAIGA, SurfaceBuilderConfig.CODEC)
    );

    static void register() {}

}
