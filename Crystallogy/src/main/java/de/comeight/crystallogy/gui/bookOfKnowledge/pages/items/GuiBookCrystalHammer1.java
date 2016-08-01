package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCrystalHammer1 extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer renderer;
	
	private BookInfusionRecipe recipeRed;
	private BookInfusionRecipe recipeBlue;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystalHammer1() {
		super("Crystal Hammer:");
		
		renderer = new BookMultiItemRenderer(new ItemStack[]{	new ItemStack(ItemHandler.crystallHammer_red), 
																new ItemStack(ItemHandler.crystallHammer_blue), 
																new ItemStack(ItemHandler.crystallHammer_green), 
																new ItemStack(ItemHandler.crystallHammer_yellow)}, 1000, 5.0F);
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.CRYSTAL_HAMMER_PAGE_2;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		BookButtonCrafting hR = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallHammerHead, 1, 0), PageRegistry.CRYSTAL_HAMMER_HEAD_PAGE);
		hR.disableFrame();
		BookButtonCrafting hB = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallHammerHead, 1, 1), PageRegistry.CRYSTAL_HAMMER_HEAD_PAGE);
		hB.disableFrame();
		
		BookButtonCrafting dR = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_red), PageRegistry.CRYSTAL_DUST_PAGE);
		dR.disableFrame();
		BookButtonCrafting dB = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_blue), PageRegistry.CRYSTAL_DUST_PAGE);
		dB.disableFrame();
		BookButtonCrafting t = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.toolRod), PageRegistry.TOOL_ROD_PAGE);
		t.disableFrame();
		BookButtonCrafting e = BookButtonCrafting.EMPTY;
		e.disableFrame();
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallHammer_red), null);
		output.disableFrame();
		recipeRed = new BookInfusionRecipe(new BookButtonCrafting[]{t, hR, dR, dR, e}, output);
		
		output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallHammer_blue), null);
		output.disableFrame();
		recipeBlue = new BookInfusionRecipe(new BookButtonCrafting[]{t, hB, dB, dB, e}, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawItem();
		drawText();
		drawCraftingChaptersText();
		drawRecipe(mouseX, mouseY);
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 100, WRAPWIDTH, 1.0F, "A Crystal Hammer is an advanced tool which can mine a 3x3 area and it can be used as a powerful weapon.\n"
				+ "Green Crystal Hammer:\n"
				+ "Durability: 1250, Mining Level: Iron\n"
				+ "Blue Crystal Hammer:\n"
				+ "Durability: 1750, Mining Level: Iron\n"
				+ "Yellow Crystal Hammer:\n"
				+ "Durability: 3750, Mining Level: Diamond\n"
				+ "Red Crystal Hammer:\n"
				+ "Durability: 6500, Mining Level: Diamond\n");
	}
	
	private void drawItem(){
		renderer.drawItem(xPosBook + 50, yPosBook + 20);
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipeRed.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT + 33, yPosBook + 20);
		recipeBlue.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT + 33, yPosBook + 130);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook +  + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, WRAPWIDTH, "Recipe:");
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipeRed.mouseReleased(mouseX, mouseY, state, this);
		recipeBlue.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
