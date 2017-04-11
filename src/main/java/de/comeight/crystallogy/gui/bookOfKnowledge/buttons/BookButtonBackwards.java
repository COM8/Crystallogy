package de.comeight.crystallogy.gui.bookOfKnowledge.buttons;

import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookButtonBackwards extends BookButton{
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookButtonBackwards(int buttonId, int x, int y) {
		super(buttonId, x, y, 25, 25);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawNormal(int x, int y){
		drawArrow(x, y, false);
	}
	
	@Override
	public void drawHover(int x, int y){
		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y - 1, 0);
		GlStateManager.scale(1.2, 1.2, 1.2);
		GlStateManager.translate(-x, -y, 0);
		
		drawArrow(x, y, true);
		
		GlStateManager.popMatrix();
	}
	
	private void drawArrow(int x, int y, boolean hover){
		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y, 0);
		GlStateManager.scale(0.4F, 0.4F, 0.4F);
		if (!canGoBack()) {
			drawTexture(0, 0, 416, 0, 35, 40, GuiBookPage.GUI_ELEMENTS);
		} else if (hover) {
			drawTexture(0, 0, 379, 0, 35, 40, GuiBookPage.GUI_ELEMENTS);
		} else {
			drawTexture(0, 0, 452, 0, 35, 40, GuiBookPage.GUI_ELEMENTS);
		}
		
        GlStateManager.disableLighting();
		GlStateManager.popMatrix();
	}
	
	@Override
	public void onClicked(GuiBookPage fromPage) {
		GuiBookPage page = getLastVisitedPage();
		if(page != null){
			openGui(fromPage, page);
		}
	}

	@Override
	protected void openGui(GuiBookPage fromPage, GuiBookPage toPage) {
		mc.displayGuiScreen(toPage);
	}
	
	private GuiBookPage getLastVisitedPage() {
		return PageRegistry.getLastVisited();
	}
	
	private boolean canGoBack(){
		return PageRegistry.canGoBack();
	}
}
