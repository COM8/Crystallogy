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
public class GuiBookCrystalOfHolding1 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer crystalOfHolding;
	
	private static final ResourceLocation PREVIEW = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/blocks/crystal_of_holding_preview.png");
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystalOfHolding1() {
		super("Crystal of Holding:");
		
		crystalOfHolding = new BookMultiItemRenderer(new ItemStack[]{new ItemStack(BlockHandler.crystalOfHolding)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.CRYSTAL_OF_HOLDING_PAGE_2;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCrystals();
		drawText();
		drawImages();
	}
	
	private void drawImages(){
		float scale = 0.62F;
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 70, 0);
		GlStateManager.scale(scale, scale / 2.5F, scale);
		
		GuiBookUtilities.drawTexture(0, 0, 0, 0, 260, 250, PREVIEW);
		
		GlStateManager.popMatrix();
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 120, WRAPWIDTH, 1.0F, "The Crystal of Holding is used to store entities. "
				+ "Once you caught one, using an Entity Grabber, you can release it again by shift right-clicking on a block. "
				+ "You also are able to plant this crystal on Sandstone.");
		
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 10, WRAPWIDTH - 10, 1.0F, "If you planted it and it's fully grown, it will spread but only if there is Sandstone around it. "
				+ "This is a way to duplicate the caught entity.");
		
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 150, WRAPWIDTH - 10, 1.0F, "This image shows the three growth stages of the crystal. Once it reached the third stage it will begin to spread.");
	}
	
	private void drawCrystals(){
		crystalOfHolding.drawItem(xPosBook + 60, yPosBook + 35);
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
