package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.gui.GuiArmorCombiner;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.items.armor.Armor_combined;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookArmorCombinerRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookButtonCrafting armor;
	private BookButtonCrafting armorCombined;
	private BookButtonCrafting catalyst;
	private int progress;
	private long lastInc;
	
	private int x;
	private int y;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookArmorCombinerRecipe(ItemStack armor, ItemStack armorCombined) {
		if(armor.getItem() instanceof ItemArmor){
			if(armorCombined.getItem() instanceof Armor_combined){
				this.x = 0;
				this.y = 0;
				this.progress = 0;
				this.lastInc = System.currentTimeMillis();
				this.armor = new BookButtonCrafting(GuiBookUtilities.getNextButtonId(), armor, null);
				this.armorCombined = new BookButtonCrafting(GuiBookUtilities.getNextButtonId(), armorCombined, PageRegistry.ARMOR_COMBINED_PAGE);
				
				int numCatalyst = 0;
				
				if(armorCombined.getItem() == ItemHandler.armorBoots_combined || armorCombined.getItem() == ItemHandler.armorHelmet_combined){
					numCatalyst = 1;
				}
				else{
					numCatalyst = 2;
				}
				this.catalyst = new BookButtonCrafting(GuiBookUtilities.getNextButtonId(), new ItemStack(ItemHandler.armorCatalys, numCatalyst), PageRegistry.ARMOR_CATALYST_PAGE);
				return;
			}
		}
		try {
			throw new Exception("Invalid arguments!");
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		armor.drawButton(mouseX, mouseY, x, y);
		armorCombined.drawButton(mouseX, mouseY, x + 140, y);
		catalyst.drawButton(mouseX, mouseY, x + 70, y + 35);
	}
	
	private void drawProgress(int mouseX, int mouseY){
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(x + 69, y, 0);
		GlStateManager.scale(2.0F, 2.0F, 2.0F);
		
		GuiBookUtilities.drawTexture(0, 0, 176, 0, progress, 13, GuiArmorCombiner.rL);
		
		GlStateManager.popMatrix();
		
		incProgress();
	}
	
	private void incProgress(){
		if(lastInc + 100 < System.currentTimeMillis()){
			progress++;
			lastInc = System.currentTimeMillis();
			
			if(progress > 14){
				progress = 0;
			}
		}
		
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state, GuiBookPage fromPage) {
		if(armorCombined.hover){
			armorCombined.onClicked(fromPage);
		}
		else if(catalyst.hover){
			catalyst.onClicked(fromPage);
		}
	}
	
}
