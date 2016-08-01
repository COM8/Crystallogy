package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPageSuggestions;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCrystals extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer crystals;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystals() {
		super("Crystals:");
		
		crystals = new BookMultiItemRenderer(new ItemStack[]{	new ItemStack(BlockHandler.crystall_red), 
																new ItemStack(BlockHandler.crystall_blue),
																new ItemStack(BlockHandler.crystall_green),
																new ItemStack(BlockHandler.crystall_yellow)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCrystals();
		drawText();
	}
	
	@Override
	protected void addButtons() {
		super.addButtons();
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 120, WRAPWIDTH, 1.0F, "Crystals are the main recourse of Crystallogy and can be found underground.\n"
				+"\n"
				+ "The best way to find them is, to hunt for them in the dark, because they act as a small light source.");
		
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, WRAPWIDTH - 10, 1.0F, "Green Crystals can be found at a \"depth\" of 128-64.\n"
				+"\n"
				+ "Blue Crystals can be found at a depth of 64-48.\n"
				+"\n"
				+ "Yellow Crystals can be found at a depth of 48-16.\n"
				+"\n"
				+ "Red Crystals are the rarest, they can only be found at a depth of 16-0 like diamonds.");
	}
	
	private void drawCrystals(){
		crystals.drawItem(xPosBook + 60, yPosBook + 30);
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 35, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																													new ItemStack(ItemHandler.crystallDust_blue), 
																													new ItemStack(ItemHandler.crystallDust_green), 
																													new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 120, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 105);
	}
	
}
