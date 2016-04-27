package de.comeight.crystallogy.items.crafting.infusion;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public abstract class InfusionRecipeSimple extends InfusionRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected ItemStack center;
	protected ItemStack[] surrounding;
	protected ItemStack result;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeSimple(String id, int totalCookTime, ItemStack center, ItemStack[] surrounding, ItemStack result) {
		super(id, totalCookTime);
		this.center = center;
		this.surrounding = surrounding;
		this.result = result;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public ArrayList<ArrayList<ItemStack>> getInputsJEI() {
		ArrayList<ArrayList<ItemStack>> ret = new ArrayList<ArrayList<ItemStack>>();
		ret.add(new ArrayList<ItemStack>());
		ret.get(0).add(center);
		int e = 1;
		for(int i = 0; i < surrounding.length; i++){
			if(surrounding[i] != null){
				for(int j = 0; j < surrounding[i].stackSize; j++){
					ret.add(new ArrayList<ItemStack>());
					ret.get(e).add(new ItemStack(surrounding[i].getItem(), 1, surrounding[i].getMetadata()));
					e++;
				}
				
			}
		}
		return ret;
	}

	@Override
	public ArrayList<ItemStack> getOutputJEI() {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(result);
		return ret;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
		if(!compare(centerInput, center)){
			return false;
		}
		int[] iSurrounding = new int[surrounding.length];
		
		for (int i = 0; i < ingredients.length; i++) {
			if(ingredients[i] != null){
				for(int e = 0; e < surrounding.length; e++){
					if(compare(ingredients[i], surrounding[e])){
						iSurrounding[e]++;
						
					}
				}
			}
		}
		
		for(int i = 0; i < iSurrounding.length; i++){
			System.out.println(iSurrounding[i] + id + surrounding[i].stackSize);
			if(surrounding[i].stackSize != iSurrounding[i]){
				return false;
			}
		}
		
		output = result;
		return true;
	}
	
}
