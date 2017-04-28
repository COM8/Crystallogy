package de.comeight.crystallogy.items;

import de.comeight.crystallogy.util.enums.EnumCrystalColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class CrystalShard extends BaseItem{
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "crystalShard";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalShard() {
        super(ID);
        setHasSubtypes(true);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + '_' + EnumCrystalColor.fromMeta(stack.getMetadata()).getName();
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for(int i = 0; i < 5; i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}