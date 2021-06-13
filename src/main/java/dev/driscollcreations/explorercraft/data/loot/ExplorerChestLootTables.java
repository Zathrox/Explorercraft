package dev.driscollcreations.explorercraft.data.loot;

import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.util.LootInjector;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksItems;
import net.minecraft.data.loot.ChestLootTables;
import net.minecraft.loot.*;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;

import java.util.function.BiConsumer;

public class ExplorerChestLootTables extends ChestLootTables {

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        consumer.accept(LootInjector.Tables.CHESTS_NETHER_BRIDGE, addJadeToStructures());
        consumer.accept(LootInjector.Tables.CHESTS_BASTION_TREASURE, addJadeToStructures());
        consumer.accept(LootInjector.Tables.CHESTS_BASTION_OTHER, addJadeToStructures());
        consumer.accept(LootInjector.Tables.CHESTS_BASTION_BRIDGE, addJadeToStructures());
        consumer.accept(LootInjector.Tables.CHESTS_RUINED_PORTAL, addJadeToStructures());
        consumer.accept(LootInjector.Tables.CHESTS_SIMPLY_DUNGEON, addHorseArmor());
    }

    private static LootTable.Builder addJadeToStructures() {
        LootTable.Builder builder = LootTable.lootTable();
        builder.withPool(LootPool.lootPool()
                                    .setRolls(ConstantRange.exactly(1))
                                    .bonusRolls(0, 1)
                                    .add(ItemLootEntry.lootTableItem(BambooGroveItems.JADE.get())
                                                      .setWeight(6)
                                                      .apply(SetCount.setCount(RandomValueRange.between(2, 5)))
                                    )
        );
        return builder;
    }

    private static LootTable.Builder addHorseArmor() {
        LootTable.Builder builder = LootTable.lootTable();
        builder.withPool(LootPool.lootPool()
                                 .setRolls(ConstantRange.exactly(1))
                                 .bonusRolls(0, 1)
                                 .add(ItemLootEntry.lootTableItem(BambooGroveItems.JADE_HORSE_ARMOR.get())
                                              .setWeight(1)
                                              .apply(SetCount.setCount(RandomValueRange.between(1, 1)))
                                 )
                                 .add(ItemLootEntry.lootTableItem(VanillaTweaksItems.RUBY_HORSE_ARMOR.get())
                                              .setWeight(1)
                                              .apply(SetCount.setCount(RandomValueRange.between(1, 1)))
                                 )
                                 .add(ItemLootEntry.lootTableItem(VanillaTweaksItems.AMETHYST_HORSE_ARMOR.get())
                                              .setWeight(1)
                                              .apply(SetCount.setCount(RandomValueRange.between(1, 1)))
                                 )
        );
        return builder;
    }

}
