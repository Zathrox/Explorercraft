package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.cymru.world.HigherThan100Placement;
import dev.driscollcreations.explorercraft.cymru.world.LowerThan100Placement;
import net.minecraft.world.level.levelgen.feature.configurations.HeightmapConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExplorerPlacement {
    public static final DeferredRegister<FeatureDecorator<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.DECORATORS, Explorercraft.MOD_ID);
    public static final RegistryObject<FeatureDecorator<HeightmapConfiguration>> HIGHER_THAN_100 = DECORATORS.register("higher_than_100", () -> new HigherThan100Placement(HeightmapConfiguration.CODEC));
    public static final RegistryObject<FeatureDecorator<HeightmapConfiguration>> LOWER_THAN_100 = DECORATORS.register("lower_than_100", () -> new LowerThan100Placement(HeightmapConfiguration.CODEC));
}
