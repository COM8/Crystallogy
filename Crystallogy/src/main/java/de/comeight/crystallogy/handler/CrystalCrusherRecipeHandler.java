package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.items.crafting.RecipeCrystalCrusher;
import net.minecraft.item.ItemStack;

public class CrystalCrusherRecipeHandler extends BaseRecipeHandler{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static CrystalCrusherRecipeHandler INSTANCE = new CrystalCrusherRecipeHandler();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystalCrusherRecipeHandler() {
		addRecipe(new RecipeCrystalCrusher(new ItemStack[]{ new ItemStack(BlockHandler.crystall_blue)}, new ItemStack[]{ new ItemStack(ItemHandler.crystallDust_blue)}, 1.0F, 200));
		addRecipe(new RecipeCrystalCrusher(new ItemStack[]{ new ItemStack(BlockHandler.crystall_green)}, new ItemStack[]{ new ItemStack(ItemHandler.crystallDust_green)}, 1.0F, 200));
		addRecipe(new RecipeCrystalCrusher(new ItemStack[]{ new ItemStack(BlockHandler.crystall_red)}, new ItemStack[]{ new ItemStack(ItemHandler.crystallDust_red)}, 1.0F, 200));
		addRecipe(new RecipeCrystalCrusher(new ItemStack[]{ new ItemStack(BlockHandler.crystall_yellow)}, new ItemStack[]{ new ItemStack(ItemHandler.crystallDust_yellow)}, 1.0F, 200));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
	
}
