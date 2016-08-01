package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookMultiItemRenderer;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookDebugTool extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookMultiItemRenderer renderer;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookDebugTool() {
		super("Debug Tool:");
		
		renderer = new BookMultiItemRenderer(new ItemStack[]{new ItemStack(ItemHandler.debugTool)}, 1000, 5.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawItem();
		drawText();
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + 120, WRAPWIDTH, 1.0F, "The Debug Tool is used by me to debug blocks and test new features.\n"
				+ "It can be enabled / disabled in the config.");
	}
	
	private void drawItem(){
		renderer.drawItem(xPosBook + 50, yPosBook + 30);
	}
	
}
