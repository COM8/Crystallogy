package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPageSuggestions;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookArmorPlates1 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer item;
	
	private BookCraftingRecipe recipe;
	private BookInfusionRecipe recipeRed;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookArmorPlates1() {
		super("Armor Plates:");
		
		item = new BookMultiItemRenderer(new ItemStack[]{	new ItemStack(ItemHandler.armorPlate, 1, 0), 
															new ItemStack(ItemHandler.armorPlate, 1, 1),
															new ItemStack(ItemHandler.armorPlate, 1, 2),
															new ItemStack(ItemHandler.armorPlate, 1, 3),
															new ItemStack(ItemHandler.armorPlate, 1, 4)}, 1000, 5.0F);
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.ARMOR_PLATE_PAGE_2;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		BookButtonCrafting cR = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_red), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting cB = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_blue), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting cG = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_green), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting cY = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_yellow), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting e = BookButtonCrafting.EMPTY;
		BookButtonCrafting d = new BookButtonCrafting(getNextButtonId(), new ItemStack[]{	new ItemStack(ItemHandler.armorPlate, 1, 0), 
																							new ItemStack(ItemHandler.armorPlate, 1, 1),
																							new ItemStack(ItemHandler.armorPlate, 1, 2),
																							new ItemStack(ItemHandler.armorPlate, 1, 3),
																							new ItemStack(ItemHandler.armorPlate, 1, 4)}, 1000, null);
		
		BookButtonCrafting[][] input = new BookButtonCrafting[][]{	{cR, cG, e},
																	{cB, cY, e},
																	{e, e, e}};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorPlate, 1, 4), null);
		recipe = new BookCraftingRecipe(input, output);
		
		BookButtonCrafting dR = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_red), PageRegistry.CRYSTAL_DUST_PAGE);
		dR.disableFrame();
		BookButtonCrafting aP = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorPlate, 1, 4), null);
		aP.disableFrame();
		e.disableFrame();
		
		BookButtonCrafting[] inputI = new BookButtonCrafting[]{aP, dR, dR, e, e};
		
		BookButtonCrafting outputI = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorPlate, 1, 0), null);
		outputI.disableFrame();
		
		recipeRed = new BookInfusionRecipe(inputI, outputI);
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
		recipeRed.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + 50, yPosBook + 100);
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 125, xSize / 2 - 12, 1.0F, "Crystal Glass is a glass type created by combining any kind of Crystal Dust with glass.");
	}
	
	private void drawItem(){
		item.drawItem(xPosBook + 50, yPosBook + 35);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 10, xSize / 2 - 10, "Recipe:");
	}

	@Override
	protected void createSuggestionsList() {
	}

	@Override
	protected void populateSuggestionsList() {
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipe.mouseReleased(mouseX, mouseY, state, this);
		recipeRed.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
