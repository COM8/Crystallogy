package de.comeight.crystallogy.gui.bookOfKnowledge.buttons;

import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
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
public abstract class BookButton extends GuiButton{
	//-----------------------------------------------Variabeln:---------------------------------------------	
	protected int xPosAbs;
	protected int yPosAbs;
	protected boolean hoverEnabled;
	
	protected Minecraft mc;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookButton(int buttonId, int x, int y) {
		super(buttonId, x, y, "");
		
		this.mc = Minecraft.getMinecraft();
		this.hoverEnabled = true;
	}
	
	public BookButton(int buttonId, int x, int y, String text) {
		super(buttonId, x, y, text);
		
		this.mc = Minecraft.getMinecraft();
		this.hoverEnabled = true;
	}

	public BookButton(int buttonId, int x, int y, int widthIn, int heightIn) {
		super(buttonId, x, y, widthIn, heightIn, "");
		
		this.mc = Minecraft.getMinecraft();
		this.hoverEnabled = true;
	}
	
	public BookButton(int buttonId, int x, int y, int widthIn, int heightIn, String text) {
		super(buttonId, x, y, widthIn, heightIn, text);
		
		this.mc = Minecraft.getMinecraft();
		this.hoverEnabled = true;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void drawButton(int mouseX, int mouseY, int x, int y){
		this.xPosAbs = x + xPosition;
		this.yPosAbs = y + yPosition;
		this.hovered = mouseX >= this.xPosAbs && mouseY >= this.yPosAbs && mouseX < this.xPosAbs + this.width && mouseY < this.yPosAbs + this.height;
		if(!hoverEnabled || getHoverState(hovered) == 1){
			drawNormal(xPosAbs, yPosAbs);
		}
		else if(getHoverState(hovered) == 2){
			drawHover(xPosAbs, yPosAbs);
		}
	}
	
	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        return this.enabled && this.visible && mouseX >= this.xPosAbs && mouseY >= this.yPosAbs && mouseX < this.xPosAbs + this.width && mouseY < this.yPosAbs + this.height;
    }
	
	public abstract void drawNormal(int x, int y);
	
	public abstract void drawHover(int x, int y);
	
	protected void renderItem(ItemStack stack, int posX, int posY, float scale)
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
            itemRenderer.renderItemOverlayIntoGUI(mc.fontRendererObj, stack, 0, 0, null);
            
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableLighting();
            
            GlStateManager.popMatrix();
        }
    }
	
	public abstract void onClicked(GuiBookPage fromPage);
	
	protected void openGui(GuiBookPage fromPage, GuiBookPage toPage){
		PageRegistry.openPage(mc, fromPage, toPage);
	}
	
	public void drawTexture(int posX, int posY, int sizeX, int sizeY, ResourceLocation texture){
    	drawTexture(posX, posY, 0, 0, sizeX, sizeY, texture);
    }
    
    public void drawTexture(int posX, int posY, int xStart, int yStart, int sizeX, int sizeY, ResourceLocation texture){
    	mc.getTextureManager().bindTexture(texture);
    	drawTexturedModalRect(posX, posY, xStart, yStart, sizeX, sizeY);
    }
    
    @Override
    public void drawTexturedModalRect(int xCoord, int yCoord, int minU, int minV, int maxU, int maxV)
    {
    	GlStateManager.pushMatrix();
    	GlStateManager.color(1.0F, 1.0F, 1.0F);
		
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
