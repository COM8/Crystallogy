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
public class GuiBookEntityJar1 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer entityJar;
	
	private static final ResourceLocation PREVIEW = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/blocks/entity_jar_preview.png");
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookEntityJar1() {
		super("Entity Jar:");
		
		entityJar = new BookMultiItemRenderer(new ItemStack[]{new ItemStack(BlockHandler.entityJar)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.ENTITY_JAR_PAGE_2;
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
		float scale = 0.7F;
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, 0);
		GlStateManager.scale(scale, scale / 1.75F, scale);
		
		GuiBookUtilities.drawTexture(0, 0, 20, 50, 230, 230, PREVIEW);
		
		GlStateManager.popMatrix();
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 110, WRAPWIDTH, 1.0F, "An Entity Jar is used to \"store\" the tags from entities. "
				+ "To give it a tag you need to shift right-click on it with an Entity Crystal Knife. "
				+ "The entity only stays stored until the chuck, the entity is in, gets unloaded.");
		
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 110, WRAPWIDTH - 10, 1.0F, "Once you managed to get an entity's tag you can use Threat Dusts to \"play\" with it.");
	}
	
	private void drawItem(){
		entityJar.drawItem(xPosBook + 60, yPosBook + 20);
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
