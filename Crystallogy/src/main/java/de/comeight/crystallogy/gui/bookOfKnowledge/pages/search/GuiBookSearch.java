package de.comeight.crystallogy.gui.bookOfKnowledge.pages.search;

import java.io.IOException;

import de.comeight.crystallogy.gui.bookOfKnowledge.AllScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.credits.GuiBookCredits;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookSearch extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	AllScrollBarList scrollingList;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookSearch() {
		super("Search:");
		setNextPage(PageRegistry.CREDITS_PAGE);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		addScrollingList();
	}
	
	private void drawChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + 10, yPosBook + 10, xSize / 2 - 10, "");
	}
	
	private void addScrollingList(){
		scrollingList = new AllScrollBarList(xSize / 2 - 20, 187, xPosBook + 10, yPosBook + 25, this);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
        scrollingList.drawScreen(mouseX, mouseY, xPosBook + 10, yPosBook + 25);
        drawChaptersText();
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		scrollingList.mouseReleased(mouseX, mouseY);
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		scrollingList.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		scrollingList.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		if(scrollingList.searchSelected){
			scrollingList.keyTyped(typedChar, keyCode);
		}
		else{
			super.keyTyped(typedChar, keyCode);
		}
	}
	
}
