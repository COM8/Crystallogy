package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.TextScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookEntityBrain extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer itemRenderer;
	
	private BookCraftingRecipe recipe1;
	private BookCraftingRecipe recipe2;
	
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
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.CUSTOM_ENTITY_AI_PAGE_1;
	}
	
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
		tbx1 = new TextScrollBarList(WRAPWIDTH, 100, xPosBook + BORDER_LEFT, yPosBook + 120, this);
		tbx1.setText("Entity Brains are rare drops, dropped by every kind of entity.\n"
				+ "\n"
				+ "Currenty 4 \"tiers\" of brains exist:\n"
				+ "\n"
				+ "-The Normal Entity Brain:\n"
				+ "Dropped by players and villagers on their death.\n"
				+ "\n"
				+ "-The Small Entity Brain:\n"
				+ "Dropped by passive mobs on their death.\n"
				+ "\n"
				+ "-The Tiny Entity Brain:\n"
				+ "Dropped by agressive and passive mobs on their death.\n"
				+ "\n"
				+ "-The Walnut Entity Brain:\n"
				+ "Dropped by agressive mobs on their death.");
	}
	
	private void initRecipe(){
		BookButtonCrafting bT = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.entityBrain, 1, 2), null);
		BookButtonCrafting bS = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.entityBrain, 1, 1), null);
		BookButtonCrafting bN = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.entityBrain, 1, 0), null);
		BookButtonCrafting s = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.SLIME_BALL), null);
		
		BookButtonCrafting[][] input = new BookButtonCrafting[][]{	{bT, bT, bT},
																	{bT, s, bT},
																	{bT, bT, bT}};
		recipe1 = new BookCraftingRecipe(input, bS);
		input = new BookButtonCrafting[][]{	{bS, bT, bS},
											{bS, s, bS},
											{bS, bS, bS}};
		recipe2 = new BookCraftingRecipe(input, bN);
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
		recipe1.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_LEFT + 10, yPosBook + 30);
		recipe2.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_LEFT + 10, yPosBook + 130);
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
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipe1.mouseReleased(mouseX, mouseY, state, this);
		recipe2.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
