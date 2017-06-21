package de.comeight.crystallogy.client.creativeTabs;

import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CrystallogyMainTab extends CreativeTabs {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystallogyMainTab() {
        super(CrystallogyMainTab.getNextID() + "crystallogy_main_tab");
        setBackgroundImageName("item_search.png");
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(BlockHandler.CRYSTAL_ORE_RED);
    }

    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}