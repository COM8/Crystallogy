package de.comeight.crystallogy.gui.bookOfKnowledge.buttons;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraft.util.ResourceLocation;

public class BookButtonScrollBarUpDown extends BookButton {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private final ResourceLocation BUTTONS = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/guiScrollBarIcons.png");
	private final boolean upwards;
	private ScrollBarList scrollBar;
	
	//-----------------------------------------------Constructor:------------------------------------------
	public BookButtonScrollBarUpDown(int buttonId, int posX, int posY, boolean upwards, ScrollBarList scrollBar) {
		super(buttonId, posX, posY, 15, 10, "");
		this.upwards = upwards;
		this.scrollBar = scrollBar;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawNormal(int x, int y) {
		if(!enabled){
			drawDisabled(x, y);
			return;
		}
		if(upwards){
			drawTexture(x, y, 0, 20, 15, 10, BUTTONS);
		}
		else{
			drawTexture(x, y, 15, 20, 15, 10, BUTTONS);
		}
	}

	@Override
	public void drawHover(int x, int y) {
		if(!enabled){
			drawDisabled(x, y);
			return;
		}
		if(upwards){
			drawTexture(x, y, 0, 10, 15, 10, BUTTONS);
		}
		else{
			drawTexture(x, y, 15, 10, 15, 10, BUTTONS);
		}
	}
	
	private void drawDisabled(int x, int y){
		if(upwards){
			drawTexture(x, y, 0, 0, 15, 10, BUTTONS);
		}
		else{
			drawTexture(x, y, 15, 0, 15, 10, BUTTONS);
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		super.mouseReleased(mouseX, mouseY);
		if(!enabled){
			return;
		}
		if(upwards){
			scrollBar.prevEntry();
		}
		else{
			scrollBar.nextEntry();
		}
	}

	@Override
	public void onClicked(GuiBookPage fromPage) {
	}
	
}
