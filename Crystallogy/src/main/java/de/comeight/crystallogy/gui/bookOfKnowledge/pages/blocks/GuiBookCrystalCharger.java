package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCrystalCharger extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer crystalGlass;
	
	private BookCraftingRecipe recipe;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystalCharger() {
		super("Crystal Charger:");
		
		crystalGlass = new BookMultiItemRenderer(new ItemStack[]{new ItemStack(BlockHandler.charger)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		initRecipe();
	}
	
	private void initRecipe(){
		BookButtonCrafting o = new BookButtonCrafting(getNextButtonId(), new ItemStack(Blocks.OBSIDIAN), null);
		BookButtonCrafting r = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.REDSTONE), null);
		BookButtonCrafting m = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.machineBlock), PageRegistry.MACHINE_BLOCK_PAGE);
		BookButtonCrafting e = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.energyCrystal, 1, ItemHandler.energyCrystal.getMaxDamage()), PageRegistry.ENERGY_CRYSTAL_PAGE);
		
		BookButtonCrafting[][] input = new BookButtonCrafting[][]{	{o, e, o},
																	{o, r, o},
																	{o, m, o}};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.charger), null);
		recipe = new BookCraftingRecipe(input, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCrystals();
		drawText();
		drawCraftingChaptersText();
		drawRecipe(mouseX, mouseY);
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipe.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 30);
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 125, xSize / 2 - 12, 1.0F, "The Crystal Charger is used to charge empty Energy Crystals.");
	}
	
	private void drawCrystals(){
		crystalGlass.drawItem(xPosBook + 50, yPosBook + 35);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 10, xSize / 2 - 10, "Recipe:");
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 70, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(),0, 0, null, new ItemStack(ItemHandler.energyCrystal, 1, ItemHandler.energyCrystal.getMaxDamage()), PageRegistry.ENERGY_CRYSTAL_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(),0, 0, null, new ItemStack(BlockHandler.machineBlock), PageRegistry.MACHINE_BLOCK_PAGE));
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 120, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 105);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipe.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
