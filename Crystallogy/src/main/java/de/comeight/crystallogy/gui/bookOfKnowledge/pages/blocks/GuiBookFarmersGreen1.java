package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPageSuggestions;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookFarmersGreen1 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer entityJar;
	
	public static final ResourceLocation PREVIEW = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/blocks/farmers_green_preview.png");
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookFarmersGreen1() {
		super("Farmers Green:");
		
		entityJar = new BookMultiItemRenderer(new ItemStack[]{new ItemStack(BlockHandler.farmersGreen)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.FARMERS_GREEN_PAGE_2;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawItem();
		drawText();
		drawImages();
	}
	
	private void drawImages(){
		float scale = 1.15F;
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 10, 0);
		GlStateManager.scale(scale, scale / 1.9F, scale);
		
		GuiBookUtilities.drawTexture(0, 0, 0, 50, 140, 180, PREVIEW);
		
		GlStateManager.popMatrix();
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 130, WRAPWIDTH, 1.0F, "If active, the Farmers Green speeds up the growth of plants in its range. "
				+ "The Range is 3 blocks on each side. "
				+ "It needs to be supplied with Fertilizer Potatoes, each potato supplies it with 12000 ticks of growth.");
		
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 130, WRAPWIDTH - 10, 1.0F, "To refuel it simply right-click it with a Fertilizer Potato. "
				+ "Shift right-click changes it from active to inactive. "
				+ "If you right-click it with an empty hand, it will tell you, how much growth is left in it.");
	}
	
	private void drawItem(){
		entityJar.drawItem(xPosBook + 60, yPosBook + 40);
	}

	@Override
	protected void createSuggestionsList() {
	}

	@Override
	protected void populateSuggestionsList() {
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
	}
	
}
