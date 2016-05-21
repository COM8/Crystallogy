package de.comeight.crystallogy.compat.jei.charger;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class ChargerRecipeHandlerJEI implements IRecipeHandler<ChargerRecipeJEI> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public Class<ChargerRecipeJEI> getRecipeClass() {
		return ChargerRecipeJEI.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return ChargerCraftingCategory.ID;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(ChargerRecipeJEI recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(ChargerRecipeJEI recipe) {
		return recipe.getInputs().size() > 0 && recipe.getOutputs().size() > 0;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
