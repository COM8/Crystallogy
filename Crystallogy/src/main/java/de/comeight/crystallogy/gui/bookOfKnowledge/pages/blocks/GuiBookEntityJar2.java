package de.comeight.crystallogy.gui.bookOfKnowledge.pages.blocks;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPageSuggestions;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class GuiBookEntityJar2 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookInfusionRecipe recipe;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookEntityJar2() {
		super("");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		initRecipe();
	}
	
	private void initRecipe(){
		BookButtonCrafting b = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.GLASS_BOTTLE), null);
		b.disableFrame();
		BookButtonCrafting bP = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.BLAZE_POWDER), null);
		bP.disableFrame();
		BookButtonCrafting e = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.ENDER_EYE), null);
		e.disableFrame();
		BookButtonCrafting p = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.pureCrystallDust), PageRegistry.PURE_CRYSTAL_DUST_PAGE);
		p.disableFrame();
		
		BookButtonCrafting[] input = new BookButtonCrafting[]{b, bP, e, p, p};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.entityJar), null);
		output.disableFrame();
		
		recipe = new BookInfusionRecipe(input, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawCraftingChaptersText();
		drawRecipe(mouseX, mouseY);
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipe.drawScreen(mouseX, mouseY, xPosBook + 55, yPosBook + 25);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP, WRAPWIDTH, "Recipe:");
	}

	@Override
	protected void createSuggestionsList() {
		suggestionsList = new ScrollBarList(xSize / 2 - 25, 175, 0, 0, this);
	}

	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.pureCrystallDust), PageRegistry.PURE_CRYSTAL_DUST_PAGE));
		BookButtonCategory infusionButton = new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.infuserBlock), PageRegistry.INFUSION_CRAFTING_PAGE_1);
		infusionButton.setCustomDescription("Infusion Crafting");
		suggestionsList.addEntry(infusionButton);
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.entityCrystalKnife), PageRegistry.ENTITY_CRYSTAL_KNIFE_PAGE_1));
		BookButtonCategory treatDusts = new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.badLuckDust), 
																												new ItemStack(ItemHandler.blindDust), 
																												new ItemStack(ItemHandler.damDust), 
																												new ItemStack(ItemHandler.drowDust), 
																												new ItemStack(ItemHandler.enderDust), 
																												new ItemStack(ItemHandler.fireDust), 
																												new ItemStack(ItemHandler.glowDust), 
																												new ItemStack(ItemHandler.hungDust), 
																												new ItemStack(ItemHandler.levDust), 
																												new ItemStack(ItemHandler.poisDust)}, 1000, PageRegistry.THREAT_DUSTS_PAGE_1);
		treatDusts.setCustomDescription("Threat Dusts");
		suggestionsList.addEntry(treatDusts);
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.playerJar), PageRegistry.PLAYER_JAR_PAGE_1));
	}

	@Override
	protected void drawSuggestionsList(int mouseX, int mouseY) {
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + BORDER_TOP, WRAPWIDTH, "Suggestions:");
		suggestionsList.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_RIGHT - 5, yPosBook + 20);
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipe.mouseReleased(mouseX, mouseY, state, this);
	}
	
}
