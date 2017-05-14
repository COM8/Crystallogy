package de.comeight.crystallogy.items.tools;

import net.minecraft.item.ItemPickaxe;

public class BaseItemPickaxe extends ItemPickaxe {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseItemPickaxe(ToolMaterial material, String id) {
        super(material);
        //setCreativeTab(CommonProxy.crystallogyMainTab); //TODO set creative tab
        setUnlocalizedName(id);
        setRegistryName(id);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}