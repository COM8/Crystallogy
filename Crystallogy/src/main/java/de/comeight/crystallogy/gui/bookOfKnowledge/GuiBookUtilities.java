package de.comeight.crystallogy.gui.bookOfKnowledge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

public class GuiBookUtilities {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void drawTextBox(int x, int y, int sizeY, String text){
		drawTextBox(x, y, sizeY, 1.5, 4210752, text);
	}
	
	public static void drawTextBox(int x, int y, int sizeY, int color, String text){
		drawTextBox(x, y, sizeY, 1.5, color, text);
	}
	
	public static void drawTextBox(int x, int y, int sizeY, double scale, String text){
		drawTextBox(x, y, sizeY, scale, 4210752, text);
	}
	
	public static void drawTextBox(int x, int y, int sizeY, double scale, int color, String text){
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(x, y, 0);
		GlStateManager.scale(scale, scale, scale);
		Minecraft.getMinecraft().fontRendererObj.drawSplitString(text, 0, 0, sizeY, color);
		
		GlStateManager.popMatrix();
	}
	
}
