package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.util.LinkedList;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import net.minecraft.client.Minecraft;

public class BookCraftingRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookButtonCrafting output;
	private BookButtonCrafting[][] input;
	
	private int x;
	private int y;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookCraftingRecipe(BookButtonCrafting[][] input, BookButtonCrafting output) {
		this.input = input;
		this.output = output;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void drawScreen(int mouseX, int mouseY, int x, int y){
		this.x = x;
		this.y = y;
		
		drawInput(mouseX, mouseY);
		drawOutput(mouseX, mouseY);
	}
	
	private void drawInput(int mouseX, int mouseY){
		for(int i = 0; i < input.length; i++){
			for(int e = 0; e < input[i].length; e++){
				input[e][i].drawButton(mouseX, mouseY, x + i * 25, y + e * 25);
			}
		}
	}
	
	private void drawOutput(int mouseX, int mouseY){
		output.drawButton(mouseX, mouseY, x + 105, y + 25);
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state, GuiBookPage fromPage) {
		LinkedList<BookButtonCrafting> list = new LinkedList<BookButtonCrafting>();
		for(int i = 0; i < input.length; i++){
			for(int e = 0; e < input[i].length; e++){
				if(!checkIfChecked(list, input[i][e])){
					if(input[i][e].hover && input[i][e].mousePressed(Minecraft.getMinecraft(), mouseX, mouseY)){
						list.add(input[i][e]);
						input[i][e].onClicked(fromPage);
					}
				}
			}
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
