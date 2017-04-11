package de.comeight.crystallogy.gui.bookOfKnowledge.buttons;

import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookMain;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookButtonHome extends BookButton {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookButtonHome(int buttonId) {
		super(buttonId, 160, 255, 25, 25);
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
		
		drawTexture(x - 1, y, 89, 0, 30, 29, GuiBookPage.GUI_ELEMENTS);
		
		GlStateManager.popMatrix();
	}
	
	@Override
	public void drawHover(int x, int y) {
		drawBackground(x, y);
		
		GlStateManager.pushMatrix();

		GlStateManager.translate(x + 4, y + 3, 0);
		GlStateManager.scale(0.7F, 0.7F, 0.7F);
		GlStateManager.translate(-x, -y, 0);
		
		drawTexture(x - 1, y, 59, 0, 30, 29, GuiBookPage.GUI_ELEMENTS);
		
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
		if(fromPage instanceof GuiBookMain){
			return;
		}
		GuiBookMain toPage = new GuiBookMain();
		PageRegistry.setCurrentPage(toPage);
		openGui(fromPage, toPage);
	}
	
}
