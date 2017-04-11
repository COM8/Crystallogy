package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.items.crafting.RecipeCharger;
import net.minecraft.item.ItemStack;

public class ChargerRecipeHandler extends BaseRecipeHandler{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static BaseRecipeHandler INSTANCE = new ChargerRecipeHandler();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ChargerRecipeHandler() {
		addRecipe(new RecipeCharger(new ItemStack[]{new ItemStack(ItemHandler.energyCrystal, 1, 12000), new ItemStack(BlockHandler.fireCrystall, 4)}, new ItemStack[]{new ItemStack(ItemHandler.energyCrystal, 1, 0)}, 1.0F, 200));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
