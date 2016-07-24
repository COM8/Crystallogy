package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;

public class ItemsScrollBarList extends ScrollBarList {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ItemsScrollBarList(int width, int height, int posX, int posY, GuiBookPage page) {
		super(width, height, posX, posY, page);
		
		addAllEntrys();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void addAllEntrys(){
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.armorCatalys), null));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorPlate, 1, 0),
																									new ItemStack(ItemHandler.armorPlate, 1, 1),
																									new ItemStack(ItemHandler.armorPlate, 1, 2),
																									new ItemStack(ItemHandler.armorPlate, 1, 3),
																									new ItemStack(ItemHandler.armorPlate, 1, 4),}, 1000, null));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.badLuckDust), 
																									new ItemStack(ItemHandler.blindDust), 
																									new ItemStack(ItemHandler.damDust), 
																									new ItemStack(ItemHandler.drowDust), 
																									new ItemStack(ItemHandler.enderDust), 
																									new ItemStack(ItemHandler.fireDust), 
																									new ItemStack(ItemHandler.glowDust), 
																									new ItemStack(ItemHandler.hungDust), 
																									new ItemStack(ItemHandler.levDust), 
																									new ItemStack(ItemHandler.poisDust)}, 1000, null));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.chargedCombinedArmorMesh), null));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
				new ItemStack(ItemHandler.crystallDust_blue), 
				new ItemStack(ItemHandler.crystallDust_green), 
				new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.combinedArmorCompound), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.combinedArmorMesh), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.crystalKnifeBlade), null));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallHammerHead, 1, 0),
																									new ItemStack(ItemHandler.crystallHammerHead, 1, 1),
																									new ItemStack(ItemHandler.crystallHammerHead, 1, 2),
																									new ItemStack(ItemHandler.crystallHammerHead, 1, 3),}, 1000, null));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallSwordBlade, 1, 0),
																									new ItemStack(ItemHandler.crystallSwordBlade, 1, 1),
																									new ItemStack(ItemHandler.crystallSwordBlade, 1, 2),
																									new ItemStack(ItemHandler.crystallSwordBlade, 1, 3),}, 1000, null));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalPickaxeHead, 1, 0),
																									new ItemStack(ItemHandler.crystalPickaxeHead, 1, 1),
																									new ItemStack(ItemHandler.crystalPickaxeHead, 1, 2),
																									new ItemStack(ItemHandler.crystalPickaxeHead, 1, 3),}, 1000, null));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.enderonCrystal), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.energyCrystal), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.energyDust), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.pureCrystallDust), null));
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.toolRod), null));
	}
	
}
