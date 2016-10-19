package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class AllScrollBarList extends ScrollBarListSearch {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public AllScrollBarList(int width, int height, int posX, int posY, GuiBookPage page) {
		super(width, height, posX, posY, page);
		
		addAllEntrys();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void addAllEntrys(){
		addEntrys(BlocksScrollBarList.getEntrys());
		addEntrys(MachinesScrollBarList.getEntrys());
		addEntrys(ItemsScrollBarList.getEntrys());
		addEntrys(ToolsScrollBarList.getEntrys());
		addEntrys(ArmorScrollBarList.getEntrys());
	}
	
}
