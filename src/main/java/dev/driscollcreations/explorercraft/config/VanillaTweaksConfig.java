package dev.driscollcreations.explorercraft.config;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraftforge.common.ForgeConfigSpec;

public class VanillaTweaksConfig {

    public static ForgeConfigSpec.BooleanValue swimmingHorse;
    public static ForgeConfigSpec.BooleanValue spawnSlimeChunkCaves;
    public static ForgeConfigSpec.BooleanValue spawnNoctilucas;

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
        common.pop();
    }
}
