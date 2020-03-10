package com.zathrox.explorercraft.core.registry;

import com.zathrox.explorercraft.common.entity.InfectedCreeperEntity;
import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExplorerEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Explorercraft.MOD_ID);

    public static final String INFECTED_CREEPER = "infected_creeper";
    public static final RegistryObject<EntityType<InfectedCreeperEntity>> WILD_BOAR = ENTITY_TYPES.register(INFECTED_CREEPER, () ->
            EntityType.Builder.<InfectedCreeperEntity>create(InfectedCreeperEntity::new, EntityClassification.CREATURE)
                    .size(EntityType.PIG.getWidth(), EntityType.PIG.getHeight())
                    .build(new ResourceLocation(Explorercraft.MOD_ID, INFECTED_CREEPER).toString())
    );
}
