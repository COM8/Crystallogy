package de.comeight.crystallogy.gui.bookOfKnowledge.pages;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks.GuiBookBlocks;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.credits.GuiBookCredits;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.infusion.GuiBookInfusionCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.items.GuiBookItems;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.search.GuiBookSearch;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookMain extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final int ID = 4;
	
	private static final ResourceLocation LOGO = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/logo.png");
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookMain() {
		super("");
		
		GuiBookPage lastPage = PageRegistry.getCurrentPage();
		if(lastPage != null){
			openGui(null, lastPage);
		}
		
		setNextPage(new GuiBookBlocks());
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onGuiOpened() {
		//Has to stay empty
	}
	
	private void drawIntroText(){
		GuiBookUtilities.drawTextBox(xPosBook + 10, yPosBook + 40, xSize / 2 - 10, 1.0F, "Hi!\n"
				+ "Welcome to the Crystallogy Book of Knowledge.\n"
				+ "This book contains everything you need to know about this mod.\n"
				+ "\n"
				+ "On the right side you can see all the different chapters this book is capable of teaching to you.\n"
				+ "If you prefer, discovering everything by yourself, you can use the \"Arrow Button\" on the lower right side to flip through the different chapters by yourself.");
	}
	
	private void drawChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + 10, yPosBook + 10, xSize / 2 - 10, "Chapters:");
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
        drawLogo();
        drawIntroText();
        drawChaptersText();
	}
	
	@Override
	protected void addButtons() {
		super.addButtons();
		int chapterButtonX = xSize / 2 + 10;
		float buttonScale = 1.0F;
		
		//Blocks:
		BookButtonCategory blocks = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 40, null, new ItemStack[]{new ItemStack(BlockHandler.crystall_red), 
																																	new ItemStack(BlockHandler.crystall_blue),
																																	new ItemStack(BlockHandler.crystall_green),
																																	new ItemStack(BlockHandler.crystall_yellow)}, 60, new GuiBookBlocks());
		blocks.setScale(buttonScale);
		blocks.setCustomDescription("Blocks");
		buttonList.add(blocks);
		
		//Items:
		BookButtonCategory items = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 75, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																																	new ItemStack(ItemHandler.crystallDust_blue), 
																																	new ItemStack(ItemHandler.crystallDust_green), 
																																	new ItemStack(ItemHandler.crystallDust_yellow)}, 60, new GuiBookItems());
		items.setScale(buttonScale);
		items.setCustomDescription("Items");
		buttonList.add(items);
		
		//Infusion Crafting:
		BookButtonCategory infusion = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 110, null, new ItemStack(BlockHandler.infuserBlock), new GuiBookInfusionCrafting());
		infusion.setScale(buttonScale);
		infusion.setCustomDescription("Infusion Crafting");
		buttonList.add(infusion);
		
		//Search:
		BookButtonCategory search = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 145, null, new ItemStack(Items.COMPASS), new GuiBookSearch());
		search.setScale(buttonScale);
		search.setCustomDescription("Search");
		buttonList.add(search);

		//Credits:
		BookButtonCategory credits = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 180, null, new ItemStack(Items.WRITTEN_BOOK), new GuiBookCredits());
		credits.setScale(buttonScale);
		credits.setCustomDescription("Credits");
		buttonList.add(credits);
		
	}
	
	private void drawLogo(){
		GlStateManager.pushMatrix();
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		
		GlStateManager.translate(xPosBook + 10, yPosBook + 10, 0);
		GlStateManager.scale(0.43, 0.07, 1.0);
		GlStateManager.translate(-xPosBook - 10, -yPosBook - 10, 0);
		
		drawTexture(xPosBook + 10, yPosBook + 10, 255, 255, LOGO);
		
		GlStateManager.popMatrix();
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
	}
}
