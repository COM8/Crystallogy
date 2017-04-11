package de.comeight.crystallogy.itemBlocks;

import java.util.List;

import de.comeight.crystallogy.blocks.PlayerJar;
import de.comeight.crystallogy.util.ToolTipBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

public class ItemBlockPlayerJar extends BaseItemBlock {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ItemBlockPlayerJar(Block block) {
		super(block, PlayerJar.ID);
		setMaxStackSize(1);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(stack.hasTagCompound()){
			NBTTagCompound tag = stack.getTagCompound();
			if(GuiScreen.isShiftKeyDown()){
				if(tag.getBoolean("hasEntity")){
					tooltip.add(TextFormatting.GOLD + "Name: " + TextFormatting.RESET + tag.getString("name"));
					tooltip.add(TextFormatting.GOLD + "UUID: " + TextFormatting.RESET + tag.getUniqueId("uuid").toString());
				}
			}
			else{
				ToolTipBuilder.addShiftForMoreDetails(tooltip);
			}
		}
		
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
}
