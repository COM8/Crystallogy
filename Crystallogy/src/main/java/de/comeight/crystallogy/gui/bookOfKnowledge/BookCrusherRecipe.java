package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.gui.GuiCompressor;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookCrusherRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookButtonCrafting input;
	private BookButtonCrafting output;
	
	private int progress;
	private long lastInc;
	
	private int x;
	private int y;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookCrusherRecipe(BookButtonCrafting input, BookButtonCrafting output) {
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
		
		GlStateManager.translate(x + 69, y, 0);
		GlStateManager.scale(2.0F, 2.0F, 2.0F);
		
		GuiBookUtilities.drawTexture(0, 0, 177, 14, progress, 30, GuiCompressor.rL);
		
		GlStateManager.popMatrix();
		
		incProgress();
	}
	
	private void incProgress(){
		if(lastInc + 100 < System.currentTimeMillis()){
			progress++;
			lastInc = System.currentTimeMillis();
			
			if(progress > 15){
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
