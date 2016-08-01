package de.comeight.crystallogy.gui.bookOfKnowledge.buttons;

import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookButtonForwards extends BookButton{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private GuiBookPage page;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookButtonForwards(int buttonId, int x, int y, GuiBookPage page) {
		super(buttonId, x, y, 25, 25);
		
		this.page = page;
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

		GlStateManager.translate(x - 3, y - 1, 0);
		GlStateManager.scale(1.2, 1.2, 1.2);
		GlStateManager.translate(-x, -y, 0);
		
		drawArrow(x, y, true);
		
		GlStateManager.popMatrix();
	}
	
	private void drawArrow(int x, int y, boolean hover){
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(x, y, 0);
        GlStateManager.scale(0.4F, 0.4F, 0.4F);
		if (page.getNextPage() == null) {
			drawTexture(0, 0, 415, 45, 35, 40, GuiBookPage.GUI_ELEMENTS);
		} else if (hover) {
			drawTexture(0, 0, 375, 45, 35, 40, GuiBookPage.GUI_ELEMENTS);
		} else {
			drawTexture(0, 0, 460, 45, 35, 40, GuiBookPage.GUI_ELEMENTS);
		}
		
		GlStateManager.popMatrix();
	}
	
	@Override
	public void onClicked(GuiBookPage fromPage) {
		if(page.getNextPage() != null){
			openGui(fromPage, page.getNextPage());
		}
	}
}
