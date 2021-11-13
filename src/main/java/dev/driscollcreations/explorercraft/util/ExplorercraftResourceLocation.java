package dev.driscollcreations.explorercraft.util;

import dev.driscollcreations.explorercraft.Explorercraft;
import net.minecraft.resources.ResourceLocation;

public class ExplorercraftResourceLocation extends ResourceLocation {
    public ExplorercraftResourceLocation(String resourceName) {
        super(addModNamespace(resourceName));
    }

    private static String addModNamespace(String resourceName) {
        if (resourceName.contains(":")) {
            return resourceName;
        }
        return Explorercraft.MOD_ID + ":" + resourceName;
    }
}