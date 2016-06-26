package de.comeight.crystallogy.compat.jei.infusion;

import de.comeight.crystallogy.CrystallogyBase;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class InfusionRecipeHandlerJEI implements IRecipeHandler<InfusionRecipeJEI> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public Class<InfusionRecipeJEI> getRecipeClass() {
		return InfusionRecipeJEI.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return InfusionCraftingCategory.ID;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(InfusionRecipeJEI recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(InfusionRecipeJEI recipe) {
		return recipe.getInputs().size() > 0 && recipe.getOutputs().size() > 0;
	}

	@Override
	public String getRecipeCategoryUid(InfusionRecipeJEI recipe) {
		return InfusionCraftingCategory.ID;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
