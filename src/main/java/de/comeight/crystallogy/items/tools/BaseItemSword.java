package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.handler.CreativeTabHandler;
import net.minecraft.item.ItemSword;

public class BaseItemSword extends ItemSword {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseItemSword(ToolMaterial material, String ID) {
        super(material);
        setCreativeTab(CreativeTabHandler.crystallogyMainTab);
        setUnlocalizedName(ID);
        setRegistryName(ID);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}