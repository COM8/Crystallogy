package de.comeight.crystallogy.items.crafting;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeCrystallLight extends InfusionRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeCrystallLight() {
		super("crystallLight", 500);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeCrystallLight();
	}

	@Override
	public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
		if(centerInput.getItem() != Items.flint_and_steel){
			return false;
		}
		
		int glowstone = 0;
		int crystallDust = 0;
		
		for (int i = 0; i < ingredients.length; i++) {
			if(ingredients[i] != null){
				if(ingredients[i].getItem() == Items.glowstone_dust){
					glowstone++;
				}
				if(ingredients[i].getItem() == ItemHandler.crystallDust_blue || ingredients[i].getItem() == ItemHandler.crystallDust_red || ingredients[i].getItem() == ItemHandler.crystallDust_green || ingredients[i].getItem() == ItemHandler.crystallDust_yellow){
					crystallDust++;
				}
			}
		}
		
		if(glowstone != 2 || crystallDust != 2){
			return false;
		}
		
		output = new ItemStack(BlockHandler.crystallLight);
		output.stackSize = 4; // TODO FIX stackSize in NetCode
		
		return true;
	}
	
}
