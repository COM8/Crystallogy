package de.comeight.crystallogy.compat.jei;

import javax.annotation.Nonnull;

import de.comeight.crystallogy.compat.jei.charger.ChargerCraftingCategory;
import de.comeight.crystallogy.compat.jei.charger.ChargerRecipeHandlerJEI;
import de.comeight.crystallogy.compat.jei.compressor.CompressorCraftingCategory;
import de.comeight.crystallogy.compat.jei.compressor.CompressorRecipeHandlerJEI;
import de.comeight.crystallogy.compat.jei.crusher.CrusherCraftingCategory;
import de.comeight.crystallogy.compat.jei.crusher.CrusherRecipeHandlerJEI;
import de.comeight.crystallogy.compat.jei.infusion.InfusionCraftingCategory;
import de.comeight.crystallogy.compat.jei.infusion.InfusionRecipeHandlerJEI;
import de.comeight.crystallogy.gui.GuiCharger;
import de.comeight.crystallogy.gui.GuiCompressor;
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
		
		registry.addRecipeCategories(new CrusherCraftingCategory(), new InfusionCraftingCategory(), new CompressorCraftingCategory(), new ChargerCraftingCategory());
		
		registry.addRecipeHandlers(new CrusherRecipeHandlerJEI(), new InfusionRecipeHandlerJEI(), new CompressorRecipeHandlerJEI(), new ChargerRecipeHandlerJEI());
		
		registry.addRecipes(CrusherCraftingCategory.getRecipes());
		registry.addRecipes(InfusionCraftingCategory.getRecipes());
		registry.addRecipes(CompressorCraftingCategory.getRecipes());
		registry.addRecipes(ChargerCraftingCategory.getRecipes());
		
		registry.addRecipeClickArea(GuiCrystallCrusher.class, 80, 35, 30, 16, CrusherCraftingCategory.ID);
		registry.addRecipeClickArea(GuiCompressor.class, 80, 35, 30, 16, CompressorCraftingCategory.ID);
		registry.addRecipeClickArea(GuiCharger.class, 80, 35, 30, 16, ChargerCraftingCategory.ID);
	}
	
}
