//package dev.driscollcreations.explorercraft.setup;
//
//import com.google.common.collect.Lists;
//import dev.driscollcreations.explorercraft.Explorercraft;
//import dev.driscollcreations.explorercraft.cymru.entity.WizardEntity;
//import dev.driscollcreations.explorercraft.vanillatweaks.entity.enderreeper.EnderreeperEntity;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.MobCategory;
//import net.minecraft.entity.EntityType;
//import net.minecraft.item.Item;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.MobCategory;
//import net.minecraft.world.item.Item;
//import net.minecraftforge.fml.RegistryObject;
//import net.minecraftforge.fmllegacy.RegistryObject;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import java.util.List;
//
//public class ExplorerEntities {
//
//    public static final DeferredRegister<EntityType<?>> ENTITIES;
//    public static final RegistryObject<EntityType<EnderreeperEntity>> ENDERREEPER;
//    //public static final RegistryObject<EntityType<WizardEntity>> WIZARD;
//    private static List<Item> spawnEggs = Lists.newArrayList();
//
//    public ExplorerEntities() {
//    }
//
//    public static <T extends Entity> RegistryObject<EntityType<T>> buildEntity(EntityType.IFactory<T> factory, String name, MobCategory entityClassification, float width, float height, int eggPrimary, int eggSecondary) {
//        String modName = new ResourceLocation(Explorercraft.MOD_ID, name).toString();
//
//        return ENTITIES.register(name, () -> {
//            return EntityType.Builder.of(factory, entityClassification).sized(width, height).build(modName);
//        });
//    }
//
//    static {
//        ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Explorercraft.MOD_ID);
//        ENDERREEPER = buildEntity(EnderreeperEntity::new, "enderreeper", MobCategory.MONSTER, 0.7F, 1.3F, 3801171, 7078066);
//        //WIZARD = buildEntity(WizardEntity::new, "wizard", MobCategory.MONSTER, 0.6F, 1.99F, 4869992, 16433238);
//    }
//
//}
