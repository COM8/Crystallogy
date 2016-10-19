package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookBaseCraftingPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookAreaPicker extends GuiBookBaseCraftingPage {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookAreaPicker() {
		super(new ItemStack(ItemHandler.areaPicker));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookCraftingRecipe getRecipe() {
		BookButtonCrafting q = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.QUARTZ),  null);
		BookButtonCrafting c = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_yellow),  PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting t = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.toolRod),  PageRegistry.TOOL_ROD_PAGE);
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.areaPicker),  null);
		BookButtonCrafting e = BookButtonCrafting.EMPTY;
		e.enableFrame();
		
		BookButtonCrafting input[][] = new BookButtonCrafting[][]{	{q,e,q},
																	{c,c,c},
																	{e,t,e}}; 
		
		return new BookCraftingRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "The Area Picker lets you determine an area in the world."
				+ "\n"
				+ "To save the first position simply right-click on a block."
				+ "\n"
				+ "To save the second position you have to shift right-click on a block."
				+ "\n"
				+ "It's used in a couple Infusion Crafting recipes for specifying a specific area in the world";
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(BlockHandler.crystall_red),
																										new ItemStack(BlockHandler.crystall_blue),
																										new ItemStack(BlockHandler.crystall_green),
																										new ItemStack(BlockHandler.crystall_yellow),}, 1000, PageRegistry.CRYSTALS_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.toolRod), PageRegistry.TOOL_ROD_PAGE));
	}
	
	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 70, 0, 0, this);
	}
	
	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 105, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 95);
	}
	
}
