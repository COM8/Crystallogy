package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;

public class GuiBookUtilities {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static int buttonId = -1;
	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public static int getNextButtonId(){
		if(buttonId >= Integer.MAX_VALUE){
			buttonId = -1;
		}
		else{
			buttonId++;
		}
		return buttonId;
	}
	
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
	
	public static void renderItem(ItemStack stack, int posX, int posY, float scale)
    {
        if (stack != null)
        {	
        	GlStateManager.pushMatrix();
        	
            GlStateManager.disableLighting();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.translate(posX, posY, 0);
            GlStateManager.scale(scale, scale, scale);

            RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();   
            itemRenderer.renderItemAndEffectIntoGUI(stack, 0, 0);
            itemRenderer.renderItemOverlayIntoGUI(Minecraft.getMinecraft().fontRendererObj, stack, 0, 0, null);
            
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableLighting();
            
            GlStateManager.popMatrix();
        }
    }
	
	public static ItemStack[] toItemStackArray(List<ItemStack> list){
		ItemStack[] ret = new ItemStack[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}
	
}
