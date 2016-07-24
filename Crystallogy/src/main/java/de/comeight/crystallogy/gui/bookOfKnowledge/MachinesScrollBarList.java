package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.item.ItemStack;

public class MachinesScrollBarList extends ScrollBarList {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MachinesScrollBarList(int width, int height, int posX, int posY, GuiBookPage page) {
		super(width, height, posX, posY, page);
		
		addAllEntrys();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void addAllEntrys(){
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.armorCombiner), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.charger), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.compressor), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.crystallCrusher), null));
	}
	
}
