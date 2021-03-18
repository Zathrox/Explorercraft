package dev.driscollcreations.explorercraft.util;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

//Based off Abnormals-Core TradeUtils (didn't want to create a dependancy just yet) and i'll need custom trades and utils later for my wizard

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID)
public final class ExplorerTrades {


    @SubscribeEvent
    public static void onPlayerMount(EntityMountEvent event) {
        Entity mountingEntity = event.getEntityMounting();
        Entity entityBeingMounted = event.getEntityBeingMounted();
        if (mountingEntity instanceof PlayerEntity && entityBeingMounted instanceof AbstractHorseEntity) {
            PlayerEntity player = (PlayerEntity) mountingEntity;
            AbstractHorseEntity horse = (AbstractHorseEntity) entityBeingMounted;
            if (!horse.isTamed() && player.isCreative()) {
                Explorercraft.LOGGER.error("Horse was tamed instantly");
                horse.setOwnerUUID(player.getUUID());
                horse.setTamed(true);
            }
        }
    }


    @SubscribeEvent
    public static void onWandererTradesEvent(WandererTradesEvent event) {
        ExplorerTrades.addWandererTrades(event,
                new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.BAMBOO_SAPLING.get().asItem(), 1, 12, 1)
        );

        ExplorerTrades.addRareWandererTrades(event,
                new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.CHERRY_SAPLING.get().asItem(), 1, 12, 1),
                new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.MAPLE_SAPLING.get().asItem(), 1, 12, 1)
        );
    }

    @SubscribeEvent
    public static void onVillagerTradesEvent(VillagerTradesEvent event) {
        ExplorerTrades.addVillagerTrades(event, VillagerProfession.FARMER, ExplorerTrades.NOVICE,
                new ExplorerTrades.ExplorerTrade(1, BambooGroveItems.RICE.get(), 32, 16, 2)
        );
    }

    public static final int NOVICE = 1;
    public static final int APPRENTICE = 2;
    public static final int JOURNEYMAN = 3;
    public static final int EXPERT = 4;
    public static final int MASTER = 5;

    public static class ExplorerTrade extends BasicTrade {
        public ExplorerTrade(ItemStack input, ItemStack input2, ItemStack output, int maxTrades, int xp, float priceMult) {
            super(input, input2, output, maxTrades, xp, priceMult);
        }

        public ExplorerTrade(Item input, int inputCount, Item output, int outputCount, int maxTrades, int xp, float priceMult) {
            this(new ItemStack(input, inputCount), ItemStack.EMPTY, new ItemStack(output, outputCount), maxTrades, xp, priceMult);
        }

        public ExplorerTrade(Item input, int inputCount, Item output, int outputCount, int maxTrades, int xp) {
            this(input, inputCount, output, outputCount, maxTrades, xp, 0.15F);
        }

        public ExplorerTrade(Item input, int inputCount, int emeraldCount, int maxTrades, int xp, float priceMult) {
            this(new ItemStack(input, inputCount), ItemStack.EMPTY, new ItemStack(Items.EMERALD, emeraldCount), maxTrades, xp, priceMult);
        }

        public ExplorerTrade(Item input, int inputCount, int emeraldCount, int maxTrades, int xp) {
            this(input, inputCount, emeraldCount, maxTrades, xp, 0.15F);
        }

        public ExplorerTrade(int emeraldCount, Item output, int outputCount, int maxTrades, int xp, float priceMult) {
            this(new ItemStack(Items.EMERALD, emeraldCount), ItemStack.EMPTY, new ItemStack(output, outputCount), maxTrades, xp, priceMult);
        }

        public ExplorerTrade(int emeraldCount, Item output, int outputCount, int maxTrades, int xp) {
            this(emeraldCount, output, outputCount, maxTrades, xp, 0.15F);
        }
    }

    public static void addVillagerTrades(VillagerTradesEvent event, int level, VillagerTrades.ITrade... trades) {
        for (VillagerTrades.ITrade trade : trades) event.getTrades().get(level).add(trade);
    }

    public static void addVillagerTrades(VillagerTradesEvent event, VillagerProfession profession, int level, VillagerTrades.ITrade... trades) {
        if (event.getType() == profession) addVillagerTrades(event, level, trades);
    }

    public static void addWandererTrades(WandererTradesEvent event, VillagerTrades.ITrade... trades) {
        for (VillagerTrades.ITrade trade : trades) event.getGenericTrades().add(trade);
    }

    public static void addRareWandererTrades(WandererTradesEvent event, VillagerTrades.ITrade... trades) {
        for (VillagerTrades.ITrade trade : trades) event.getRareTrades().add(trade);
    }

    public static void addCompatVillagerTrades(VillagerTradesEvent event, String modid, int level, VillagerTrades.ITrade... trades) {
        if (ModList.get().isLoaded(modid)) addVillagerTrades(event, level, trades);
    }

    public static void addCompatVillagerTrades(VillagerTradesEvent event, String modid, VillagerProfession profession, int level, VillagerTrades.ITrade... trades) {
        if (ModList.get().isLoaded(modid)) addVillagerTrades(event, profession, level, trades);
    }

    public static void addCompatWandererTrades(WandererTradesEvent event, String modid, VillagerTrades.ITrade... trades) {
        if (ModList.get().isLoaded(modid)) addWandererTrades(event, trades);
    }

    public static void addCompatRareWandererTrades(WandererTradesEvent event, String modid, VillagerTrades.ITrade... trades) {
        if (ModList.get().isLoaded(modid)) addRareWandererTrades(event, trades);
    }
}