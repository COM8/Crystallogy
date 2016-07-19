package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.io.IOException;

import de.comeight.crystallogy.CrystallogyBase;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookPage extends GuiScreen {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final int ID = 4;
	private int buttonId = -1;
	
	private static final ResourceLocation RLBOOK = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/bookPage.png");
	/** The X size of the inventory window in pixels. */
	protected int xSize = 256;
    /** The Y size of the inventory window in pixels. */
    protected int ySize = 256;
    
    protected int xPosBook;
    protected int yPosBook;
    
    protected final int WRAPWIDTH = (int) (xSize / 2.8);
    
    protected final String HEADING;
	
	//-----------------------------------------------Constructor:-------------------------------------------
    public GuiBookPage(String heading) {
    	this.mc = Minecraft.getMinecraft();
    	this.HEADING = heading;
    	this.xPosBook = (width - xSize) / 2;
    	this.yPosBook = (height - ySize) / 2;
    	
	    onGuiOpened();	
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
    protected int getNextButtonId(){
    	buttonId++;
    	return buttonId;
    }
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	protected void onGuiOpened(){
		PageRegistry.setCurrentPage(this);
	}
    
    @Override
	protected void actionPerformed(GuiButton button) throws IOException {
		for (GuiButton guiButton : buttonList) {
			if(guiButton.id == button.id){
				if(guiButton instanceof BookButton){
					BookButton bookButton = (BookButton) guiButton;
					bookButton.onClicked(this);
				}
			}
		}
	}
    
    @Override
	public void initGui() {
		addButtons();
	}
	
	protected void addButtons(){
		buttonList.add(new BookButtonBackwards(getNextButtonId(), 10, 225));
	}
    
    @Override
	public void drawBackground(int tint) {
		
		mc.getTextureManager().bindTexture(RLBOOK);
		drawTexturedModalRect(xPosBook, yPosBook, 0, 0, xSize, ySize);
	}
	
	protected void drawHeading(){
		GlStateManager.pushMatrix();
		GlStateManager.translate(xPosBook + 10, yPosBook + 10, 0);
		GlStateManager.scale(1.25, 1.25, 1.25);
		fontRendererObj.drawSplitString(HEADING, 0, 0, WRAPWIDTH, 4210752);
		GlStateManager.popMatrix();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.xPosBook = (width - xSize) / 2;
    	this.yPosBook = (height - ySize) / 2;
		
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
		xPosBook = (w - xSize) / 2;
    	yPosBook = (h - ySize) / 2;
	}
	
}
