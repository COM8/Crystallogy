package de.comeight.crystallogy.items.crafting.infusion;

import java.util.LinkedList;

import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public abstract class InfusionRecipeBaseEntityBrainAi extends InfusionRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeBaseEntityBrainAi(String id, int totalCookTime) {
		super(id, totalCookTime);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	protected BlockPos[] getAreaFromCompound(NBTTagCompound compound){
		if(compound == null){
			return null;
		}
		BlockPos pos1 = Utilities.readBlockPosFromNBT(compound, "area1");
		BlockPos pos2 = Utilities.readBlockPosFromNBT(compound, "area2");
		if(pos1 == null || pos2 == null){
			return null;
		}
		
		int xMin = 0;
		int yMin = 0;
		int zMin = 0;
		
		int xMax = 0;
		int yMax = 0;
		int zMax = 0;
		
		if(pos1.getX() > pos2.getX()){
			xMin = pos2.getX();
			xMax = pos1.getX();
		}
		else{
			xMin = pos1.getX();
			xMax = pos2.getX();
		}
		
		if(pos1.getY() > pos2.getY()){
			yMin = pos1.getY();
			yMax = pos2.getY();
		}
		else{
			yMin = pos2.getY();
			yMax = pos1.getY();
		}
		
		if(pos1.getZ() > pos2.getZ()){
			zMin = pos2.getZ();
			zMax = pos1.getZ();
		}
		else{
			zMin = pos1.getZ();
			zMax = pos2.getZ();
		}
		
		return new BlockPos[]{new BlockPos(xMin, yMin, zMin), new BlockPos(xMax, yMax, zMax)};
	}
	
	protected BlockPos getItemsTargetFromCompound(NBTTagCompound compound){
		if(compound == null){
			return null;
		}
		return Utilities.readBlockPosFromNBT(compound, "spot");
	}
	
	@Override
	public LinkedList<LinkedList<ItemStack>> getReturns() {
		LinkedList<LinkedList<ItemStack>> list = super.getReturns();
		
		LinkedList<ItemStack> areaPicker = new LinkedList<ItemStack>();
		areaPicker.add(new ItemStack(ItemHandler.areaPicker));
		areaPicker.add(new ItemStack(ItemHandler.areaPicker));
		list.add(areaPicker);
		
		LinkedList<ItemStack> spotPicker = new LinkedList<ItemStack>();
		spotPicker.add(new ItemStack(ItemHandler.spotPicker));
		spotPicker.add(new ItemStack(ItemHandler.spotPicker));
		list.add(spotPicker);
		
		return list;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
}
