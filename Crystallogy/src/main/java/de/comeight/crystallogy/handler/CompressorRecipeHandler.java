package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.items.crafting.RecipeCompressor;
import net.minecraft.item.ItemStack;

public class CompressorRecipeHandler extends BaseRecipeHandler{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static BaseRecipeHandler INSTANCE = new CompressorRecipeHandler();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CompressorRecipeHandler() {
		addRecipe(new RecipeCompressor(new ItemStack[]{new ItemStack(ItemHandler.energyDust, 8)}, new ItemStack[]{new ItemStack(ItemHandler.energyCrystal, 1, ItemHandler.energyCrystal.getMaxDamage())}, 1.0F, 200));
		
		addRecipe(new RecipeCompressor(new ItemStack[]{new ItemStack(ItemHandler.hunterArmorMesh, 4)}, new ItemStack[]{new ItemStack(ItemHandler.hunterArmorCompound, 2)}, 1.0F, 1200));
		addRecipe(new RecipeCompressor(new ItemStack[]{new ItemStack(ItemHandler.chargedCombinedArmorMesh, 4)}, new ItemStack[]{new ItemStack(ItemHandler.combinedArmorCompound, 2)}, 1.0F, 1200));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
