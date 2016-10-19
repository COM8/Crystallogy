package de.comeight.crystallogy.items.tools;

import java.util.List;

import de.comeight.crystallogy.items.BaseItem;
import de.comeight.crystallogy.util.ToolTipBuilder;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class SpotPicker extends BaseItem{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "spotPicker";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public SpotPicker() {
		super(ID);
		setMaxStackSize(1);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		NBTTagCompound compound = stack.getTagCompound();
		if(compound == null){
			compound = new NBTTagCompound();
		}
		
		Utilities.saveBlockPosToNBT(compound, pos, "spot");
		if(worldIn.isRemote){
			playerIn.addChatComponentMessage(new TextComponentString("§5" + Utilities.localizeText("item.spotPicker.playerMessage.0") + "§6 X=" + pos.getX() + " Y=" + pos.getY() + " Z=" + pos.getZ()));
		}
		stack.setTagCompound(compound);
		return EnumActionResult.SUCCESS;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(Utilities.localizeText("item.spotPicker.tooltip.0"));
		if(GuiScreen.isShiftKeyDown()){
			tooltip.add("");
			tooltip.add(Utilities.localizeText("item.spotPicker.tooltip.1"));
			BlockPos p = null;
			if(stack.hasTagCompound()){
				p = Utilities.readBlockPosFromNBT(stack.getTagCompound(), "spot");
			}
			
			if(p == null){
				tooltip.add("X: §6-");
				tooltip.add("Y: §6-");
				tooltip.add("Z: §6-");
			}
			else{
				tooltip.add("X: §6" + p.getX());
				tooltip.add("Y: §6" + p.getY());
				tooltip.add("Z: §6" + p.getZ());
			}
		}
		else{
			ToolTipBuilder.addShiftForMoreDetails(tooltip);
		}
	}
	
}
