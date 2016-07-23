package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.ArmorScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookArmor extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	ArmorScrollBarList scrollingList;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookArmor() {
		super("Armor:");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.TOOLS_PAGE;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		addScrollingList();
	}
	
	private void addScrollingList(){
		scrollingList = new ArmorScrollBarList(xSize / 2 - 20, 175, xPosBook + BORDER_LEFT -5, yPosBook + 23, this);
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
