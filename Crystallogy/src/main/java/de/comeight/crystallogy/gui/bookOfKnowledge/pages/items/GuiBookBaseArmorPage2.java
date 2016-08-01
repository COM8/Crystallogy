package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPageSuggestions;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public abstract class GuiBookBaseArmorPage2 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookCraftingRecipe recipeHelmet;
	private BookCraftingRecipe recipeChestplate;
	private BookCraftingRecipe recipeLeggins;
	private BookCraftingRecipe recipeBoots;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookBaseArmorPage2() {
		super("");
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	protected abstract ItemStack getArmorMaterial();
	
	protected abstract GuiBookPage getArmorMaterialPage();
	
	protected abstract ItemArmor getHelmet();
	
	protected abstract ItemArmor getChestplate();
	
	protected abstract ItemArmor getLeggins();
	
	protected abstract ItemArmor getBoots();
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		BookButtonCrafting k = new BookButtonCrafting(getNextButtonId(), getArmorMaterial(), getArmorMaterialPage());
		BookButtonCrafting e = BookButtonCrafting.EMPTY;
		e.enableFrame();
		
		BookButtonCrafting[][] input = new BookButtonCrafting[][]{	{k,k,k},
																	{k,e,k},
																	{e,e,e}};
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(getHelmet()), null);
		recipeHelmet = new BookCraftingRecipe(input, output);
		
		input = new BookButtonCrafting[][]{	{k,e,k},
											{k,k,k},
											{k,k,k}};
		output = new BookButtonCrafting(getNextButtonId(), new ItemStack(getChestplate()), null);
		recipeChestplate = new BookCraftingRecipe(input, output);
		
		input = new BookButtonCrafting[][]{	{k,k,k},
											{k,e,k},
											{k,e,k}};
		output = new BookButtonCrafting(getNextButtonId(), new ItemStack(getLeggins()), null);
		recipeLeggins = new BookCraftingRecipe(input, output);
		
		input = new BookButtonCrafting[][]{	{k,e,k},
											{k,e,k},
											{e,e,e}};
		output = new BookButtonCrafting(getNextButtonId(), new ItemStack(getBoots()), null);
		recipeBoots = new BookCraftingRecipe(input, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCraftingChaptersText();
		drawRecipe(mouseX, mouseY);
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipeHelmet.drawScreen(mouseX, mouseY, xPosBook + BORDER_LEFT, yPosBook + 30);
		recipeChestplate.drawScreen(mouseX, mouseY, xPosBook + BORDER_LEFT, yPosBook + 110);
		recipeLeggins.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP);
		recipeBoots.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 90);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP, WRAPWIDTH, "Recipe:");
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 35, 0, 0, this);
	}

	@Override
	protected abstract void populateSuggestionsList();

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 170, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + 185);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipeHelmet.mouseReleased(mouseX, mouseY, state, this);
		recipeChestplate.mouseReleased(mouseX, mouseY, state, this);
		recipeLeggins.mouseReleased(mouseX, mouseY, state, this);
		recipeBoots.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
