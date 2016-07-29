package de.comeight.crystallogy.gui.bookOfKnowledge;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookMultiInfusion {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookInfusionRecipe[] recipes;
	
	private long lastChgange;
	private int frame;
	private final int minFrameDuration = 10000; 
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookMultiInfusion(BookInfusionRecipe[] recipes) {
		this.recipes = recipes;
		
		this.lastChgange = System.currentTimeMillis();
		this.frame = 0;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void drawRecipe(int mouseX, int mouseY, int x, int y){
		incFrame();
		
		recipes[frame].drawScreen(mouseX, mouseY, x, y);
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
			resetRecipeFrame();
		}
	}
	
	private void resetRecipeFrame(){
		recipes[frame].setFrame(1);
	}
	
}
