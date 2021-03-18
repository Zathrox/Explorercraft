package dev.driscollcreations.explorercraft.data.client;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.setup.Registration;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.item.Items;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExplorercraftLootTableProvider extends LootTableProvider {

    public ExplorercraftLootTableProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ExplorercraftBlockLootTables::new, LootParameterSets.BLOCK)/*,
                Pair.of(ExplorercraftEntityLootTables::new, LootParameterSets.ENTITY)*/
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((p_218436_2_, p_218436_3_) -> {
            LootTableManager.validate(validationtracker, p_218436_2_, p_218436_3_);
        });
    }

    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

    public static class ExplorercraftBlockLootTables extends BlockLootTables {

        @Override
        protected void addTables() {

            //======= Bamboo Grove
            dropSelf(BambooGroveBlocks.BAMBOO_BUTTON.get());
            add(BambooGroveBlocks.BAMBOO_DOOR.get(),
                    block -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            dropSelf(BambooGroveBlocks.BAMBOO_FENCE.get());
            dropSelf(BambooGroveBlocks.BAMBOO_FENCE_GATE.get());
            dropSelf(BambooGroveBlocks.BAMBOO_LOG.get());
            add(BambooGroveBlocks.BAMBOO_LEAVES.get(), (leaves) -> {
                return  createLeavesDrops(BambooGroveBlocks.BAMBOO_LEAVES.get(), BambooGroveBlocks.BAMBOO_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES);
            });
            dropSelf(BambooGroveBlocks.BAMBOO_PLANKS.get());
            dropSelf(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get());
            dropSelf(BambooGroveBlocks.BAMBOO_SAPLING.get());
            add(BambooGroveBlocks.BAMBOO_SLAB.get(), BlockLootTables::createSlabItemTable);
            dropSelf(BambooGroveBlocks.BAMBOO_STAIRS.get());
            dropPottedContents(BambooGroveBlocks.POTTED_BAMBOO_SAPLING.get());
            dropSelf(BambooGroveBlocks.BAMBOO_PRESSURE_PLATE.get());
            dropSelf(BambooGroveBlocks.BAMBOO_TRAPDOOR.get());

            dropSelf(BambooGroveBlocks.CHERRY_BUTTON.get());
            add(BambooGroveBlocks.CHERRY_DOOR.get(),
                    block -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            dropSelf(BambooGroveBlocks.CHERRY_FENCE.get());
            dropSelf(BambooGroveBlocks.CHERRY_FENCE_GATE.get());
            dropSelf(BambooGroveBlocks.CHERRY_LOG.get());
            add(BambooGroveBlocks.CHERRY_LEAVES.get(), (leaves) -> {
                return  createLeavesDrops(BambooGroveBlocks.CHERRY_LEAVES.get(), BambooGroveBlocks.CHERRY_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES);
            });
            dropSelf(BambooGroveBlocks.CHERRY_PLANKS.get());
            dropSelf(BambooGroveBlocks.CHERRY_SAPLING.get());
            add(BambooGroveBlocks.CHERRY_SLAB.get(), BlockLootTables::createSlabItemTable);

            dropSelf(BambooGroveBlocks.CHERRY_STAIRS.get());
            dropPottedContents(BambooGroveBlocks.POTTED_CHERRY_SAPLING.get());
            dropSelf(BambooGroveBlocks.CHERRY_PRESSURE_PLATE.get());
            dropSelf(BambooGroveBlocks.CHERRY_TRAPDOOR.get());
            dropSelf(BambooGroveBlocks.CHERRY_WOOD.get());
            dropSelf(BambooGroveBlocks.CHERRY_STRIPPED_LOG.get());
            dropSelf(BambooGroveBlocks.CHERRY_STRIPPED_WOOD.get());

            dropSelf(BambooGroveBlocks.MAPLE_BUTTON.get());
            add(BambooGroveBlocks.MAPLE_DOOR.get(),
                    block -> createSinglePropConditionTable(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            createDoorTable(BambooGroveBlocks.MAPLE_DOOR.get());
            dropSelf(BambooGroveBlocks.MAPLE_FENCE.get());
            dropSelf(BambooGroveBlocks.MAPLE_FENCE_GATE.get());
            dropSelf(BambooGroveBlocks.MAPLE_LOG.get());
            add(BambooGroveBlocks.MAPLE_LEAVES.get(), (leaves) -> {
                return  createLeavesDrops(BambooGroveBlocks.MAPLE_LEAVES.get(), BambooGroveBlocks.MAPLE_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES);
            });
            dropSelf(BambooGroveBlocks.MAPLE_PLANKS.get());
            dropSelf(BambooGroveBlocks.MAPLE_SAPLING.get());
            add(BambooGroveBlocks.MAPLE_SLAB.get(), BlockLootTables::createSlabItemTable);
            dropSelf(BambooGroveBlocks.MAPLE_STAIRS.get());
            dropPottedContents(BambooGroveBlocks.POTTED_MAPLE_SAPLING.get());
            dropSelf(BambooGroveBlocks.MAPLE_PRESSURE_PLATE.get());
            dropSelf(BambooGroveBlocks.MAPLE_TRAPDOOR.get());
            dropSelf(BambooGroveBlocks.MAPLE_WOOD.get());
            dropSelf(BambooGroveBlocks.MAPLE_STRIPPED_LOG.get());
            dropSelf(BambooGroveBlocks.MAPLE_STRIPPED_WOOD.get());

            add(BambooGroveBlocks.TATAMI.get(),
                    block -> createSinglePropConditionTable(block, BedBlock.PART, BedPart.HEAD));
            dropSelf(BambooGroveBlocks.TATAMI_HALF.get());

            dropSelf(BambooGroveBlocks.RICE_STRAW_BLOCK.get());
            add(BambooGroveBlocks.RICE_BASE.get(), noDrop());
            add(BambooGroveBlocks.RICE_TOP.get(), noDrop());

            ILootCondition.IBuilder ilootcondition$ibuilder1 = BlockStateProperty.hasBlockStateProperties(BambooGroveBlocks.RICE_TOP.get()).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CropsBlock.AGE, 7));
            this.add(BambooGroveBlocks.RICE_TOP.get(), createCropDrops(BambooGroveBlocks.RICE_TOP.get(), BambooGroveItems.RICE_STRAW.get(), BambooGroveItems.RICE.get(), ilootcondition$ibuilder1));

            dropSelf(BambooGroveBlocks.JADE_BLOCK.get());
            add(BambooGroveBlocks.JADE_ORE.get(), createOreDrop(BambooGroveBlocks.JADE_ORE.get(), BambooGroveItems.JADE.get()));

            //======== Vanilla Tweaks
            dropSelf(VanillaTweaksBlocks.SLIMEY_STONE.get());
            dropSelf(VanillaTweaksBlocks.DISSOLVED_STONE.get());

        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return Registration.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }

    public static class ExplorercraftEntityLootTables extends EntityLootTables {

        @Override
        public void accept(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        }
    }
}
