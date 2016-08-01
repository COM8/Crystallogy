package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookChargerRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookCompressorRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
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
public class GuiBookEnergyCrystal extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer renderer;
	
	private BookCompressorRecipe recipeCompressor;
	private BookChargerRecipe recipeCharger;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookEnergyCrystal() {
		super("Energy Crystal:");
		
		renderer = new BookMultiItemRenderer(new ItemStack[]{	new ItemStack(ItemHandler.energyCrystal, 1, 0),
																new ItemStack(ItemHandler.energyCrystal, 1, ItemHandler.energyCrystal.getMaxDamage() / 4),
																new ItemStack(ItemHandler.energyCrystal, 1, ItemHandler.energyCrystal.getMaxDamage() / 2),
																new ItemStack(ItemHandler.energyCrystal, 1, ItemHandler.energyCrystal.getMaxDamage() / 4 * 3),
																new ItemStack(ItemHandler.energyCrystal, 1, ItemHandler.energyCrystal.getMaxDamage())}, 1000, 5.0F);
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		BookButtonCrafting d = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.energyDust, 8), PageRegistry.ENERGY_DUST_PAGE);
		BookButtonCrafting cE = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.energyCrystal, 1, ItemHandler.energyCrystal.getMaxDamage()), null);
		
		recipeCompressor = new BookCompressorRecipe(d, cE);
		recipeCharger = new BookChargerRecipe();
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
		recipeCompressor.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 25);
		recipeCharger.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 70);
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 120, WRAPWIDTH, 1.0F, "The Energy Crystal is used to power The Hunter's Dream Armor. "
				+ "You just need to have a charged one in your inventory while you wear the armor.\n"
				+ "To recharge one just put it with a couple Fire Crystals in the Charger.");
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
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.energyDust), PageRegistry.ENERGY_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.fireCrystall), PageRegistry.FIRE_CRYSTAL_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.compressor), PageRegistry.COMPRESSOR_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.charger), PageRegistry.CRYSTAL_CHARGER_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_hunter), 
																													new ItemStack(ItemHandler.armorChestplate_hunter), 
																													new ItemStack(ItemHandler.armorLeggins_hunter), 
																													new ItemStack(ItemHandler.armorBoots_hunter)}, 1000, PageRegistry.HUNTERS_ARMOR_PAGE_1));
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 120, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 105);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipeCompressor.mouseReleased(mouseX, mouseY, state, this);
		recipeCharger.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
