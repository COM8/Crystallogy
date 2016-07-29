package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookBaseCraftingPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GuiBookEnergyDust extends GuiBookBaseCraftingPage {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookEnergyDust() {
		super(new ItemStack(ItemHandler.energyDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookCraftingRecipe getRecipe() {
		BookButtonCrafting r = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.REDSTONE),  null);
		BookButtonCrafting d = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_red),  PageRegistry.CRYSTAL_DUST_PAGE);
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.energyDust),  null);
		
		BookButtonCrafting input[][] = new BookButtonCrafting[][]{	{r,r,r},
																	{r,d,r},
																	{r,r,r}}; 
		
		return new BookCraftingRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "Energy Dust is a crafting component for the Energy Crystal.";
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																													new ItemStack(ItemHandler.crystallDust_blue), 
																													new ItemStack(ItemHandler.crystallDust_green), 
																													new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.energyCrystal), PageRegistry.ENERGY_CRYSTAL_PAGE));
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
