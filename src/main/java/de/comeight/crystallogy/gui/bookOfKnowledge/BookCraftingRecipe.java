package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.util.LinkedList;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookCraftingRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookButtonCrafting output;
	private BookButtonCrafting[][] input;
	private BookButtonCrafting hoverButton;
	
	private int x;
	private int y;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookCraftingRecipe(BookButtonCrafting[][] input, BookButtonCrafting output) {
		this.input = input;
		this.output = output;
		this.hoverButton = null;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void drawScreen(int mouseX, int mouseY, int x, int y){
		this.x = x;
		this.y = y;
		this.hoverButton = null;
		
		drawArrow();
		drawInput(mouseX, mouseY);
		drawOutput(mouseX, mouseY);
	}
	
	private void drawArrow(){
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(x + 80, y + 28, 0);
		GlStateManager.scale(2.5F, 2.5F, 2.5F);
		
		GuiBookUtilities.drawTexture(0, 0, 0, 0, 11, 15, GuiBookPage.GUI_ELEMENTS);
		
		GlStateManager.popMatrix();
	}
	
	private void drawInput(int mouseX, int mouseY){
		for(int i = 0; i < input.length; i++){
			for(int e = 0; e < input[i].length; e++){
				input[e][i].drawButton(mouseX, mouseY, x + i * 25, y + e * 25);
				if(input[e][i].hover){
					hoverButton = input[e][i];
				}
			}
		}
	}
	
	private void drawOutput(int mouseX, int mouseY){
		output.drawButton(mouseX, mouseY, x + 115, y + 25);
		if(output.hover){
			hoverButton = output;
		}
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state, GuiBookPage fromPage) {
		if(hoverButton != null){
			hoverButton.onClicked(fromPage);
		}
	}
	
	private boolean checkIfChecked(LinkedList<BookButtonCrafting> list, BookButtonCrafting button){
		for (BookButtonCrafting bookButtonCrafting : list) {
			if(bookButtonCrafting == button){
				return true;
			}
		}
		return false;
	}
	
}
