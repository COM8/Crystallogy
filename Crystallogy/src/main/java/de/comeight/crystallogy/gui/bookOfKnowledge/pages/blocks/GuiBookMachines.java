package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.MachinesScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookItems;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookMachines extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	MachinesScrollBarList scrollingList;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookMachines() {
		super("Machines:");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.ITEMS_PAGE;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		addScrollingList();
	}
	
	private void addScrollingList(){
		scrollingList = new MachinesScrollBarList(xSize / 2 - 20, 145, xPosBook + BORDER_LEFT - 5, yPosBook + 23, this);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
        scrollingList.drawScreen(mouseX, mouseY, xPosBook + BORDER_LEFT - 5, yPosBook + 23);
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
	
}
