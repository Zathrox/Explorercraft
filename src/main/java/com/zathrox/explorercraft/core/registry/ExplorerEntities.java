package com.zathrox.explorercraft.core.registry;

import com.google.common.collect.Lists;
import com.zathrox.explorercraft.common.entity.*;
import com.zathrox.explorercraft.core.Explorercraft;
import com.zathrox.explorercraft.core.config.EntityConfig;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Explorercraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ExplorerEntities {
    private static List<EntityType> entities = Lists.newArrayList();
    private static List<Item> spawnEggs = Lists.newArrayList();

    public static final EntityType<BrushStoogeEntity> BRUSH_STOOGE = createEntity(BrushStoogeEntity::new, EntityClassification.MONSTER, "brush_stooge", 0.4F, 0.7F, 10092400, 16777215);
    public static final EntityType<EnderGhastEntity> ENDERGHAST = createEntity(EnderGhastEntity::new, EntityClassification.MONSTER, "enderghast", 10F, 4.5F, 4801171, 6078066);
    public static final EntityType<EnderreeperEntity> ENDERREEPER = createEntity(EnderreeperEntity::new, EntityClassification.MONSTER, "enderreeper", 0.6F, 1.99F, 3801171, 7078066);
    public static final EntityType<InfectedCreeperEntity> INFECTED_CREEPER = createEntity(InfectedCreeperEntity::new, EntityClassification.MONSTER, "infected_creeper", 0.6F, 1.7F, 894731, 0xdc1c1c);
    public static final EntityType<InfectedSkeletonEntity> INFECTED_SKELETON = createEntity(InfectedSkeletonEntity::new, EntityClassification.MONSTER, "infected_skeleton", 0.6F, 1.99F, 16777215, 10092400);
    public static final EntityType<InfectedZombieEntity> INFECTED_ZOMBIE = createEntity(InfectedZombieEntity::new, EntityClassification.MONSTER, "infected_zombie", 0.6F, 1.99F, 16777234, 10092500);
    public static final EntityType<SkeletaurEntity> SKELETAUR = createEntity(SkeletaurEntity::new, EntityClassification.MONSTER, "skeletaur", 1.3964844F, 2.25F, 16433238, 4869992);
    public static final EntityType<WizardEntity> WIZARD = createEntity(WizardEntity::new, EntityClassification.MONSTER, "wizard", 0.6F, 1.99F, 4869992, 16433238);

    private static <T extends Entity> EntityType<T> createEntity(EntityType.IFactory<T> factory, EntityClassification entityClassification, String name, float width, float height, int eggPrimary, int eggSecondary) {
        ResourceLocation location = new ResourceLocation(Explorercraft.MOD_ID, name);

        EntityType<T> entity = EntityType.Builder.create(factory, entityClassification)
                .size(width, height).setTrackingRange(64)
                .setShouldReceiveVelocityUpdates(true)
                .setUpdateInterval(3)
                .build(location.toString());

        entity.setRegistryName(location);

        entities.add(entity);
        spawnEggs.add(createSpawnEggForEntity(entity, eggPrimary, eggSecondary, ExplorerItemGroups.EXPLORERCRAFT));

        return entity;
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        for (EntityType entity : entities) {
            event.getRegistry().register(entity);
        }

        EntitySpawnPlacementRegistry.register(BRUSH_STOOGE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
        EntitySpawnPlacementRegistry.register(ENDERGHAST, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EnderGhastEntity::spawnConditions);
        EntitySpawnPlacementRegistry.register(ENDERREEPER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EnderreeperEntity::spawnEnderreeper);
        EntitySpawnPlacementRegistry.register(INFECTED_CREEPER, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
        EntitySpawnPlacementRegistry.register(INFECTED_SKELETON, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
        EntitySpawnPlacementRegistry.register(INFECTED_ZOMBIE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
        EntitySpawnPlacementRegistry.register(WIZARD, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canSpawnOn);
        EntitySpawnPlacementRegistry.register(SKELETAUR, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawnInLight);
    }

    @SubscribeEvent
    public static void registerSpawnEggs(RegistryEvent.Register<Item> event) {
        for (Item spawnEgg : spawnEggs) {
            event.getRegistry().register(spawnEgg);
        }
    }

    public static Item createSpawnEggForEntity(@SuppressWarnings("rawtypes") EntityType entityType, int eggColor1, int eggColor2, ItemGroup itemGroup) {
        return new SpawnEggItem(entityType, eggColor1, eggColor2, new Item.Properties().group(itemGroup)).setRegistryName(entityType.getRegistryName() + "_spawn_egg");
    }

    private static void registerEntityWorldSpawn(EntityType<?> type, int weight, int minCount, int maxCount, Biome... biomes) {

        for (Biome biome : biomes) {
            if (biome != null) {
                biome.getSpawns(type.getClassification()).add(new Biome.SpawnListEntry(type, weight, minCount, maxCount));
            }
        }
    }

    public static void registerEntityWorldSpawns() {
        if(EntityConfig.enderreeper_enabled.get()) {
            registerEntityWorldSpawn(ENDERREEPER, 2, 1, 1, Biomes.THE_END);
            registerEntityWorldSpawn(ENDERREEPER, 5, 1, 1, Biomes.END_BARRENS, Biomes.SMALL_END_ISLANDS, Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS);
        }

        if(EntityConfig.enderreeper_nether_spawn_enabled.get()) {
            registerEntityWorldSpawn(ENDERREEPER, 2, 1, 2, Biomes.NETHER);
        }

        if(EntityConfig.enderghast_enabled.get()) {
            registerEntityWorldSpawn(ENDERGHAST, 10, 1, 4, Biomes.THE_END);
            registerEntityWorldSpawn(ENDERGHAST, 15, 1, 4, Biomes.END_BARRENS, Biomes.SMALL_END_ISLANDS, Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS);
        }
        if(EntityConfig.infected_skeleton_enabled.get()) {
            registerEntityWorldSpawn(INFECTED_SKELETON, 90, 4, 4, Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS, Biomes.MODIFIED_JUNGLE_EDGE, Biomes.MODIFIED_JUNGLE);
        }

        if(EntityConfig.infected_skeleton_enabled.get()) {
            registerEntityWorldSpawn(INFECTED_ZOMBIE, 95, 4, 4, Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS, Biomes.MODIFIED_JUNGLE_EDGE, Biomes.MODIFIED_JUNGLE);
        }

        if(EntityConfig.infected_creeper_enabled.get()) {
            registerEntityWorldSpawn(INFECTED_CREEPER, 80, 4, 4, Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS, Biomes.MODIFIED_JUNGLE_EDGE, Biomes.MODIFIED_JUNGLE);
        }

        if(EntityConfig.brush_stooge_enabled.get()) {
            registerEntityWorldSpawn(BRUSH_STOOGE, 88, 2, 8, Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_EDGE, Biomes.JUNGLE_HILLS, Biomes.BAMBOO_JUNGLE, Biomes.BAMBOO_JUNGLE_HILLS, Biomes.MODIFIED_JUNGLE_EDGE, Biomes.MODIFIED_JUNGLE);
        }

        if(EntityConfig.skeletaur_enabled.get()) {
            registerEntityWorldSpawn(SKELETAUR, 70, 1, 1,Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.FLOWER_FOREST);
        }
    }
}