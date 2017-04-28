package de.comeight.crystallogy.items;

import net.minecraft.item.Item;

public abstract class BaseItem extends Item{
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseItem(String id) {
        //setCreativeTab() //TODO set creative tab
        setUnlocalizedName(id);
        setRegistryName(id);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}