package de.comeight.crystallogy.gui.bookOfKnowledge;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookMultiRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookCraftingRecipe[] recipes;
	
	private long lastChgange;
	private int frame;
	private final int minFrameDuration = 1000; 
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookMultiRecipe(BookCraftingRecipe[] recipes) {
		this.recipes = recipes;
		
		this.lastChgange = System.currentTimeMillis();
		this.frame = 0;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void drawRecipe(int mouseX, int mouseY, int x, int y){
		recipes[frame].drawScreen(mouseX, mouseY, x, y);
		
		incFrame();
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state, GuiBookPage fromPage) {
		recipes[frame].mouseReleased(mouseX, mouseY, state, fromPage);
	}
	
	private void incFrame(){
		if(lastChgange + minFrameDuration < System.currentTimeMillis()){
			frame++;
			lastChgange = System.currentTimeMillis();
			if(frame >= recipes.length){
				frame = 0;
			}
		}
	}
	
}
