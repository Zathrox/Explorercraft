package com.zathrox.explorercraft.core.registry;


import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.BannerPattern;

import java.util.ArrayList;
import java.util.List;

public class ExplorerBannerPattern {

    private static List<BannerPattern> patterns = new ArrayList<>();

    public static BannerPattern WELSH_FLAG = BannerPattern.create("WELSH_FLAG", "welshflag", "flag", new ItemStack(ExplorerItems.LEEK));
    public static BannerPattern WELSH_DRAGON = BannerPattern.create("WELSH_DRAGON", "welshdragon", "dra", new ItemStack(ExplorerItems.RUBY));
    public static BannerPattern WALES = BannerPattern.create("WALES", "wales", "wal", new ItemStack(ExplorerItems.RUBY));

    public static void init(){
        WELSH_FLAG = banner("welshflag",  new ItemStack(ExplorerItems.RUBY));
        System.out.println("BANNERS ADDED");
        //banner(WELSH_DRAGON);
        //banner(WALES);
    }

    public static BannerPattern banner(String name, ItemStack craftingStack){

        final BannerPattern pattern = BannerPattern.create(name.toUpperCase(), Explorercraft.MOD_ID + "_" + name, Explorercraft.MOD_ID + "_" + name, craftingStack);
        patterns.add(pattern);
        return pattern;

   }

    public static List<BannerPattern> getModdedPatterns () {

        return patterns;
    }
}


