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
public class GuiBookCrystalPickaxe1 extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer renderer;
	
	private BookInfusionRecipe recipeRed;
	private BookInfusionRecipe recipeBlue;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystalPickaxe1() {
		super("Crystal Hammer:");
		
		renderer = new BookMultiItemRenderer(new ItemStack[]{	new ItemStack(ItemHandler.crystalPickaxe_red), 
																new ItemStack(ItemHandler.crystalPickaxe_blue), 
																new ItemStack(ItemHandler.crystalPickaxe_green), 
																new ItemStack(ItemHandler.crystalPickaxe_yellow)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.CRYSTAL_PICKAXE_PAGE_2;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		initRecipe();
	}
	
	private void initRecipe(){
		BookButtonCrafting hR = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystalPickaxeHead, 1, 0), PageRegistry.CRYSTAL_PICKAXE_HEAD_PAGE);
		hR.disableFrame();
		BookButtonCrafting hB = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystalPickaxeHead, 1, 1), PageRegistry.CRYSTAL_PICKAXE_HEAD_PAGE);
		hB.disableFrame();
		
		BookButtonCrafting dR = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_red), PageRegistry.CRYSTAL_DUST_PAGE);
		dR.disableFrame();
		BookButtonCrafting dB = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_blue), PageRegistry.CRYSTAL_DUST_PAGE);
		dB.disableFrame();
		BookButtonCrafting t = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.toolRod), PageRegistry.TOOL_ROD_PAGE);
		t.disableFrame();
		BookButtonCrafting e = BookButtonCrafting.EMPTY;
		e.disableFrame();
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystalPickaxe_red), null);
		output.disableFrame();
		recipeRed = new BookInfusionRecipe(new BookButtonCrafting[]{t, hR, dR, dR, e}, output);
		
		output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystalPickaxe_blue), null);
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
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 120, WRAPWIDTH, 1.0F, "A Crystal Pickaxe is a powerful tool. It can mine a x*x*x area.\n"
				+ "If you shift right-click you can change the mode.\n"
				+ "Green Crystal Pickaxe:\n"
				+ "Modes: 6*6*6, 6*6*1, 1*1*1\n"
				+ "Blue Crystal Pickaxe:\n"
				+ "Modes: 10*10*10, 10*10*1, 1*1*1\n"
				+ "Yellow Crystal Pickaxe:\n"
				+ "Modes: 14*14*14, 14*14*1, 1*1*1\n"
				+ "Red Crystal Pickaxe:\n"
				+ "Modes: 18*18*18, 18*18*1, 1*1*1\n");
	}
	
	private void drawItem(){
		renderer.drawItem(xPosBook + 50, yPosBook + 30);
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
