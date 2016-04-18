package de.comeight.crystallogy.items.crafting;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
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
	
	protected boolean compare(ItemStack i1, ItemStack i2){
		if(i1.getItem() != i2.getItem()){
			return false;
		}
		if(i1.getItem().getHasSubtypes()){
			if(i1.getItem().getDamage(i1) != i2.getItem().getDamage(i2)){
				return false;
			}
		}
		return true;
	}
	
}
