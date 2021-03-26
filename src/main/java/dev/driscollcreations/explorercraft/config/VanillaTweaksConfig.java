package dev.driscollcreations.explorercraft.config;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraftforge.common.ForgeConfigSpec;

public class VanillaTweaksConfig {

    public static ForgeConfigSpec.BooleanValue swimmingHorse;
    public static ForgeConfigSpec.BooleanValue spawnSlimeChunkCaves;
    public static ForgeConfigSpec.BooleanValue spawnNoctilucas;
    public static ForgeConfigSpec.BooleanValue spawnForestedMountain;
    public static ForgeConfigSpec.IntValue forestedMountainRarity;
    public static ForgeConfigSpec.BooleanValue spawnMarbleInForestedMountains;
    public static ForgeConfigSpec.BooleanValue spawnMarbleInOverworld;
    public static ForgeConfigSpec.IntValue marbleVeinSizeInForestedMountains;
    public static ForgeConfigSpec.IntValue marbleChanceInForestedMountains;
    public static ForgeConfigSpec.IntValue marbleVeinSizeInOverworld;
    public static ForgeConfigSpec.IntValue marbleChanceInOverworld;

    public static void init(ForgeConfigSpec.Builder common, ForgeConfigSpec.Builder client) {

        common.push("Vanilla Tweaks");
        common.comment("Control how the vanilla tweaks to the game function");
        swimmingHorse = common
            .comment("Allow Horses to swim - (They wont buck you off if in deep water)")
            .translation(Explorercraft.MOD_ID + ".config.swimmingHorse")
            .define("swimmingHorse", true);

        spawnSlimeChunkCaves = common
            .comment("Spawn slimey caves in Overworld")
            .translation(Explorercraft.MOD_ID + ".config.spawnSpawnChunkCaves")
            .define("spawnSpawnChunkCaves", true);

        spawnNoctilucas = common
            .comment("Allow noctilucas to spawn naturally in the world")
            .translation(Explorercraft.MOD_ID + ".config.spawnNoctilucas")
            .define("spawnNoctilucas", true);

        spawnForestedMountain = common
            .comment("Spawn Forested Mountains in Overworld")
            .translation(Explorercraft.MOD_ID + ".config.spawnForestedMountain")
            .define("spawnForestedMountain", true);

        forestedMountainRarity = common
            .comment("Forested Mountain rarity - Higher equals more frequent")
            .translation(Explorercraft.MOD_ID + ".config.forestedMountainRarity")
            .defineInRange("forestedMountainRarity", 5, 0, 100);

        spawnMarbleInForestedMountains = common
            .comment("Spawn Marble in Forested Mountain (has a much higher ratio)")
            .translation(Explorercraft.MOD_ID + ".config.spawnMarbleInForestedMountains")
            .define("spawnMarbleInForestedMountains", true);

        spawnMarbleInOverworld = common
            .comment("Spawn Marble in Overworld")
            .translation(Explorercraft.MOD_ID + ".config.spawnMarbleInOverworld")
            .define("spawnMarbleInOverworld", true);

        marbleVeinSizeInForestedMountains = common
            .comment("Size of Marble vein in the Forested Mountain biome")
            .translation(Explorercraft.MOD_ID + ".config.marbleVeinSizeInForestedMountains")
            .defineInRange("marbleVeinSizeInForestedMountains", 64, 0, 64);

        marbleChanceInForestedMountains = common
            .comment("Chance of spawning Marble in the Forested Mountain biome")
            .translation(Explorercraft.MOD_ID + ".config.marbleChanceInForestedMountains")
            .defineInRange("marbleChanceInForestedMountains", 60, 0, 100);

        marbleVeinSizeInOverworld = common
            .comment("Size of Marble vein generated all over the Overworld")
            .translation(Explorercraft.MOD_ID + ".config.marbleVeinSizeInOverworld")
            .defineInRange("marbleVeinSizeInOverworld", 20, 0, 64);

        marbleChanceInOverworld = common
           .comment("Chance of spawning Marble in the Overworld")
           .translation(Explorercraft.MOD_ID + ".config.marbleChanceInOverworld")
           .defineInRange("marbleChanceInOverworld", 10, 0, 100);

        common.pop();
    }
}
