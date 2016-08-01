package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookChargerRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookButtonCrafting energyCrystalEmpty;
	private BookButtonCrafting energyCrystalFull;
	private BookButtonCrafting fireCrystal;
	
	private int progress;
	private long lastInc;
	
	private int x;
	private int y;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookChargerRecipe() {
		energyCrystalEmpty = new BookButtonCrafting(GuiBookUtilities.getNextButtonId(), new ItemStack(ItemHandler.energyCrystal, 1, ItemHandler.energyCrystal.getMaxDamage()), PageRegistry.ENERGY_CRYSTAL_PAGE);
		energyCrystalFull  = new BookButtonCrafting(GuiBookUtilities.getNextButtonId(), new ItemStack(ItemHandler.energyCrystal), PageRegistry.ENERGY_CRYSTAL_PAGE);
		fireCrystal = new BookButtonCrafting(GuiBookUtilities.getNextButtonId(), new ItemStack(BlockHandler.fireCrystall), PageRegistry.FIRE_CRYSTAL_PAGE);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void drawScreen(int mouseX, int mouseY, int x, int y){
		this.x = x;
		this.y = y;
		
		drawSlots(mouseX, mouseY);
		drawProgress(mouseX, mouseY);
	}
	
	private void drawSlots(int mouseX, int mouseY){
		energyCrystalEmpty.drawButton(mouseX, mouseY, x, y);
		fireCrystal.drawButton(mouseX, mouseY, x, y + 35);
		energyCrystalFull.drawButton(mouseX, mouseY, x + 70, y + 17);
	}
	
	private void drawProgress(int mouseX, int mouseY){
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(x + 33, y + 20, 0);
		GlStateManager.scale(2.5F, 2.5F, 2.5F);
		
		GuiBookUtilities.drawTexture(0, 0, 0, 0, progress, 15, GuiBookPage.GUI_ELEMENTS);
		
		GlStateManager.popMatrix();
		
		incProgress();
	}
	
	private void incProgress(){
		if(lastInc + 100 < System.currentTimeMillis()){
			progress++;
			lastInc = System.currentTimeMillis();
			
			if(progress > 11){
				progress = 0;
			}
		}
		
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state, GuiBookPage fromPage) {
		if(energyCrystalEmpty.hover){
			energyCrystalEmpty.onClicked(fromPage);
		}
		else if(energyCrystalFull.hover){
			energyCrystalFull.onClicked(fromPage);
		}
		else if(fireCrystal.hover){
			fireCrystal.onClicked(fromPage);
		}
	}
	
}
