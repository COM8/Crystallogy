package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;

public class CrystalHammer_red extends BaseItemHammer {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "crystalHammer_red";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalHammer_red() {
        super(CustomToolMaterials.CRYSTAL_RED.setRepairItem(new ItemStack(ItemHandler.CRYSTAL_SHARD, 0)), ID);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}