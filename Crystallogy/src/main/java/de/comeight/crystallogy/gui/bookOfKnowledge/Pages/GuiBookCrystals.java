package de.comeight.crystallogy.gui.bookOfKnowledge.Pages;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class GuiBookCrystals extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystals() {
		super("Crystals:");
		
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
	}
	
	@Override
	protected void addButtons() {
		super.addButtons();
	}
	
}
