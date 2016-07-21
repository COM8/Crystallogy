package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.io.IOException;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButton;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonBackwards;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonForwards;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonHome;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookPage extends GuiScreen {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static int buttonId = -1;
	
	private static final ResourceLocation BOOK = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/bookPage.png");
	/** The X size of the inventory window in pixels. */
	protected int xSize = 256;
    /** The Y size of the inventory window in pixels. */
    protected int ySize = 256;
    
    protected int xPosBook;
    protected int yPosBook;
    
    protected final int WRAPWIDTH = (int) (xSize / 2.8);
    
    protected final String HEADING;
    
    protected GuiBookPage nextPage;
    
	//-----------------------------------------------Constructor:-------------------------------------------
    public GuiBookPage(String heading) {
    	this.mc = Minecraft.getMinecraft();
    	this.HEADING = heading;
    	
    	calcBookPos();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
    public static int getNextButtonId(){
    	buttonId++;
    	return buttonId;
    }
    
    public void setNextPage(GuiBookPage nextPage) {
		this.nextPage = nextPage;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
    protected void openGui(GuiBookPage fromPage, GuiBookPage toPage){
		PageRegistry.openPage(mc, fromPage, toPage);
	}
    
    protected void calcBookPos(){
		this.xPosBook = (width - xSize) / 2;
    	this.yPosBook = (height - ySize) / 2;
	}
    
    protected void onGuiOpened(){
		PageRegistry.setCurrentPage(this);
	}
    
    @Override
	protected void actionPerformed(GuiButton button) throws IOException {
    	try {
    		for (GuiButton guiButton : buttonList) {
    			if(guiButton.id == button.id){
    				if(guiButton instanceof BookButton){
    					BookButton bookButton = (BookButton) guiButton;
    					bookButton.onClicked(this);
    				}
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @Override
	public void initGui() {
		addButtons();
	}
	
	protected void addButtons(){
		buttonList.add(new BookButtonBackwards(getNextButtonId(), 10, 225));
		buttonList.add(new BookButtonForwards(getNextButtonId(), xSize - 35, 225, nextPage));
		buttonList.add(new BookButtonHome(getNextButtonId()));
	}
    
    @Override
	public void drawBackground(int tint) {
    	drawTexture(xPosBook, yPosBook, xSize, ySize, BOOK);
	}
	
	protected void drawHeading(){
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + 15, yPosBook + 10, 0);
		GlStateManager.scale(1.25, 1.25, 1.25);
		fontRendererObj.drawSplitString(HEADING, 0, 0, WRAPWIDTH, 4210752);
		
		GlStateManager.popMatrix();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		calcBookPos();
		
		drawBackground(0);
		drawHeading();
	
		for (int i = 0; i < this.buttonList.size(); ++i)
        {
            ((BookButton)buttonList.get(i)).drawButton(mouseX, mouseY, xPosBook, yPosBook);
        }
	}
	
	@SideOnly(Side.CLIENT)
	private void renderItem(ItemStack stack, int posX, int posY, float scale)
    {
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        if (stack != null)
        {
        	GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            RenderHelper.enableStandardItemLighting();
            
            GlStateManager.translate(posX, posY, 0);
            GlStateManager.scale(scale, scale, scale);
            
            itemRenderer.renderItemAndEffectIntoGUI(stack, 0, 0);
            itemRenderer.renderItemOverlayIntoGUI(this.fontRendererObj, stack, 0, 0, null);
            
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == 1 || this.mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode))
        {
            this.mc.thePlayer.closeScreen();
        }
    }
	
	protected Item getItemFromBlock(Block block){
		return Item.getItemFromBlock(block);
	}
	
	@Override
	public void onResize(Minecraft mcIn, int w, int h) {
		super.onResize(mcIn, w, h);
		calcBookPos();
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
