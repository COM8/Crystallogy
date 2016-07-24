package de.comeight.crystallogy.gui.bookOfKnowledge.buttons;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
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
	protected ItemStack[] items;
	private int frameDuration;
	private long lastFrameInc;
	protected int frame;
	private String description;
	private float scale = 1.0F;
	
	private GuiBookPage page;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookButtonCategory(int buttonId, int x, int y, ResourceLocation background, ItemStack item, GuiBookPage page) {
		this(buttonId, x, y, background, new ItemStack[]{item}, 1000, page);
	}
	
	public BookButtonCategory(int buttonId, int x, int y, ResourceLocation background, ItemStack[] items, int frameDuration, GuiBookPage page) {
		super(buttonId, x, y, 25, 25);
		
		this.description = null;
		this.background = background;
		
		this.items = items;
		this.frameDuration = frameDuration;
		this.lastFrameInc = System.currentTimeMillis();
		this.frame = 0;
		this.page = page;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public void setCustomDescription(String description){
		this.description = description;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public String getDescription() {
		if(description == null){
			//return removeFormattingCodes(items[0].getDisplayName());
			return items[0].getDisplayName();
		}
		else{
			return description;
		}
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawNormal(int x, int y){
		GlStateManager.pushMatrix();
		
		GlStateManager.enableLighting();
		
		drawDescription(x, y);
		GlStateManager.translate(x, y, 0);
		GlStateManager.scale(this.scale, this.scale, this.scale);
		GlStateManager.translate(-x, -y, 0);
		
		drawFrame(x, y, false);
		drawBackgound(x, y, false);
		drawItem(x + 3, y + 3, false);
		
		GlStateManager.disableLighting();
		
		GlStateManager.popMatrix();
	}

	private String removeFormattingCodes(String text) {
		int i = -1;
		while ((i = text.indexOf('§')) >= 0) {
			if (i >= 0) {
				if (i + 1 >= text.length()) {
					text = text.substring(0, i) + text.substring(i + 1);
				} else {
					text = text.substring(0, i) + text.substring(i + 2);
				}
			}
		}

		return text;
	}
	
	@Override
	public void drawHover(int x, int y){
		GlStateManager.pushMatrix();
		
		GlStateManager.enableLighting();
		
		drawDescription(x, y);
		GlStateManager.translate(x - 3, y - 3, 0);
		GlStateManager.scale(1.2 * this.scale, 1.2 * this.scale, 1.2 * this.scale);
		GlStateManager.translate(-x, -y, 0);
		
		drawFrame(x, y, true);
		drawBackgound(x, y, true);
		drawItem(x + 3, y + 3, true);
		
		GlStateManager.disableLighting();
		
		GlStateManager.popMatrix();
	}
	
	protected void drawDescription(int x, int y){
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
	
	protected void drawBackgound(int x, int y, boolean hover){
		if(background != null){
			GlStateManager.pushMatrix();
			GlStateManager.enableLighting();
			
			GlStateManager.translate(x, y, 0);
			double scale = 0.1;
	        GlStateManager.scale(scale, scale, scale);
	        drawTexture(x, y, 256, 256, background);
			
	        GlStateManager.disableLighting();
			GlStateManager.popMatrix();
		}
	}
	
	protected void drawFrame(int x, int y, boolean hover){
		GlStateManager.pushMatrix();
		GlStateManager.enableLighting();
		
		GlStateManager.translate(x, y, 0);
        GlStateManager.scale(0.1, 0.1, 0.1);
        GlStateManager.translate(-x, -y, 0);
        drawTexture(x, y, 256, 256, FRAME);
		
        GlStateManager.disableLighting();
		GlStateManager.popMatrix();
	}
	
	private void decFrameTime(){
		if(System.currentTimeMillis() >= lastFrameInc + frameDuration){
			frame++;
			lastFrameInc = System.currentTimeMillis();
			if(frame >= items.length){
				frame = 0;
			}
		}
	}
	
	protected void drawItem(int x, int y, boolean hover){
		if(items != null){
			if(items.length > 1){
				GuiBookUtilities.renderItem(items[frame], x, y, 1.2F);
				decFrameTime();
			}
			else{
				GuiBookUtilities.renderItem(items[frame], x, y, 1.2F);
			}
			
		}
	}
	
	@Override
	public void onClicked(GuiBookPage fromPage){
		if(page != null){
			openGui(fromPage, page);
		}
	}
}
