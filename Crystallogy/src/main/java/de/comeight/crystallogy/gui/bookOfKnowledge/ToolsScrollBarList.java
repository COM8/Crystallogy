package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;

public class ToolsScrollBarList extends ScrollBarList {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ToolsScrollBarList(int width, int height, int posX, int posY, GuiBookPage page) {
		super(width, height, posX, posY, page);
		
		addAllEntrys();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void addAllEntrys(){
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.entityCrystalKnife), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.entityGrabber), null));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallHammer_red), 
																									new ItemStack(ItemHandler.crystallHammer_blue), 
																									new ItemStack(ItemHandler.crystallHammer_green), 
																									new ItemStack(ItemHandler.crystallHammer_yellow)}, 1000, null));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalSword_red), 
																									new ItemStack(ItemHandler.crystalSword_blue), 
																									new ItemStack(ItemHandler.crystalSword_green), 
																									new ItemStack(ItemHandler.crystalSword_yellow)}, 1000, null));

		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalPickaxe_red), 
																									new ItemStack(ItemHandler.crystalPickaxe_blue), 
																									new ItemStack(ItemHandler.crystalPickaxe_green), 
																									new ItemStack(ItemHandler.crystalPickaxe_yellow)}, 1000, null));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.playerCrystalKnife), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.vaporizer), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.vaporizerDirection), null));
	}
	
}
