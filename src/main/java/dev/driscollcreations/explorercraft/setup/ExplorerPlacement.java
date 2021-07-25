package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.cymru.world.HigherThan100SurfacePlacement;
import dev.driscollcreations.explorercraft.cymru.world.LowerThan100SurfacePlacement;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExplorerPlacement {
    public static final DeferredRegister<Placement<?>> DECORATORS = DeferredRegister.create(ForgeRegistries.DECORATORS, Explorercraft.MOD_ID);
    public static final RegistryObject<Placement<NoPlacementConfig>> HIGHER_THAN_100 = DECORATORS.register("higher_than_100", () -> new HigherThan100SurfacePlacement(NoPlacementConfig.CODEC));
    public static final RegistryObject<Placement<NoPlacementConfig>> LOWER_THAN_100 = DECORATORS.register("lower_than_100", () -> new LowerThan100SurfacePlacement(NoPlacementConfig.CODEC));

}
