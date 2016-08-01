package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiBookPlayerCrystalKnife1 extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer renderer;
	
	private static final ResourceLocation PREVIEW = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/items/player_crystal_knife_preview.png");
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookPlayerCrystalKnife1() {
		super("Player Crystal Knife:");
		
		renderer = new BookMultiItemRenderer(new ItemStack[]{new ItemStack(ItemHandler.playerCrystalKnife)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.PLAYER_CRYSTAL_KNIFE_PAGE_2;
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
		float scale = 0.65F;
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, 0);
		GlStateManager.scale(scale, scale / 2.0F, scale);
		
		GuiBookUtilities.drawTexture(0, 0, 0, 0, 250, 250, PREVIEW);
		
		GlStateManager.popMatrix();
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 110, WRAPWIDTH, 1.0F, "The Player Crystal Knife is used to get a tag from a player by attacking it with the knife.\n"
				+ "Once you got a tag the knife will glow. "
				+ "Now you can shift right-click on a Player Jar to move the tag to it.\n"
				+ "If you shift right-click in the air you can remove the tag from your knife.\n"
				+ "\n"
				+ "To get a tag from yourself just right-click it.");
	}
	
	private void drawItem(){
		renderer.drawItem(xPosBook + 50, yPosBook + 30);
	}
	
}
