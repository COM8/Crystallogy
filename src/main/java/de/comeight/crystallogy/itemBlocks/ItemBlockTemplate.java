package de.comeight.crystallogy.itemBlocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemBlockTemplate extends BaseItemBlock{
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------
    public ItemBlockTemplate(Block block) {
        super(block, "CrystalOre.ID"); //Change ID

        setHasSubtypes(true);
        setMaxDamage(0);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------
    @Override
    public int getMetadata(int damage) {
        return damage;
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

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        //EnumCrystalColor c = EnumCrystalColor.fromMeta(stack.getItemDamage());
        //return super.getUnlocalizedName(stack) + '_' + c.getName();
        return "";
    }

    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------

}