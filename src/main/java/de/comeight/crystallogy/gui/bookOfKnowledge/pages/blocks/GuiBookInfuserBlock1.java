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
public class GuiBookInfuserBlock1 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer crystalGlass;
	
	private static final ResourceLocation CRYSTAL_GLASS_PREVIEW = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/blocks/infuser_block_preview.png");
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookInfuserBlock1() {
		super("Infuser Block:");
		
		crystalGlass = new BookMultiItemRenderer(new ItemStack[]{new ItemStack(BlockHandler.infuserBlock)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.INFUSRER_BLOCK_PAGE_2;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawItem();
		drawUsageChaptersText();
		drawText();
		drawImages();
	}
	
	private void drawImages(){
		float scale = 0.7F;
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 90, 0);
		GlStateManager.scale(scale, scale / 1.2F, scale);
		
		GuiBookUtilities.drawTexture(0, 0, 20, 0, 230, 230, CRYSTAL_GLASS_PREVIEW);
		
		GlStateManager.popMatrix();
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 125, WRAPWIDTH, 1.0F, "The Infuser Block is used for infusing items. It is used to build the Infusion Structure.");
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 30, WRAPWIDTH - 10, 1.0F, "Place one down and add one on each side (north, south, ...) but leave one block space between them. If you did it right you can see a particle effect above the center Infuser Block.");
	}
	
	private void drawItem(){
		crystalGlass.drawItem(xPosBook + 50, yPosBook + 35);
	}
	
	private void drawUsageChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, WRAPWIDTH, "Usage:");
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
