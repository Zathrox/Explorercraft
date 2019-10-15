package com.zathrox.explorercraft.core.data.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zathrox.explorercraft.core.registry.ExplorerBlocks;
import com.zathrox.explorercraft.core.registry.ExplorerItems;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.state.properties.SlabType;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
import net.minecraft.world.storage.loot.conditions.MatchTool;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraft.world.storage.loot.functions.ApplyBonus;
import net.minecraft.world.storage.loot.functions.ExplosionDecay;
import net.minecraft.world.storage.loot.functions.SetCount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.BiConsumer;

public class ExplorercraftLootTableProvider implements IDataProvider {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final DataGenerator generator;

    public ExplorercraftLootTableProvider(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {
        registerLootTables((id, lootTable) -> {
            final Path path = getPath(id);
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
            } catch (IOException ex) {
                LOGGER.error("Couldn't save loot table {}", path, ex);
            }
        });
    }

    private Path getPath(ResourceLocation id) {
        return generator.getOutputFolder().resolve("data/" + id.getNamespace() + "/loot_tables/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "LootTables";
    }

    protected void registerLootTables(BiConsumer<ResourceLocation, LootTable> consumer) {
        registerBlock(ExplorerBlocks.AMETHYST_ORE, addFortuneBlockLootTable(ExplorerBlocks.AMETHYST_ORE, ExplorerItems.AMETHYST), consumer);
        registerBlock(ExplorerBlocks.JADE_ORE, addFortuneBlockLootTable(ExplorerBlocks.JADE_ORE, ExplorerItems.JADE), consumer);
        registerBlock(ExplorerBlocks.RUBY_ORE, addFortuneBlockLootTable(ExplorerBlocks.RUBY_ORE, ExplorerItems.RUBY), consumer);
        registerBlock(ExplorerBlocks.AMETHYST_BLOCK, addBasicBlockLootTable(ExplorerBlocks.AMETHYST_BLOCK), consumer);
        registerBlock(ExplorerBlocks.JADE_BLOCK, addBasicBlockLootTable(ExplorerBlocks.JADE_BLOCK), consumer);
        registerBlock(ExplorerBlocks.RUBY_BLOCK, addBasicBlockLootTable(ExplorerBlocks.RUBY_BLOCK), consumer);
        registerBlock(ExplorerBlocks.BASALT, addDifferentDropLootTable(ExplorerBlocks.BASALT, ExplorerBlocks.BASALT_COBBLESTONE), consumer);
        registerBlock(ExplorerBlocks.BASALT_SLAB, addSlabBlockLootTable(ExplorerBlocks.BASALT_SLAB), consumer);
    }


    private static void registerBlock(Block block, LootTable lootTable, BiConsumer<ResourceLocation, LootTable> consumer) {
        final ResourceLocation registryName = block.getRegistryName();
        consumer.accept(new ResourceLocation(registryName.getNamespace(), "blocks/" + registryName.getPath()), lootTable);
    }

    private static LootTable addSlabBlockLootTable(Block block){
        return LootTable.builder()
                .setParameterSet(LootParameterSets.BLOCK) //
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(block)
                                .acceptFunction(SetCount.func_215932_a(ConstantRange.of(2))
                                .acceptCondition(BlockStateProperty.builder(block)
                                        .with(SlabBlock.TYPE, SlabType.DOUBLE)))
                                .acceptCondition(SurvivesExplosion.builder())))
                .build();
    }

    private static LootTable addBasicBlockLootTable(IItemProvider itemProvider) {
        return LootTable.builder() //
                .setParameterSet(LootParameterSets.BLOCK) //
                .addLootPool(LootPool.builder() //
                        .rolls(ConstantRange.of(1)) //
                        .addEntry(ItemLootEntry.builder(itemProvider)) //
                        .acceptCondition(SurvivesExplosion.builder())) //
                .build();
    }

    private static LootTable addDifferentDropLootTable(Block block, IItemProvider itemProvider) {
        return LootTable.builder()
                .setParameterSet(LootParameterSets.BLOCK)
                .addLootPool(LootPool.builder()
                        .rolls(ConstantRange.of(1))
                        .addEntry(ItemLootEntry.builder(block)
                                .acceptCondition(MatchTool.builder(ItemPredicate.Builder.create()
                                        .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1))))
                                ).func_216080_a(ItemLootEntry.builder(itemProvider)
                                        .acceptCondition(SurvivesExplosion.builder()))))
                        .build();

    }

    private static LootTable addFortuneBlockLootTable(Block block, IItemProvider itemProvider) {
        return LootTable.builder() //
                .setParameterSet(LootParameterSets.BLOCK) //
                .addLootPool(LootPool.builder() //
                        .rolls(ConstantRange.of(1)) //
                        .addEntry(ItemLootEntry.builder(block) //
                                .acceptCondition(MatchTool.builder(ItemPredicate.Builder.create() //
                                        .enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.IntBound.atLeast(1)))) //
                                ).func_216080_a(ItemLootEntry.builder(itemProvider) //
                                        .acceptFunction(ApplyBonus.func_215869_a(Enchantments.FORTUNE)) //
                                        .acceptFunction(ExplosionDecay.func_215863_b()))))
                .build();
    }
}
