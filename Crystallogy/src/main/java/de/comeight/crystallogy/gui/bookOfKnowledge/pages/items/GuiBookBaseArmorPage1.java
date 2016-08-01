package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public abstract class GuiBookBaseArmorPage1 extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer renderer;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookBaseArmorPage1(String title) {
		super(title);
		
		renderer = new BookMultiItemRenderer(new ItemStack[]{	new ItemStack(getHelmet()), 
																new ItemStack(getChestplate()), 
																new ItemStack(getLeggins()), 
																new ItemStack(getBoots())}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------	
	protected abstract ItemArmor getHelmet();
	
	protected abstract ItemArmor getChestplate();
	
	protected abstract ItemArmor getLeggins();
	
	protected abstract ItemArmor getBoots();
	
	protected abstract String getDescription();
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawItem();
		drawText();
		drawImages();
	}
	
	protected abstract void drawImages();
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 140, WRAPWIDTH, 1.0F, getDescription());
	}
	
	private void drawItem(){
		renderer.drawItem(xPosBook + 50, yPosBook + 50);
	}
	
}
