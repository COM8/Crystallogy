package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.CrystallogyBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookButtonCategory extends BookButton{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private ResourceLocation background;
	private static final ResourceLocation FRAME = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/frame.png");
	private ItemStack[] items;
	private int frameDuration;
	private int tick;
	private int frame;
	private String description;
	
	private GuiBookPage page;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookButtonCategory(int buttonId, int x, int y, ResourceLocation background, ItemStack item, GuiBookPage page) {
		this(buttonId, x, y, background, new ItemStack[]{item}, y, page);
	}
	
	public BookButtonCategory(int buttonId, int x, int y, ResourceLocation background, ItemStack[] items, int frameDuration, GuiBookPage page) {
		super(buttonId, x, y, 25, 25);
		
		this.description = null;
		this.background = background;
		
		this.items = items;
		this.frameDuration = frameDuration;
		this.tick = frameDuration;
		this.frame = 0;
		this.page = page;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public void setCustomDescription(String description){
		this.description = description;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawNormal(int x, int y){
		GlStateManager.pushMatrix();
		
		GlStateManager.enableLighting();
		
		drawFrame(x, y, false);
		drawBackgound(x, y, false);
		drawItem(x + 3, y + 3, false);
		drawDescription(x, y);
		
		GlStateManager.disableLighting();
		
		GlStateManager.popMatrix();
	}
	
	@Override
	public void drawHover(int x, int y){
		GlStateManager.pushMatrix();
		
		GlStateManager.enableLighting();
		
		drawDescription(x, y);
		GlStateManager.translate(x - 3, y - 3, 0);
		GlStateManager.scale(1.2, 1.2, 1.2);
		GlStateManager.translate(-x, -y, 0);
		
		drawFrame(x, y, true);
		drawBackgound(x, y, true);
		drawItem(x + 3, y + 3, true);
		
		GlStateManager.disableLighting();
		
		GlStateManager.popMatrix();
	}
	
	private void drawDescription(int x, int y){
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 30, y + 0, 0);
		GlStateManager.scale(1.0, 1.0, 1.0);
		if(description == null){
			mc.fontRendererObj.drawSplitString(items[frame].getDisplayName(), 0, 0, 60, 4210752);
		}
		else{
			mc.fontRendererObj.drawSplitString(description, 0, 0, 60, 4210752);
		}
		GlStateManager.popMatrix();
	}
	
	private void drawBackgound(int x, int y, boolean hover){
		if(background != null){
			GlStateManager.pushMatrix();
			GlStateManager.enableLighting();
			
			GlStateManager.translate(x, y, 0);
			double scale = 0.1;
	        GlStateManager.scale(scale, scale, scale);
			mc.getTextureManager().bindTexture(background);
			drawTexturedModalRect(0, 0, 0, 0, 256, 256);
			
	        GlStateManager.disableLighting();
			GlStateManager.popMatrix();
		}
	}
	
	private void drawFrame(int x, int y, boolean hover){
		GlStateManager.pushMatrix();
		GlStateManager.enableLighting();
		
		GlStateManager.translate(x, y, 0);
		double scale = 0.1;
        GlStateManager.scale(scale, scale, scale);
		mc.getTextureManager().bindTexture(FRAME);
		drawTexturedModalRect(0, 0, 0, 0, 256, 256);
		
        GlStateManager.disableLighting();
		GlStateManager.popMatrix();
	}
	
	private void decFrameTime(){
		tick--;
		if(tick <= 0){
			frame++;
			tick = frameDuration;
			if(frame >= items.length){
				frame = 0;
			}
		}
	}
	
	private void drawItem(int x, int y, boolean hover){
		if(items != null){
			if(items.length > 1){
				renderItem(items[frame], x, y, 1.2F);
				decFrameTime();
			}
			else{
				renderItem(items[frame], x, y, 1.2F);
			}
			
		}
	}
	
	@Override
	protected void onClicked(GuiBookPage fromPage){
		if(page != null){
			openGui(fromPage, page);
		}
	}
}
