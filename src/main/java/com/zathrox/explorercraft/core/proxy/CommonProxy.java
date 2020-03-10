package com.zathrox.explorercraft.core.proxy;

import com.google.common.collect.ImmutableMap;
import com.zathrox.explorercraft.common.world.OreGeneration;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.config.Config;
import com.zathrox.explorercraft.core.events.ExplorerPlayerEvents;
import com.zathrox.explorercraft.core.registry.*;
import com.zathrox.explorercraft.core.util.ExplorerVanillaCompat;
import com.zathrox.explorercraft.core.data.ExplorercraftDataGenerator;
import com.zathrox.explorercraft.core.util.ExplorerTrades;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonProxy {

    public CommonProxy() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.common_config);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::postInit);

        Config.loadConfig(Config.common_config, FMLPaths.CONFIGDIR.get().resolve("explorercraft-common.toml").toString());

        MinecraftForge.EVENT_BUS.register(new ExplorerPlayerEvents());
        MinecraftForge.EVENT_BUS.register(new ExplorercraftDataGenerator());
        ExplorerBannerPattern.init();
    }

    protected InventoryChangeTrigger.Instance hasItem(IItemProvider itemIn) {
        return this.hasItem((IItemProvider) ItemPredicate.Builder.create().item(itemIn).build());
    }

    private static final Map<VillagerProfession, Int2ObjectMap<VillagerTrades.ITrade[]>> VANILLA_TRADES = new HashMap<>();

    static
    {
        VillagerTrades.VILLAGER_DEFAULT_TRADES.entrySet().forEach(e ->
        {
            Int2ObjectMap<VillagerTrades.ITrade[]> copy = new Int2ObjectOpenHashMap<>();
            e.getValue().int2ObjectEntrySet().forEach(ent -> copy.put(ent.getIntKey(), Arrays.copyOf(ent.getValue(), ent.getValue().length)));
            VANILLA_TRADES.put(e.getKey(), copy);
        });
    }

    protected void preInit(FMLCommonSetupEvent event) {
        Explorercraft.LOGGER.debug("CommonProxy preInit method");

        OreGeneration.setup();
        ExplorerVanillaCompat.setup();
        this.changeVanillaFields();






    }

    protected void init(InterModEnqueueEvent event) {
        Explorercraft.LOGGER.debug("CommonProxy init method");
    }

    protected void postInit(InterModProcessEvent event) {
        Explorercraft.LOGGER.debug("CommonProxy postInit method");

        //EntityOvergrownSkeleton.addSpawn();
        //EnderreeperEntity.addSpawn();
        ExplorerEntities.registerEntityWorldSpawns();
        //ExplorerBiomes.registerBiomes();
        EnchantmentType.BOW.canEnchantItem(ExplorerItems.JADE_BOW);
    }



    void changeVanillaFields() {


        addVillagerTrades(VillagerProfession.FARMER,  ExplorerTrades.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[] {
                //new BasicTrade(new ItemStack(ExplorerItems.LEEK), new ItemStack(Items.EMERALD), 22, 16, 2),
                new ExplorerTrades.EmeraldForItemsTrade(ExplorerItems.LEEK, 22, 16, 2),
                new ExplorerTrades.EmeraldForItemsTrade(ExplorerItems.RICE, 32, 16, 2),})));

        addVillagerTrades(VillagerProfession.BUTCHER,  ExplorerTrades.newTradeMap(ImmutableMap.of(3, new VillagerTrades.ITrade[] {
                new ExplorerTrades.EmeraldForItemsTrade(ExplorerItems.LAMB_SHANK, 7, 16, 20)})));

        addVillagerTrades(VillagerProfession.CLERIC,  ExplorerTrades.newTradeMap(ImmutableMap.of(3, new VillagerTrades.ITrade[] {
                new ExplorerTrades.ItemsForEmeraldsTrade(ExplorerItems.NOCTILUCA, 5, 1, 15)})));


        /*
         * Override of Villager Trades without the need to overwrite the original villager profession list, it simply injects into the code
         */
        /*Int2ObjectMap<VillagerTrades.ITrade[]> farmerTrades = VANILLA_TRADES.getOrDefault(VillagerProfession.FARMER, new Int2ObjectOpenHashMap<>());
        Int2ObjectMap<VillagerTrades.ITrade[]> butcherTrades = VANILLA_TRADES.getOrDefault(VillagerProfession.BUTCHER, new Int2ObjectOpenHashMap<>());

        Int2ObjectMap<List<VillagerTrades.ITrade>> mutableTrades = new Int2ObjectOpenHashMap<>();
        Int2ObjectMap<List<VillagerTrades.ITrade>> mutableTrades2 = new Int2ObjectOpenHashMap<>();
        Int2ObjectMap<VillagerTrades.ITrade[]> newFarmerTrades = ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[] {
                new ExplorerVillagerUtil.EmeraldForItemsTrade(ExplorerItems.LEEK, 22, 16, 2),
                new ExplorerVillagerUtil.EmeraldForItemsTrade(ExplorerItems.RICE, 32, 16, 2),
        }));
        Int2ObjectMap<VillagerTrades.ITrade[]> newButcherTrades = ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(3, new VillagerTrades.ITrade[] {
                new ExplorerVillagerUtil.EmeraldForItemsTrade(ExplorerItems.LAMB_SHANK, 7, 16, 20)
        }));


        for (int i = 1; i < 6; i++)
        {
            mutableTrades.put(i, NonNullList.create());
            mutableTrades2.put(i, NonNullList.create());
        }
        newFarmerTrades.int2ObjectEntrySet().forEach(e ->
        {
            Arrays.stream(e.getValue()).forEach(mutableTrades.get(e.getIntKey())::add);
        });
        farmerTrades.int2ObjectEntrySet().forEach(e ->
        {
            Arrays.stream(e.getValue()).forEach(mutableTrades.get(e.getIntKey())::add);
        });

        butcherTrades.int2ObjectEntrySet().forEach(e ->
        {
            Arrays.stream(e.getValue()).forEach(mutableTrades2.get(e.getIntKey())::add);
        });
        newButcherTrades.int2ObjectEntrySet().forEach(e ->
        {
            Arrays.stream(e.getValue()).forEach(mutableTrades2.get(e.getIntKey())::add);
        });
        MinecraftForge.EVENT_BUS.post(new VillagerTradesEvent(mutableTrades, VillagerProfession.FARMER));
        MinecraftForge.EVENT_BUS.post(new VillagerTradesEvent(mutableTrades2, VillagerProfession.BUTCHER));
        mutableTrades.int2ObjectEntrySet().forEach(e -> newFarmerTrades.put(e.getIntKey(), e.getValue().toArray(new VillagerTrades.ITrade[0])));
        mutableTrades2.int2ObjectEntrySet().forEach(e -> newButcherTrades.put(e.getIntKey(), e.getValue().toArray(new VillagerTrades.ITrade[0])));
        VillagerTrades.field_221239_a.put(VillagerProfession.FARMER, newFarmerTrades);
        VillagerTrades.field_221239_a.put(VillagerProfession.BUTCHER, newButcherTrades);*/


        /*
         * This chunk of code, does the same as above but without the need to replace or add ALL villager profession code, it simply only changes the profession of choice but still a might pain in the ass to create
         */
        /*Int2ObjectMap<VillagerTrades.ITrade[]> trades = ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[] {
                new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.WHEAT, 20, 16, 2),
                new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.POTATO, 26, 16, 2),
                new ExplorerVillagerUtil.EmeraldForItemsTrade(ExplorerItems.LEEK, 22, 16, 2),
                new ExplorerVillagerUtil.EmeraldForItemsTrade(ExplorerItems.RICE, 32, 16, 2),
                new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.CARROT, 22, 16, 2),
                new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.BEETROOT, 15, 16, 2),
                new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.BREAD, 1, 6, 16, 1)
        }, 2, new VillagerTrades.ITrade[] {
                new ExplorerVillagerUtil.EmeraldForItemsTrade(Blocks.PUMPKIN, 6, 12, 10),
                new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.PUMPKIN_PIE, 1, 4, 5),
                new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.APPLE, 1, 4, 16, 5)
        }, 3, new VillagerTrades.ITrade[] {
                new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.COOKIE, 3, 18, 10),
                new ExplorerVillagerUtil.EmeraldForItemsTrade(Blocks.MELON, 4, 12, 20)
        }, 4, new VillagerTrades.ITrade[] {
                new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.CAKE, 1, 1, 12, 15),
                new ExplorerVillagerUtil.SuspiciousStewForEmeraldTrade(Effects.NIGHT_VISION, 100, 15),
                new ExplorerVillagerUtil.SuspiciousStewForEmeraldTrade(Effects.JUMP_BOOST, 160, 15),
                new ExplorerVillagerUtil.SuspiciousStewForEmeraldTrade(Effects.WEAKNESS, 140, 15),
                new ExplorerVillagerUtil.SuspiciousStewForEmeraldTrade(Effects.BLINDNESS, 120, 15),
                new ExplorerVillagerUtil.SuspiciousStewForEmeraldTrade(Effects.POISON, 280, 15),
                new ExplorerVillagerUtil.SuspiciousStewForEmeraldTrade(Effects.SATURATION, 7, 15)
        }, 5, new VillagerTrades.ITrade[] {
                new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.GOLDEN_CARROT, 3, 3, 30),
                new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.GLISTERING_MELON_SLICE, 4, 3, 30)
        }));

        Int2ObjectMap<List<VillagerTrades.ITrade>> mutableTrades = new Int2ObjectOpenHashMap<>();
        for (int i = 1; i < 6; i++)
        {
            mutableTrades.put(i, NonNullList.create());
        }
        trades.int2ObjectEntrySet().forEach(e ->
        {
            Arrays.stream(e.getValue()).forEach(mutableTrades.get(e.getIntKey())::add);
        });
        MinecraftForge.EVENT_BUS.post(new VillagerTradesEvent(mutableTrades, VillagerProfession.FARMER));
        Int2ObjectMap<VillagerTrades.ITrade[]> newTrades = new Int2ObjectOpenHashMap<>();
        mutableTrades.int2ObjectEntrySet().forEach(e -> newTrades.put(e.getIntKey(), e.getValue().toArray(new VillagerTrades.ITrade[0])));
        VillagerTrades.field_221239_a.put(VillagerProfession.FARMER, newTrades);*/


        /*
         * Override of Villager Trades, however, you need to create all possibly trades from all professions or it simply doesn't make any trades for other professions
         */
        /*
        VillagerTrades.field_221239_a = Util.make(Maps.newHashMap(), (map) -> {
                map.put(VillagerProfession.FARMER, ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[] {
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.WHEAT, 20, 16, 2),
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.POTATO, 26, 16, 2),
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(ExplorerItems.LEEK, 22, 16, 2),
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(ExplorerItems.RICE, 32, 16, 2),
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.CARROT, 22, 16, 2),
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.BEETROOT, 15, 16, 2),
                    new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.BREAD, 1, 6, 16, 1)
            }, 2, new VillagerTrades.ITrade[] {
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Blocks.PUMPKIN, 6, 12, 10),
                    new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.PUMPKIN_PIE, 1, 4, 5),
                    new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.APPLE, 1, 4, 16, 5)
            }, 3, new VillagerTrades.ITrade[] {
                    new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.COOKIE, 3, 18, 10),
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Blocks.MELON, 4, 12, 20)
            }, 4, new VillagerTrades.ITrade[] {
                    new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.CAKE, 1, 1, 12, 15),
                    new ExplorerVillagerUtil.SuspiciousStewForEmeraldTrade(Effects.NIGHT_VISION, 100, 15),
                    new ExplorerVillagerUtil.SuspiciousStewForEmeraldTrade(Effects.JUMP_BOOST, 160, 15),
                    new ExplorerVillagerUtil.SuspiciousStewForEmeraldTrade(Effects.WEAKNESS, 140, 15),
                    new ExplorerVillagerUtil.SuspiciousStewForEmeraldTrade(Effects.BLINDNESS, 120, 15),
                    new ExplorerVillagerUtil.SuspiciousStewForEmeraldTrade(Effects.POISON, 280, 15),
                    new ExplorerVillagerUtil.SuspiciousStewForEmeraldTrade(Effects.SATURATION, 7, 15)
            }, 5, new VillagerTrades.ITrade[] {
                    new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.GOLDEN_CARROT, 3, 3, 30),
                    new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.GLISTERING_MELON_SLICE, 4, 3, 30)
            })));
            map.put(VillagerProfession.BUTCHER, ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[] {
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.CHICKEN, 14, 16, 2),
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.PORKCHOP, 7, 16, 2),
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.RABBIT, 4, 16, 2),
                    new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.RABBIT_STEW, 1, 1, 1)
            }, 2, new VillagerTrades.ITrade[]{
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.COAL, 15, 16, 2),
                    new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.COOKED_PORKCHOP, 1, 5, 16, 5),
                    new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.COOKED_CHICKEN, 1, 8, 16, 5)
            }, 3, new VillagerTrades.ITrade[]{
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.MUTTON, 7, 16, 20),
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.BEEF, 10, 16, 20)
            }, 4, new VillagerTrades.ITrade[]{
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.DRIED_KELP_BLOCK, 10, 12, 30),
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(ExplorerItems.LAMB_SHANK, 7, 16, 20)
            }, 5, new VillagerTrades.ITrade[]{
                    new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.SWEET_BERRIES, 10, 12, 30)
            })));
            map.put(VillagerProfession.FISHERMAN, ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{
                            new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.STRING, 20, 16, 2),
                            new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.COAL, 10, 16, 2),
                            new ExplorerVillagerUtil.ItemsForEmeraldsAndItemsTrade(Items.COD, 6, Items.COOKED_COD, 6, 16, 1),
                            new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.COD_BUCKET, 3, 1, 16, 1)
                    }, 2,
                            new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.COD, 15, 16, 10),
                            new ExplorerVillagerUtil.ItemsForEmeraldsAndItemsTrade(Items.SALMON, 6, Items.COOKED_SALMON, 6, 16, 5),
                            new ExplorerVillagerUtil.ItemsForEmeraldsAndItemsTrade(Items.SALMON, 16, ExplorerItems.SALMON_SUSHI, 16, 16, 5),
                            new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.CAMPFIRE, 2, 1, 5)
                    }, 3, new VillagerTrades.ITrade[]{
                            new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.SALMON, 13, 16, 20),
                            new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.FISHING_ROD, 3, 3, 10, 0.2F)
                    }, 4, new VillagerTrades.ITrade[]{
                            new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.TROPICAL_FISH, 6, 12, 30)
                    }, 5, new VillagerTrades.ITrade[]{
                            new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.PUFFERFISH, 4, 12, 30),
                            new ExplorerVillagerUtil.EmeraldForVillageTypeItemTrade(1, 12, 30, ImmutableMap.<IVillagerType, Item>builder().put(IVillagerType.PLAINS, Items.OAK_BOAT).put(IVillagerType.TAIGA, Items.SPRUCE_BOAT).put(IVillagerType.SNOW, Items.SPRUCE_BOAT).put(IVillagerType.DESERT, Items.JUNGLE_BOAT).put(IVillagerType.JUNGLE, Items.JUNGLE_BOAT).put(IVillagerType.SAVANNA, Items.ACACIA_BOAT).put(IVillagerType.SWAMP, Items.DARK_OAK_BOAT).build())})));
            map.put(VillagerProfession.SHEPHERD, ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Blocks.WHITE_WOOL, 18, 16, 2), new ExplorerVillagerUtil.EmeraldForItemsTrade(Blocks.BROWN_WOOL, 18, 16, 2), new ExplorerVillagerUtil.EmeraldForItemsTrade(Blocks.BLACK_WOOL, 18, 16, 2), new ExplorerVillagerUtil.EmeraldForItemsTrade(Blocks.GRAY_WOOL, 18, 16, 2), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.SHEARS, 2, 1, 1)}, 2, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.WHITE_DYE, 12, 16, 10), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.GRAY_DYE, 12, 16, 10), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.BLACK_DYE, 12, 16, 10), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.LIGHT_BLUE_DYE, 12, 16, 10), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.LIME_DYE, 12, 16, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.WHITE_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.ORANGE_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.MAGENTA_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIGHT_BLUE_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.YELLOW_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIME_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.PINK_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.GRAY_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIGHT_GRAY_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.CYAN_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.PURPLE_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BLUE_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BROWN_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.GREEN_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.RED_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BLACK_WOOL, 1, 1, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.WHITE_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.ORANGE_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.MAGENTA_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIGHT_BLUE_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.YELLOW_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIME_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.PINK_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.GRAY_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIGHT_GRAY_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.CYAN_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.PURPLE_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BLUE_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BROWN_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.GREEN_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.RED_CARPET, 1, 4, 16, 5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BLACK_CARPET, 1, 4, 16, 5)}, 3, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.YELLOW_DYE, 12, 16, 20), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.LIGHT_GRAY_DYE, 12, 16, 20), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.ORANGE_DYE, 12, 16, 20), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.RED_DYE, 12, 16, 20), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.PINK_DYE, 12, 16, 20), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.WHITE_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.YELLOW_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.RED_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BLACK_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BLUE_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BROWN_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.CYAN_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.GRAY_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.GREEN_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIGHT_BLUE_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIGHT_GRAY_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIME_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.MAGENTA_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.ORANGE_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.PINK_BED, 3, 1, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.PURPLE_BED, 3, 1, 12, 10)}, 4, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.BROWN_DYE, 12, 16, 30), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.PURPLE_DYE, 12, 16, 30), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.BLUE_DYE, 12, 16, 30), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.GREEN_DYE, 12, 16, 30), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.MAGENTA_DYE, 12, 16, 30), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.CYAN_DYE, 12, 16, 30), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.WHITE_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.BLUE_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.LIGHT_BLUE_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.RED_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.PINK_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.GREEN_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.LIME_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.GRAY_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.BLACK_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.PURPLE_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.MAGENTA_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.CYAN_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.BROWN_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.YELLOW_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.ORANGE_BANNER, 3, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.LIGHT_GRAY_BANNER, 3, 1, 12, 15)}, 5, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.PAINTING, 2, 3, 30)})));
            map.put(VillagerProfession.FLETCHER, ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.STICK, 32, 16, 2), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.ARROW, 1, 16, 1), new ExplorerVillagerUtil.ItemsForEmeraldsAndItemsTrade(Blocks.GRAVEL, 10, Items.FLINT, 10, 12, 1)}, 2, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.FLINT, 26, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.BOW, 2, 1, 5)}, 3, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.STRING, 14, 16, 20), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.CROSSBOW, 3, 1, 10)}, 4, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.FEATHER, 24, 16, 30), new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.BOW, 2, 3, 15)}, 5, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.TRIPWIRE_HOOK, 8, 12, 30), new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.CROSSBOW, 3, 3, 15), new ExplorerVillagerUtil.ItemWithPotionForEmeraldsAndItemsTrade(Items.ARROW, 5, Items.TIPPED_ARROW, 5, 2, 12, 30)})));
            map.put(VillagerProfession.LIBRARIAN, ExplorerVillagerUtil.newTradeMap(ImmutableMap.<Integer, VillagerTrades.ITrade[]>builder().put(1, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.PAPER, 24, 16, 2), new ExplorerVillagerUtil.EnchantedBookForEmeraldsTrade(1), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BOOKSHELF, 6, 3, 12, 1)}).put(2, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.BOOK, 4, 12, 10), new ExplorerVillagerUtil.EnchantedBookForEmeraldsTrade(5), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.LANTERN, 1, 1, 5)}).put(3, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.INK_SAC, 5, 12, 20), new ExplorerVillagerUtil.EnchantedBookForEmeraldsTrade(10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.GLASS, 1, 4, 10)}).put(4, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.WRITABLE_BOOK, 2, 12, 30), new ExplorerVillagerUtil.EnchantedBookForEmeraldsTrade(15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.CLOCK, 5, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.COMPASS, 4, 1, 15)}).put(5, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.NAME_TAG, 20, 1, 30)}).build()));
            map.put(VillagerProfession.CARTOGRAPHER, ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.PAPER, 24, 16, 2), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.MAP, 7, 1, 1)}, 2, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.GLASS_PANE, 11, 16, 10), new ExplorerVillagerUtil.EmeraldForMapTrade(13, "Monument", MapDecoration.Type.MONUMENT, 12, 5)}, 3, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.COMPASS, 1, 12, 20), new ExplorerVillagerUtil.EmeraldForMapTrade(14, "Mansion", MapDecoration.Type.MANSION, 12, 10)}, 4, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.ITEM_FRAME, 7, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.WHITE_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.BLUE_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.LIGHT_BLUE_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.RED_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.PINK_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.GREEN_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.LIME_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.GRAY_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.BLACK_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.PURPLE_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.MAGENTA_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.CYAN_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.BROWN_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.YELLOW_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.ORANGE_BANNER, 3, 1, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.LIGHT_GRAY_BANNER, 3, 1, 15)}, 5, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.GLOBE_BANNER_PATTERN, 8, 1, 30)})));
            map.put(VillagerProfession.CLERIC, ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.ROTTEN_FLESH, 32, 16, 2), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.REDSTONE, 1, 2, 1)}, 2, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.GOLD_INGOT, 3, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.LAPIS_LAZULI, 1, 1, 5)}, 3, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.RABBIT_FOOT, 2, 12, 20), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.GLOWSTONE, 4, 1, 12, 10)}, 4, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.SCUTE, 4, 12, 30), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.GLASS_BOTTLE, 9, 12, 30), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.ENDER_PEARL, 5, 1, 15)}, 5, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.NETHER_WART, 22, 12, 30), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.EXPERIENCE_BOTTLE, 3, 1, 30)})));
            map.put(VillagerProfession.ARMORER, ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.COAL, 15, 16, 2), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_LEGGINGS), 7, 1, 12, 1, 0.2F), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_BOOTS), 4, 1, 12, 1, 0.2F), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_HELMET), 5, 1, 12, 1, 0.2F), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_CHESTPLATE), 9, 1, 12, 1, 0.2F)}, 2, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.IRON_INGOT, 4, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_BOOTS), 1, 1, 12, 5, 0.2F), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_LEGGINGS), 3, 1, 12, 5, 0.2F)}, 3, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.LAVA_BUCKET, 1, 12, 20), new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.DIAMOND, 1, 12, 20), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_HELMET), 1, 1, 12, 10, 0.2F), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.CHAINMAIL_CHESTPLATE), 4, 1, 12, 10, 0.2F), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.SHIELD), 5, 1, 12, 10, 0.2F)}, 4, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.DIAMOND_LEGGINGS, 14, 3, 15, 0.2F), new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.DIAMOND_BOOTS, 8, 3, 15, 0.2F)}, 5, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.DIAMOND_HELMET, 8, 3, 30, 0.2F), new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.DIAMOND_CHESTPLATE, 16, 3, 30, 0.2F)})));
            map.put(VillagerProfession.WEAPONSMITH, ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.COAL, 15, 16, 2), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.IRON_AXE), 3, 1, 12, 1, 0.2F), new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.IRON_SWORD, 2, 3, 1)}, 2, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.IRON_INGOT, 4, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F)}, 3, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.FLINT, 24, 12, 20)}, 4, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.DIAMOND, 1, 12, 30), new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.DIAMOND_AXE, 12, 3, 15, 0.2F)}, 5, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.DIAMOND_SWORD, 8, 3, 30, 0.2F)})));
            map.put(VillagerProfession.TOOLSMITH, ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.COAL, 15, 16, 2), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.STONE_AXE), 1, 1, 12, 1, 0.2F), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.STONE_SHOVEL), 1, 1, 12, 1, 0.2F), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.STONE_PICKAXE), 1, 1, 12, 1, 0.2F), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.STONE_HOE), 1, 1, 12, 1, 0.2F)}, 2, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.IRON_INGOT, 4, 12, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F)}, 3, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.FLINT, 30, 12, 20), new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.IRON_AXE, 1, 3, 10, 0.2F), new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.IRON_SHOVEL, 2, 3, 10, 0.2F), new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.IRON_PICKAXE, 3, 3, 10, 0.2F), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.DIAMOND_HOE), 4, 1, 3, 10, 0.2F)}, 4, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.DIAMOND, 1, 12, 30), new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.DIAMOND_AXE, 12, 3, 15, 0.2F), new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.DIAMOND_SHOVEL, 5, 3, 15, 0.2F)}, 5, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EnchantedItemForEmeraldsTrade(Items.DIAMOND_PICKAXE, 13, 3, 30, 0.2F)})));
            map.put(VillagerProfession.LEATHERWORKER, ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.LEATHER, 6, 16, 2), new ExplorerVillagerUtil.DyedArmorForEmeraldsTrade(Items.LEATHER_LEGGINGS, 3), new ExplorerVillagerUtil.DyedArmorForEmeraldsTrade(Items.LEATHER_CHESTPLATE, 7)}, 2, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.FLINT, 26, 12, 10), new ExplorerVillagerUtil.DyedArmorForEmeraldsTrade(Items.LEATHER_HELMET, 5, 12, 5), new ExplorerVillagerUtil.DyedArmorForEmeraldsTrade(Items.LEATHER_BOOTS, 4, 12, 5)}, 3, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.RABBIT_HIDE, 9, 12, 20), new ExplorerVillagerUtil.DyedArmorForEmeraldsTrade(Items.LEATHER_CHESTPLATE, 7)}, 4, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.SCUTE, 4, 12, 30), new ExplorerVillagerUtil.DyedArmorForEmeraldsTrade(Items.LEATHER_HORSE_ARMOR, 6, 12, 15)}, 5, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.ItemsForEmeraldsTrade(new ItemStack(Items.SADDLE), 6, 1, 12, 30, 0.2F), new ExplorerVillagerUtil.DyedArmorForEmeraldsTrade(Items.LEATHER_HELMET, 5, 12, 30)})));
            map.put(VillagerProfession.MASON, ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(1, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.CLAY_BALL, 10, 16, 2), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Items.BRICK, 1, 10, 16, 1)}, 2, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Blocks.STONE, 20, 16, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.CHISELED_STONE_BRICKS, 1, 4, 16, 5)}, 3, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Blocks.GRANITE, 16, 16, 20), new ExplorerVillagerUtil.EmeraldForItemsTrade(Blocks.ANDESITE, 16, 16, 20), new ExplorerVillagerUtil.EmeraldForItemsTrade(Blocks.DIORITE, 16, 16, 20), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.POLISHED_ANDESITE, 1, 4, 16, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.POLISHED_DIORITE, 1, 4, 16, 10), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.POLISHED_GRANITE, 1, 4, 16, 10)}, 4, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.EmeraldForItemsTrade(Items.QUARTZ, 12, 12, 30), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.ORANGE_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.WHITE_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BLUE_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIGHT_BLUE_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.GRAY_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIGHT_GRAY_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BLACK_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.RED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.PINK_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.MAGENTA_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIME_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.GREEN_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.CYAN_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.PURPLE_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.YELLOW_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BROWN_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.ORANGE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.WHITE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BLUE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.GRAY_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BLACK_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.RED_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.PINK_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.MAGENTA_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.LIME_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.GREEN_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.CYAN_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.PURPLE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.YELLOW_GLAZED_TERRACOTTA, 1, 1, 12, 15), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.BROWN_GLAZED_TERRACOTTA, 1, 1, 12, 15)}, 5, new VillagerTrades.ITrade[]{new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.QUARTZ_PILLAR, 1, 1, 12, 30), new ExplorerVillagerUtil.ItemsForEmeraldsTrade(Blocks.QUARTZ_BLOCK, 1, 1, 12, 30)})));
        });*/
    }

    /**
     * Used to create a new trade map for a villager's trade list for a specific villager profession
     * Example: addVillagerTrades(VillagerProfession.CLERIC,  ExplorerVillagerUtil.newTradeMap(ImmutableMap.of(3, new VillagerTrades.ITrade[] {
     *                 new ExplorerVillagerUtil.ItemsForEmeraldsTrade(ExplorerItems.NOCTILUCA, 5, 1, 15)})));
     * @param prof
     * @param newTrades
     */
    public void addVillagerTrades(VillagerProfession prof, Int2ObjectMap<VillagerTrades.ITrade[]> newTrades) {

        Int2ObjectMap<VillagerTrades.ITrade[]> profTrades = VANILLA_TRADES.getOrDefault(prof, new Int2ObjectOpenHashMap<>());
        Int2ObjectMap<List<VillagerTrades.ITrade>> mutableTrades = new Int2ObjectOpenHashMap<>();
        Int2ObjectMap<VillagerTrades.ITrade[]> newProfList = newTrades;
        for (int i = 1; i < 6; i++)
        {
            mutableTrades.put(i, NonNullList.create());
        }
        newProfList.int2ObjectEntrySet().forEach(e ->
        {
            Arrays.stream(e.getValue()).forEach(mutableTrades.get(e.getIntKey())::add);
        });
        profTrades.int2ObjectEntrySet().forEach(e ->
        {
            Arrays.stream(e.getValue()).forEach(mutableTrades.get(e.getIntKey())::add);
        });
        MinecraftForge.EVENT_BUS.post(new VillagerTradesEvent(mutableTrades, prof));
        mutableTrades.int2ObjectEntrySet().forEach(e -> newProfList.put(e.getIntKey(), e.getValue().toArray(new VillagerTrades.ITrade[0])));
        VillagerTrades.VILLAGER_DEFAULT_TRADES.put(prof, newProfList);
    }
}