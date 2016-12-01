package de.comeight.crystallogy.gui.bookOfKnowledge.pages.mechanisms;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.TextScrollBarList;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCraftingInfusion;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPageSuggestions;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.handler.RecipeHandler;
import de.comeight.crystallogy.items.EnergyCrystal;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipeEntityBrainFollowPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCustomEntityAi1 extends GuiBookPageSuggestions {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookInfusionRecipe recipe1;
	private BookInfusionRecipe recipe2;
	
	private TextScrollBarList tbx1;
	private static final ResourceLocation PREVIEW = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/book/mechanisms/custom_ai_preview.png");
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCustomEntityAi1() {
		super("Custom Entity Ai:");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.CUSTOM_ENTITY_AI_PAGE_2;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void addButtons() {
		super.addButtons();
		initRecipe();
	}
	
	@Override
	protected void addGuiElements() {
		super.addGuiElements();
		initTextBoxes();
		addGuiElement(tbx1);
	}
	
	protected void initTextBoxes(){
		tbx1 = new TextScrollBarList(WRAPWIDTH, 80, xPosBook + BORDER_LEFT, yPosBook + 140, this);
		tbx1.setText("Let your worst enemies alongside with your best pets work hand in hand for your personal benefit.\n"
				+ "\n"
				+ ""
				+ "Currently there exist four different Ais:\n"
				+ "-The Follow Player Ai:\n"
				+ "It allows you to manipulate entities to keep following you all the time. "
				+ "Basically like a cat or a dog follows you.\n"
				+ "\n"
				+ "-The Move To Pos Ai:\n"
				+ "It allows you to say, to an entity, where it has to go to.\n"
				+ "\n"
				+ "-The Quarry Ai:\n"
				+ "You can use your entity to go mining for you. "
				+ "It mines a big hole from the top to the bottom and dropps the items on the ground. "
				+ "An example can be seen in the picture at the top.\n"
				+ "\n"
				+ "-The Collect Items Ai:\n"
				+ "This Ai allows an entity to collect dropped items for you and it also puts them in a container you specified before.");
	}
	
	private void initRecipe(){
		BookButtonCraftingInfusion b = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(ItemHandler.entityBrain, 1, 0), PageRegistry.ENTITY_BRAIN_PAGE);
		BookButtonCraftingInfusion k = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(ItemHandler.playerCrystalKnife), PageRegistry.PLAYER_CRYSTAL_KNIFE_PAGE_1);
		BookButtonCraftingInfusion p = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(ItemHandler.pureCrystallDust), PageRegistry.PURE_CRYSTAL_DUST_PAGE);
		BookButtonCraftingInfusion e = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(ItemHandler.energyCrystal, 1, 0), PageRegistry.ENERGY_CRYSTAL_PAGE);
		
		BookButtonCraftingInfusion s = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(ItemHandler.spotPicker), PageRegistry.SPOT_PICKER_PAGE);
		BookButtonCraftingInfusion l = new BookButtonCraftingInfusion(getNextButtonId(), new ItemStack(Items.LEAD), null);
		
		BookButtonCraftingInfusion[] input = new BookButtonCraftingInfusion[]{b, k, p, p, e};
		
		BookButtonCraftingInfusion output = new BookButtonCraftingInfusion(getNextButtonId(), RecipeHandler.infusionRecipeEntityBrainMoveToPos.getOutputJEI().get(0), null);
		output.disableFrame();
		
		recipe1 = new BookInfusionRecipe(input, output);
		
		input = new BookButtonCraftingInfusion[]{b, s, p, p, l};
		output = new BookButtonCraftingInfusion(getNextButtonId(), RecipeHandler.infusionRecipeEntityBrainFollowPlayer.getOutputJEI().get(0), null);
		recipe2 = new BookInfusionRecipe(input, output);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawText(mouseX, mouseY);
		drawCraftingChaptersText();
		drawRecipe(mouseX, mouseY);
		drawImage();
	}
	
	private void drawImage(){
		float scale = 0.685F;
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP + 20, 0);
		GlStateManager.scale(scale, scale / 1.6F, scale);
		
		GuiBookUtilities.drawTexture(0, 0, 0, 0, 250, 250, PREVIEW);
		
		GlStateManager.popMatrix();
	}
	
	private void drawRecipe(int mouseX, int mouseY){
		recipe1.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_LEFT + 30, yPosBook + 20);
		recipe2.drawScreen(mouseX, mouseY, xPosBook + xSize / 2 + BORDER_LEFT + 30, yPosBook + 135);
	}
	
	private void drawText(int mouseX, int mouseY){
		tbx1.drawScreen(mouseX, mouseY, xPosBook + BORDER_LEFT, yPosBook + 140);
	}
	
	private void drawCraftingChaptersText(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize / 2 + BORDER_RIGHT, yPosBook + 10, WRAPWIDTH, "Recipes:");
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		recipe1.mouseReleased(mouseX, mouseY, state, this);
		recipe2.mouseReleased(mouseX, mouseY, state, this);
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
