package com.zathrox.explorercraft.core.registry;


import com.zathrox.explorercraft.core.Explorercraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.BannerPattern;

import java.util.ArrayList;
import java.util.List;

public class ExplorerBannerPattern {

    private static List<BannerPattern> patterns = new ArrayList<>();

    public static BannerPattern WELSH_FLAG; /*= BannerPattern.create("WELSH_FLAG", "welshflag", "flag", new ItemStack(ExplorerItems.LEEK));*/
    //public static BannerPattern WELSH_DRAGON; /*= BannerPattern.create("WELSH_DRAGON", "welshdragon", "dra", new ItemStack(ExplorerItems.RUBY));*/
    public static BannerPattern WALES; /*= BannerPattern.create("WALES", "wales", "wal", new ItemStack(ExplorerItems.RUBY));*/

    public static void init(){
        WELSH_FLAG = banner("WELSH_FLAG", "welshflag", "flag", new ItemStack(ExplorerItems.LEEK));
        //WELSH_DRAGON = banner("WELSH_DRAGON", "welshdragon", "dra",  new ItemStack(ExplorerItems.RUBY));
        //WALES = banner("WALES", "wales", "wal", new ItemStack(ExplorerBlocks.DAFFODIL));
        BannerPattern.field_222480_O = BannerPattern.field_222480_O + 1;
        //banner(WELSH_DRAGON);
        //banner(WALES);
    }

    public static BannerPattern banner(String enumName, String fileName, String hashname, ItemStack craftingStack){

        final BannerPattern pattern = BannerPattern.create(enumName, fileName, hashname, craftingStack);
        patterns.add(pattern);
        return pattern;

   }

    public static List<BannerPattern> getModdedPatterns () {

        return patterns;
    }
}


