package com.zathrox.explorercraft.core.util;

import javax.annotation.Nonnull;

/**
 * Assorted common utility code
 *
 * @author Cadiboo
 */

public class ExplorerUtil {

    /**
     * Returns null, while claiming to never return null.
     * Useful for constants with @ObjectHolder who's values are null at compile time, but not at runtime
     *
     * @return null
     */
    @Nonnull
    // Get rid of "Returning null from Nonnull method" warnings
    @SuppressWarnings("ConstantConditions")
    public static <T> T _null() {
        return null;
    }

}

