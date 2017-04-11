package de.comeight.crystallogy.tabs;

import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CrystallogyMainTab extends CreativeTabs{
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystallogyMainTab() {
		super(CreativeTabs.getNextID(), "crystallogy");
		
		setBackgroundImageName("item_search.png");
		
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
    {
		return Item.getItemFromBlock(BlockHandler.crystall_green);
    }
	
	@Override
	public boolean hasSearchBar() {
		return true;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
}
