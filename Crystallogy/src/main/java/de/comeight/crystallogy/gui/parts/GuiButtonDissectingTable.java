package de.comeight.crystallogy.gui.parts;

import de.comeight.crystallogy.gui.GuiDissectingTable;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonDissectingTable extends GuiButton {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private GuiDissectingTable gui;
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiButtonDissectingTable(int buttonId, int x, int y, GuiDissectingTable gui) {
		super(buttonId, x, y, "Go!");
		this.gui = gui;
		this.width = 27;
		this.enabled = false;
	}

	public GuiButtonDissectingTable(int buttonId, int x, int y, int heightIn, GuiDissectingTable gui) {
		super(buttonId, x, y, 27, heightIn, "Go!");
		this.gui = gui;
		this.enabled = false;
	}
	
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		gui.go();
		this.enabled = false;
	}
	
	public void enable(){
		this.enabled = true;
	}
	
}
