package de.comeight.crystallogy.items;

import de.comeight.crystallogy.handler.CreativeTabHandler;
import net.minecraft.item.Item;

public abstract class BaseItem extends Item{
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseItem(String id) {
        setUnlocalizedName(id);
        setRegistryName(id);
        setCreativeTab(CreativeTabHandler.crystallogyMainTab);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}