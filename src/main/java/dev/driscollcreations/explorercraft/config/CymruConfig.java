package dev.driscollcreations.explorercraft.config;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraftforge.common.ForgeConfigSpec;

public class CymruConfig {

    public static ForgeConfigSpec.BooleanValue spawnSnowdonia;
    public static ForgeConfigSpec.IntValue snowdoniaRarity;
    public static ForgeConfigSpec.BooleanValue spawnSlateInSnowdonia;
    public static ForgeConfigSpec.IntValue slateVeinSizeInSnowdonia;
    public static ForgeConfigSpec.IntValue slateChanceInSnowdonia;
    public static ForgeConfigSpec.BooleanValue spawnExtraGoldOre;
    public static ForgeConfigSpec.BooleanValue spawnExtraCoalOre;
    public static ForgeConfigSpec.IntValue cawlStackSize;

    public static void init(ForgeConfigSpec.Builder common, ForgeConfigSpec.Builder client) {

        common.push("Vanilla Tweaks");
        common.comment("Control how the vanilla tweaks to the game function");
        spawnSnowdonia = common
            .comment("Spawn Snowdonia biome in Overworld")
            .translation(Explorercraft.MOD_ID + ".config.spawnSnowdonia")
            .define("spawnSnowdonia", true);

        snowdoniaRarity = common
            .comment("Snowdonia rarity - Higher equals more frequent")
            .translation(Explorercraft.MOD_ID + ".config.snowdoniaRarity")
            .defineInRange("snowdoniaRarity", 2, 0, 100);

        spawnSlateInSnowdonia = common
            .comment("Spawn Slate in Snowdonia (has a much higher ratio)")
            .translation(Explorercraft.MOD_ID + ".config.spawnSlateInSnowdonia")
            .define("spawnSlateInSnowdonia", true);

        slateVeinSizeInSnowdonia = common
            .comment("Size of Slate vein in the Snowdonia")
            .translation(Explorercraft.MOD_ID + ".config.slateVeinSizeInSnowdonia")
            .defineInRange("slateVeinSizeInSnowdonia", 44, 0, 64);

        slateChanceInSnowdonia = common
            .comment("Chance of spawning Slate in theSnowdonia")
            .translation(Explorercraft.MOD_ID + ".config.slateChanceInSnowdonia")
            .defineInRange("slateChanceInSnowdonia", 40, 0, 100);

        spawnExtraGoldOre = common
            .comment("Spawn extra gold ore in Snowdonia")
            .translation(Explorercraft.MOD_ID + ".config.spawnExtraGoldOre")
            .define("spawnExtraGoldOre", true);

        spawnExtraCoalOre = common
            .comment("Spawn extra coal ore in Snowdonia")
            .translation(Explorercraft.MOD_ID + ".config.spawnExtraCoalOre")
            .define("spawnExtraCoalOre", true);

        cawlStackSize = common
             .comment("Sets the max stack size for cawl")
             .translation(Explorercraft.MOD_ID + ".config.cawlStackSize")
             .defineInRange("cawlStackSize", 16, 1, 64);

        common.pop();
    }
}
