package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ArmorScrollBarList extends ScrollBarList {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ArmorScrollBarList(int width, int height, int posX, int posY, GuiBookPage page) {
		super(width, height, posX, posY, page);
		
		addAllEntrys();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void addAllEntrys(){
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_blue), 
																									new ItemStack(ItemHandler.armorChestplate_blue), 
																									new ItemStack(ItemHandler.armorLeggins_blue), 
																									new ItemStack(ItemHandler.armorBoots_blue)}, 1000, PageRegistry.BLUE_CRYSTAL_ARMOR_PAGE_1));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_red), 
																									new ItemStack(ItemHandler.armorChestplate_red), 
																									new ItemStack(ItemHandler.armorLeggins_red), 
																									new ItemStack(ItemHandler.armorBoots_red)}, 1000, PageRegistry.RED_CRYSTAL_ARMOR_PAGE_1));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_green), 
																									new ItemStack(ItemHandler.armorChestplate_green), 
																									new ItemStack(ItemHandler.armorLeggins_green), 
																									new ItemStack(ItemHandler.armorBoots_green)}, 1000, PageRegistry.GREEN_CRYSTAL_ARMOR_PAGE_1));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_yellow), 
																									new ItemStack(ItemHandler.armorChestplate_yellow), 
																									new ItemStack(ItemHandler.armorLeggins_yellow), 
																									new ItemStack(ItemHandler.armorBoots_yellow)}, 1000, PageRegistry.YELLOW_CRYSTAL_ARMOR_PAGE_1));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_combined), 
																									new ItemStack(ItemHandler.armorChestplate_combined), 
																									new ItemStack(ItemHandler.armorLeggins_combined), 
																									new ItemStack(ItemHandler.armorBoots_combined)}, 1000, PageRegistry.COMBINED_ARMOR_PAGE_1));
		
		addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_hunter), 
																									new ItemStack(ItemHandler.armorChestplate_hunter), 
																									new ItemStack(ItemHandler.armorLeggins_hunter), 
																									new ItemStack(ItemHandler.armorBoots_hunter)}, 1000, PageRegistry.HUNTERS_ARMOR_PAGE_1));
	}
	
}
