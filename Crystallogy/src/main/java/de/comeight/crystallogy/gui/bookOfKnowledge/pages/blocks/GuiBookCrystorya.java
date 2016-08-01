package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPageSuggestions;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCrystorya extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer crystorya;
	
	private static final ResourceLocation PREVIEW = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/blocks/crystorya_preview.png");
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystorya() {
		super("Crystorya:");
		
		crystorya = new BookMultiItemRenderer(new ItemStack[]{new ItemStack(BlockHandler.crystorya)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawItem();
		drawText();
		drawImages();
	}
	
	private void drawImages(){
		float scale = 0.65F;
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + BORDER_LEFT, yPosBook + 155, 0);
		GlStateManager.scale(scale, scale / 1.5F, scale);
		
		drawTexture(0, 0, 0, 35, 250, 170, PREVIEW);
		
		GlStateManager.popMatrix();
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 105, WRAPWIDTH, 1.0F, "Crystorya is a Plant which grows everywhere where you can find Grass Blocks.\n"
				+ "It's used for crafting Fertilizer Potatoes.");
	}
	
	private void drawItem(){
		crystorya.drawItem(xPosBook + 60, yPosBook + 20);
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 35, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.fertilizerPotato), PageRegistry.FERTILIZER_POTATO_PAGE));
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + 20);
	}
	
}
