package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiInfusion;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPageSuggestions;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookPureCrystalDust extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiInfusion recipes;
	
	private BookMultiItemRenderer renderer;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookPureCrystalDust() {
		super("Pure Crystal Dust:");
		
		renderer = new BookMultiItemRenderer(new ItemStack[]{	new ItemStack(ItemHandler.pureCrystallDust)}, 1000, 5.0F);
		
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	protected void initRecipe() {
		BookButtonCrafting d = new BookButtonCrafting(getNextButtonId(), new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																							new ItemStack(ItemHandler.crystallDust_blue), 
																							new ItemStack(ItemHandler.crystallDust_green), 
																							new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE);
		d.disableFrame();
		BookButtonCrafting w = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.WATER_BUCKET), null);
		w.disableFrame();
		BookButtonCrafting o1 = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.pureCrystallDust, 1), null);
		o1.disableFrame();
		BookButtonCrafting o2 = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.pureCrystallDust, 2), null);
		o2.disableFrame();
		BookButtonCrafting o3 = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.pureCrystallDust, 3), null);
		o3.disableFrame();
		BookButtonCrafting o4 = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.pureCrystallDust, 4), null);
		o4.disableFrame();
		BookButtonCrafting e = BookButtonCrafting.EMPTY;
		e.disableFrame();
		
		BookInfusionRecipe r1 = new BookInfusionRecipe(new BookButtonCrafting[]{w, d, e, e, e}, o1);
		BookInfusionRecipe r2 = new BookInfusionRecipe(new BookButtonCrafting[]{w, d, d, e, e}, o2);
		BookInfusionRecipe r3 = new BookInfusionRecipe(new BookButtonCrafting[]{w, d, d, d, e}, o3);
		BookInfusionRecipe r4 = new BookInfusionRecipe(new BookButtonCrafting[]{w, d, d, d, d}, o4);
		
		recipes = new BookMultiInfusion(new BookInfusionRecipe[]{r1, r2, r3, r4});
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
		recipes.drawRecipe(mouseX, mouseY, xPosBook + xSize / 2 + 45, yPosBook + 35);
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 120, WRAPWIDTH, 1.0F, "Pure Crystal Dust is the pure form of a Crystal Dust.\n"
				+ "The recipe accepts up to four Crystal Dust per infusion operation.");
	}
	
	private void drawItem(){
		renderer.drawItem(xPosBook + 50, yPosBook + 30);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 10, WRAPWIDTH, "Recipe:");
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 35, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(),0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																													new ItemStack(ItemHandler.crystallDust_blue),
																													new ItemStack(ItemHandler.crystallDust_green),
																													new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 85, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 70);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipes.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
