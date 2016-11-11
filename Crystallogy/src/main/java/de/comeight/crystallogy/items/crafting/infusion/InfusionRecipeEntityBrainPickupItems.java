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

public class InfusionRecipeEntityBrainPickupItems extends InfusionRecipeBaseEntityBrainAi {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeEntityBrainPickupItems() {
		super("entityBrainPickupItems", 200);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeEntityBrainPickupItems();
	}

	@Override
	public ArrayList<List<ItemStack>> getInputsJEI() {
		ArrayList<List<ItemStack>> ret = new ArrayList<List<ItemStack>>();
		ret.add(new ArrayList<ItemStack>());
		ret.get(0).add(new ItemStack(ItemHandler.entityBrain, 1, 0));
		ret.add(new ArrayList<ItemStack>());
		ret.get(1).add(new ItemStack(ItemHandler.areaPicker));
		ret.add(new ArrayList<ItemStack>());
		ret.get(2).add(new ItemStack(ItemHandler.spotPicker));
		ret.add(new ArrayList<ItemStack>());
		ret.get(3).add(new ItemStack(Items.SLIME_BALL));
		ret.add(new ArrayList<ItemStack>());
		ret.get(4).add(new ItemStack(ItemHandler.energyCrystal));
		return ret;
	}

	@Override
	public ArrayList<ItemStack> getOutputJEI() {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ItemStack s = new ItemStack(ItemHandler.entityBrain, 1, 0);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger(NBTTags.CUSTOM_AI_TYPE, EnumCustomAis.PICKUP_ITEMS.ID);
		saveDataToCompound(compound, new BlockPos(0, 3, 0), new BlockPos(0, 0, 0), new BlockPos(0, 3, 0));
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
		
		int areaPicker = 0;
		int spotPicker = 0;
		int slimeBall = 0;
		int energyCrystal = 0;
		
		BlockPos area[] = null;
		BlockPos itemsTargetPos = null;
		
		for(int i = 0;i < ingredients.length; i++){
			ItemStack itemstack = ingredients[i];
			if(itemstack != null){
				if(itemstack.getItem() == ItemHandler.areaPicker){
					area = getAreaFromCompound(itemstack.getTagCompound());
					if(area == null){
						return false;
					}
					areaPicker++;
				}
				else if(itemstack.getItem() == ItemHandler.spotPicker){
					itemsTargetPos = getItemsTargetFromCompound(itemstack.getTagCompound());
					if(itemsTargetPos == null){
						return false;
					}
					spotPicker++;
				}
				else if(itemstack.getItem() == ItemHandler.energyCrystal && itemstack.getItemDamage() == 0){
					energyCrystal++;
				}
				else if(itemstack.getItem() == Items.SLIME_BALL){
					slimeBall++;
				}
				else{
					return false;
				}
			}
		}
		
		if(areaPicker != 1 || spotPicker != 1 || slimeBall != 1 || energyCrystal != 1){
			return false;
		}
		saveDataToCompound(compound, area[0], area[1], itemsTargetPos);
		compound.setInteger(NBTTags.CUSTOM_AI_TYPE, EnumCustomAis.PICKUP_ITEMS.ID);
		output = new ItemStack(ItemHandler.entityBrain, 1, 0);
		output.setTagCompound(compound);
		
		return true;
	}
	
	private void saveDataToCompound(NBTTagCompound compound, BlockPos areaMin, BlockPos areaMax, BlockPos itemsTargetPos){
		Utilities.saveBlockPosToNBT(compound, areaMin, NBTTags.AREA_MIN);
		Utilities.saveBlockPosToNBT(compound, areaMax, NBTTags.AREA_MAX);
		Utilities.saveBlockPosToNBT(compound, itemsTargetPos, NBTTags.ITEMS_TARGET_POS);
	}
	
}
