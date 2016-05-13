package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.items.crafting.RecipeCompressor;
import net.minecraft.item.ItemStack;

public class CompressorRecipeHandler extends BaseRecipeHandler{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static BaseRecipeHandler INSTANCE = new CompressorRecipeHandler();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CompressorRecipeHandler() {
		addRecipe(new RecipeCompressor(new ItemStack[]{new ItemStack(ItemHandler.energyDust, 8)}, new ItemStack[]{new ItemStack(ItemHandler.energyCrystal, 1, ItemHandler.energyCrystal.getMaxDamage())}, 1.0F, 200));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
