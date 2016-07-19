package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.util.LinkedList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ScrollBarList {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected final Minecraft mc = Minecraft.getMinecraft();
	private GuiBookPage page;
	private long lastMouseEvent;
	protected Long mouseCooldownUntil = 0L;
	protected boolean shouldWaitForInput = true;
	
	protected int width;
	protected int height;
	protected int posX;
	protected int posY;
	
	protected final int entryHeigtht = 35;
	
	protected boolean scrollEnabeld = true;
	protected int scrollPos = 0;
	protected int hoverIndex = -1;
	
	protected LinkedList<BookButtonCategory> list = new LinkedList<BookButtonCategory>();
	
	protected final ResourceLocation BAR = new ResourceLocation("textures/gui/container/creative_inventory/tab_items.png");
	protected final ResourceLocation SCROLL = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
	protected final int SCROLL_BAR_WIDTH = 10; 
	
	//-----------------------------------------------Constructor:-------------------------------------------
    public ScrollBarList(int width, int height, int posX, int posY, GuiBookPage page)
    {
    	this.page = page;
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
    }
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
    
    
    
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
    public void drawScreen(int mouseX, int mouseY, int posX, int posY){
    	drawBackground(posX, posY);
    	
    	shouldEnbaleScroll();
    	drawScrollBar(posX, posY);
    	
    	int drawStratIndex;
    	int drawEndIndex;
    	if(scrollEnabeld){
    		drawStratIndex = scrollPos / entryHeigtht;
        	drawEndIndex = drawStratIndex + (height / entryHeigtht);
        	if(drawEndIndex >= list.size()){
        		drawEndIndex = list.size();
        	}
    	}
    	else{
    		drawStratIndex = 0;
        	drawEndIndex = list.size();
    	}
    	
    	
    	int relativeHoverIndex = getHoverIndex(mouseX - posX, mouseY - posY);
    	
    	hoverIndex = relativeHoverIndex;
    	if(hoverIndex != -1){
    		hoverIndex += drawStratIndex;
    	}
    	
    	for (int i = drawStratIndex; i < drawEndIndex; i++) {
    		if(list.get(i) != null){
    			if((i - drawStratIndex) == relativeHoverIndex){
    				drawEntry(i, true, posX, posY + ((i - drawStratIndex) * entryHeigtht));
    			}
    			else{
    				drawEntry(i, false, posX, posY + ((i - drawStratIndex) * entryHeigtht));
    			}
    		}
		}
    }
    
    protected void shouldEnbaleScroll(){
    	if((height / entryHeigtht) >= list.size()){
    		scrollEnabeld = false;
    	}
    	else{
    		scrollEnabeld = true;
    	}
    }
    
    protected int getHoverIndex(int mouseX, int mouseY){
    	if(mouseX < 0 || mouseY < 0 || mouseX > width - 10 || mouseY > height){
    		return -1;
    	}
    	return mouseY / entryHeigtht;
    }
    
    public void mouseReleased(int mouseX, int mouseY){
    	if(shouldWaitForInput){
    		lastMouseEvent = System.currentTimeMillis() + 100;
    		shouldWaitForInput = false;
    		return;
    	}
    	if(System.currentTimeMillis() < lastMouseEvent){
    		return;
    	}
    	if(hoverIndex >= 0){
			list.get(hoverIndex).onClicked(page);
		}
    }
    
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	if(!scrollEnabeld || clickedMouseButton != 0){
    		return;
    	}
    	if(mouseY >= (posY + height)){
    		scrollPos = entryHeigtht * list.size() - height;
    	}
    	else if(mouseY <= posY){
    		scrollPos = 0;
    	}
    	else{
    		scrollPos = (int) ( ((double)(mouseY - posY) / (double) height) * (entryHeigtht * (list.size() - height / entryHeigtht)));
    	}
    }
    
    protected void drawScrollBar(int posX, int posY){
	    drawTexture(posX + width - 10, posY, 175, 18, 10, 110, BAR);
	    drawTexture(posX + width - 10, posY + 110, 175, 18, 10, height - 110, BAR);
	    
	    if(scrollEnabeld){
	    	int y = (int) ((double)scrollPos / (entryHeigtht * (list.size() - (height) / entryHeigtht)) * (height - 13));
	        drawTexture(posX + width - 10, posY + y, 233, 1, 10, 13, SCROLL);
	    }
	    else{
	    	drawTexture(posX + width - 10, posY, 245, 1, 10, 13, SCROLL);
	    }
    }
    
    protected void drawBackground(int posX, int posY){
    	//drawTexture(posX, posY, width, height, Gui.OPTIONS_BACKGROUND);
    }
    
    protected void drawEntry(int index, boolean hover, int posX, int posY){
    	if(index % 2 == 0){
    		final int zLevel = 300;
            final int backgroundColor = 0xF0100010;
    		GuiUtils.drawGradientRect(zLevel, 20, 40, 20, 40, backgroundColor, backgroundColor);
    	}
    	
    	if(hover){
    		//drawTexture(posX, posY, width - 10, entryHeigtht, Gui.ICONS);
    		list.get(index).drawHover(posX + 5, posY + 5);
    	}
    	else{
    		list.get(index).drawNormal(posX + 5, posY + 5);
    	}
    }
    
    public void addEntry(BookButtonCategory button){
    	list.add(button);
    }
    
    public void drawTexture(int posX, int posY, int sizeX, int sizeY, ResourceLocation texture){
    	drawTexture(posX, posY, 0, 0, sizeX, sizeY, texture);
    }
    
    public void drawTexture(int posX, int posY, int xStart, int yStart, int sizeX, int sizeY, ResourceLocation texture){
    	mc.getTextureManager().bindTexture(texture);
    	GuiUtils.drawTexturedModalRect(posX, posY, xStart, yStart, sizeX, sizeY, 300);
    	//drawTexturedModalRect(posX, posY, xStart, yStart, sizeX, sizeY);
    }
    
    /**
     * Draws a textured rectangle using the texture currently bound to the TextureManager
     */
    public void drawTexturedModalRect(float xCoord, float yCoord, int minU, int minV, int maxU, int maxV)
    {
    	GlStateManager.pushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos((double)(xCoord + 0.0F), (double)(yCoord + (float)maxV), 0).tex((double)((float)(minU + 0) * 0.00390625F), (double)((float)(minV + maxV) * 0.00390625F)).endVertex();
        vertexbuffer.pos((double)(xCoord + (float)maxU), (double)(yCoord + (float)maxV), 0).tex((double)((float)(minU + maxU) * 0.00390625F), (double)((float)(minV + maxV) * 0.00390625F)).endVertex();
        vertexbuffer.pos((double)(xCoord + (float)maxU), (double)(yCoord + 0.0F), 0).tex((double)((float)(minU + maxU) * 0.00390625F), (double)((float)(minV + 0) * 0.00390625F)).endVertex();
        vertexbuffer.pos((double)(xCoord + 0.0F), (double)(yCoord + 0.0F), 0).tex((double)((float)(minU + 0) * 0.00390625F), (double)((float)(minV + 0) * 0.00390625F)).endVertex();
        tessellator.draw();
        
        GlStateManager.popMatrix();
    }
   
}