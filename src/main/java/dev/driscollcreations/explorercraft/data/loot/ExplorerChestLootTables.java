package dev.driscollcreations.explorercraft.data.loot;

import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.util.LootInjector;
import net.minecraft.data.loot.ChestLoot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public class ExplorerChestLootTables extends ChestLoot {

    @Override
    public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        consumer.accept(LootInjector.Tables.CHESTS_NETHER_BRIDGE, addJadeToStructures());
        consumer.accept(LootInjector.Tables.CHESTS_BASTION_TREASURE, addJadeToStructures());
        consumer.accept(LootInjector.Tables.CHESTS_BASTION_OTHER, addJadeToStructures());
        consumer.accept(LootInjector.Tables.CHESTS_BASTION_BRIDGE, addJadeToStructures());
        consumer.accept(LootInjector.Tables.CHESTS_RUINED_PORTAL, addJadeToStructures());
        //consumer.accept(LootInjector.Tables.CHESTS_RUINED_PORTAL, addHorseArmor());
    }

    private static LootTable.Builder addJadeToStructures() {
        LootTable.Builder builder = LootTable.lootTable();
        builder.withPool(LootPool.lootPool()
                                    .setRolls(ConstantValue.exactly(1))
                                    .bonusRolls(0, 1)
                                    .add(LootItem.lootTableItem(BambooGroveItems.JADE.get())
                                                      .setWeight(6)
                                                      .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5)))
                                    )
        );
        return builder;
    }

//    private static LootTable.Builder addHorseArmor() {
//        LootTable.Builder builder = LootTable.lootTable();
//        builder.withPool(LootPool.lootPool()
//                                 .setRolls(ConstantValue.exactly(1))
//                                 .add(ItemLootEntry.lootTableItem(BambooGroveItems.JADE_HORSE_ARMOR.get())
//                                              .setWeight(10)
//                                 )
//                                 .add(ItemLootEntry.lootTableItem(VanillaTweaksItems.RUBY_HORSE_ARMOR.get())
//                                              .setWeight(10)
//                                 )
//                                 .add(ItemLootEntry.lootTableItem(VanillaTweaksItems.AMETHYST_HORSE_ARMOR.get())
//                                              .setWeight(10)
//                                 )
//        );
//        return builder;
//    }

}
