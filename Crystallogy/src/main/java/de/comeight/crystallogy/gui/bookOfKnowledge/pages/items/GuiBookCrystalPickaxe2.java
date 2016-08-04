package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPageSuggestions;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCrystalPickaxe2 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookInfusionRecipe recipeGreen;
	private BookInfusionRecipe recipeYellow;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystalPickaxe2() {
		super("");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		initRecipe();
	}
	
	private void initRecipe(){
		BookButtonCrafting hG = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystalPickaxeHead, 1, 2), PageRegistry.CRYSTAL_PICKAXE_HEAD_PAGE);
		hG.disableFrame();
		BookButtonCrafting hY = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystalPickaxeHead, 1, 3), PageRegistry.CRYSTAL_PICKAXE_HEAD_PAGE);
		hY.disableFrame();

		BookButtonCrafting dG = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_green), PageRegistry.CRYSTAL_DUST_PAGE);
		dG.disableFrame();
		BookButtonCrafting dY = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_yellow), PageRegistry.CRYSTAL_DUST_PAGE);
		dY.disableFrame();
		BookButtonCrafting t = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.toolRod), PageRegistry.TOOL_ROD_PAGE);
		t.disableFrame();
		BookButtonCrafting e = BookButtonCrafting.EMPTY;
		e.disableFrame();
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystalPickaxe_green), null);
		output.disableFrame();
		recipeGreen = new BookInfusionRecipe(new BookButtonCrafting[]{t, hG, dG, dG, e}, output);
		
		output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystalPickaxe_yellow), null);
		output.disableFrame();
		recipeYellow = new BookInfusionRecipe(new BookButtonCrafting[]{t, hY, dY, dY, e}, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawRecipe(mouseX, mouseY);
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipeGreen.drawScreen(mouseX, mouseY, xPosBook + 53, yPosBook + BORDER_TOP);
		recipeYellow.drawScreen(mouseX, mouseY, xPosBook + 53, yPosBook + BORDER_TOP + 110);
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 140, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																													new ItemStack(ItemHandler.crystallDust_blue), 
																													new ItemStack(ItemHandler.crystallDust_green), 
																													new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalPickaxeHead, 1, 0), 
																													new ItemStack(ItemHandler.crystalPickaxeHead, 1, 1), 
																													new ItemStack(ItemHandler.crystalPickaxeHead, 1, 2), 
																													new ItemStack(ItemHandler.crystalPickaxeHead, 1, 3)}, 1000, PageRegistry.CRYSTAL_PICKAXE_HEAD_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.toolRod), PageRegistry.TOOL_ROD_PAGE));
		BookButtonCategory infusionButton = new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.infuserBlock), PageRegistry.INFUSION_CRAFTING_PAGE_1);
		infusionButton.setCustomDescription("Infusion Crafting");
		suggestionsList.addEntry(infusionButton);
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + BORDER_TOP + 15);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipeGreen.mouseReleased(mouseX, mouseY, state, this);
		recipeYellow.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
