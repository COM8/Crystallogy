package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookCraftingRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPageSuggestions;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCrystalGlass extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer crystalGlass;
	
	private static final ResourceLocation CRYSTAL_GLASS_PREVIEW = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/blocks/crystal_glass_preview.png");
	
	private BookCraftingRecipe recipe;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystalGlass() {
		super("Crystal Glass:");
		
		crystalGlass = new BookMultiItemRenderer(new ItemStack[]{	new ItemStack(BlockHandler.crystalGlas, 1, 0), 
																	new ItemStack(BlockHandler.crystalGlas, 1, 1),
																	new ItemStack(BlockHandler.crystalGlas, 1, 2),
																	new ItemStack(BlockHandler.crystalGlas, 1, 3)}, 1000, 5.0F);
		initRecipe();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initRecipe(){
		BookButtonCrafting g = new BookButtonCrafting(getNextButtonId(), GuiBookUtilities.toItemStackArray(Utilities.getOresFrom("blockGlass")), 500, null);
		BookButtonCrafting d = new BookButtonCrafting(getNextButtonId(), new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																							new ItemStack(ItemHandler.crystallDust_blue), 
																							new ItemStack(ItemHandler.crystallDust_green), 
																							new ItemStack(ItemHandler.crystallDust_yellow)}, 1500, PageRegistry.CRYSTAL_DUST_PAGE);
		
		BookButtonCrafting[][] input = new BookButtonCrafting[][]{	{g, g, g},
																	{d, g, d},
																	{g, g, g}};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack[]{	new ItemStack(BlockHandler.crystalGlas, 7, 0), 
																								new ItemStack(BlockHandler.crystalGlas, 7, 1),
																								new ItemStack(BlockHandler.crystalGlas, 7, 2),
																								new ItemStack(BlockHandler.crystalGlas, 7, 3)}, 1500, null);
		recipe = new BookCraftingRecipe(input, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCrystals();
		drawText();
		drawCraftingChaptersText();
		drawImages();
		drawRecipe(mouseX, mouseY);
	}
	
	private void drawImages(){
		float scale = 0.75F;
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + BORDER_LEFT, yPosBook + 155, 0);
		GlStateManager.scale(scale, scale / 1.2F, scale);
		
		GuiBookUtilities.drawTexture(0, 0, 0, 100, 230, 115, CRYSTAL_GLASS_PREVIEW);
		
		GlStateManager.popMatrix();
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipe.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 30);
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 125, xSize / 2 - 12, 1.0F, "Crystal Glass is a glass type, created by combining any kind of Crystal Dust with glass.");
	}
	
	private void drawCrystals(){
		crystalGlass.drawItem(xPosBook + 50, yPosBook + 35);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 10, xSize / 2 - 10, "Recipe:");
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 35, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																													new ItemStack(ItemHandler.crystallDust_blue), 
																													new ItemStack(ItemHandler.crystallDust_green), 
																													new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + ySize - 120, xSize / 2 - 10, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + ySize - 105);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipe.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
