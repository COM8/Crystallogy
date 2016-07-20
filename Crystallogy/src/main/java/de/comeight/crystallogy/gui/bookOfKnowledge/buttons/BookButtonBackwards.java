package de.comeight.crystallogy.gui.bookOfKnowledge.buttons;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookButtonBackwards extends BookButton{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static final ResourceLocation BUTTON_NORMAL = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/back_normal.png");
	private static final ResourceLocation BUTTON_HOVER = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/back_hover.png");
	private static final ResourceLocation BUTTON_NO = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/back_no.png");
	
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

		GlStateManager.translate(x - 5, y - 5, 0);
		GlStateManager.scale(1.4, 1.4, 1.4);
		GlStateManager.translate(-x, -y, 0);
		
		drawArrow(x, y, true);
		
		GlStateManager.popMatrix();
	}
	
	private void drawArrow(int x, int y, boolean hover){
		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y, 0);
		double scale = 0.1;
        GlStateManager.scale(scale, scale, scale);
		if (!canGoBack()) {
			drawTexture(0, 0, 256, 256, BUTTON_NO);
		} else if (hover) {
			drawTexture(0, 0, 256, 256, BUTTON_HOVER);
		} else {
			drawTexture(0, 0, 256, 256, BUTTON_NORMAL);
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
