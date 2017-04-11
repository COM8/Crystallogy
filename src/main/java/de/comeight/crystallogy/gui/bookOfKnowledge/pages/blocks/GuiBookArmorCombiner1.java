package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookArmorCombinerRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPageSuggestions;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookArmorCombiner1 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer crystalOfHolding;
	
	private BookArmorCombinerRecipe recipeHelmet;
	private BookArmorCombinerRecipe recipeChestplate;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookArmorCombiner1() {
		super("Armor Combiner");
		
		crystalOfHolding = new BookMultiItemRenderer(new ItemStack[]{new ItemStack(BlockHandler.armorCombiner)}, 1000, 5.0F);
		
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.ARMOR_COMBINER_PAGE_2;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		recipeHelmet = new BookArmorCombinerRecipe(new ItemStack(Items.CHAINMAIL_HELMET), new ItemStack(ItemHandler.armorHelmet_combined));
		recipeChestplate = new BookArmorCombinerRecipe(new ItemStack(Items.CHAINMAIL_CHESTPLATE), new ItemStack(ItemHandler.armorChestplate_combined));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawItem();
		drawText();
		drawUsageChapterText();
		drawRecipes(mouseX, mouseY);
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 120, WRAPWIDTH, 1.0F, "The Armor Combiner is used to \"combine\" different kinds of armor."
				+ "\n\n\n"
				+ "In the right slot you have to put a piece of Combined Armor. "
				+ "In the left slot you have to put the armor part you like to combine onto the Combined Armor in the right slot.\n");
		
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 10, WRAPWIDTH - 10, 1.0F, ""
				+ "To start the progress you have to put enough Armor Catalyst in the center slot.\n"
				+ "\n"
				+ "The chain armor is representing any kind of armor in the pictures below and on the next page.");
	}
	
	private void drawItem(){
		crystalOfHolding.drawItem(xPosBook + 60, yPosBook + 35);
	}
	
	private void drawUsageChapterText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 150, xSize / 2 - 10, "Usage:");
	}
	
	private void drawRecipes(int mouseX, int mouseY){
		recipeHelmet.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 100);
		recipeChestplate.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 170);
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipeHelmet.mouseReleased(mouseX, mouseY, state, this);
		recipeChestplate.mouseReleased(mouseX, mouseY, state, this);
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
	
}
