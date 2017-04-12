package de.comeight.crystallogy.gui.bookOfKnowledge.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.IGuiClickable;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
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
    
    protected final int WRAPWIDTH = xSize / 2 - 20;
    
    protected final String HEADING;
    
    protected GuiBookPage nextPage;
    
    private List<IGuiClickable> guiElements;
    
	//-----------------------------------------------Constructor:-------------------------------------------
    public GuiBookPage(String heading) {
    	this.mc = Minecraft.getMinecraft();
    	this.HEADING = heading;
    	this.nextPage = null;
    	this.guiElements = new ArrayList<IGuiClickable>();
    	calcBookPos();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
    public static int getNextButtonId(){
    	return GuiBookUtilities.getNextButtonId();
    }
    
    public GuiBookPage getNextPage() {
		return nextPage;
	}
    
    protected IGuiClickable getGuiElementById(int id){
    	return guiElements.get(id);
    }
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
    protected void openGui(GuiBookPage fromPage, GuiBookPage toPage){
		PageRegistry.openPage(mc, fromPage, toPage);
	}
    
    protected int addGuiElement(IGuiClickable element){
    	guiElements.add(element);
    	return guiElements.size() - 1;
    }
    
    protected void calcBookPos(){
		this.xPosBook = (width - xSize) / 2;
    	this.yPosBook = (height - ySize) / 2;
	}
    
    public void onGuiOpened(){
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
		addGuiElements();
	}
	
	protected void addButtons(){
		buttonList.add(new BookButtonBackwards(getNextButtonId(), BORDER_LEFT, 234));
		buttonList.add(new BookButtonForwards(getNextButtonId(), xSize - 27, 234, this));
		buttonList.add(new BookButtonHome(getNextButtonId()));
		//buttonList.add(new BookButtonClipToBook(getNextButtonId()));
	}
	
	protected void addGuiElements(){
		
	}
    
    @Override
	public void drawBackground(int tint) {
    	GlStateManager.pushMatrix();
    	
    	GlStateManager.translate(xPosBook, yPosBook, 0);
    	GlStateManager.scale(1.5, 1.0, 1.0);
    	
    	GuiBookUtilities.drawTexture(0, 0, xSize * 2 / 3, ySize, BOOK);
    	
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
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if(mouseButton == 1 && PageRegistry.canGoBack()){
			goBackOnePage();
		}
		
		for (IGuiClickable element : guiElements) {
			if(element != null){
				element.mouseClicked(mouseX, mouseY, mouseButton);
			}
		}
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		for (IGuiClickable element : guiElements) {
			if(element != null){
				element.mouseReleased(mouseX, mouseY);
			}
		}
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		for (IGuiClickable element : guiElements) {
			if(element != null){
				element.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
			}
		}
	}
	
	protected void goBackOnePage(){
		GuiBookPage page = PageRegistry.getLastVisited();
		if(page != null){
			mc.displayGuiScreen(page);
		}
	}
	
}
