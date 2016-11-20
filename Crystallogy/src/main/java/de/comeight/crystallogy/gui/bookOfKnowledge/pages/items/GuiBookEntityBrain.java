package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
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
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookEntityBrain extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer itemRenderer;
	
	private BookInfusionRecipe recipe;
	
	private TextScrollBarList tbx1;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookEntityBrain() {
		super("Entity Brain:");
		
		itemRenderer = new BookMultiItemRenderer(	new ItemStack[]{new ItemStack(ItemHandler.entityBrain, 1, 0),
													new ItemStack(ItemHandler.entityBrain, 1, 1),
													new ItemStack(ItemHandler.entityBrain, 1, 2),
													new ItemStack(ItemHandler.entityBrain, 1, 3)}, 1000, 5.0F);
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
		tbx1 = new TextScrollBarList(WRAPWIDTH, 100, xPosBook + BORDER_LEFT, yPosBook + 120, this);
		tbx1.setText("Entity Brains are rare drops, dropped by every kind of entity.\n"
				+ "\n"
				+ "Currenty 4 \"tiers\" of brains exist:\n"
				+ "\n"
				+ "-The Normal Entity Brain:\n"
				+ "Dropped by players and villagers on their death.\n"
				+ "-The Small Entity Brain:\n"
				+ "Dropped by passive mobs on their death.\n"
				+ "-The Tiny Entity Brain:\n"
				+ "Dropped by agressive and passive mobs on their death.\n"
				+ "-The Walnut Entity Brain:\n"
				+ "Dropped by agressive mobs on their death.");
		addGuiElement(tbx1);
	}
	
	private void initRecipe(){
		BookButtonCrafting c = new BookButtonCrafting(getNextButtonId(), new ItemStack(Blocks.COBBLESTONE), null);
		c.disableFrame();
		BookButtonCrafting p = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.pureCrystallDust), PageRegistry.PURE_CRYSTAL_DUST_PAGE);
		p.disableFrame();
		
		
		BookButtonCrafting[] input = new BookButtonCrafting[]{c, p, p, p, p};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.magicStoneOfForgetfulness, 8), null);
		output.disableFrame();
		
		recipe = new BookInfusionRecipe(input, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawItem();
		drawText(mouseX, mouseY);
		drawCraftingChaptersText();
		drawRecipe(mouseX, mouseY);
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipe.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + 45, yPosBook + 25);
	}
	
	private void drawText(int mouseX, int mouseY){
		tbx1.drawScreen(mouseX, mouseY, xPosBook + BORDER_LEFT, yPosBook + 120);
	}
	
	private void drawItem(){
		itemRenderer.drawItem(xPosBook + 50, yPosBook + 30);
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
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.pureCrystallDust), PageRegistry.PURE_CRYSTAL_DUST_PAGE));

		BookButtonCategory infusionButton = new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.infuserBlock), PageRegistry.INFUSION_CRAFTING_PAGE_1);
		infusionButton.setCustomDescription("Infusion Crafting");
		suggestionsList.addEntry(infusionButton);
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
