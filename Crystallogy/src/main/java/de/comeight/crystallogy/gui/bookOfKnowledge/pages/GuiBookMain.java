package de.comeight.crystallogy.gui.bookOfKnowledge.pages;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
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
	private static final ResourceLocation LOGO = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/logo.png");
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookMain() {
		super("");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.BLOCKS_PAGE;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onGuiOpened() {
		super.onGuiOpened();
	}
	
	private void drawIntroText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 50, WRAPWIDTH, 1.0F, "Hi " + mc.thePlayer.getName() + "!\n"
				+ "\n"
				+ "Welcome to the Crystallogy Book of Knowledge.\n"
				+ "This book contains everything you need to know about this mod.\n"
				+ "\n"
				+ "On the right side you can see all the different chapters this book is capable of teaching to you.\n"
				+ "If you prefer, discovering everything by yourself, you can use the \"Arrow Buttons\" on the lower right side to flip through the different chapters by yourself.");
	}
	
	private void drawChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, xSize / 2 - 10, "Chapters:");
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
		int chapterButtonX = xSize / 2 + BORDER_RIGHT;
		float buttonScale = 1.0F;
		
		//Blocks:
		BookButtonCategory blocks = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 40, null, new ItemStack[]{new ItemStack(BlockHandler.crystall_red), 
																																	new ItemStack(BlockHandler.crystall_blue),
																																	new ItemStack(BlockHandler.crystall_green),
																																	new ItemStack(BlockHandler.crystall_yellow)}, 1000, PageRegistry.BLOCKS_PAGE);
		blocks.setScale(buttonScale);
		blocks.setCustomDescription("Blocks");
		buttonList.add(blocks);
		
		//Items:
		BookButtonCategory items = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 75, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																																	new ItemStack(ItemHandler.crystallDust_blue), 
																																	new ItemStack(ItemHandler.crystallDust_green), 
																																	new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.ITEMS_PAGE);
		items.setScale(buttonScale);
		items.setCustomDescription("Items");
		buttonList.add(items);
		
		//Infusion Crafting:
		BookButtonCategory infusion = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 110, null, new ItemStack(BlockHandler.infuserBlock), PageRegistry.INFUSION_CRAFTING_PAGE);
		infusion.setScale(buttonScale);
		infusion.setCustomDescription("Infusion Crafting");
		buttonList.add(infusion);
		
		//Search:
		BookButtonCategory search = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 145, null, new ItemStack(Items.COMPASS), PageRegistry.SEARCH_PAGE);
		search.setScale(buttonScale);
		search.setCustomDescription("Search");
		buttonList.add(search);

		//Credits:
		BookButtonCategory credits = new BookButtonCategory(GuiBookPage.getNextButtonId(), chapterButtonX, 180, null, new ItemStack(Items.WRITTEN_BOOK), PageRegistry.CREDITS_PAGE);
		credits.setScale(buttonScale);
		credits.setCustomDescription("Credits");
		buttonList.add(credits);
		
	}
	
	private void drawLogo(){
		GlStateManager.pushMatrix();
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		
		GlStateManager.translate(xPosBook + BORDER_LEFT, yPosBook + 10, 0);
		GlStateManager.scale(0.66, 0.12, 1.0);
		GlStateManager.translate(-xPosBook - BORDER_LEFT, -yPosBook - 10, 0);
		
		drawTexture(xPosBook + BORDER_LEFT, yPosBook + 10, 255, 255, LOGO);
		
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
