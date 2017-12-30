package de.comeight.crystallogy.items.tools;

import net.minecraft.item.ItemStack;

public class BaseItemHammer extends BaseItemPickaxe {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseItemHammer(ToolMaterial material, String id) {
        super(material, id);
        attackDamage *= 1.7;
        attackSpeed = -3.4F;
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------
    @Override
    public int getMaxDamage(ItemStack stack) {
        return super.getMaxDamage(stack) * 15;
    }

    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}