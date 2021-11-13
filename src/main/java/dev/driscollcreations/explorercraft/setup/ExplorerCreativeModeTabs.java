package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class ExplorerCreativeModeTabs {

    public static final CreativeModeTab EXPLORERCRAFT = new ModCreativeModeTab(Explorercraft.MOD_ID, () -> new ItemStack(BambooGroveBlocks.BAMBOO_PLANKS.get()));

    public static final class ModCreativeModeTab extends CreativeModeTab {

        @Nonnull
        private final Supplier<ItemStack> iconSupplier;

        public ModCreativeModeTab(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        @Nonnull
        public ItemStack makeIcon() {
            return iconSupplier.get();
        }

    }

}