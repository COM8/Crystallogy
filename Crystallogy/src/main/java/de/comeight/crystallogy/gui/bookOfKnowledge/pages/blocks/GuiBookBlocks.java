package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.gui.bookOfKnowledge.BlocksScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookBlocks extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	BlocksScrollBarList scrollingList;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookBlocks() {
		super("Blocks:");
		setNextPage(PageRegistry.MACHINES_PAGE);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		addScrollingList();
		
		int chapterButtonX = xSize / 2 + 10;
		float buttonScale = 1.0F;
		
		//Machines:
		BookButtonCategory machines = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 40, null, new ItemStack(BlockHandler.compressor), PageRegistry.MACHINES_PAGE);
		machines.setScale(buttonScale);
		machines.setCustomDescription("Machines");
		buttonList.add(machines);
	}
	
	private void drawChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + 10, yPosBook + 10, xSize / 2 - 10, "Subchapters:");
	}
	
	private void addScrollingList(){
		scrollingList = new BlocksScrollBarList(xSize / 2 - 20, 210, xPosBook + 10, yPosBook + 20, this);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
        scrollingList.drawScreen(mouseX, mouseY, xPosBook + 10, yPosBook + 20);
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
	
}
