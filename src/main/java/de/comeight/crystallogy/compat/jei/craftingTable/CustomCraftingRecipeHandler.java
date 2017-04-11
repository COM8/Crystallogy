package de.comeight.crystallogy.compat.jei.craftingTable;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;

public class CustomCraftingRecipeHandler implements IRecipeHandler<CustomCraftingRecipeWrapper> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CustomCraftingRecipeHandler() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public Class<CustomCraftingRecipeWrapper> getRecipeClass() {
		return CustomCraftingRecipeWrapper.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return VanillaRecipeCategoryUid.CRAFTING;
	}

	@Override
	public String getRecipeCategoryUid(CustomCraftingRecipeWrapper recipe) {
		return VanillaRecipeCategoryUid.CRAFTING;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(CustomCraftingRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(CustomCraftingRecipeWrapper recipe) {
		return true;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
