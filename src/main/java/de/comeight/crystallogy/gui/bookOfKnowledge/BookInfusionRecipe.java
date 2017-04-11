package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.util.LinkedList;

import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookInfusionRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookButtonCrafting[] input;
	private BookButtonCrafting output;
	
	private int x;
	private int y;
	
	private final int frameDuration = 2500; 
	private long lastFrame;
	private int frame;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookInfusionRecipe(BookButtonCrafting[] input, BookButtonCrafting output) {
		if(input.length != 5){
			try {
				throw new Exception("input.length != 5!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < input.length; i++) {
			if(input[i] != null)
			input[i].disableFrame();
		}
		
		this.input = input;
		this.output = output;
		lastFrame = System.currentTimeMillis();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public void setFrame(int frame){
		this.frame = frame;
		this.lastFrame = System.currentTimeMillis();
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void drawScreen(int mouseX, int mouseY, int x, int y){
		this.x = x;
		this.y = y;
		
		drawInfuserBlock();
		if(frame == 0){
			drawInput(mouseX, mouseY);
			GuiBookUtilities.drawTextBox(x - 10, y + 10, 100, 1.2F, "Input:");
		}
		else{
			drawOutput(mouseX, mouseY);
			GuiBookUtilities.drawTextBox(x - 10, y + 10, 100, 1.2F, "Output:");
		}
		manageFrames();
	}
	
	private void drawInput(int mouseX, int mouseY){
		if(input[0] != null && input[0] != BookButtonCrafting.EMPTY){
			input[0].drawButton(mouseX, mouseY, x + 30, y + 35);
		}
		
		if(input[1] != null && input[1] != BookButtonCrafting.EMPTY){
			input[1].drawButton(mouseX, mouseY, x + 30, y);
		}
		if(input[2] != null && input[2] != BookButtonCrafting.EMPTY){
			input[2].drawButton(mouseX, mouseY, x - 5, y + 35);
		}
		if(input[3] != null && input[3] != BookButtonCrafting.EMPTY){
			input[3].drawButton(mouseX, mouseY, x + 65, y + 35);
		}
		if(input[4] != null && input[4] != BookButtonCrafting.EMPTY){
			input[4].drawButton(mouseX, mouseY, x + 30, y + 70);
		}
	}
	
	private void drawOutput(int mouseX, int mouseY){
		if(output!= null){
			output.drawButton(mouseX, mouseY, x + 30, y + 35);
		}
	}
	
	private void drawInfuserBlock(){
		ItemStack infuserBlock = new ItemStack(BlockHandler.infuserBlock);
		float scale = 1.0F;
		
		GuiBookUtilities.renderItem(infuserBlock, x + 35, y + 20, scale);
		GuiBookUtilities.renderItem(infuserBlock, x, y + 55, scale);
		GuiBookUtilities.renderItem(infuserBlock, x + 35, y + 55, scale);
		GuiBookUtilities.renderItem(infuserBlock, x + 70, y + 55, scale);
		GuiBookUtilities.renderItem(infuserBlock, x + 35, y + 90, scale);
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state, GuiBookPage fromPage) {
		LinkedList<BookButtonCrafting> list = new LinkedList<BookButtonCrafting>();
		for(int i = 0; i < input.length; i++){
			if(input[i] != null && !checkIfChecked(list, input[i])){
				if(input[i].hover && input[i].mousePressed(Minecraft.getMinecraft(), mouseX, mouseY)){
					list.add(input[i]);
					input[i].onClicked(fromPage);
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
	
	private void manageFrames(){
		if(System.currentTimeMillis() >= lastFrame + frameDuration){
			frame++;
			lastFrame = System.currentTimeMillis();
			if(frame >= 2){
				frame = 0;
			}
		}
	}
	
}
