package dev.driscollcreations.explorercraft.config;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    private static final ForgeConfigSpec.Builder common_builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec common_config;

    private static final ForgeConfigSpec.Builder client_builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec client_config;

    static {

        BambooGroveConfig.init(common_builder, client_builder);
        VanillaTweaksConfig.init(common_builder, client_builder);

        common_config = common_builder.build();
        client_config = client_builder.build();

    }

    public static void init() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, common_config);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, client_config);
    }

}