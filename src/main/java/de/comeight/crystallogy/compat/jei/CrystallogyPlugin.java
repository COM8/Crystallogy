package de.comeight.crystallogy.compat.jei;

import javax.annotation.Nonnull;

import de.comeight.crystallogy.compat.jei.crusher.CrusherCraftingCategory;
import de.comeight.crystallogy.compat.jei.crusher.CrusherRecipeHandlerJEI;
import de.comeight.crystallogy.compat.jei.infusion.InfusionCraftingCategory;
import de.comeight.crystallogy.compat.jei.infusion.InfusionRecipeHandlerJEI;
import de.comeight.crystallogy.gui.GuiCrystallCrusher;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;

@JEIPlugin
public class CrystallogyPlugin extends BlankModPlugin {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static IJeiHelpers jeiHelper;
	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void register(@Nonnull IModRegistry registry) {
		jeiHelper = registry.getJeiHelpers();
		
		registry.addRecipeCategories(new CrusherCraftingCategory(), new InfusionCraftingCategory());
		
		registry.addRecipeHandlers(new CrusherRecipeHandlerJEI(), new InfusionRecipeHandlerJEI());
		
		registry.addRecipes(CrusherCraftingCategory.getRecipes());
		registry.addRecipes(InfusionCraftingCategory.getRecipes());
		
		registry.addRecipeClickArea(GuiCrystallCrusher.class, 80, 35, 30, 16, CrusherCraftingCategory.ID);
	}
	
}
