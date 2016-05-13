package de.comeight.crystallogy.items;

import java.util.List;

import de.comeight.crystallogy.util.ToolTipBuilder;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
		subItems.add(new ItemStack(itemIn, 1, getMaxDamage()));
		subItems.add(new ItemStack(itemIn, 1, 0));
    }
	
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
