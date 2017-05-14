package de.comeight.crystallogy.items.tools;

import net.minecraft.item.ItemSword;

public class BaseItemSword extends ItemSword {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseItemSword(ToolMaterial material, String ID) {
        super(material);
        //setCreativeTab(CommonProxy.crystallogyMainTab); //TODO set creative tab
        setUnlocalizedName(ID);
        setRegistryName(ID);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}