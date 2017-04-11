package de.comeight.crystallogy.gui.bookOfKnowledge.buttons;

import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BookButtonCraftingInfusion extends BookButtonCrafting {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BookButtonCraftingInfusion(int buttonId, ItemStack item, GuiBookPage page) {
		super(buttonId, item, page);
		disableFrame();
	}

	public BookButtonCraftingInfusion(int buttonId, ItemStack[] items, int frameDuration, GuiBookPage page) {
		super(buttonId, items, frameDuration, page);
		disableFrame();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
