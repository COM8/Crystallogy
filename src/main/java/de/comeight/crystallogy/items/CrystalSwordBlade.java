package de.comeight.crystallogy.items;

import de.comeight.crystallogy.util.enums.EnumCrystalColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class CrystalSwordBlade extends BaseItem{
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "crystalSwordBlade";

    //-----------------------------------------------Constructor:-------------------------------------------
    public CrystalSwordBlade() {
        super(ID);
        setHasSubtypes(true);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + '_' + EnumCrystalColor.fromMeta(stack.getMetadata()).getName();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab))
        {
            for (int i = 0; i < 5; ++i)
            {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }
    
    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}