package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.items.crafting.RecipeCompressor;
import net.minecraft.item.ItemStack;

public class ChargerRecipeHandler extends BaseRecipeHandler{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static BaseRecipeHandler INSTANCE = new ChargerRecipeHandler();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ChargerRecipeHandler() {
		addRecipe(new RecipeCompressor(new ItemStack[]{new ItemStack(ItemHandler.energyCrystal, 1), new ItemStack(BlockHandler.fireCrystall, 4)}, new ItemStack[]{new ItemStack(ItemHandler.energyCrystal, 1, 0)}, 1.0F, 200));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
