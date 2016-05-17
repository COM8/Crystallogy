package de.comeight.crystallogy.compat.jei.compressor;

import de.comeight.crystallogy.CrystallogyBase;
import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

public class CompressorRecipeHandlerJEI implements IRecipeHandler<CompressorRecipeJEI> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public Class<CompressorRecipeJEI> getRecipeClass() {
		return CompressorRecipeJEI.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return CompressorCraftingCategory.ID;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(CompressorRecipeJEI recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(CompressorRecipeJEI recipe) {
		return recipe.getInputs().size() > 0 && recipe.getOutputs().size() > 0;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
