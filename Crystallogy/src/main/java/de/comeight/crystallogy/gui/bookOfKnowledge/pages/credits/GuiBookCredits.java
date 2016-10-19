package de.comeight.crystallogy.gui.bookOfKnowledge.pages.credits;

import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookUtilities;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.util.RGBColor;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCredits extends GuiBookPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCredits() {
		super("Credits:");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawText();
		drawVersion();
	}
	
	private void drawText(){
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP + 20, WRAPWIDTH, 1.0F, "First of all THANKS for reading this book.\n"
				+ "\n"
				+ "This book was written and developed by COM8.\n"
				+ "\n"
				+ "I know that there will be spelling mistakes and or grammatical problems that I was unable to sort out. "
				+ "If you find one, feel free to report it as a bug at:\n");
		
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP + 130, WRAPWIDTH, 1.0F, new RGBColor(0.0F, 0.0F, 1.0F).toInt(), "https://github.com/COM8/Crystallogy/issues");
		
		GuiBookUtilities.drawTextBox(xPosBook + BORDER_LEFT, yPosBook + BORDER_TOP + 160, WRAPWIDTH, 1.0F, "If you have a suggestion how I could improve this book, you can create an \"Issue\" under the link above with the label \"enhancement\".\n");
	}
	
	private void drawVersion(){
		GuiBookUtilities.drawTextBox(xPosBook + xSize - 35, yPosBook + BORDER_TOP, WRAPWIDTH, 1.0F, new RGBColor(1.0F, 0.0F, 0.0F).toInt(), "v.1.3");
	}
	
}
