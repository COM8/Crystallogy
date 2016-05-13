package de.comeight.crystallogy.compat.jei.crusher;

import de.comeight.crystallogy.CrystallogyBase;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class CrusherRecipeHandlerJEI implements IRecipeHandler<CrusherRecipeJEI> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public Class<CrusherRecipeJEI> getRecipeClass() {
		return CrusherRecipeJEI.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return CrusherCraftingCategory.ID;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(CrusherRecipeJEI recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(CrusherRecipeJEI recipe) {
		return recipe.getInputs().size() > 0 && recipe.getOutputs().size() > 0;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
