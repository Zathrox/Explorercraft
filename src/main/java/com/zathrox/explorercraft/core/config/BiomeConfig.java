package com.zathrox.explorercraft.core.config;

import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraftforge.common.ForgeConfigSpec;

public class BiomeConfig {

    public static ForgeConfigSpec.BooleanValue spawnBambooForest;
    public static ForgeConfigSpec.BooleanValue spawnForestedMountain;
    public static ForgeConfigSpec.BooleanValue spawnSnowdonia;
    public static ForgeConfigSpec.BooleanValue spawnFungalForest;

    public static ForgeConfigSpec.IntValue bambooForestWeight;
    public static ForgeConfigSpec.IntValue forestedMountainWeight;
    public static ForgeConfigSpec.IntValue snowdoniaWeight;
    public static ForgeConfigSpec.IntValue fungalForestWeight;

    public static void init(ForgeConfigSpec.Builder common, ForgeConfigSpec.Builder client) {
        common.push("Spawn Biomes");
        common.comment("Disable spawning of the biomes in the mod, DISABLING these may affect Advancement progression");
        spawnBambooForest = common
                .comment("Spawn Bamboo Forests in Overworld")
                .translation(Explorercraft.MOD_ID + ".config.spawnBambooForest")
                .define("spawnBambooForest", true);
        spawnForestedMountain = common
                .comment("Spawn Forested Mountains in Overworld")
                .translation(Explorercraft.MOD_ID + ".config.spawnForestedMountain")
                .define("spawnForestedMountain", true);
        spawnSnowdonia = common
                .comment("Spawn Snowdonia in Overworld")
                .translation(Explorercraft.MOD_ID + ".config.spawnSnowdonia")
                .define("spawnSnowdonia", true);
        spawnFungalForest = common
                .comment("Spawn Fungal Forests in Overworld")
                .translation(Explorercraft.MOD_ID + ".config.spawnFungalForest")
                .define("spawnFungalForest", true);
        common.pop();

        common.push("Biome Controllers");
        common.comment("Various settings for controlling the biomes in the mod: Plains(Default: 10)");
        bambooForestWeight = common.comment("Bamboo Forest Weight")
                .translation(Explorercraft.MOD_ID + ".config.bambooForestWeight")
                .defineInRange("bambooForestWeight", 8, 0, 100);
        forestedMountainWeight = common
                .comment("Forested Mountain Weight")
                .translation(Explorercraft.MOD_ID + ".config.forestedMountainWeight")
                .defineInRange("forestedMountainWeight", 8, 0, 100);
        snowdoniaWeight = common
                .comment("Snowdonia Weight")
                .translation(Explorercraft.MOD_ID + ".config.snowdoniaWeight")
                .defineInRange("snowdoniaWeight", 8, 0, 100);
        fungalForestWeight = common
                .comment("Fungal Forests Weight")
                .translation(Explorercraft.MOD_ID + ".config.fungalForestWeight")
                .defineInRange("fungalForestWeight", 8, 0, 100);
        common.pop();
    }

}