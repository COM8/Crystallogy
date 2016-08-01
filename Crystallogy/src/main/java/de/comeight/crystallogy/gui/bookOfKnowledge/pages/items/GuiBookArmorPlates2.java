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
public class GuiBookArmorPlates2 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	private BookInfusionRecipe recipeBlue;
	private BookInfusionRecipe recipeGreen;
	private BookInfusionRecipe recipeYellow;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookArmorPlates2() {
		super("");
		
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		BookButtonCrafting e = BookButtonCrafting.EMPTY;
		e.disableFrame();
		BookButtonCrafting dB = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_blue), PageRegistry.CRYSTAL_DUST_PAGE);
		dB.disableFrame();
		BookButtonCrafting aP = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorPlate, 1, 4), null);
		aP.disableFrame();
		BookButtonCrafting[] inputBlue = new BookButtonCrafting[]{aP, dB, dB, e, e};
		BookButtonCrafting outputBlue = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorPlate, 1, 1), null);
		outputBlue.disableFrame();
		recipeBlue = new BookInfusionRecipe(inputBlue, outputBlue);
		
		BookButtonCrafting dG = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_green), PageRegistry.CRYSTAL_DUST_PAGE);
		dG.disableFrame();
		BookButtonCrafting[] inputGreen = new BookButtonCrafting[]{aP, dG, dG, e, e};
		BookButtonCrafting outputGreen = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorPlate, 1, 2), null);
		outputGreen.disableFrame();
		recipeGreen = new BookInfusionRecipe(inputGreen, outputGreen);
		
		BookButtonCrafting dY = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_yellow), PageRegistry.CRYSTAL_DUST_PAGE);
		dY.disableFrame();
		BookButtonCrafting[] inputYellow = new BookButtonCrafting[]{aP, dY, dY, e, e};
		BookButtonCrafting outputYellow = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorPlate, 1, 3), null);
		outputYellow.disableFrame();
		recipeYellow = new BookInfusionRecipe(inputYellow, outputYellow);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawRecipe(mouseX, mouseY);
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipeBlue.drawScreen(mouseX, mouseY, xPosBook + 50, yPosBook + BORDER_TOP);
		recipeGreen.drawScreen(mouseX, mouseY, xPosBook + 50, yPosBook + 120);
		recipeYellow.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + 50, yPosBook + BORDER_TOP);
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
		recipeBlue.mouseReleased(mouseX, mouseY, state, this);
		recipeGreen.mouseReleased(mouseX, mouseY, state, this);
		recipeYellow.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
