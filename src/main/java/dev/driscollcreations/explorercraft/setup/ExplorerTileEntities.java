package dev.driscollcreations.explorercraft.setup;

import com.google.common.collect.Sets;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import dev.driscollcreations.explorercraft.vanillatweaks.tileentities.SleepingBagTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExplorerTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Explorercraft.MOD_ID);

    public static final RegistryObject<TileEntityType<SleepingBagTileEntity>> SLEEPING_BAG = TILE_ENTITIES.register("sleeping_bag",
            () -> new TileEntityType<>(SleepingBagTileEntity::new, Sets.newHashSet(VanillaTweaksBlocks.LEATHER_SLEEPING_BAG.get(), VanillaTweaksBlocks.GRAY_SLEEPING_BAG.get(), VanillaTweaksBlocks.LIGHT_GRAY_SLEEPING_BAG.get(), VanillaTweaksBlocks.BROWN_SLEEPING_BAG.get(), VanillaTweaksBlocks.WHITE_SLEEPING_BAG.get(), VanillaTweaksBlocks.BLACK_SLEEPING_BAG.get(), VanillaTweaksBlocks.PINK_SLEEPING_BAG.get(), VanillaTweaksBlocks.RED_SLEEPING_BAG.get(), VanillaTweaksBlocks.ORANGE_SLEEPING_BAG.get(), VanillaTweaksBlocks.YELLOW_SLEEPING_BAG.get(), VanillaTweaksBlocks.LIME_SLEEPING_BAG.get(), VanillaTweaksBlocks.GREEN_SLEEPING_BAG.get(), VanillaTweaksBlocks.LIGHT_BLUE_SLEEPING_BAG.get(), VanillaTweaksBlocks.BLUE_SLEEPING_BAG.get(), VanillaTweaksBlocks.CYAN_SLEEPING_BAG.get(), VanillaTweaksBlocks.MAGENTA_SLEEPING_BAG.get(), VanillaTweaksBlocks.PURPLE_SLEEPING_BAG.get()), null));

//    public static final RegistryObject<TileEntityType<SleepingBagTileEntity>> SLEEPING_BAG = TILE_ENTITIES.register("sleeping_bag",
//            () -> TileEntityType.Builder.of(SleepingBagTileEntity::new, VanillaTweaksBlocks.LEATHER_SLEEPING_BAG.get()).build(null));

}
