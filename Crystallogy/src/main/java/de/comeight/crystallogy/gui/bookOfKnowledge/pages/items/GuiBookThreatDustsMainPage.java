package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPageSuggestions;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookThreatDustsMainPage extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer itemRenderer;
	
	private static final ResourceLocation PREVIEW = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/items/threat_dust_preview.png");
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookThreatDustsMainPage() {
		super("Threat Dusts:");
		
		itemRenderer = new BookMultiItemRenderer(new ItemStack[]{	new ItemStack(ItemHandler.badLuckDust), 
																	new ItemStack(ItemHandler.blindDust), 
																	new ItemStack(ItemHandler.damDust), 
																	new ItemStack(ItemHandler.drowDust), 
																	new ItemStack(ItemHandler.enderDust), 
																	new ItemStack(ItemHandler.fireDust), 
																	new ItemStack(ItemHandler.glowDust), 
																	new ItemStack(ItemHandler.hungDust), 
																	new ItemStack(ItemHandler.levDust),
																	new ItemStack(ItemHandler.poisDust)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.THREAT_DUSTS_AI_DUST_PAGE;
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
		float scale = 1.05F;
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, 0);
		GlStateManager.scale(scale, scale / 1.6F, scale);
		
		GuiBookUtilities.drawTexture(0, 0, 60, 50, 150, 200, PREVIEW);
		
		GlStateManager.popMatrix();
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 120, WRAPWIDTH, 1.0F, "Threat Dusts are dusts which can be apply to an Entity Jar or a Player Jar by right-clicking the jar. "
				+ "If you eat them, they apply the effect to yourself.");
	}
	
	private void drawItem(){
		itemRenderer.drawItem(xPosBook + 50, yPosBook + 30);
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 70, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.aiRemoverDust), PageRegistry.THREAT_DUSTS_AI_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.badLuckDust), PageRegistry.THREAT_DUSTS_BAD_LUCK_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.blindDust), PageRegistry.THREAT_DUSTS_BLIND_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.damDust), PageRegistry.THREAT_DUSTS_DAM_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.drowDust), PageRegistry.THREAT_DUSTS_DROW_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.enderDust), PageRegistry.THREAT_DUSTS_ENDER_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.fireDust), PageRegistry.THREAT_DUSTS_FIRE_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.glowDust), PageRegistry.THREAT_DUSTS_GLOW_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.hungDust), PageRegistry.THREAT_DUSTS_HUNG_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.levDust), PageRegistry.THREAT_DUSTS_LEV_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.poisDust), PageRegistry.THREAT_DUSTS_POIS_DUST_PAGE));
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 110, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 95);
	}
	
}
