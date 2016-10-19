package de.comeight.crystallogy.items.crafting.infusion;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.util.EnumCustomAis;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class InfusionRecipeEntityBrainQuarry extends InfusionRecipeBaseEntityBrainAi {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeEntityBrainQuarry() {
		super("entityBrainQuarry", 200);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeEntityBrainQuarry();
	}

	@Override
	public ArrayList<List<ItemStack>> getInputsJEI() {
		ArrayList<List<ItemStack>> ret = new ArrayList<List<ItemStack>>();
		ret.add(new ArrayList<ItemStack>());
		ret.get(0).add(new ItemStack(ItemHandler.entityBrain, 1, 0));
		
		ret.add(new ArrayList<ItemStack>());
		ret.get(1).add(new ItemStack(ItemHandler.areaPicker));
		
		ret.add(new ArrayList<ItemStack>());
		ret.get(2).add(new ItemStack(ItemHandler.crystalPickaxe_red));
		
		ret.add(new ArrayList<ItemStack>());
		ret.get(3).add(new ItemStack(ItemHandler.energyCrystal));
		
		ret.add(new ArrayList<ItemStack>());
		ret.get(4).add(new ItemStack(Items.DYE, 1, 4));
		return ret;
	}

	@Override
	public ArrayList<ItemStack> getOutputJEI() {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ItemStack s = new ItemStack(ItemHandler.entityBrain, 1, 0);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("aiType", EnumCustomAis.QUARRY.ID);
		Utilities.saveBlockPosToNBT(compound, new BlockPos(0, 3, 0), "areaMin");
		Utilities.saveBlockPosToNBT(compound, new BlockPos(5, 0, 5), "areaMax");
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
		int pickAxe = 0;
		int energyCrystal = 0;
		int lapis = 0;
		
		BlockPos area[] = null;
		
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
				else if(itemstack.getItem() == ItemHandler.crystalPickaxe_red){
					pickAxe++;
				}
				else if(itemstack.getItem() == Items.DYE && itemstack.getItemDamage() == 4){
					lapis++;
				}
				else if(itemstack.getItem() == ItemHandler.energyCrystal && itemstack.getItemDamage() == 0){
					energyCrystal++;
				}
				else{
					return false;
				}
			}
		}
		
		if(areaPicker != 1 || pickAxe != 1 || energyCrystal != 1 || lapis != 1){
			return false;
		}
		
		Utilities.saveBlockPosToNBT(compound, area[0], "areaMin");
		Utilities.saveBlockPosToNBT(compound, area[1], "areaMax");
		compound.setInteger("aiType", EnumCustomAis.QUARRY.ID);
		output = new ItemStack(ItemHandler.entityBrain, 1, 0);
		output.setTagCompound(compound);
		return true;
	}
	
}
