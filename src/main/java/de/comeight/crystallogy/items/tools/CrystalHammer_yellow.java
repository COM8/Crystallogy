package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;

public class CrystalHammer_yellow extends BaseItemHammer {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "crystalHammer_yellow";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalHammer_yellow() {
        super(CustomToolMaterials.CRYSTAL_YELLOW.setRepairItem(new ItemStack(ItemHandler.CRYSTAL_SHARD, 3)), ID);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}