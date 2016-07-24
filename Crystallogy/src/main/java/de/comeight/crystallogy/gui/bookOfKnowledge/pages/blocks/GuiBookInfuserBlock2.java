package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPageSuggestions;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class GuiBookInfuserBlock2 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	private BookCraftingRecipe recipe;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookInfuserBlock2() {
		super("");
		
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		BookButtonCrafting c = new BookButtonCrafting(getNextButtonId(), new ItemStack(Blocks.CRAFTING_TABLE), null);
		BookButtonCrafting s = new BookButtonCrafting(getNextButtonId(), new ItemStack(Blocks.COBBLESTONE), null);
		BookButtonCrafting p = new BookButtonCrafting(getNextButtonId(), new ItemStack(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE), null);
		
		BookButtonCrafting[][] input = new BookButtonCrafting[][]{	{s, c, s},
																	{BookButtonCrafting.EMPTY, s, BookButtonCrafting.EMPTY},
																	{p, p, p}};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.infuserBlock), null);
		recipe = new BookCraftingRecipe(input, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCraftingChaptersText();
		drawRecipe(mouseX, mouseY);
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipe.drawScreen(mouseX, mouseY, xPosBook + BORDER_LEFT, yPosBook + 30);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP, WRAPWIDTH, "Recipe:");
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 35, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		BookButtonCategory infusionButton = new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.infuserBlock), PageRegistry.INFUSION_CRAFTING_PAGE);
		infusionButton.setCustomDescription("Infusion Crafting");
		suggestionsList.addEntry(infusionButton);
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, WRAPWIDTH, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + 25);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipe.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
