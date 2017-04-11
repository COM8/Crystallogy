package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.util.RGBColor;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookSearchField extends GuiTextField {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookSearchField(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height) {
		super(componentId, fontrendererObj, x, y, par5Width, par6Height);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void drawScreen(int x, int y){
		this.xPosition = x + 3;
		this.yPosition = y + 1;
		
		GlStateManager.pushMatrix();
		
		setTextColor(new RGBColor(1.0F, 1.0F, 1.0F).toInt());
		drawTextBox();
		
		GlStateManager.popMatrix();
	}
	
	
}
