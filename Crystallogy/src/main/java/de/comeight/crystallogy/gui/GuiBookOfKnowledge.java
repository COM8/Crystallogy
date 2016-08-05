package de.comeight.crystallogy.gui;

import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraft.client.gui.GuiScreen;

public class GuiBookOfKnowledge extends GuiScreen {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final int ID = 4;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookOfKnowledge() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void initGui() {
		if(!openClipedPage()){
			openLastOpenedPage();
		}
	}
	
	private boolean openClipedPage(){
		GuiBookPage clipedPage = PageRegistry.getClipedPage(); 
		if(clipedPage == null){
			return false;
		}
		mc.displayGuiScreen(clipedPage);
		return true;
	}
	
	private void openLastOpenedPage(){
		GuiBookPage lastPage = PageRegistry.getCurrentPage();
		if(lastPage != null){
			mc.displayGuiScreen(lastPage);
		}
		else{
			mc.displayGuiScreen(PageRegistry.MAIN_PAGE);
		}
	}
	
}
