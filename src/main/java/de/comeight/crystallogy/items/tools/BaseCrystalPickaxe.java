package de.comeight.crystallogy.items.tools;

import net.minecraft.item.ItemStack;

public class BaseCrystalPickaxe extends BaseItemPickaxe{
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseCrystalPickaxe(ToolMaterial material, String id) {
        super(material, id);
        attackDamage *= 0.25;
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------
    @Override
    public int getMaxDamage(ItemStack stack) {
        return super.getMaxDamage(stack) * 10;
    }

    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}