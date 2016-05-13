package de.comeight.crystallogy.util;

import java.util.List;

import net.minecraft.util.text.TextFormatting;

public class ToolTipBuilder {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private List<String> tooltip;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ToolTipBuilder(List<String> tooltip) {
		this.tooltip = tooltip;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void add(String text){
		tooltip.add(text);
	}
	
	public static void addShiftForMoreDetails(List<String> tooltip){
		tooltip.add("");
		tooltip.add(TextFormatting.GOLD + ">>Press " + TextFormatting.BOLD + "SHIFT" + TextFormatting.RESET + TextFormatting.GOLD + " for more details!<<");
	}
	
}
