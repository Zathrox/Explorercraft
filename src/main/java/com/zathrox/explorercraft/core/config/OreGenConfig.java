package com.zathrox.explorercraft.core.config;

import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraftforge.common.ForgeConfigSpec;

public class OreGenConfig {

    public static ForgeConfigSpec.BooleanValue spawnBasalt;
    public static ForgeConfigSpec.BooleanValue spawnMarble;
    public static ForgeConfigSpec.BooleanValue spawnAmethyst;
    public static ForgeConfigSpec.BooleanValue spawnJade;
    public static ForgeConfigSpec.BooleanValue spawnRuby;

    public static ForgeConfigSpec.IntValue basaltVeinSize;
    public static ForgeConfigSpec.IntValue marbleVeinSize;
    public static ForgeConfigSpec.IntValue marbleChance;
    public static ForgeConfigSpec.IntValue basaltChance;

    public static void init(ForgeConfigSpec.Builder common, ForgeConfigSpec.Builder client) {
        common.push("Spawn World Generation");
        common.comment("Control the various world generation that spawns in Explorercraft, DISABLING these may affect Advancement progression");
        spawnBasalt = common
                .comment("Spawn Basalt in Overworld")
                .translation(Explorercraft.MOD_ID + ".config.spawnBasalt")
                .define("spawnBasalt", true);
        spawnMarble = common
                .comment("Spawn Marble in Overworld")
                .translation(Explorercraft.MOD_ID + ".config.spawnMarble")
                .define("spawnMarble", true);
        spawnAmethyst = common
                .comment("Spawn Amethysts in Overworld")
                .translation(Explorercraft.MOD_ID + ".config.spawnAmethyst")
                .define("spawnAmethyst", true);
        spawnJade = common
                .comment("Spawn Jade in Overworld")
                .translation(Explorercraft.MOD_ID + ".config.spawnJade")
                .define("spawnJade", true);
        spawnRuby = common
                .comment("Spawn Ruby in Overworld")
                .translation(Explorercraft.MOD_ID + ".config.spawnRuby")
                .define("spawnRuby", true);
        common.pop();

        common.push("World Generation Controllers");
        common.comment("Fine tune the world generation that spawns in Explorercraft");
        basaltVeinSize = common
                .comment("Size of Basalt Vein")
                .translation(Explorercraft.MOD_ID + ".config.basaltVeinSize")
                .defineInRange("basaltVeinSize", 33, 0, 33);
        marbleVeinSize = common
                .comment("Size of Marble Vein")
                .translation(Explorercraft.MOD_ID + ".config.marbleVeinSize")
                .defineInRange("marbleVeinSize", 33, 0, 33);
        basaltChance = common
                .comment("Chance of spawning Basalt")
                .translation(Explorercraft.MOD_ID + ".config.basaltChance")
                .defineInRange("basaltChance", 10, 0, 100);
        marbleChance = common
                .comment("Chance of spawning Marble")
                .translation(Explorercraft.MOD_ID + ".config.marbleChance")
                .defineInRange("marbleChance", 10, 0, 100);
        common.pop();
    }

}
