package de.comeight.crystallogy.itemBlocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
        subItems.add(new ItemStack(itemIn, 1, 2));
        subItems.add(new ItemStack(itemIn, 1, 3));
        subItems.add(new ItemStack(itemIn, 1, 4));
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