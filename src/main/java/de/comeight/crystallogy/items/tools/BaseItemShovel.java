package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.handler.CreativeTabHandler;
import net.minecraft.item.ItemSpade;

public class BaseItemShovel extends ItemSpade{
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseItemShovel(ToolMaterial material, String id) {
        super(material);
        setCreativeTab(CreativeTabHandler.crystallogyMainTab);
        setUnlocalizedName(id);
        setRegistryName(id);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}