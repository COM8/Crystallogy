package de.comeight.crystallogy.gui.bookOfKnowledge.Pages;

import org.lwjgl.opengl.GL11;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.bookOfKnowledge.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.ScrollBarList;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookMain extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final int ID = 4;
	
	private static final ResourceLocation LOGO = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/logo.png");
	
	private ScrollBarList scrollingList; 
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookMain() {
		super("");
		
		GuiBookPage lastPage = PageRegistry.getCurrentPage();
		if(lastPage != null){
			openGui(null, lastPage);
		}
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onGuiOpened() {
		
	}
	
	protected void openGui(GuiBookPage fromPage, GuiBookPage toPage){
		PageRegistry.addCourse(fromPage);
		mc.displayGuiScreen(toPage);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
        drawLogo();
        
        scrollingList.drawScreen(mouseX, mouseY, xPosBook + 10 + xSize / 2, yPosBook + 50);
        
	}
	
	private void drawLogo(){
		GlStateManager.pushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);
		
		GlStateManager.translate(xPosBook + 10, yPosBook + 10, 0);
		GlStateManager.scale(0.43, 0.07, 1.0);
		GlStateManager.translate(-xPosBook - 10, -yPosBook - 10, 0);
		mc.getTextureManager().bindTexture(LOGO);
		drawTexturedModalRect(xPosBook + 10, yPosBook + 10, 0, 0, 255, 255);
		
		GlStateManager.popMatrix();
	}
	
	@Override
	protected void addButtons() {
		super.addButtons();
		
		addScrollingList();
	}
	
	private void addScrollingList(){
		scrollingList = new ScrollBarList(xSize / 2 - 20, 175, xPosBook + 10, yPosBook + 50, this);
		
		//Blocks:
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.armorCombiner), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.charger), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.compressor), null));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(BlockHandler.crystall_red), 
																										new ItemStack(BlockHandler.crystall_blue),
																										new ItemStack(BlockHandler.crystall_green),
																										new ItemStack(BlockHandler.crystall_yellow)}, 60, new GuiBookCrystals()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(BlockHandler.crystalGlas, 1, 0),
																										new ItemStack(BlockHandler.crystalGlas, 1, 1),
																										new ItemStack(BlockHandler.crystalGlas, 1, 2),
																										new ItemStack(BlockHandler.crystalGlas, 1, 3),}, 60, new GuiBookCrystals()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.crystallCrusher), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.crystalLight), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.crystalOfHolding), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.crystorya), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.entityJar), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.farmersGreen), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.fireCrystall), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.infuserBlock), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.machineBlock), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.playerJar), null));
		
		
		
		//Items:
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.entityGrabber), null));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																										new ItemStack(ItemHandler.crystallDust_blue), 
																										new ItemStack(ItemHandler.crystallDust_green), 
																										new ItemStack(ItemHandler.crystallDust_yellow)}, 60, new GuiBookCrystalDusts()));

		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_blue), 
																										new ItemStack(ItemHandler.armorChestplate_blue), 
																										new ItemStack(ItemHandler.armorLeggins_blue), 
																										new ItemStack(ItemHandler.armorBoots_blue)}, 60, new GuiBookCrystalDusts()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_red), 
																										new ItemStack(ItemHandler.armorChestplate_red), 
																										new ItemStack(ItemHandler.armorLeggins_red), 
																										new ItemStack(ItemHandler.armorBoots_red)}, 60, new GuiBookCrystalDusts()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_green), 
																										new ItemStack(ItemHandler.armorChestplate_green), 
																										new ItemStack(ItemHandler.armorLeggins_green), 
																										new ItemStack(ItemHandler.armorBoots_green)}, 60, new GuiBookCrystalDusts()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_yellow), 
																										new ItemStack(ItemHandler.armorChestplate_yellow), 
																										new ItemStack(ItemHandler.armorLeggins_yellow), 
																										new ItemStack(ItemHandler.armorBoots_yellow)}, 60, new GuiBookCrystalDusts()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_combined), 
																										new ItemStack(ItemHandler.armorChestplate_combined), 
																										new ItemStack(ItemHandler.armorLeggins_combined), 
																										new ItemStack(ItemHandler.armorBoots_combined)}, 60, new GuiBookCrystalDusts()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorHelmet_hunter), 
																										new ItemStack(ItemHandler.armorChestplate_hunter), 
																										new ItemStack(ItemHandler.armorLeggins_hunter), 
																										new ItemStack(ItemHandler.armorBoots_hunter)}, 60, new GuiBookCrystalDusts()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.armorCatalys), null));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorPlate, 1, 0),
																										new ItemStack(ItemHandler.armorPlate, 1, 1),
																										new ItemStack(ItemHandler.armorPlate, 1, 2),
																										new ItemStack(ItemHandler.armorPlate, 1, 3),
																										new ItemStack(ItemHandler.armorPlate, 1, 4),}, 60, new GuiBookCrystals()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.badLuckDust), 
																										new ItemStack(ItemHandler.blindDust), 
																										new ItemStack(ItemHandler.damDust), 
																										new ItemStack(ItemHandler.drowDust), 
																										new ItemStack(ItemHandler.enderDust), 
																										new ItemStack(ItemHandler.fireDust), 
																										new ItemStack(ItemHandler.glowDust), 
																										new ItemStack(ItemHandler.hungDust), 
																										new ItemStack(ItemHandler.levDust), 
																										new ItemStack(ItemHandler.poisDust)}, 60, new GuiBookCrystalDusts()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.chargedCombinedArmorMesh), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.combinedArmorCompound), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.combinedArmorMesh), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.crystalKnifeBlade), null));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallHammer_red), 
																										new ItemStack(ItemHandler.crystallHammer_blue), 
																										new ItemStack(ItemHandler.crystallHammer_green), 
																										new ItemStack(ItemHandler.crystallHammer_yellow)}, 60, new GuiBookCrystalDusts()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallHammerHead, 1, 0),
																										new ItemStack(ItemHandler.crystallHammerHead, 1, 1),
																										new ItemStack(ItemHandler.crystallHammerHead, 1, 2),
																										new ItemStack(ItemHandler.crystallHammerHead, 1, 3),}, 60, new GuiBookCrystals()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallSwordBlade, 1, 0),
																										new ItemStack(ItemHandler.crystallSwordBlade, 1, 1),
																										new ItemStack(ItemHandler.crystallSwordBlade, 1, 2),
																										new ItemStack(ItemHandler.crystallSwordBlade, 1, 3),}, 60, new GuiBookCrystals()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalSword_red), 
																										new ItemStack(ItemHandler.crystalSword_blue), 
																										new ItemStack(ItemHandler.crystalSword_green), 
																										new ItemStack(ItemHandler.crystalSword_yellow)}, 60, new GuiBookCrystalDusts()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalPickaxe_red), 
																										new ItemStack(ItemHandler.crystalPickaxe_blue), 
																										new ItemStack(ItemHandler.crystalPickaxe_green), 
																										new ItemStack(ItemHandler.crystalPickaxe_yellow)}, 60, new GuiBookCrystalDusts()));
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalPickaxeHead, 1, 0),
																										new ItemStack(ItemHandler.crystalPickaxeHead, 1, 1),
																										new ItemStack(ItemHandler.crystalPickaxeHead, 1, 2),
																										new ItemStack(ItemHandler.crystalPickaxeHead, 1, 3),}, 60, new GuiBookCrystals()));
		
		
		
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.enderonCrystal), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.energyCrystal), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.energyDust), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.fireDust), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.playerCrystalKnife), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.pureCrystallDust), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.toolRod), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.vaporizer), null));
		scrollingList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.vaporizerDirection), null));
																								
	}
	
	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		scrollingList.mouseReleased(mouseX, mouseY);
	}
	
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
		scrollingList.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
	}
}
