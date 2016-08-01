package de.comeight.crystallogy.gui.bookOfKnowledge;

import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageRegistryEntry {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public final GuiBookPage PAGE;
	public final int ID;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public PageRegistryEntry(GuiBookPage page, int id) {
		this.PAGE = page;
		this.ID = id;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
