package dev.driscollcreations.explorercraft.setup;

import dev.driscollcreations.explorercraft.Explorercraft;
import dev.driscollcreations.explorercraft.bamboogrove.setup.BambooGroveBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public final class ExplorerItemGroups {

    public static final ItemGroup EXPLORERCRAFT = new ModItemGroup(Explorercraft.MOD_ID, () -> new ItemStack(BambooGroveBlocks.BAMBOO_PLANKS.get()));

    public static final class ModItemGroup extends ItemGroup {

        @Nonnull
        private final Supplier<ItemStack> iconSupplier;

        public ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
            super(name);
            this.iconSupplier = iconSupplier;
        }

        @Override
        @Nonnull
        public ItemStack createIcon() {
            return iconSupplier.get();
        }

    }

}