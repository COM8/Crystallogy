package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPageSuggestions;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookEnderonCrystal extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer renderer;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookEnderonCrystal() {
		super("Enderon Crystal:");
		
		renderer = new BookMultiItemRenderer(new ItemStack[]{new ItemStack(ItemHandler.enderonCrystal)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawItem();
		drawText();
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 110, WRAPWIDTH, 1.0F, "Enderon Crystals can teleport you to a random location with an radius of about 1000 blocks.\n"
				+ "To use one, simply hold right-click and DON'T move. "
				+ "He will start to absorb energy from it's environment and tell you about every 5% its current charge."
				+ "If you move your progess will get reset!\n"
				+ "\n"
				+ "They can be found in loot chests like them in dungeons.");
	}
	
	private void drawItem(){
		renderer.drawItem(xPosBook + 50, yPosBook + 25);
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 70, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
	}
	
}
