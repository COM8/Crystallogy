package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.io.IOException;
import java.util.LinkedList;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;

public class ScrollBarListSearch extends ScrollBarList {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookSearchField searchField;
	public boolean searchSelected = false;
	
	private LinkedList<BookButtonCategory> fullList = new LinkedList<BookButtonCategory>();
	private String prevText = "x";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ScrollBarListSearch(int width, int height, int posX, int posY, GuiBookPage page) {
		super(width, height, posX, posY, page);
		
		searchField = new BookSearchField(GuiBookPage.getNextButtonId(), mc.fontRendererObj, 0, 0, width - 14, 10);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawScreen(int mouseX, int mouseY, int posX, int posY) {
		if(!prevText.equals(searchField.getText())){
			prevText = searchField.getText();
			shrinkList(prevText);
		}
		
		super.drawScreen(mouseX, mouseY, posX, posY);
		drawSearchField(posX, posY);
	}
	
	private void drawSearchField(int x, int y){
		searchField.drawScreen(x, y);
		searchSelected = searchField.isFocused();
	}
	
	@Override
	public void addEntry(BookButtonCategory button) {
		fullList.add(button);
	}
	
	private void shrinkList(String text){
		if(prevText.equals("")){
			list = fullList;
		}
		else{
			list = new LinkedList<BookButtonCategory>();
			for (BookButtonCategory bookButtonCategory : fullList) {
				if(bookButtonCategory.getDescription().toLowerCase().contains(text.toLowerCase())){
					list.add(bookButtonCategory);
				}
			}
		}
	}
	
	@Override
	protected void drawEntrys(int mouseX, int mouseY){
		int drawStratIndex;
    	int drawEndIndex;
    	if(scrollEnabeld){
    		drawStratIndex = scrollPos / entryHeigtht;
        	drawEndIndex = drawStratIndex + ((height - 12) / entryHeigtht);
        	if(drawEndIndex >= list.size()){
        		drawEndIndex = list.size();
        	}
    	}
    	else{
    		drawStratIndex = 0;
        	drawEndIndex = list.size();
    	}
    	
    	
    	int relativeHoverIndex = getHoverIndex(mouseX - this.posX, mouseY - this.posY);
    	
    	hoverIndex = relativeHoverIndex;
    	if(hoverIndex != -1){
    		hoverIndex += drawStratIndex;
    	}
    	
    	for (int i = drawStratIndex; i < drawEndIndex; i++) {
    		if(list.get(i) != null){
    			if((i - drawStratIndex) == relativeHoverIndex){
    				drawEntry(i, true, this.posX, this.posY + 12 + ((i - drawStratIndex) * entryHeigtht));
    			}
    			else{
    				drawEntry(i, false, this.posX, this.posY + 12 + ((i - drawStratIndex) * entryHeigtht));
    			}
    		}
		}
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		searchField.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	public void keyTyped(char typedChar, int keyCode) throws IOException {
		searchField.textboxKeyTyped(typedChar, keyCode);
	}
	
	@Override
	protected int getHoverIndex(int mouseX, int mouseY){
    	if(mouseX < 0 || mouseY < 12 || mouseX > width - 10 || mouseY > height){
    		return -1;
    	}
    	return (mouseY - 12) / entryHeigtht;
    }
	
}
