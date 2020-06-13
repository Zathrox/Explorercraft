package com.zathrox.explorercraft.core.config;

import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraftforge.common.ForgeConfigSpec;

public class GeneralConfig {

    public static ForgeConfigSpec.BooleanValue swimmingHorse;
    public static ForgeConfigSpec.BooleanValue spawnNoctilucas;
    public static ForgeConfigSpec.IntValue ashTreeRarity;
    public static ForgeConfigSpec.IntValue slateDungeonRarity;

    public static void init(ForgeConfigSpec.Builder common, ForgeConfigSpec.Builder client) {
        common.push("Vanilla Tweaks");
        common.comment("Control how the vanilla tweaks to the game handle");
        swimmingHorse = common
                .comment("Allow Swimming Horses")
                .translation(Explorercraft.MOD_ID + ".config.swimmingHorse")
                .define("swimmingHorse", true);
        spawnNoctilucas = common
                .comment("Allow noctilucas to spawn naturally in the world")
                .translation(Explorercraft.MOD_ID + ".config.spawnNoctilucas")
                .define("spawnNoctilucas", true);
        ashTreeRarity = common.comment("Ash Tree Rarity")
                .translation(Explorercraft.MOD_ID + ".config.ashTreeRarity")
                .defineInRange("ashTreeRarity", 10, 0, 100);
        slateDungeonRarity = common.comment("Slate Dungeon Rarity")
                .translation(Explorercraft.MOD_ID + ".config.slateDungeonRarity")
                .defineInRange("slateDungeonRarity", 10, 0, 100);
        common.pop();
    }

}

