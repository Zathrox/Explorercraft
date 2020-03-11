package com.zathrox.explorercraft.core.util;

import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID)
public final class LootHandler {

    @SubscribeEvent
    public static void lootLoad(LootTableLoadEvent evt) {
        String prefix = "minecraft:chests/";
        String mob = "minecraft:entities/";
        String village = "minecraft:chests/village/";
        String name = evt.getName().toString();

        if (name.startsWith(prefix)) {
            String file = name.substring(name.indexOf(prefix) + prefix.length());
            switch (file) {
                case "abandoned_mineshaft":
                case "desert_pyramid":
                case "end_city_treasure":
                case "jungle_temple":
                case "simple_dungeon":
                case "nether_bridge":
                case "shipwreck_supply":
                case "shipwreck_treasure":
                case "spawn_bonus_chest":
                case "stronghold_corridor":
                case "stronghold_crossing":
                case "underwater_ruin_big":
                case "underwater_ruin_small":
                case "woodland_mansion":
                    evt.getTable().addPool(getInjectPool(file));
                    break;
                default:
                    break;
            }
        }

        if (name.startsWith(mob)) {
            String file = name.substring(name.indexOf(mob) + mob.length());
            switch (file) {
                case "sheep":
                    evt.getTable().addPool(getInjectPool(file));
                    break;
                default:
                    break;
            }
        }
        if (name.startsWith(village)) {
            String file = name.substring(name.indexOf(mob) + mob.length());
            switch (file) {
                case "village_armorer":
                case "village_butcher":
                case "village_cartographer":
                case "village_desert_house":
                case "village_fisher":
                case "village_fletcher":
                case "village_mason":
                case "village_plains_house":
                case "village_savanna_house":
                case "village_shepherd":
                case "village_snowy_house":
                case "village_taiga_house":
                case "village_tannery":
                case "village_temple":
                case "village_toolsmith":
                case "village_weaponsmith":
                    evt.getTable().addPool(getInjectPool(file));
                    break;
                default:
                    break;
            }
        }
    }

    private static LootPool getInjectPool(String entryName) {
        return LootPool.builder()
                .name("explorercraft_loot")
                .addEntry(getInjectEntry(entryName, 10))
                .bonusRolls(0, 1)
                .build();
    }

    private static LootEntry.Builder getInjectEntry(String name, int weight) {
        ResourceLocation table = new ResourceLocation(Explorercraft.MOD_ID, "inject/" + name);
        return TableLootEntry.builder(table)
                .weight(weight);
    }

}