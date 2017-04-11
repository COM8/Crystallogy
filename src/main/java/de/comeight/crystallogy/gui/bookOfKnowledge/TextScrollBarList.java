package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.util.List;

import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TextScrollBarList extends ScrollBarList {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private List<String> text;
	private int textWidth;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public TextScrollBarList(int width, int height, int posX, int posY, GuiBookPage page) {
		super(width, height, posX, posY, page);
		this.textWidth = width - 15;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public void setText(String text){
		this.text = mc.fontRendererObj.listFormattedStringToWidth(text, textWidth);
	}
	
	protected int getEntryHeight(){
    	return 10;
    }
    
    protected int getEntrysSize(){
    	return text.size();
    }
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
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
    	
    	int y = 0;
    	for (int i = drawStratIndex; i < drawEndIndex; i++) {
    		mc.fontRendererObj.drawSplitString(text.get(i), posX, posY + y, textWidth, 4210752);
    		y += getEntryHeight();
		}
	}
	
	@Override
	protected void shouldEnbaleScroll(){
    	if((height / getEntryHeight()) >= getEntrysSize()){
    		scrollEnabeld = false;
    	}
    	else{
    		scrollEnabeld = true;
    	}
    }
	
}
