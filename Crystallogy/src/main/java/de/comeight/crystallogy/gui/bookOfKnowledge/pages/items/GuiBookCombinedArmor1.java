package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemArmor;

public class GuiBookCombinedArmor1 extends GuiBookBaseArmorPage1 {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCombinedArmor1() {
		super("Combined Armor:");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.COMBINED_ARMOR_PAGE_2;
	}
	
	@Override
	protected ItemArmor getHelmet() {
		return ItemHandler.armorHelmet_combined;
	}

	@Override
	protected ItemArmor getChestplate() {
		return ItemHandler.armorChestplate_combined;
	}

	@Override
	protected ItemArmor getLeggins() {
		return ItemHandler.armorLeggins_combined;
	}

	@Override
	protected ItemArmor getBoots() {
		return ItemHandler.armorBoots_combined;
	}

	@Override
	protected String getDescription() {
		return "The Combined Armor is the top tier armor of Crystallogy. You can combine it with different types of armor, in the Armor Combiner, to use all their benefits.";
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void drawImages(){
	}
	
	private void drawUsageText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, WRAPWIDTH, "Usage:");
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP + 15, WRAPWIDTH - 10, 1.0F, "It's used like all the other armor. If you like to remove all armor pieces from the Combined Armor, you have to shift-right click the Combined Armor piece in you hand.");
	}
	
	private void drawImpInfoText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP + 100, WRAPWIDTH, "Important Information:");
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP + 115, WRAPWIDTH - 10, 1.0F, "Not every kind of armor is working properly with the Combined Armor due to the current limitations of Minecraft armor logic. Example: Almost every kind of armor which requires a full suit of armor to be worn.");
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		drawUsageText();
		drawImpInfoText();
	}
	
}
