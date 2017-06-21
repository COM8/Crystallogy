package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.handler.CreativeTabHandler;
import net.minecraft.item.ItemPickaxe;

public class BaseItemPickaxe extends ItemPickaxe {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseItemPickaxe(ToolMaterial material, String id) {
        super(material);
        setCreativeTab(CreativeTabHandler.crystallogyMainTab);
        setUnlocalizedName(id);
        setRegistryName(id);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}