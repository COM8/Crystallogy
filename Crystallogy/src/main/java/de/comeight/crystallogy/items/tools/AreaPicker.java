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

public class AreaPicker extends BaseItem{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "areaPicker";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public AreaPicker() {
		super(ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		NBTTagCompound compound = stack.getTagCompound();
		if(compound == null){
			compound = new NBTTagCompound();
		}
		
		if(playerIn.isSneaking()){
			Utilities.saveBlockPosToNBT(compound, pos, "area2");
			if(worldIn.isRemote){
				playerIn.addChatComponentMessage(new TextComponentString("§5" + Utilities.localizeText("item.areaPicker.playerMessage.1") + "§6 X=" + pos.getX() + " Y=" + pos.getY() + " Z=" + pos.getZ()));
			}
		}
		else{
			Utilities.saveBlockPosToNBT(compound, pos, "area1");
			if(worldIn.isRemote){
				playerIn.addChatComponentMessage(new TextComponentString("§5" + Utilities.localizeText("item.areaPicker.playerMessage.0") + "§6 X=" + pos.getX() + " Y=" + pos.getY() + " Z=" + pos.getZ()));
			}
		}
		stack .setTagCompound(compound);
		return EnumActionResult.SUCCESS;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(Utilities.localizeText("item.areaPicker.tooltip.0"));
		tooltip.add(Utilities.localizeText("item.areaPicker.tooltip.1"));
		if(GuiScreen.isShiftKeyDown()){
			tooltip.add("");
			tooltip.add(Utilities.localizeText("item.areaPicker.tooltip.2"));
			BlockPos p1 = null;
			BlockPos p2 = null;
			if(stack.hasTagCompound()){
				p1 = Utilities.readBlockPosFromNBT(stack.getTagCompound(), "area1");
				p2 = Utilities.readBlockPosFromNBT(stack.getTagCompound(), "area2");
			}
			
			if(p1 == null){
				tooltip.add("xMin: §6-");
				tooltip.add("yMin: §6-");
				tooltip.add("zMin: §6-");
			}
			else{
				tooltip.add("xMin: §6" + p1.getX());
				tooltip.add("yMin: §6" + p1.getY());
				tooltip.add("zMin: §6" + p1.getZ());
			}
			tooltip.add("");
			if(p2 == null){
				tooltip.add("xMax: §6-");
				tooltip.add("yMax: §6-");
				tooltip.add("zMax: §6-");
			}
			else{
				tooltip.add("xMax: §6" + p2.getX());
				tooltip.add("yMax: §6" + p2.getY());
				tooltip.add("zMax: §6" + p2.getZ());
			}
		}
		else{
			ToolTipBuilder.addShiftForMoreDetails(tooltip);
		}
	}
	
}
