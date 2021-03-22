package dev.driscollcreations.explorercraft.config;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraftforge.common.ForgeConfigSpec;

public class BambooGroveConfig {

    public static ForgeConfigSpec.BooleanValue spawnBambooGroves;
    public static ForgeConfigSpec.IntValue bambooGroveRarity;
    public static ForgeConfigSpec.BooleanValue spawnJade;
    public static ForgeConfigSpec.BooleanValue spawnRicePaddies;
    public static ForgeConfigSpec.BooleanValue spawnCherryBlossomStructures;
    public static ForgeConfigSpec.BooleanValue spawnToriiGates;

    public static void init(ForgeConfigSpec.Builder common, ForgeConfigSpec.Builder client) {
        common.push("Bamboo Grove World Generation");
        common.comment("Control the various world generation that spawns in the Bamboo Grove, DISABLING these may affect Advancement progression");
        spawnBambooGroves = common
            .comment("Spawn Bamboo Groves in Overworld")
            .translation(Explorercraft.MOD_ID + ".config.spawnBambooGroves")
            .define("spawnBambooGroves", true);

        bambooGroveRarity = common
            .comment("Bamboo Grove rarity - Higher equals more frequent")
            .translation(Explorercraft.MOD_ID + ".config.bambooGroveRarity")
            .defineInRange("bambooGroveRarity", 5, 0, 30);

        spawnJade = common
            .comment("Spawn Jade in the Bamboo Grove")
            .translation(Explorercraft.MOD_ID + ".config.spawnJade")
            .define("spawnJade", true);

        spawnRicePaddies = common
            .comment("Spawn rice paddies in the Bamboo Grove")
            .translation(Explorercraft.MOD_ID + ".config.spawnRicePaddies")
            .define("spawnRicePaddies", true);

        spawnCherryBlossomStructures = common
            .comment("Spawn Cherry blossom center pieces inside the Bamboo Grove")
            .translation(Explorercraft.MOD_ID + ".config.spawnCherryBlossomStructures")
            .define("spawnCherryBlossomStructures", true);

        spawnToriiGates = common
           .comment("Spawn giant Torii gates inside the Bamboo Grove")
           .translation(Explorercraft.MOD_ID + ".config.spawnToriiGates")
           .define("spawnToriiGates", true);

        common.pop();
    }
}
