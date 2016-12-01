package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.util.LinkedList;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonScrollBarUpDown;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ScrollBarList implements IGuiClickable{
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected final Minecraft mc = Minecraft.getMinecraft();
	private GuiBookPage page;
	protected long lastMouseEvent;
	protected Long mouseCooldownUntil = 0L;
	protected boolean shouldWaitForInput = true;
	
	protected int width;
	protected int height;
	protected int posX;
	protected int posY;
	
	protected boolean scrollEnabeld = true;
	protected int scrollPos = 0;
	protected int hoverIndex = -1;
	protected boolean scrolling;
	
	protected LinkedList<BookButtonCategory> list = new LinkedList<BookButtonCategory>();
	
	protected final ResourceLocation BAR = new ResourceLocation("textures/gui/container/creative_inventory/tab_items.png");
	protected final ResourceLocation SCROLL = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
	protected final int SCROLL_BAR_WIDTH = 10;
	//protected BookButtonScrollBarUpDown[] navButtons;
	
	//-----------------------------------------------Constructor:-------------------------------------------
    public ScrollBarList(int width, int height, int posX, int posY, GuiBookPage page)
    {
    	this.page = page;
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        this.scrolling = false;
        //this.navButtons = new BookButtonScrollBarUpDown[2];
        //navButtons[0] = new BookButtonScrollBarUpDown(0, posX + width - 15, posY, true, this);
        //navButtons[1] = new BookButtonScrollBarUpDown(1, posX + width - 15, posY + height - 10, false, this);
    }
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
    protected int getEntryHeight(){
    	return 35;
    }
    
    protected int getEntrysSize(){
    	return list.size();
    }
    
    
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
    public void nextEntry(){
    	
    }
    
    public void prevEntry(){
    	
    }
    
    public void addEntry(BookButtonCategory button){
    	list.add(button);
    }
    
    public void drawScreen(int mouseX, int mouseY, int posX, int posY){
    	this.posX = posX;
        this.posY = posY;
    	
    	drawBackground(this.posX, this.posY);
    	shouldEnbaleScroll();
    	drawScrollBar(this.posX, this.posY);
    	drawEntrys(mouseX, mouseY);
    	
    	/*if(height >= 40){
    		navButtons[0].enabled = true;
    		navButtons[0].drawButton(mouseX, mouseY, posX + width - 15, posY);
    		navButtons[1].enabled = true;
    		//navButtons[1].drawButton(mouseX, mouseY, posX, posY + height - 10);
    	}
    	else{
    		navButtons[0].enabled = false;
    		navButtons[1].enabled = false;
    	}*/
    }
    
	protected void drawEntrys(int mouseX, int mouseY){
		int drawStratIndex;
    	int drawEndIndex;
    	if(scrollEnabeld){
    		drawStratIndex = scrollPos / getEntryHeight();
        	drawEndIndex = drawStratIndex + (height / getEntryHeight());
        	if(drawEndIndex >= getEntrysSize()){
        		drawEndIndex = getEntrysSize();
        	}
    	}
    	else{
    		drawStratIndex = 0;
        	drawEndIndex = getEntrysSize();
    	}
    	
    	
    	int relativeHoverIndex = getHoverIndex(mouseX - this.posX, mouseY - this.posY);
    	
    	hoverIndex = relativeHoverIndex;
    	if(hoverIndex != -1){
    		hoverIndex += drawStratIndex;
    	}
    	
    	for (int i = drawStratIndex; i < drawEndIndex; i++) {
    		if(list.get(i) != null){
    			if((i - drawStratIndex) == relativeHoverIndex){
    				drawEntry(i, true, this.posX, this.posY + ((i - drawStratIndex) * getEntryHeight()));
    			}
    			else{
    				drawEntry(i, false, this.posX, this.posY + ((i - drawStratIndex) * getEntryHeight()));
    			}
    		}
		}
	}
    
    protected void shouldEnbaleScroll(){
    	if((height / getEntryHeight()) >= getEntrysSize()){
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
    	return mouseY / getEntryHeight();
    }
    
    @Override
    public void mouseReleased(int mouseX, int mouseY){
    	if(scrolling){
    		scrolling = false;
    		return;
    	}
    	if(shouldWaitForInput){
    		lastMouseEvent = System.currentTimeMillis();
    		shouldWaitForInput = false;
    		return;
    	}
    	
    	if(System.currentTimeMillis() < lastMouseEvent + 100){
    		return;
    	}
    	else{
    		lastMouseEvent = System.currentTimeMillis();
    	}
    	//navButtons[0].mouseReleased(mouseX, mouseY);
    	//navButtons[1].mouseReleased(mouseX, mouseY);
    	
    	if(hoverIndex >= 0 && hoverIndex < getEntrysSize()){
			list.get(hoverIndex).onClicked(page);
		}
    }
    
    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
    	if(!scrollEnabeld || clickedMouseButton != 0){
    		return;
    	}
    	if((mouseX > posX + width || mouseX < posX + width - 10) && !scrolling){
    		return;
    	}
    	if((mouseY > posY + height || mouseY < posY) && !scrolling){
    		return;
    	}
    	scrolling = true;
    	
    	if(mouseY >= (posY + height - 10)){
    		scrollPos = getEntryHeight() * getEntrysSize() - height;
    	}
    	else if(mouseY <= posY + 10){
    		scrollPos = 0;
    	}
    	else{
    		scrollPos = (int) ( ((double)(mouseY - posY) / (double) height) * (getEntryHeight() * (getEntrysSize() - height / getEntryHeight())));
    	}
    }
    
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton){
    	
    }
    
    protected void drawScrollBar(int x, int y){
    	if(height > 110){
    		drawTexture(x + width - 10, y, 175, 18, 10, 110, BAR);
    	    drawTexture(x + width - 10, y + 110, 175, 18, 10, height - 110, BAR);
    	}
    	else{
    		drawTexture(x + width - 10, y, 175, 18, 10, height, BAR);
    	}
	    
	    if(scrollEnabeld){
	    	int yScroll = (int) ((double)scrollPos / (getEntryHeight() * (getEntrysSize() - (height) / getEntryHeight())) * (height - 13));
	        drawTexture(x + width - 10, y + yScroll, 233, 1, 10, 13, SCROLL);
	    }
	    else{
	    	drawTexture(x + width - 10, y, 245, 1, 10, 13, SCROLL);
	    }
    }
    
    protected void drawBackground(int x, int y){
    	//drawTexture(x, y, width, height, Gui.OPTIONS_BACKGROUND);
    }
    
    protected void drawEntry(int index, boolean hover, int x, int y){
    	if(index % 2 == 0){
    		final int zLevel = 300;
            final int backgroundColor = 0xF0100010;
    		GuiUtils.drawGradientRect(zLevel, 20, 40, 20, 40, backgroundColor, backgroundColor);
    	}
    	
    	if(hover){
    		//drawTexture(x, y, width - 10, getEntryHeight(), Gui.ICONS);
    		list.get(index).drawHover(x + 5, y + 5);
    	}
    	else{
    		list.get(index).drawNormal(x + 5, y + 5);
    	}
    }
    
    public void drawTexture(int x, int y, int sizeX, int sizeY, ResourceLocation texture){
    	drawTexture(x, y, 0, 0, sizeX, sizeY, texture);
    }
    
    public void drawTexture(int x, int y, int xStart, int yStart, int sizeX, int sizeY, ResourceLocation texture){
    	mc.getTextureManager().bindTexture(texture);
    	GuiBookUtilities.drawTexturedModalRect(x, y, xStart, yStart, sizeX, sizeY);
    }
}
