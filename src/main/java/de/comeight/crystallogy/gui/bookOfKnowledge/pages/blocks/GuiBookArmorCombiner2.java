package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookArmorCombinerRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
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
public class GuiBookArmorCombiner2 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookCraftingRecipe recipe;
	
	private BookArmorCombinerRecipe recipeLeggings;
	private BookArmorCombinerRecipe recipeBoots;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookArmorCombiner2() {
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
		BookButtonCrafting r = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_red), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting b = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_blue), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting g = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_green), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting y = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_yellow), PageRegistry.CRYSTALS_PAGE);
		BookButtonCrafting m = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.machineBlock), PageRegistry.MACHINE_BLOCK_PAGE);
		BookButtonCrafting a = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorCatalys), PageRegistry.ARMOR_CATALYST_PAGE);
		BookButtonCrafting c = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorChestplate_combined), PageRegistry.COMBINED_ARMOR_PAGE_1);
		BookButtonCrafting d = new BookButtonCrafting(getNextButtonId(), new ItemStack(Blocks.DIAMOND_BLOCK), null);
		
		BookButtonCrafting[][] input = new BookButtonCrafting[][]{	{y, d, r},
																	{b, c, g},
																	{a, m, a}};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.armorCombiner), null);
		recipe = new BookCraftingRecipe(input, output);
		
		recipeLeggings = new BookArmorCombinerRecipe(new ItemStack(Items.CHAINMAIL_LEGGINGS), new ItemStack(ItemHandler.armorLeggins_combined));
		recipeBoots = new BookArmorCombinerRecipe(new ItemStack(Items.CHAINMAIL_BOOTS), new ItemStack(ItemHandler.armorBoots_combined));
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCraftingChaptersText();
		drawRecipe(mouseX, mouseY);
		drawText();
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipeLeggings.drawScreen(mouseX, mouseY, xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP);
		recipeBoots.drawScreen(mouseX, mouseY, xPosBook + BORDER_LEFT, yPosBook + 80);
		
		recipe.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 25);
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 145, WRAPWIDTH, 1.0F, "Keep in mind, not every armor, from every mod is working 100% with this armor!\n"
				+ "If an armor has properties that can't stack, you should bind this piece first to your armor."
				+ "\n"
				+ "To unbind all armor pieces, shift right-click it, while you hold it in your hand.");
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, xSize / 2 - 10, "Recipe:");
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 105, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(BlockHandler.crystall_red), 
																													new ItemStack(BlockHandler.crystall_blue), 
																													new ItemStack(BlockHandler.crystall_green), 
																													new ItemStack(BlockHandler.crystall_yellow)}, 1000, PageRegistry.CRYSTALS_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.machineBlock), PageRegistry.MACHINE_BLOCK_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.armorCatalys), PageRegistry.ARMOR_CATALYST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_combined), 
																										new ItemStack(ItemHandler.armorChestplate_combined), 
																										new ItemStack(ItemHandler.armorLeggins_combined), 
																										new ItemStack(ItemHandler.armorBoots_combined)}, 1000, PageRegistry.COMBINED_ARMOR_PAGE_1));
		BookButtonCategory infusionButton = new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.infuserBlock), PageRegistry.INFUSION_CRAFTING_PAGE_1);
		infusionButton.setCustomDescription("Infusion Crafting");
		suggestionsList.addEntry(infusionButton);
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 110, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + 120);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipe.mouseReleased(mouseX, mouseY, state, this);
		recipeBoots.mouseReleased(mouseX, mouseY, state, this);
		recipeLeggings.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
