package de.comeight.crystallogy.gui.bookOfKnowledge.pages.infusion;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookInfusionCrafting1 extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static final ResourceLocation PREVIEW = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/blocks/infuser_block_preview.png");
	
	private BookInfusionRecipe exampleRecipe;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookInfusionCrafting1() {
		super("Infusion Crafting:");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.INFUSION_CRAFTING_PAGE_2;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawImages();
		drawText();
	}
	
	private void drawImages(){
		float scale = 0.7F;
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, 0);
		GlStateManager.scale(scale, scale / 1.2F, scale);
		
		GuiBookUtilities.drawTexture(0, 0, 20, 0, 230, 230, PREVIEW);
		
		GlStateManager.popMatrix();
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP + 20, WRAPWIDTH, 1.0F, "Infusion Crafting is a process where you infuse items with other items.");
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP + 60, WRAPWIDTH, "The Structure:");
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP + 75, WRAPWIDTH - 10, 1.0F, "Place one Infuser Block down and add one on each side (north, south, ...) but leave one block space between them.\n"
				+ "If you did it right you can see a particle effect above the center Infuser Block.\n"
				+ "On the right you can see a working example.");
	}
	
}
