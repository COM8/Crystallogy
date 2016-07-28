package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCrusherRecipe;
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
import net.minecraft.item.ItemStack;

public class GuiBookCrystalDusts extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer renderer;
	
	private BookCrusherRecipe recipeRed;
	private BookCrusherRecipe recipeBlue;
	private BookCrusherRecipe recipeGreen;
	private BookCrusherRecipe recipeYellow;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystalDusts() {
		super("Crystal Dust:");
		
		renderer = new BookMultiItemRenderer(new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red),
																new ItemStack(ItemHandler.crystallDust_blue),
																new ItemStack(ItemHandler.crystallDust_green),
																new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, 5.0F);
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		BookButtonCrafting cR = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_red), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting cB = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_blue), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting cG = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_green), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting cY = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_yellow), PageRegistry.CRYSTALS_PAGE);
		
		BookButtonCrafting dR = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_red), PageRegistry.CRYSTAL_DUST_PAGE);
		BookButtonCrafting dB = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_blue), PageRegistry.CRYSTAL_DUST_PAGE);
		BookButtonCrafting dG = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_green), PageRegistry.CRYSTAL_DUST_PAGE);
		BookButtonCrafting dY = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_yellow), PageRegistry.CRYSTAL_DUST_PAGE);
		
		recipeRed = new BookCrusherRecipe(cR, dR);
		recipeBlue = new BookCrusherRecipe(cB, dB);
		recipeGreen = new BookCrusherRecipe(cG, dG);
		recipeYellow = new BookCrusherRecipe(cY, dY);
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
		recipeRed.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + 45, yPosBook + 30);
		recipeBlue.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + 45, yPosBook + 65);
		recipeGreen.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + 45, yPosBook + 100);
		recipeYellow.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + 45, yPosBook + 135);
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 120, WRAPWIDTH, 1.0F, "Crystal Dusts are created in the Crystal Crusher by crushing a Crystal in it's equivalent dust. "
				+ "Currently there are 4 different types of dust.");
	}
	
	private void drawItem(){
		renderer.drawItem(xPosBook + 50, yPosBook + 30);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 10, WRAPWIDTH / 2 + 10, "Recipe (Crusher):");
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 35, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(),0, 0, null, new ItemStack[]{	new ItemStack(BlockHandler.crystall_red), 
																													new ItemStack(BlockHandler.crystall_blue),
																													new ItemStack(BlockHandler.crystall_green),
																													new ItemStack(BlockHandler.crystall_yellow)}, 1000, PageRegistry.CRYSTALS_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																													new ItemStack(ItemHandler.crystallDust_blue), 
																													new ItemStack(ItemHandler.crystallDust_green), 
																													new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.crystallCrusher), PageRegistry.CRYSTAL_CRUSHER_PAGE));
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 85, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 70);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipeRed.mouseReleased(mouseX, mouseY, state, this);
		recipeBlue.mouseReleased(mouseX, mouseY, state, this);
		recipeGreen.mouseReleased(mouseX, mouseY, state, this);
		recipeYellow.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
