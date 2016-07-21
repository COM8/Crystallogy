package de.comeight.crystallogy.gui;

import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
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
		openLastOpenedPage();
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
