package dev.driscollcreations.explorercraft.util;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveItems;
import dev.driscollcreations.explorercraft.cymru.items.CymruItems;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Random;

//Based off Abnormals-Core TradeUtils (didn't want to create a dependancy just yet) and i'll need custom trades and utils later for my wizard

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID)
public final class ExplorerTrades {

    @SubscribeEvent
    public static void onPlayerMount(EntityMountEvent event) {
        Entity mountingEntity = event.getEntityMounting();
        Entity entityBeingMounted = event.getEntityBeingMounted();
        if (mountingEntity instanceof Player && entityBeingMounted instanceof AbstractHorse) {
            Player player = (Player) mountingEntity;
            AbstractHorse horse = (AbstractHorse) entityBeingMounted;
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
                new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.BAMBOO_SAPLING.get().asItem(), 1, 3, 1)
        );

        ExplorerTrades.addRareWandererTrades(event,
                new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.CHERRY_SAPLING.get().asItem(), 1, 3, 1),
                new ExplorerTrades.ExplorerTrade(1, BambooGroveBlocks.MAPLE_SAPLING.get().asItem(), 1, 3, 1),
                new ExplorerTrades.ExplorerTrade(1, BambooGroveItems.CHERRY_BLOSSOM.get().asItem(), 1, 12, 1)
        );
    }

    @SubscribeEvent
    public static void onVillagerTradesEvent(VillagerTradesEvent event) {
        ExplorerTrades.addVillagerTrades(event, VillagerProfession.FARMER, ExplorerTrades.NOVICE,
                new ExplorerTrades.ExplorerTrade(BambooGroveItems.RICE.get(), 32, 1, 16, 2),
                new ExplorerTrades.ExplorerTrade(CymruItems.LEEK.get(), 22, 1, 16, 2)
        );

        ExplorerTrades.addVillagerTrades(event, VillagerProfession.BUTCHER, ExplorerTrades.JOURNEYMAN,
                new ExplorerTrades.ExplorerTrade(CymruItems.LAMB_SHANK.get(), 7, 1, 16, 5)
        );

        ExplorerTrades.addVillagerTrades(event, VillagerProfession.CLERIC, ExplorerTrades.JOURNEYMAN,
                new ExplorerTrades.ExplorerTrade(VanillaTweaksItems.NOCTILUCA.get(), 5, 1, 8, 5)
        );

//        ExplorerTrades.addVillagerTrades(event, VillagerProfession.CARTOGRAPHER, ExplorerTrades.MASTER,
//                new ExplorerTrades.EmeraldForMapTrade(32, ExplorerStructures.TEMPLE_RUINS.get(), MapDecoration.Type.TARGET_X, 12, 10)
//        );
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

    public static class EmeraldForMapTrade implements VillagerTrades.ItemListing {
        private final int emeraldCost;
        private final StructureFeature<?> destination;
        private final MapDecoration.Type destinationType;
        private final int maxUses;
        private final int villagerXp;

        public EmeraldForMapTrade(int emeraldCost, StructureFeature<?> structure, MapDecoration.Type decoration, int maxUsesIn, int xpValueIn) {
            this.emeraldCost = emeraldCost;
            this.destination = structure;
            this.destinationType = decoration;
            this.maxUses = maxUsesIn;
            this.villagerXp = xpValueIn;
        }

        @Nullable
        public MerchantOffer getOffer(Entity trader, Random random) {
            if (!(trader.level instanceof ServerLevel)) {
                return null;
            } else {
                ServerLevel serverlevel = (ServerLevel)trader.level;
                BlockPos blockpos = serverlevel.findNearestMapFeature(this.destination, trader.blockPosition(), 100, true);
                if (blockpos != null) {
                    ItemStack itemstack = MapItem.create(serverlevel, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
                    MapItem.renderBiomePreviewMap(serverlevel, itemstack);
                    MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
                    itemstack.setHoverName(new TranslatableComponent("filled_map." + this.destination.getFeatureName().toLowerCase(Locale.ROOT)));
                    return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
                } else {
                    return null;
                }
            }
        }
    }

    public static void addVillagerTrades(VillagerTradesEvent event, int level, VillagerTrades.ItemListing... trades) {
        for (VillagerTrades.ItemListing trade : trades) event.getTrades().get(level).add(trade);
    }

    public static void addVillagerTrades(VillagerTradesEvent event, VillagerProfession profession, int level, VillagerTrades.ItemListing... trades) {
        if (event.getType() == profession) addVillagerTrades(event, level, trades);
    }

    public static void addWandererTrades(WandererTradesEvent event, VillagerTrades.ItemListing... trades) {
        for (VillagerTrades.ItemListing trade : trades) event.getGenericTrades().add(trade);
    }

    public static void addRareWandererTrades(WandererTradesEvent event, VillagerTrades.ItemListing... trades) {
        for (VillagerTrades.ItemListing trade : trades) event.getRareTrades().add(trade);
    }

    public static void addCompatVillagerTrades(VillagerTradesEvent event, String modid, int level, VillagerTrades.ItemListing... trades) {
        if (ModList.get().isLoaded(modid)) addVillagerTrades(event, level, trades);
    }

    public static void addCompatVillagerTrades(VillagerTradesEvent event, String modid, VillagerProfession profession, int level, VillagerTrades.ItemListing... trades) {
        if (ModList.get().isLoaded(modid)) addVillagerTrades(event, profession, level, trades);
    }

    public static void addCompatWandererTrades(WandererTradesEvent event, String modid, VillagerTrades.ItemListing... trades) {
        if (ModList.get().isLoaded(modid)) addWandererTrades(event, trades);
    }

    public static void addCompatRareWandererTrades(WandererTradesEvent event, String modid, VillagerTrades.ItemListing... trades) {
        if (ModList.get().isLoaded(modid)) addRareWandererTrades(event, trades);
    }
}