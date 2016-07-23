package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPageSuggestions;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GuiBookFarmersGreen2 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookCraftingRecipe recipe;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookFarmersGreen2() {
		super("");
		
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		BookButtonCrafting g = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystalGlas, 1, 2), PageRegistry.CRYSTAL_GLASS_PAGE);
		BookButtonCrafting i = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.IRON_INGOT), null);
		BookButtonCrafting p = new BookButtonCrafting(getNextButtonId(), GuiBookUtilities.toItemStackArray(OreDictionary.getOres("plankWood")), 1000, null);
		
		BookButtonCrafting[][] input = new BookButtonCrafting[][]{	{i, p, i},
																	{g, BookButtonCrafting.EMPTY, g},
																	{g, g, g}};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.farmersGreen), null);
		recipe = new BookCraftingRecipe(input, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCraftingChaptersText();
		drawRecipe(mouseX, mouseY);
		initRecipe();
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipe.drawScreen(mouseX, mouseY, xPosBook + 30, yPosBook + 30);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP, WRAPWIDTH, "Recipe:");
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 70, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(BlockHandler.crystalGlas, 1, 0), 
																										new ItemStack(BlockHandler.crystalGlas, 1, 1),
																										new ItemStack(BlockHandler.crystalGlas, 1, 2),
																										new ItemStack(BlockHandler.crystalGlas, 1, 3)}, 1000, PageRegistry.PLAYER_JAR_1));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.fertilizerPotato), PageRegistry.FERTILIZER_POTATO_PAGE));
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + 25);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipe.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
