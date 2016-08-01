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
public class GuiBookCrystalSword1 extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer renderer;
	
	private BookInfusionRecipe recipeRed;
	private BookInfusionRecipe recipeBlue;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystalSword1() {
		super("Crystal Sword:");
		
		renderer = new BookMultiItemRenderer(new ItemStack[]{	new ItemStack(ItemHandler.crystalSword_red), 
																new ItemStack(ItemHandler.crystalSword_blue), 
																new ItemStack(ItemHandler.crystalSword_green), 
																new ItemStack(ItemHandler.crystalSword_yellow)}, 1000, 5.0F);
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.CRYSTAL_SWORD_PAGE_2;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		BookButtonCrafting hR = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallSwordBlade, 1, 0), PageRegistry.CRYSTAL_SWORD_BLADE_PAGE);
		hR.disableFrame();
		BookButtonCrafting hB = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallSwordBlade, 1, 1), PageRegistry.CRYSTAL_SWORD_BLADE_PAGE);
		hB.disableFrame();
		
		BookButtonCrafting dR = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_red), PageRegistry.CRYSTAL_DUST_PAGE);
		dR.disableFrame();
		BookButtonCrafting dB = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_blue), PageRegistry.CRYSTAL_DUST_PAGE);
		dB.disableFrame();
		BookButtonCrafting t = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.toolRod), PageRegistry.TOOL_ROD_PAGE);
		t.disableFrame();
		BookButtonCrafting e = BookButtonCrafting.EMPTY;
		e.disableFrame();
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystalSword_red), null);
		output.disableFrame();
		recipeRed = new BookInfusionRecipe(new BookButtonCrafting[]{t, hR, dR, dR, e}, output);
		
		output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystalSword_blue), null);
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
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 120, WRAPWIDTH, 1.0F, "A Crystal Sword is a powerful tool. It combines the durability of diamonds with the sharpness of crystals.\n"
				+ "Green Crystal Hammer:\n"
				+ "Attack Damage: 9.5\n"
				+ "Blue Crystal Hammer:\n"
				+ "Attack Damage: 11.5\n"
				+ "Yellow Crystal Hammer:\n"
				+ "Attack Damage: 12.5\n"
				+ "Red Crystal Hammer:\n"
				+ "Attack Damage: 13.5\n");
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
