package de.comeight.crystallogy.handler;

import java.util.ArrayList;

import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipe;
import net.minecraft.item.ItemStack;

public class InfusionRecipeHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static ArrayList<InfusionRecipe> recipes = new ArrayList<InfusionRecipe>();

	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeHandler() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public static int getIndexOfRecipe(InfusionRecipe recipe){
		for (int i = 0; i < recipes.size(); i++) {
			if(recipes.get(i).id.equals(recipe.id)){
				return i;
			}
		}
		return -1;
	}
	
	public static InfusionRecipe getRecipe(int index){
		return recipes.get(index).getRecipe();
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void addRecipe(InfusionRecipe recipe){
		recipes.add(recipe);
	}
	
	public static InfusionRecipe matchRecipes(ItemStack centerInfuserBlock, ItemStack[] infuserBlocks){
		if(centerInfuserBlock == null || infuserBlocks.length == 0){
			return null;
		}
		for (int i = 0; i < recipes.size(); i++) {
			if(recipes.get(i).match(centerInfuserBlock, infuserBlocks)){
				InfusionRecipe r = recipes.get(i).getRecipe();
				r.match(centerInfuserBlock, infuserBlocks);
				return r;
			}
		}
		return null;
	}
	
}
