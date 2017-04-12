package de.comeight.crystallogy.gui.bookOfKnowledge;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IGuiClickable {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
    void mouseReleased(int mouseX, int mouseY);
	
	void mouseClicked(int mouseX, int mouseY, int mouseButton);
	
	void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick);
	
}
