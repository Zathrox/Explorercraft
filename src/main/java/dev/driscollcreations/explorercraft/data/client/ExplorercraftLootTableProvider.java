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
            LootTableManager.validateLootTable(validationtracker, p_218436_2_, p_218436_3_);
        });
    }

    private static final float[] DEFAULT_SAPLING_DROP_RATES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] RARE_SAPLING_DROP_RATES = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

    public static class ExplorercraftBlockLootTables extends BlockLootTables {

        @Override
        protected void addTables() {

            //======= Bamboo Grove
            registerDropSelfLootTable(BambooGroveBlocks.BAMBOO_BUTTON.get());
            registerLootTable(BambooGroveBlocks.BAMBOO_DOOR.get(),
                    block -> droppingWhen(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            registerDropSelfLootTable(BambooGroveBlocks.BAMBOO_FENCE.get());
            registerDropSelfLootTable(BambooGroveBlocks.BAMBOO_FENCE_GATE.get());
            registerDropSelfLootTable(BambooGroveBlocks.BAMBOO_LOG.get());
            registerLootTable(BambooGroveBlocks.BAMBOO_LEAVES.get(), (leaves) -> {
                return  droppingWithChancesAndSticks(BambooGroveBlocks.BAMBOO_LEAVES.get(), BambooGroveBlocks.BAMBOO_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES);
            });
            registerDropSelfLootTable(BambooGroveBlocks.BAMBOO_PLANKS.get());
            registerDropSelfLootTable(BambooGroveBlocks.BAMBOO_PLANKS_VERTICAL.get());
            registerDropSelfLootTable(BambooGroveBlocks.BAMBOO_SAPLING.get());
            registerLootTable(BambooGroveBlocks.BAMBOO_SLAB.get(), BlockLootTables::droppingSlab);
            registerDropSelfLootTable(BambooGroveBlocks.BAMBOO_STAIRS.get());
            registerFlowerPot(BambooGroveBlocks.POTTED_BAMBOO_SAPLING.get());
            registerDropSelfLootTable(BambooGroveBlocks.BAMBOO_PRESSURE_PLATE.get());
            registerDropSelfLootTable(BambooGroveBlocks.BAMBOO_TRAPDOOR.get());

            registerDropSelfLootTable(BambooGroveBlocks.CHERRY_BUTTON.get());
            registerLootTable(BambooGroveBlocks.CHERRY_DOOR.get(),
                    block -> droppingWhen(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            registerDropSelfLootTable(BambooGroveBlocks.CHERRY_FENCE.get());
            registerDropSelfLootTable(BambooGroveBlocks.CHERRY_FENCE_GATE.get());
            registerDropSelfLootTable(BambooGroveBlocks.CHERRY_LOG.get());
            registerLootTable(BambooGroveBlocks.CHERRY_LEAVES.get(), (leaves) -> {
                return  droppingWithChancesAndSticks(BambooGroveBlocks.CHERRY_LEAVES.get(), BambooGroveBlocks.CHERRY_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES);
            });
            registerDropSelfLootTable(BambooGroveBlocks.CHERRY_PLANKS.get());
            registerDropSelfLootTable(BambooGroveBlocks.CHERRY_SAPLING.get());
            registerLootTable(BambooGroveBlocks.CHERRY_SLAB.get(), BlockLootTables::droppingSlab);

            registerDropSelfLootTable(BambooGroveBlocks.CHERRY_STAIRS.get());
            registerFlowerPot(BambooGroveBlocks.POTTED_CHERRY_SAPLING.get());
            registerDropSelfLootTable(BambooGroveBlocks.CHERRY_PRESSURE_PLATE.get());
            registerDropSelfLootTable(BambooGroveBlocks.CHERRY_TRAPDOOR.get());
            registerDropSelfLootTable(BambooGroveBlocks.CHERRY_WOOD.get());
            registerDropSelfLootTable(BambooGroveBlocks.CHERRY_STRIPPED_LOG.get());
            registerDropSelfLootTable(BambooGroveBlocks.CHERRY_STRIPPED_WOOD.get());

            registerDropSelfLootTable(BambooGroveBlocks.MAPLE_BUTTON.get());
            registerLootTable(BambooGroveBlocks.MAPLE_DOOR.get(),
                    block -> droppingWhen(block, DoorBlock.HALF, DoubleBlockHalf.LOWER));
            registerDoor(BambooGroveBlocks.MAPLE_DOOR.get());
            registerDropSelfLootTable(BambooGroveBlocks.MAPLE_FENCE.get());
            registerDropSelfLootTable(BambooGroveBlocks.MAPLE_FENCE_GATE.get());
            registerDropSelfLootTable(BambooGroveBlocks.MAPLE_LOG.get());
            registerLootTable(BambooGroveBlocks.MAPLE_LEAVES.get(), (leaves) -> {
                return  droppingWithChancesAndSticks(BambooGroveBlocks.MAPLE_LEAVES.get(), BambooGroveBlocks.MAPLE_SAPLING.get(), DEFAULT_SAPLING_DROP_RATES);
            });
            registerDropSelfLootTable(BambooGroveBlocks.MAPLE_PLANKS.get());
            registerDropSelfLootTable(BambooGroveBlocks.MAPLE_SAPLING.get());
            registerLootTable(BambooGroveBlocks.MAPLE_SLAB.get(), BlockLootTables::droppingSlab);
            registerDropSelfLootTable(BambooGroveBlocks.MAPLE_STAIRS.get());
            registerFlowerPot(BambooGroveBlocks.POTTED_MAPLE_SAPLING.get());
            registerDropSelfLootTable(BambooGroveBlocks.MAPLE_PRESSURE_PLATE.get());
            registerDropSelfLootTable(BambooGroveBlocks.MAPLE_TRAPDOOR.get());
            registerDropSelfLootTable(BambooGroveBlocks.MAPLE_WOOD.get());
            registerDropSelfLootTable(BambooGroveBlocks.MAPLE_STRIPPED_LOG.get());
            registerDropSelfLootTable(BambooGroveBlocks.MAPLE_STRIPPED_WOOD.get());

            registerLootTable(BambooGroveBlocks.TATAMI.get(),
                    block -> droppingWhen(block, BedBlock.PART, BedPart.HEAD));
            registerDropSelfLootTable(BambooGroveBlocks.TATAMI_HALF.get());

            registerDropSelfLootTable(BambooGroveBlocks.RICE_STRAW_BLOCK.get());
            registerLootTable(BambooGroveBlocks.RICE_BASE.get(), blockNoDrop());
            registerLootTable(BambooGroveBlocks.RICE_TOP.get(), blockNoDrop());

            ILootCondition.IBuilder ilootcondition$ibuilder1 = BlockStateProperty.builder(BambooGroveBlocks.RICE_TOP.get()).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(CropsBlock.AGE, 7));
            this.registerLootTable(BambooGroveBlocks.RICE_TOP.get(), droppingAndBonusWhen(BambooGroveBlocks.RICE_TOP.get(), BambooGroveItems.RICE_STRAW.get(), BambooGroveItems.RICE.get(), ilootcondition$ibuilder1));

            registerDropSelfLootTable(BambooGroveBlocks.JADE_BLOCK.get());
            registerLootTable(BambooGroveBlocks.JADE_ORE.get(), droppingItemWithFortune(BambooGroveBlocks.JADE_ORE.get(), BambooGroveItems.JADE.get()));

            //======== Vanilla Tweaks
            registerDropSelfLootTable(VanillaTweaksBlocks.SLIMEY_STONE.get());
            registerDropSelfLootTable(VanillaTweaksBlocks.DISSOLVED_STONE.get());

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
