package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.world.gen.surfacebuilders.LoggingSurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.level.levelgen.surfacebuilders.SurfaceBuilderBaseConfiguration;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ExplorerSurfaceBuilders {

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderBaseConfiguration>> LOGGING_DEFAULT = Registration.SURFACE_BUILDERS.register(
            "logging_default",
            () -> new LoggingSurfaceBuilder<>(() -> SurfaceBuilder.DEFAULT, SurfaceBuilderBaseConfiguration.CODEC)
    );

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderBaseConfiguration>> BAMBOO_GROVE_LOG = Registration.SURFACE_BUILDERS.register(
            "bamboo_grove_log",
            () -> new LoggingSurfaceBuilder<>(() -> SurfaceBuilder.GIANT_TREE_TAIGA, SurfaceBuilderBaseConfiguration.CODEC)
    );

    static void register() {}

}
