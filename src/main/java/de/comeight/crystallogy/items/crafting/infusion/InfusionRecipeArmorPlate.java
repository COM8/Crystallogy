package de.comeight.crystallogy.items.crafting.infusion;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;

public class InfusionRecipeArmorPlate extends InfusionRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeArmorPlate() {
		super("armorPlate", 100);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeArmorPlate();
	}

	@Override
	public ArrayList<List<ItemStack>> getInputsJEI() {
		ArrayList<List<ItemStack>> ret = new ArrayList<List<ItemStack>>();
		ret.add(new ArrayList<ItemStack>());
		ret.get(0).add(new ItemStack(ItemHandler.armorPlate, 1, 4));
		ret.add(new ArrayList<ItemStack>());
		ret.get(1).add(new ItemStack(ItemHandler.crystallDust_red));
		ret.get(1).add(new ItemStack(ItemHandler.crystallDust_blue));
		ret.get(1).add(new ItemStack(ItemHandler.crystallDust_green));
		ret.get(1).add(new ItemStack(ItemHandler.crystallDust_yellow));
		ret.add(new ArrayList<ItemStack>());
		ret.get(2).add(new ItemStack(ItemHandler.crystallDust_red));
		ret.get(2).add(new ItemStack(ItemHandler.crystallDust_blue));
		ret.get(2).add(new ItemStack(ItemHandler.crystallDust_green));
		ret.get(2).add(new ItemStack(ItemHandler.crystallDust_yellow));
		return ret;
	}

	@Override
	public ArrayList<ItemStack> getOutputJEI() {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(ItemHandler.armorPlate, 1, 0));
		ret.add(new ItemStack(ItemHandler.armorPlate, 1, 1));
		ret.add(new ItemStack(ItemHandler.armorPlate, 1, 2));
		ret.add(new ItemStack(ItemHandler.armorPlate, 1, 3));
		return ret;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
		if(!compare(centerInput, new ItemStack(ItemHandler.armorPlate, 1, 4))){
			return false;
		}
		
		int dust_red = 0;
		
		int dust_blue = 0;
		
		int dust_green = 0;
		
		int dust_yellow = 0;
		
		for (int i = 0; i < ingredients.length; i++) {
			if(ingredients[i].getItem() == ItemHandler.crystallDust_red){
				dust_red++;
			}
			else if(ingredients[i].getItem() == ItemHandler.crystallDust_blue){
				dust_blue++;
			}
			else if(ingredients[i].getItem() == ItemHandler.crystallDust_green){
				dust_green++;
			}
			else if(ingredients[i].getItem() == ItemHandler.crystallDust_yellow){
				dust_yellow++;
			}
			else{
				return false;
			}
		}
		if(ingredients.length != 2){
			return false;
		}
		if(dust_blue == 2){
			output = new ItemStack(ItemHandler.armorPlate, 1, 1);
			return true;
		}
		else if(dust_green == 2){
			output = new ItemStack(ItemHandler.armorPlate, 1, 2);
			return true;
		}
		else if(dust_red == 2){
			output = new ItemStack(ItemHandler.armorPlate, 1, 0);
			return true;
		}
		else if(dust_yellow == 2){
			output = new ItemStack(ItemHandler.armorPlate, 1, 3);
			return true;
		}
		
		return false;
	}
	
}
