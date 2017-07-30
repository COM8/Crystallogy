package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;

public class CrystalHammer_blue extends BaseItemHammer {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "crystalHammer_blue";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalHammer_blue() {
        super(CustomToolMaterials.CRYSTAL_BLUE.setRepairItem(new ItemStack(ItemHandler.CRYSTAL_SHARD, 1)), ID);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}