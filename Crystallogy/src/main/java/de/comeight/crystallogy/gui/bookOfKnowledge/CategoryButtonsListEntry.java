package de.comeight.crystallogy.gui.bookOfKnowledge;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.ICraftingGridHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.ITickTimer;
import net.minecraft.client.gui.GuiListExtended.IGuiListEntry;
import net.minecraft.util.ResourceLocation;

public class CategoryButtonsListEntry implements IGuiListEntry{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BookButtonCategory button;
	private GuiBookPage page;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CategoryButtonsListEntry(BookButtonCategory button, GuiBookPage page) {
		this.button = button;
		this.page = page;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
		button.drawButton(mouseX, mouseY, x, y);
	}

	@Override
	public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
		button.onClicked(page);
		return false;
	}

	@Override
	public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
	}
	
}
