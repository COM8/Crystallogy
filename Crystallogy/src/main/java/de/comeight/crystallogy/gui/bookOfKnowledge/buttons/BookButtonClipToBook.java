package de.comeight.crystallogy.gui.bookOfKnowledge.buttons;

import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookButtonClipToBook extends BookButton {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookButtonClipToBook(int buttonId) {
		super(buttonId, 120, 255, 25, 25);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawNormal(int x, int y) {
		drawBackground(x, y);
		
		GlStateManager.pushMatrix();

		GlStateManager.translate(x + 4, y + 3, 0);
		GlStateManager.scale(0.7F, 0.7F, 0.7F);
		GlStateManager.translate(-x, -y, 0);
		
		drawTexture(x, y + 5, 0, 20, 23, 25, GuiBookPage.GUI_ELEMENTS);
		
		GlStateManager.popMatrix();
	}
	
	@Override
	public void drawHover(int x, int y) {
		drawBackground(x, y);
		
		GlStateManager.pushMatrix();

		GlStateManager.translate(x + 4, y + 3, 0);
		GlStateManager.scale(0.7F, 0.7F, 0.7F);
		GlStateManager.translate(-x, -y, 0);
		
		drawTexture(x, y + 5, 0, 47, 23, 25, GuiBookPage.GUI_ELEMENTS);
		
		GlStateManager.popMatrix();
	}
	
	private void drawBackground(int x, int y){
		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y, 0);
		GlStateManager.scale(2.0F, 2.0F, 2.0F);
		GlStateManager.translate(-x, -y, 0);
		
		drawTexture(x, y, 12, 0, 14, 16, GuiBookPage.GUI_ELEMENTS);
		
		GlStateManager.popMatrix();
	}
	
	@Override
	public void onClicked(GuiBookPage fromPage) {
		PageRegistry.clipToBook(fromPage);
	}
	
}
