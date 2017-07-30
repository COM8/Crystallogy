package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;

public class CrystalHammer_white extends BaseItemHammer {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "crystalHammer_white";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalHammer_white() {
        super(CustomToolMaterials.CRYSTAL_WHITE.setRepairItem(new ItemStack(ItemHandler.CRYSTAL_SHARD, 4)), ID);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}