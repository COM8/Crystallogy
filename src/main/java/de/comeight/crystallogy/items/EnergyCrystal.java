package de.comeight.crystallogy.items;

import java.util.List;

import de.comeight.crystallogy.util.ToolTipBuilder;
import javafx.util.converter.PercentageStringConverter;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class EnergyCrystal extends BaseItem {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "energyCrystal";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EnergyCrystal() {
		super(ID);
		setMaxStackSize(1);
		setMaxDamage(12000);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean hasEffect(ItemStack stack) {
		if(stack.getItemDamage() != stack.getMaxDamage()){
			return true;
		}
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(GuiScreen.isShiftKeyDown()){
			tooltip.add("");
			tooltip.add(TextFormatting.DARK_PURPLE + "Chrage left:");
			int percent = 100 - (int)((double)stack.getItemDamage() / (double)stack.getMaxDamage() * 100);
			tooltip.add(TextFormatting.BLUE + String.valueOf(percent) + "%");
		}
		else{
			ToolTipBuilder.addShiftForMoreDetails(tooltip);
		}
		tooltip.add("");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
}
