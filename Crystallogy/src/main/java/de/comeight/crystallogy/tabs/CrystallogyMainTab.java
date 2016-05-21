package de.comeight.crystallogy.tabs;

import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CrystallogyMainTab extends CreativeTabs{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private int index;

	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystallogyMainTab() {
		super(CreativeTabs.getNextID(), "crystallogyMainTab");
		index = 0;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem()
    {
		return Item.getItemFromBlock(BlockHandler.crystall_green);
    }
	
}
