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
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCombinedArmorMesh extends GuiBookBaseCraftingPage {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCombinedArmorMesh() {
		super(new ItemStack(ItemHandler.combinedArmorMesh));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookCraftingRecipe getRecipe() {
		BookButtonCrafting g = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystalGlas, 1, 1),  PageRegistry.CRYSTAL_GLASS_PAGE);
		BookButtonCrafting a = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorPlate, 1, 4),  PageRegistry.ARMOR_PLATE_PAGE_1);
		BookButtonCrafting c = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorCatalys),  PageRegistry.ARMOR_CATALYST_PAGE);
		BookButtonCrafting h = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.hunterArmorMesh),  PageRegistry.HUNTERS_ARMOR_MESH_PAGE);
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.combinedArmorMesh),  null);
		
		BookButtonCrafting input[][] = new BookButtonCrafting[][]{	{c,a,g},
																	{a,h,a},
																	{g,a,c}}; 
		
		return new BookCraftingRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "The Combined Armor Mesh is a crafting component for the Charged Combined Armor Mesh.";
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.hunterArmorMesh),  PageRegistry.HUNTERS_ARMOR_MESH_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(BlockHandler.crystalGlas, 1, 0),
																										new ItemStack(BlockHandler.crystalGlas, 1, 1),
																										new ItemStack(BlockHandler.crystalGlas, 1, 2),
																										new ItemStack(BlockHandler.crystalGlas, 1, 3),}, 1000, PageRegistry.CRYSTAL_GLASS_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorPlate, 1, 0),
																										new ItemStack(ItemHandler.armorPlate, 1, 1),
																										new ItemStack(ItemHandler.armorPlate, 1, 2),
																										new ItemStack(ItemHandler.armorPlate, 1, 3),
																										new ItemStack(ItemHandler.armorPlate, 1, 4),}, 1000, PageRegistry.ARMOR_PLATE_PAGE_1));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.armorCatalys),  PageRegistry.ARMOR_CATALYST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.chargedCombinedArmorMesh),  PageRegistry.CHARGED_COMBINED_ARMOR_MESH_PAGE));
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
	
}
