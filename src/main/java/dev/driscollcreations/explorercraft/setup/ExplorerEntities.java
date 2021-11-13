package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.cymru.entity.WizardEntity;
import dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.EnderreeperEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.List;

public class ExplorerEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES;
    public static final RegistryObject<EntityType<EnderreeperEntity>> ENDERREEPER;
    public static final RegistryObject<EntityType<WizardEntity>> WIZARD;

    public ExplorerEntities() {
    }

    public static <T extends Entity> RegistryObject<EntityType<T>> buildEntity(EntityType.IFactory<T> factory, String name, EntityClassification entityClassification,  float width, float height) {
        String modName = new ResourceLocation(Explorercraft.MOD_ID, name).toString();

        return ENTITIES.register(name, () -> {
            return EntityType.Builder.of(factory, entityClassification).sized(width, height).build(modName);
        });
    }

    static {
        ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Explorercraft.MOD_ID);
        ENDERREEPER = buildEntity(EnderreeperEntity::new, "enderreeper", EntityClassification.MONSTER, 0.7F, 1.3F);
        WIZARD = buildEntity(WizardEntity::new, "wizard", EntityClassification.MONSTER, 0.6F, 1.99F);
    }

    public static void onEntitySpawn(final BiomeLoadingEvent event) {
        String biome = event.getName().toString();
        addEntityToSpecificBiomes(event, ENDERREEPER.get(), 50, 1, 1,
                Biomes.END_BARRENS, Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS);
        addEntityToSpecificBiomes(event, ENDERREEPER.get(), 20, 1, 1,
                Biomes.THE_END);

    }

    private static void addEntityToAllBiomesExceptThese(BiomeLoadingEvent event, EntityType<?> type,
                                                        int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes) {
        // Goes through each entry in the biomes and sees if it matches the current biome we are loading
        boolean isBiomeSelected = Arrays.stream(biomes).map(RegistryKey::location)
                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if(!isBiomeSelected) {
            addEntityToAllBiomes(event.getSpawns(), type, weight, minCount, maxCount);
        }
    }

    @SafeVarargs
    private static void addEntityToSpecificBiomes(BiomeLoadingEvent event, EntityType<?> type,
                                                  int weight, int minCount, int maxCount, RegistryKey<Biome>... biomes) {
        // Goes through each entry in the biomes and sees if it matches the current biome we are loading
        boolean isBiomeSelected = Arrays.stream(biomes).map(RegistryKey::location)
                .map(Object::toString).anyMatch(s -> s.equals(event.getName().toString()));

        if(isBiomeSelected) {
            addEntityToAllBiomes(event.getSpawns(), type, weight, minCount, maxCount);
        }
    }

    private static void addEntityToAllBiomes(MobSpawnInfoBuilder spawns, EntityType<?> type,
                                             int weight, int minCount, int maxCount) {
        List<MobSpawnInfo.Spawners> base = spawns.getSpawner(type.getCategory());
        base.add(new MobSpawnInfo.Spawners(type,weight, minCount, maxCount));
    }

}
