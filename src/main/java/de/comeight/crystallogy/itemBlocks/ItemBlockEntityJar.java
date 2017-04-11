package de.comeight.crystallogy.itemBlocks;

import java.util.List;

import de.comeight.crystallogy.blocks.EntityJar;
import de.comeight.crystallogy.util.ToolTipBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;

public class ItemBlockEntityJar extends BaseItemBlock {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ItemBlockEntityJar(Block block) {
		super(block, EntityJar.ID);
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
