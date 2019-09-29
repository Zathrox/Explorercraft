package com.zathrox.explorercraft.core.config;

import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraftforge.common.ForgeConfigSpec;

public class GeneralConfig {

    public static ForgeConfigSpec.BooleanValue swimmingHorse;

    public static void init(ForgeConfigSpec.Builder common, ForgeConfigSpec.Builder client) {
        common.push("Vanilla Tweaks");
        common.comment("Control how the vanilla tweaks to the game handle");
        swimmingHorse = common
                .comment("Allow Swimming Horses")
                .translation(Explorercraft.MOD_ID + ".config.swimmingHorse")
                .define("swimmingHorse", true);
        common.pop();
    }

}

