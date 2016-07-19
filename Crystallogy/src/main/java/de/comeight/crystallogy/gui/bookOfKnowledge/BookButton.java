package de.comeight.crystallogy.gui.bookOfKnowledge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
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
	
	public void drawNormal(int x, int y){

	}
	
	public void drawHover(int x, int y){

	}
	
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
	
	protected void onClicked(GuiBookPage fromPage){
		
	}
	
	protected void openGui(GuiBookPage fromPage, GuiBookPage toPage){
		PageRegistry.addCourse(fromPage);
		mc.displayGuiScreen(toPage);
	}
	
}
