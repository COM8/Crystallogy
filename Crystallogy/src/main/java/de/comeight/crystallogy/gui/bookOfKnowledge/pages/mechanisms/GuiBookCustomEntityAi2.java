package de.comeight.crystallogy.gui.bookOfKnowledge.pages.mechanisms;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.TextScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCraftingInfusion;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPageSuggestions;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.handler.RecipeHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCustomEntityAi2 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookInfusionRecipe recipe1;
	private BookInfusionRecipe recipe2;
	
	private TextScrollBarList tbx1;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCustomEntityAi2() {
		super("");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		initRecipe();
	}
	
	@Override
	protected void addGuiElements() {
		super.addGuiElements();
		initTextBoxes();
		addGuiElement(tbx1);
	}
	
	protected void initTextBoxes(){
		tbx1 = new TextScrollBarList(WRAPWIDTH - 10, 120, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 140, this);
		tbx1.setText("The Area Picker specifies the area the entity has to work in "
				+ "and the Spot Picker specifies a specific point for example if you use the Pick up Items Ai, "
				+ "it specifies the position where the entity should drop off its collected items.\n"
				+ "\n"
				+ "The Player Crystal Knife specifies the player the entity should follow.\n"
				+ "\n"
				+ "If you want to specifie, what should happen if the entity finishes it's job "
				+ "or wether it should get teleported to it's target if it gets stuck in some place,"
				+ "you can use the Dissecting Table for this.");
	}
	
	private void initRecipe(){
		BookButtonCraftingInfusion b = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(ItemHandler.entityBrain), PageRegistry.ENTITY_BRAIN_PAGE);
		BookButtonCraftingInfusion e = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(ItemHandler.energyCrystal, 1, 0), PageRegistry.ENERGY_CRYSTAL_PAGE);
		BookButtonCraftingInfusion s = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(ItemHandler.spotPicker), PageRegistry.SPOT_PICKER_PAGE);
		BookButtonCraftingInfusion a = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(ItemHandler.areaPicker), PageRegistry.AREA_PICKER_PAGE);
		BookButtonCraftingInfusion p = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(ItemHandler.crystalPickaxe_red), PageRegistry.CRYSTAL_PICKAXE_PAGE_1);
		BookButtonCraftingInfusion sl = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(Items.SLIME_BALL), null);
		BookButtonCraftingInfusion l = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(Items.DYE, 1, 4), null);
		
		BookButtonCraftingInfusion[] input = new BookButtonCraftingInfusion[]{b, e, sl, s, a};
		
		BookButtonCraftingInfusion output = new BookButtonCraftingInfusion(getNextButtonId(), RecipeHandler.infusionRecipeEntityBrainPickupItems.getOutputJEI().get(0), null);
		output.disableFrame();
		
		recipe1 = new BookInfusionRecipe(input, output);
		
		input = new BookButtonCraftingInfusion[]{b, l, a, p, e};
		output = new BookButtonCraftingInfusion(getNextButtonId(), RecipeHandler.infusionRecipeEntityBrainQuarry.getOutputJEI().get(0), null);
		recipe2 = new BookInfusionRecipe(input, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawText(mouseX, mouseY);
		drawCraftingChaptersText();
		drawRecipe(mouseX, mouseY);
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipe1.drawScreen(mouseX, mouseY, xPosBook + BORDER_LEFT + 30, yPosBook + 20);
		recipe2.drawScreen(mouseX, mouseY, xPosBook + BORDER_LEFT + 30, yPosBook + 135);
	}
	
	private void drawText(int mouseX, int mouseY){
		tbx1.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 10, WRAPWIDTH, "Recipes:");
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipe1.mouseReleased(mouseX, mouseY, state, this);
		recipe2.mouseReleased(mouseX, mouseY, state, this);
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 70, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(),0, 0, null, new ItemStack(BlockHandler.dissectingTable), PageRegistry.DISSECTING_TABLE_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(),0, 0, null, new ItemStack(ItemHandler.areaPicker), PageRegistry.AREA_PICKER_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(),0, 0, null, new ItemStack(ItemHandler.spotPicker), PageRegistry.SPOT_PICKER_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(),0, 0, null, new ItemStack(ItemHandler.playerCrystalKnife), PageRegistry.PLAYER_CRYSTAL_KNIFE_PAGE_1));
		BookButtonCategory infusionButton = new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.infuserBlock), PageRegistry.INFUSION_CRAFTING_PAGE_1);
		infusionButton.setCustomDescription("Infusion Crafting");
		suggestionsList.addEntry(infusionButton);
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 120, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 105);
	}
	
}
