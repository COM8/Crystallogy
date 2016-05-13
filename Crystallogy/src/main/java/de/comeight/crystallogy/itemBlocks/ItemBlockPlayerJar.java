package de.comeight.crystallogy.itemBlocks;

import java.util.List;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.util.ToolTipBuilder;
import net.minecraft.block.Block;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemBlockPlayerJar extends BaseItemBlock {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ItemBlockPlayerJar(Block block) {
		super(block, BlockHandler.playerJar.ID);
		setMaxStackSize(1);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(stack.hasTagCompound()){
			NBTTagCompound tag = stack.getTagCompound();
			if(GuiScreen.isShiftKeyDown()){
				if(tag.getBoolean("hasPlayer")){
					tooltip.add("Name: " + tag.getString("playerName"));
					tooltip.add("UUID: " + tag.getUniqueId("playerUUID").toString());
					tooltip.add("Threat: " + tag.getString("liquid"));
				}
			}
			else{
				ToolTipBuilder.addShiftForMoreDetails(tooltip);
			}
		}
		
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
}
