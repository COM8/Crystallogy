package de.comeight.crystallogy.gui.bookOfKnowledge.buttons;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookMain;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookButtonHome extends BookButton {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static final ResourceLocation BUTTON_NORMAL = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/home_normal.png");
	private static final ResourceLocation BUTTON_HOVER = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/home_hover.png");
	private static final ResourceLocation BUTTON_BACKGROUND = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/home_background.png");
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookButtonHome(int buttonId) {
		super(buttonId, 255 / 2 + 5, 255, 25, 25);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawNormal(int x, int y) {
		drawBackground(x, y);
		
		GlStateManager.pushMatrix();

		GlStateManager.translate(x + 4, y + 3, 0);
		GlStateManager.scale(0.075, 0.075, 0.075);
		GlStateManager.translate(-x, -y, 0);
		
		drawTexture(x, y, 260, 260, BUTTON_NORMAL);
		
		GlStateManager.popMatrix();
	}
	
	@Override
	public void drawHover(int x, int y) {
		drawBackground(x, y);
		
		GlStateManager.pushMatrix();

		GlStateManager.translate(x + 4, y + 3, 0);
		GlStateManager.scale(0.075, 0.075, 0.075);
		GlStateManager.translate(-x, -y, 0);
		
		drawTexture(x, y, 260, 260, BUTTON_HOVER);
		
		GlStateManager.popMatrix();
	}
	
	private void drawBackground(int x, int y){
		GlStateManager.pushMatrix();

		GlStateManager.translate(x, y, 0);
		GlStateManager.scale(0.1, 0.1, 0.1);
		GlStateManager.translate(-x, -y, 0);
		
		drawTexture(x, y, 265, 255, BUTTON_BACKGROUND);
		
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
