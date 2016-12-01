package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.TextScrollBarList;
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
public class GuiBookDissectingTable extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer renderer;
	
	private BookCraftingRecipe recipe;
	private TextScrollBarList tbx1;
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookDissectingTable() {
		super("Dissecting Table:");
		
		renderer = new BookMultiItemRenderer(new ItemStack[]{new ItemStack(BlockHandler.dissectingTable)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addGuiElements() {
		super.addGuiElements();
		initTextBoxes();
		addGuiElement(tbx1);
	}
	
	@Override
	protected void addButtons() {
		super.addButtons();
		initRecipe();
	}
	
	private void initRecipe(){
		BookButtonCrafting p = new BookButtonCrafting(getNextButtonId(), new ItemStack(Blocks.PLANKS), null);
		BookButtonCrafting s = new BookButtonCrafting(getNextButtonId(), new ItemStack(Blocks.WOODEN_SLAB), null);
		BookButtonCrafting b = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.BOOK), null);
		
		BookButtonCrafting d = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_green), PageRegistry.CRYSTAL_DUST_PAGE);
		
		BookButtonCrafting[][] input = new BookButtonCrafting[][]{	{b, s, d},
																	{p, s, p},
																	{p, BookButtonCrafting.EMPTY, p}};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.dissectingTable), null);
		recipe = new BookCraftingRecipe(input, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCrystals();
		drawCraftingChaptersText();
		drawRecipe(mouseX, mouseY);
		drawText(mouseX, mouseY);
	}
	
	private void drawText(int mouseX, int mouseY){
		tbx1.drawScreen(mouseX, mouseY, xPosBook + BORDER_LEFT, yPosBook + 120);
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipe.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 30);
	}
	
	protected void initTextBoxes(){
		tbx1 = new TextScrollBarList(WRAPWIDTH, 100, xPosBook + BORDER_LEFT, yPosBook + 120, this);
		tbx1.setText("The Dissecting Table lets you modify the behavior of the entity once it finishes its job.\n"
				+ "\n"
				+ "To use it you have to put in the left slot a couple unused Entity Brains to fill up the tank with brain parts.\n"
				+ "In the center slot you insert the Entity Brain you like to manipulate "
				+ "and in the right slot you have to insert an Entity Crystal Knife.\n"
				+ "\n"
				+ "To start the process you have to have filled up the tank on the left up to at least 20% "
				+ "and you have to change at least one neuron.\n"
				+ "\n"
				+ "Currently there exist two different neurons you can enable or disable:\n"
				+ "-Neuron X59U:\n"
				+ "Neuron X59U lets you decide whether you want to let run the ai continuously."
				+ "What this means is, that for example if the Quarry Ai finishes it will start over again and search for new unbroken blocks again.\n"
				+ "\n"
				+ "-Neuron X25E:\n"
				+ "Neuron X25E lets you decide whether the entity should get teleported to its target if a) it gets stuck somehow "
				+ "or b) the distance between the entity and its target is more than 16 blocks away.");
	}
	
	private void drawCrystals(){
		renderer.drawItem(xPosBook + 50, yPosBook + 35);
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
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																													new ItemStack(ItemHandler.crystallDust_blue), 
																													new ItemStack(ItemHandler.crystallDust_green), 
																													new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.entityBrain, 1, 0),
																													new ItemStack(ItemHandler.entityBrain, 1, 1),
																													new ItemStack(ItemHandler.entityBrain, 1, 2),
																													new ItemStack(ItemHandler.entityBrain, 1, 3),}, 1000, PageRegistry.ENTITY_BRAIN_PAGE));
		BookButtonCategory customAiButton = new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.entityBrain), PageRegistry.CUSTOM_ENTITY_AI_PAGE_1);
		customAiButton.setCustomDescription("Custom Entity Ai");
		suggestionsList.addEntry(customAiButton);
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.entityCrystalKnife), PageRegistry.ENTITY_CRYSTAL_KNIFE_PAGE_1));
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
