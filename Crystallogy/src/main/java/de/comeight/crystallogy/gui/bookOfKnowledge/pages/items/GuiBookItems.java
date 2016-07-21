package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.ItemsScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookItems extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	ItemsScrollBarList scrollingList;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookItems() {
		super("Items:");
		setNextPage(PageRegistry.ARMOR_PAGE);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		addScrollingList();
		
		int chapterButtonX = xSize / 2 + 10;
		float buttonScale = 1.0F;
		
		//Armor:
		BookButtonCategory armor = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 75, null, new ItemStack(ItemHandler.armorChestplate_hunter), PageRegistry.ARMOR_PAGE);
		armor.setScale(buttonScale);
		armor.setCustomDescription("Armor");
		buttonList.add(armor);
		
		//Tools:
		BookButtonCategory tools = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 40, null, new ItemStack(ItemHandler.playerCrystalKnife), PageRegistry.TOOLS_PAGE);
		tools.setScale(buttonScale);
		tools.setCustomDescription("Tools");
		buttonList.add(tools);
	}
	
	private void addScrollingList(){
		scrollingList = new ItemsScrollBarList(xSize / 2 - 20, 175, xPosBook + 10, yPosBook + 20, this);
	}
	
	private void drawChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + 10, yPosBook + 10, xSize / 2 - 10, "Subchapters:");
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
