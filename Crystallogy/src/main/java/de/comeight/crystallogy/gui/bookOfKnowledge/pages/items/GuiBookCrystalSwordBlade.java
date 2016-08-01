package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiRecipe;
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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCrystalSwordBlade extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer renderer;
	
	private BookMultiRecipe recipes;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystalSwordBlade() {
		super("Crystal Sword Blade:");
		
		renderer = new BookMultiItemRenderer(new ItemStack[]{	new ItemStack(ItemHandler.crystallSwordBlade, 1, 0),
																new ItemStack(ItemHandler.crystallSwordBlade, 1, 1),
																new ItemStack(ItemHandler.crystallSwordBlade, 1, 2),
																new ItemStack(ItemHandler.crystallSwordBlade, 1, 3),}, 1000, 5.0F);
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		BookButtonCrafting cR = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_red), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting cB = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_blue), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting cG = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_green), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting cY = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_yellow), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting e = BookButtonCrafting.EMPTY;
		
		BookButtonCrafting dR = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallSwordBlade, 1, 0), null);
		BookButtonCrafting dB = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallSwordBlade, 1, 1), null);
		BookButtonCrafting dG = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallSwordBlade, 1, 2), null);
		BookButtonCrafting dY = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallSwordBlade, 1, 3), null);
		
		BookButtonCrafting[][] input = new BookButtonCrafting[][]{	{cR,e,e},
																	{cR,e,e},
																	{cR,e,e}};
		BookCraftingRecipe recipeRed = new BookCraftingRecipe(input, dR);
		
		input = new BookButtonCrafting[][]{	{cB,e,e},
											{cB,e,e},
											{cB,e,e}};
		BookCraftingRecipe recipeBlue = new BookCraftingRecipe(input, dB);
		
		input = new BookButtonCrafting[][]{	{cG,e,e},
											{cG,e,e},
											{cG,e,e}};
		BookCraftingRecipe recipeGreen = new BookCraftingRecipe(input, dG);
		
		input = new BookButtonCrafting[][]{	{cY,e,e},
											{cY,e,e},
											{cY,e,e}};
		BookCraftingRecipe recipeYellow = new BookCraftingRecipe(input, dY);
		
		recipes = new BookMultiRecipe(new BookCraftingRecipe[]{recipeRed, recipeBlue, recipeGreen, recipeYellow});
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
		recipes.drawRecipe(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 35);
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 120, WRAPWIDTH, 1.0F, "Crystal Sword Blades are crafting components. They're used for crafting Crystal Swords and they exist in four colors:\n"
				+ "-red\n"
				+ "-blue\n"
				+ "-green\n"
				+ "-yellow");
	}
	
	private void drawItem(){
		renderer.drawItem(xPosBook + 50, yPosBook + 30);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 10, WRAPWIDTH, "Recipe:");
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 70, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(),0, 0, null, new ItemStack[]{	new ItemStack(BlockHandler.crystall_red), 
																													new ItemStack(BlockHandler.crystall_blue),
																													new ItemStack(BlockHandler.crystall_green),
																													new ItemStack(BlockHandler.crystall_yellow)}, 1000, PageRegistry.CRYSTALS_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(),0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalSword_red), 
																													new ItemStack(ItemHandler.crystalSword_blue),
																													new ItemStack(ItemHandler.crystalSword_green),
																													new ItemStack(ItemHandler.crystalSword_yellow)}, 1000, PageRegistry.CRYSTAL_SWORD_PAGE_1));
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 120, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 105);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipes.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
