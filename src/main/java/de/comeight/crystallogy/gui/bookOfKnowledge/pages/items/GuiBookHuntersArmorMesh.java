package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookBaseCraftingPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookHuntersArmorMesh extends GuiBookBaseCraftingPage {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookHuntersArmorMesh() {
		super(new ItemStack(ItemHandler.hunterArmorMesh));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookCraftingRecipe getRecipe() {
		BookButtonCrafting g = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.GOLD_NUGGET),  null);
		BookButtonCrafting d = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.DIAMOND),  null);
		BookButtonCrafting a = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorPlate, 1, 4),  PageRegistry.ARMOR_PLATE_PAGE_1);
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.hunterArmorMesh),  null);
		
		BookButtonCrafting input[][] = new BookButtonCrafting[][]{	{g,a,g},
																	{a,d,a},
																	{g,a,g}}; 
		
		return new BookCraftingRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "The Hunter's Armor Mesh is a crafting component for the Hunter's Dream Armor Compound.";
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.hunterArmorCompound),  PageRegistry.HUNTERS_ARMOR_COMPOUND_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorPlate, 1, 0),
																										new ItemStack(ItemHandler.armorPlate, 1, 1),
																										new ItemStack(ItemHandler.armorPlate, 1, 2),
																										new ItemStack(ItemHandler.armorPlate, 1, 3),
																										new ItemStack(ItemHandler.armorPlate, 1, 4),}, 1000, PageRegistry.ARMOR_PLATE_PAGE_1));
	}
	
	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 70, 0, 0, this);
	}
	
	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 120, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 105);
	}
	
}
