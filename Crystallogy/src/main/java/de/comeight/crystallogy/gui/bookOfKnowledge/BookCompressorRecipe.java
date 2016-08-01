package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookCompressorRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookButtonCrafting input;
	private BookButtonCrafting output;
	
	private int progress;
	private long lastInc;
	
	private int x;
	private int y;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookCompressorRecipe(BookButtonCrafting input, BookButtonCrafting output) {
		this.input = input;
		this.output = output;
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
		input.drawButton(mouseX, mouseY, x, y);
		output.drawButton(mouseX, mouseY, x + 70, y);
	}
	
	private void drawProgress(int mouseX, int mouseY){
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(x + 33, y + 3, 0);
		GlStateManager.scale(2.5F, 2.5F, 2.5F);
		
		GuiBookUtilities.drawTexture(0, 0, 0, 0, progress, 15, GuiBookPage.GUI_ELEMENTS);
		
		GlStateManager.popMatrix();
		
		incProgress();
	}
	
	private void incProgress(){
		if(lastInc + 200 < System.currentTimeMillis()){
			progress++;
			lastInc = System.currentTimeMillis();
			
			if(progress > 11){
				progress = 0;
			}
		}
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state, GuiBookPage fromPage) {
		if(input.hover){
			input.onClicked(fromPage);
		}
		else if(output.hover){
			output.onClicked(fromPage);
		}
	}
	
}
