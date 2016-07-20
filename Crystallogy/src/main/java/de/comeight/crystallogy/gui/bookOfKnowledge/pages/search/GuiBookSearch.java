package de.comeight.crystallogy.gui.bookOfKnowledge.pages.search;

import de.comeight.crystallogy.gui.bookOfKnowledge.AllScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookMachines;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.credits.GuiBookCredits;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookSearch extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	AllScrollBarList scrollingList;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookSearch() {
		super("Search:");
		setNextPage(new GuiBookCredits());
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		addScrollingList();
		
		int chapterButtonX = xSize / 2 + 10;
		float buttonScale = 1.0F;
		
		//All:
		BookButtonCategory all = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 40, null, new ItemStack(BlockHandler.compressor), new GuiBookMachines());
		all.setScale(buttonScale);
		all.setCustomDescription("Machines");
		buttonList.add(all);
	}
	
	private void drawChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + 10, yPosBook + 10, xSize / 2 - 10, "Subchapters:");
	}
	
	private void addScrollingList(){
		scrollingList = new AllScrollBarList(xSize / 2 - 20, 210, xPosBook + 10, yPosBook + 20, this);
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
