package com.zathrox.explorercraft.core.config;

import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraftforge.common.ForgeConfigSpec;

public class EntityConfig {

    public static ForgeConfigSpec.BooleanValue brush_stooge_enabled;
    public static ForgeConfigSpec.BooleanValue enderreeper_enabled;
    public static ForgeConfigSpec.BooleanValue enderreeper_overworld_spawn_enabled;
    public static ForgeConfigSpec.BooleanValue enderreeper_nether_spawn_enabled;
    public static ForgeConfigSpec.BooleanValue enderghast_enabled;
    public static ForgeConfigSpec.BooleanValue infected_creeper_enabled;
    public static ForgeConfigSpec.BooleanValue infected_skeleton_enabled;
    public static ForgeConfigSpec.BooleanValue infected_zombie_enabled;
    public static ForgeConfigSpec.BooleanValue skeletaur_enabled;
    public static ForgeConfigSpec.IntValue wizard_weight;

    public static void init(ForgeConfigSpec.Builder common, ForgeConfigSpec.Builder client) {
        common.push("Spawn Explorercraft Entities");
        brush_stooge_enabled = common
                .comment("Decide if you want the Brush Stooge to spawn in your world")
                .translation(Explorercraft.MOD_ID + ".config.brush_stooge_enabled")
                .define("brush_stooge_enabled", true);
        enderghast_enabled = common
                .comment("Decide if you want the Enderghast to spawn in your world (The End Dimension)")
                .translation(Explorercraft.MOD_ID + ".config.enderghast_enabled")
                .define("enderghast_enabled", true);
        enderreeper_enabled = common
                .comment("Decide if you want the Enderreeper to spawn in your world (The End Dimension)")
                .translation(Explorercraft.MOD_ID + ".config.enderreeper_enabled")
                .define("enderreeper_enabled", true);
        enderreeper_overworld_spawn_enabled = common
                .comment("Decide if you want the Enderreeper to spawn in the overworld (Similar to the current enderman)")
                .translation(Explorercraft.MOD_ID + ".config.enderreeper_overworld_spawn_enabled")
                .define("enderreeper_overworld_spawn_enabled", false);
        enderreeper_nether_spawn_enabled = common
                .comment("Decide if you want the Enderreeper to spawn in the Nether (Similar to current endermen)")
                .translation(Explorercraft.MOD_ID + ".config.enderreeper_nether_spawn_enabled")
                .define("enderreeper_nether_spawn_enabled", false);
        infected_creeper_enabled = common
                .comment("Decide if you want the Infected Creeper to spawn in your world")
                .translation(Explorercraft.MOD_ID + ".config.infected_creeper_enabled")
                .define("infected_creeper_enabled", true);
        infected_skeleton_enabled = common
                .comment("Decide if you want the Infected Skeleton to spawn in your world")
                .translation(Explorercraft.MOD_ID + ".config.infected_skeleton_enabled")
                .define("infested_skeleton_enabled", true);
        infected_zombie_enabled = common
                .comment("Decide if you want the Infected Zombie to spawn in your world")
                .translation(Explorercraft.MOD_ID + ".config.infected_zombie_enabled")
                .define("infected_zombie_enabled", true);
        skeletaur_enabled = common
                .comment("Decide if you want the Skeletaur to spawn in your world")
                .translation(Explorercraft.MOD_ID + ".config.skeletaur_enabled")
                .define("skeletaur_enabled", true);
        wizard_weight = common
                .comment("Decide how frequently you want the wizard tower/wizard to spawn in your world, Wizards will only spawn in a Wizard Tower, no-other places (higher spawns increase changes for dodgey worldgen)")
                .translation(Explorercraft.MOD_ID + ".config.wizard_weight")
                .defineInRange("wizard_weight", 5, 0, 50);
        common.pop();
    }
}
