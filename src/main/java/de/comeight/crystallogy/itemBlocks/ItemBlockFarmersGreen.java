package de.comeight.crystallogy.itemBlocks;

import java.util.List;

import de.comeight.crystallogy.blocks.FarmersGreen;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockFarmersGreen extends BaseItemBlock {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ItemBlockFarmersGreen(Block block) {
		super(block, FarmersGreen.ID);
		setMaxStackSize(1);
		setHasSubtypes(true);
        setMaxDamage(0);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@SideOnly(Side.CLIENT)
	@Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
		NBTTagCompound compound = new NBTTagCompound();
		
		compound.setInteger("growthLeft", 12000);
		ItemStack i1 = new  ItemStack(itemIn, 1, 0);
		i1.setTagCompound(compound);
	
		ItemStack i2 = new  ItemStack(itemIn, 1, 1);
		
		subItems.add(i1);
		subItems.add(i2);
    }
	
	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
