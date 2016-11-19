package de.comeight.crystallogy.items.crafting.infusion;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.util.EnumCustomAis;
import de.comeight.crystallogy.util.NBTTags;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class InfusionRecipeEntityBrainMoveToPos extends InfusionRecipeBaseEntityBrainAi {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeEntityBrainMoveToPos() {
		super("entityBrainMoveToPos", 200);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeEntityBrainMoveToPos();
	}

	@Override
	public ArrayList<List<ItemStack>> getInputsJEI() {
		ArrayList<List<ItemStack>> ret = new ArrayList<List<ItemStack>>();
		ret.add(new ArrayList<ItemStack>());
		ret.get(0).add(new ItemStack(ItemHandler.entityBrain, 1, 0));
		ret.add(new ArrayList<ItemStack>());
		ret.get(1).add(new ItemStack(ItemHandler.spotPicker));
		ret.add(new ArrayList<ItemStack>());
		ret.get(2).add(new ItemStack(ItemHandler.pureCrystallDust));
		ret.add(new ArrayList<ItemStack>());
		ret.get(3).add(new ItemStack(ItemHandler.pureCrystallDust));
		ret.add(new ArrayList<ItemStack>());
		ret.get(4).add(new ItemStack(Items.LEAD));
		return ret;
	}

	@Override
	public ArrayList<ItemStack> getOutputJEI() {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ItemStack s = new ItemStack(ItemHandler.entityBrain, 1, 0);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger(NBTTags.CUSTOM_AI_TYPE, EnumCustomAis.MOVE_TO_POS.ID);
		Utilities.saveBlockPosToNBT(compound, new BlockPos(0, 3, 0), NBTTags.TARGET_POS);
		s.setTagCompound(compound);
		ret.add(s);
		return ret;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
		if(!ItemStack.areItemsEqual(centerInput, new ItemStack(ItemHandler.entityBrain, 1, 0))){
			return false;
		}
		
		this.output = null;
		
		NBTTagCompound compound = centerInput.getTagCompound();
		if(compound == null){
			compound = new NBTTagCompound();
		}
		
		int spotPicker = 0;
		int lead = 0;
		int pureCrystalDust = 0;
		
		BlockPos targetPos = null;
		
		for(int i = 0;i < ingredients.length; i++){
			ItemStack itemstack = ingredients[i];
			if(itemstack != null){
				if(itemstack.getItem() == ItemHandler.spotPicker){
					targetPos = getItemsTargetFromCompound(itemstack.getTagCompound());
					if(targetPos == null){
						return false;
					}
					spotPicker++;
				}
				else if(itemstack.getItem() == Items.LEAD){
					lead++;
				}
				else if(itemstack.getItem() == ItemHandler.pureCrystallDust){
					pureCrystalDust++;
				}
				else{
					return false;
				}
			}
		}
		
		if(spotPicker != 1 || pureCrystalDust != 2 || lead != 1){
			return false;
		}
		Utilities.saveBlockPosToNBT(compound, targetPos, NBTTags.TARGET_POS);
		compound.setInteger(NBTTags.CUSTOM_AI_TYPE, EnumCustomAis.MOVE_TO_POS.ID);
		compound.setBoolean(NBTTags.FORCE_MOVE_TO, false);
		compound.setBoolean(NBTTags.RUN_CONTINUOUSLY, false);
		output = new ItemStack(ItemHandler.entityBrain, 1, 0);
		output.setTagCompound(compound);
		
		return true;
	}
	
}
