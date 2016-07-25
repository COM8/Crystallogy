package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
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
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GuiBookCrystalCrusher extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer crystalGlass;
	
	private BookCraftingRecipe recipe;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystalCrusher() {
		super("Crystal Crusher:");
		
		crystalGlass = new BookMultiItemRenderer(new ItemStack[]{new ItemStack(BlockHandler.crystallCrusher)}, 1000, 5.0F);
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		BookButtonCrafting iB = new BookButtonCrafting(getNextButtonId(), new ItemStack(Blocks.IRON_BLOCK), null);
		BookButtonCrafting b = new BookButtonCrafting(getNextButtonId(), new ItemStack(Blocks.BRICK_BLOCK), null);
		BookButtonCrafting i = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.IRON_INGOT), null);
		
		BookButtonCrafting[][] input = new BookButtonCrafting[][]{	{b, b, b},
																	{i, BookButtonCrafting.EMPTY, i},
																	{i, iB, i}};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystallCrusher), null);
		recipe = new BookCraftingRecipe(input, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawItem();
		drawText();
		drawCraftingChaptersText();
		drawRecipe(mouseX, mouseY);
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipe.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 30);
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 125, xSize / 2 - 12, 1.0F, "The Crystal Crusher is used to crush Crystals from their block form in a really fine pile of dust.");
	}
	
	private void drawItem(){
		crystalGlass.drawItem(xPosBook + 50, yPosBook + 35);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 10, xSize / 2 - 10, "Recipe:");
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 35, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																													new ItemStack(ItemHandler.crystallDust_blue), 
																													new ItemStack(ItemHandler.crystallDust_green), 
																													new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 120, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 105);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipe.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
