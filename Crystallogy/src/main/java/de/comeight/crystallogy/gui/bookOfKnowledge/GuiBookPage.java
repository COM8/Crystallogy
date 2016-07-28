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
	private static final ResourceLocation BOOK = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/bookPage.png");
	public static final ResourceLocation GUI_ELEMENTS = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/guiEmements.png");
	/** The X size of the inventory window in pixels. */
	protected final int xSize = 384;
    /** The Y size of the inventory window in pixels. */
    protected final int ySize = 256;
    /** The recommend border for if you draw something onto the book. */
    protected final int BORDER_TOP = 10;
    /** The recommend border for if you draw something onto the book. */
    protected final int BORDER_LEFT = 15;
    /** The recommend border for if you draw something onto the book. */
    protected final int BORDER_RIGHT = 15;
    
    protected int xPosBook;
    protected int yPosBook;
    
    protected final int WRAPWIDTH = (int) (xSize / 2) - 20;
    
    protected final String HEADING;
    
    protected GuiBookPage nextPage;
    
	//-----------------------------------------------Constructor:-------------------------------------------
    public GuiBookPage(String heading) {
    	this.mc = Minecraft.getMinecraft();
    	this.HEADING = heading;
    	this.nextPage = null;
    	
    	calcBookPos();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
    public static int getNextButtonId(){
    	return GuiBookUtilities.getNextButtonId();
    }
    
    public GuiBookPage getNextPage() {
		return nextPage;
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
		buttonList.add(new BookButtonBackwards(getNextButtonId(), BORDER_LEFT, 227));
		buttonList.add(new BookButtonForwards(getNextButtonId(), xSize - 40, 227, this));
		buttonList.add(new BookButtonHome(getNextButtonId()));
	}
    
    @Override
	public void drawBackground(int tint) {
    	GlStateManager.pushMatrix();
    	
    	GlStateManager.translate(xPosBook, yPosBook, 0);
    	GlStateManager.scale(1.5, 1.0, 1.0);
    	
    	drawTexture(0, 0, xSize * 2 / 3, ySize, BOOK);
    	
    	GlStateManager.popMatrix();
	}
	
	protected void drawHeading(){
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP, 0);
		GlStateManager.scale(1.25, 1.25, 1.25);
		GuiBookUtilities.drawTextBox(0, 0, WRAPWIDTH / 2 + 10, HEADING);
		
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
	
	@Deprecated
	public void drawTexture(int posX, int posY, int sizeX, int sizeY, ResourceLocation texture){
    	GuiBookUtilities.drawTexture(posX, posY, sizeX, sizeY, texture);
    }
    
	@Deprecated
    public void drawTexture(int posX, int posY, int xStart, int yStart, int sizeX, int sizeY, ResourceLocation texture){
    	GuiBookUtilities.drawTexture(posX, posY, xStart, yStart, sizeX, sizeY, texture);
    }
    
	@Deprecated
    @Override
    public void drawTexturedModalRect(int xCoord, int yCoord, int minU, int minV, int maxU, int maxV)
    {
    	GuiBookUtilities.drawTexturedModalRect(xCoord, yCoord, minU, minV, maxU, maxV);
    }
	
}
