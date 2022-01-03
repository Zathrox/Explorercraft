package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.EnderreeperEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExplorerEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES;
    public static final RegistryObject<EntityType<EnderreeperEntity>> ENDERREEPER;
    //public static final RegistryObject<EntityType<WizardEntity>> WIZARD;

    private ExplorerEntities() {}

    public static <T extends Entity> RegistryObject<EntityType<T>> buildEntity(EntityType.EntityFactory<T> factory, String name, MobCategory entityClassification, float width, float height) {
        String modName = new ResourceLocation(Explorercraft.MOD_ID, name).toString();

        return ENTITIES.register(name, () -> EntityType.Builder.of(factory, entityClassification).sized(width, height).build(modName));
    }

    static {
        ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Explorercraft.MOD_ID);
        ENDERREEPER = buildEntity(EnderreeperEntity::new, "enderreeper", MobCategory.MONSTER, 0.7F, 1.3F);
        //WIZARD = buildEntity(WizardEntity::new, "wizard", MobCategory.MONSTER, 0.6F, 1.99F, 4869992, 16433238);
    }

}
