package de.comeight.crystallogy.gui.bookOfKnowledge.pages;

import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class GuiBookPageSuggestions extends GuiBookPage{
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected ScrollBarList suggestionsList;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookPageSuggestions(String heading) {
		super(heading);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	protected abstract void createSuggestionsList();
	
	protected abstract void populateSuggestionsList();
	
	protected abstract void drawSuggestionsList(int mouseX, int mouseY);
	
	@Override
	protected void addButtons() {
		super.addButtons();
		createSuggestionsList();
		populateSuggestionsList();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawSuggestionsList(mouseX, mouseY);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		if(suggestionsList != null){
			suggestionsList.mouseReleased(mouseX, mouseY);
		}
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		if(suggestionsList != null){
			suggestionsList.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		}
	}
	
}
