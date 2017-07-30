package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;

public class CrystalHammer_green extends BaseItemHammer {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "crystalHammer_green";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalHammer_green() {
        super(CustomToolMaterials.CRYSTAL_GREEN.setRepairItem(new ItemStack(ItemHandler.CRYSTAL_SHARD, 2)), ID);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}