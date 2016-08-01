package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookBaseCraftingPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookFertilizerPotato extends GuiBookBaseCraftingPage {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookFertilizerPotato() {
		super(new ItemStack(ItemHandler.fertilizerPotato));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookCraftingRecipe getRecipe() {
		BookButtonCrafting c = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystorya),  PageRegistry.CRYSTORYA_PAGE);
		BookButtonCrafting d = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_green),  PageRegistry.CRYSTAL_DUST_PAGE);
		BookButtonCrafting b = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.DYE, 1, 15),  null);
		BookButtonCrafting p = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.POISONOUS_POTATO),  null);
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.fertilizerPotato),  null);
		
		BookButtonCrafting input[][] = new BookButtonCrafting[][]{	{c,p,d},
																	{b,p,b},
																	{d,p,c}}; 
		
		return new BookCraftingRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "Fertilizer Potatoes are EXTREMELY poisonous. If you eat them you get a whole bunch of negative effects so be careful!";
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawUsageText();
	}
	
	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.crystorya),  PageRegistry.CRYSTORYA_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red),
																										new ItemStack(ItemHandler.crystallDust_blue),
																										new ItemStack(ItemHandler.crystallDust_green),
																										new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.farmersGreen),  PageRegistry.FARMERS_GREEN_PAGE_1));
	}
	
	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 105, 0, 0, this);
	}
	
	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 145, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 130);
	}
	
	private void drawUsageText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP + 160, WRAPWIDTH, "Usage:");
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP + 175, WRAPWIDTH - 10, 1.0F, "It is used to refuel the Farmers Green. Just right-click the Farmers Green with it, to refuel it.");
	}
	
}
