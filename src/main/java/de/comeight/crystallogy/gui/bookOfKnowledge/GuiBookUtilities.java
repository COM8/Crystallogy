package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.util.List;

import de.comeight.crystallogy.util.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookUtilities {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static int buttonId = -1;
	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public static int getNextButtonId(){
		if(buttonId >= Integer.MAX_VALUE){
			buttonId = -1;
			Log.warn("buttonId overflow!");
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
	
	public static void drawTexture(int posX, int posY, int sizeX, int sizeY, ResourceLocation texture){
    	drawTexture(posX, posY, 0, 0, sizeX, sizeY, texture);
    }
    
    public static void drawTexture(int posX, int posY, int xStart, int yStart, int sizeX, int sizeY, ResourceLocation texture){
    	Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
    	drawTexturedModalRect(posX, posY, xStart, yStart, sizeX, sizeY);
    }
    
    public static void drawTexturedModalRect(int xCoord, int yCoord, int minU, int minV, int maxU, int maxV)
    {
    	GlStateManager.pushMatrix();
    	GlStateManager.color(1.0F, 1.0F, 1.0F);
    	GlStateManager.disableLighting();
		
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos((double)(xCoord + 0.0F), (double)(yCoord + (float)maxV), 0).tex((double)((float)(minU + 0) * 0.00390625F), (double)((float)(minV + maxV) * 0.00390625F)).endVertex();
        vertexbuffer.pos((double)(xCoord + (float)maxU), (double)(yCoord + (float)maxV), 0).tex((double)((float)(minU + maxU) * 0.00390625F), (double)((float)(minV + maxV) * 0.00390625F)).endVertex();
        vertexbuffer.pos((double)(xCoord + (float)maxU), (double)(yCoord + 0.0F), 0).tex((double)((float)(minU + maxU) * 0.00390625F), (double)((float)(minV + 0) * 0.00390625F)).endVertex();
        vertexbuffer.pos((double)(xCoord + 0.0F), (double)(yCoord + 0.0F), 0).tex((double)((float)(minU + 0) * 0.00390625F), (double)((float)(minV + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
        
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }
	
	public static void renderItem(ItemStack stack, int posX, int posY, float scale)
    {
        if (stack != null)
        {	
        	GlStateManager.pushMatrix();
        	
            GlStateManager.disableLighting();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            
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
