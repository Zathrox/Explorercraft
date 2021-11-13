package dev.driscollcreations.explorercraft.setup;

import com.google.common.collect.Sets;
import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import dev.driscollcreations.explorercraft.cymru.blocks.CymruBlocks;
import dev.driscollcreations.explorercraft.vanillatweaks.setup.VanillaTweaksBlocks;
import dev.driscollcreations.explorercraft.vanillatweaks.tileentities.SleepingBagBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ExplorerTileEntities {

    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Explorercraft.MOD_ID);

    public static final RegistryObject<BlockEntityType<SleepingBagBlockEntity>> SLEEPING_BAG = TILE_ENTITIES.register("sleeping_bag",
            () -> new BlockEntityType<>(SleepingBagBlockEntity::new, Sets.newHashSet(VanillaTweaksBlocks.LEATHER_SLEEPING_BAG.get(), VanillaTweaksBlocks.GRAY_SLEEPING_BAG.get(), VanillaTweaksBlocks.LIGHT_GRAY_SLEEPING_BAG.get(), VanillaTweaksBlocks.BROWN_SLEEPING_BAG.get(), VanillaTweaksBlocks.WHITE_SLEEPING_BAG.get(), VanillaTweaksBlocks.BLACK_SLEEPING_BAG.get(), VanillaTweaksBlocks.PINK_SLEEPING_BAG.get(), VanillaTweaksBlocks.RED_SLEEPING_BAG.get(), VanillaTweaksBlocks.ORANGE_SLEEPING_BAG.get(), VanillaTweaksBlocks.YELLOW_SLEEPING_BAG.get(), VanillaTweaksBlocks.LIME_SLEEPING_BAG.get(), VanillaTweaksBlocks.GREEN_SLEEPING_BAG.get(), VanillaTweaksBlocks.LIGHT_BLUE_SLEEPING_BAG.get(), VanillaTweaksBlocks.BLUE_SLEEPING_BAG.get(), VanillaTweaksBlocks.CYAN_SLEEPING_BAG.get(), VanillaTweaksBlocks.MAGENTA_SLEEPING_BAG.get(), VanillaTweaksBlocks.PURPLE_SLEEPING_BAG.get()), null));

//    public static final RegistryObject<BlockEntityType<BlackHoleBlockEntity>> BLACK_HOLE = TILE_ENTITIES.register("black_hole",
//            () -> new BlockEntityType<>(BlackHoleBlockEntity::new, Sets.newHashSet(VanillaTweaksBlocks.BLACK_HOLE.get()), null));

    public static final RegistryObject<BlockEntityType<ExplorerSigns.CustomSignBlockEntity>> EXPLORER_SIGNS = TILE_ENTITIES.register("explorer_signs",
            () -> BlockEntityType.Builder.of(ExplorerSigns.CustomSignBlockEntity::new, CymruBlocks.ASH_WALL_SIGN.get(), BambooGroveBlocks.BAMBOO_WALL_SIGN.get(), BambooGroveBlocks.CHERRY_WALL_SIGN.get(), BambooGroveBlocks.CHERRY_BLOSSOM_WALL_SIGN.get(), BambooGroveBlocks.MAPLE_WALL_SIGN.get(), CymruBlocks.ASH_STANDING_SIGN.get(), BambooGroveBlocks.BAMBOO_STANDING_SIGN.get(), BambooGroveBlocks.CHERRY_STANDING_SIGN.get(), BambooGroveBlocks.CHERRY_BLOSSOM_STANDING_SIGN.get(), BambooGroveBlocks.MAPLE_STANDING_SIGN.get()).build(null));


//    public static final RegistryObject<BlockEntityType<SleepingBagBlockEntity>> SLEEPING_BAG = TILE_ENTITIES.register("sleeping_bag",
//            () -> BlockEntityType.Builder.of(SleepingBagBlockEntity::new, VanillaTweaksBlocks.LEATHER_SLEEPING_BAG.get()).build(null));

}
