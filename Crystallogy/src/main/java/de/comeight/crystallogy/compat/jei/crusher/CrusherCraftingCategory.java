package de.comeight.crystallogy.compat.jei.crusher;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.compat.jei.CrystallogyPlugin;
import de.comeight.crystallogy.handler.CrystalCrusherRecipeHandler;
import de.comeight.crystallogy.items.crafting.BaseRecipe;
import de.comeight.crystallogy.items.crafting.RecipeCrystalCrusher;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableAnimated.StartDirection;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class CrusherCraftingCategory implements IRecipeCategory {
	//-----------------------------------------------Variabeln:---------------------------------------------
	@Nonnull
	public static final String ID = CrystallogyBase.MODID + ":crystallCrusher.JEI";
	@Nonnull
	private static final String TITEL = "Crystal Crusher";
	@Nonnull
	private final IDrawable background; 
	@Nonnull
	private final IDrawable slotsDrawable;
	@Nonnull
	private final IDrawable progressBackground;
	@Nonnull
	private final IDrawableAnimated progress;
	
	public static final ResourceLocation rL = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/GuiCompressor.png");
	
	private static final int INPUT_SLOT = 0;
	private static final int OUTPUT_SLOT = 1;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrusherCraftingCategory() {
		background = CrystallogyPlugin.jeiHelper.getGuiHelper().createBlankDrawable(172, 40);
		progressBackground = CrystallogyPlugin.jeiHelper.getGuiHelper().createDrawable(rL, 80, 35, 22, 15);
		IDrawableStatic temp = CrystallogyPlugin.jeiHelper.getGuiHelper().createDrawable(rL, 177, 14, 22, 16);
		progress = CrystallogyPlugin.jeiHelper.getGuiHelper().createAnimatedDrawable(temp, 20, StartDirection.LEFT, false);
		slotsDrawable = CrystallogyPlugin.jeiHelper.getGuiHelper().getSlotDrawable();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------	
	@Nonnull
	@Override
	public String getUid() {
		return ID;
	}
	
	@Nonnull
	@Override
	public String getTitle() {
		return TITEL;
	}

	@Nonnull
	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Nonnull
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper) {
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup group = recipeLayout.getItemStacks();
		group.init(INPUT_SLOT, true, 50, 10);
		group.init(OUTPUT_SLOT, false, 111, 10);
		group.set(ingredients);
	}
	
	public static ArrayList<CrusherRecipeJEI> getRecipes(){
		ArrayList<CrusherRecipeJEI> recipes = new ArrayList<CrusherRecipeJEI>();
		
		for (BaseRecipe recipeCrystalCrusher : CrystalCrusherRecipeHandler.INSTANCE.getRecipes()) {
			recipes.add(new CrusherRecipeJEI((RecipeCrystalCrusher)recipeCrystalCrusher));
		}
		
		return recipes;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawExtras(Minecraft minecraft) {
		progressBackground.draw(minecraft, 80, 12);
		slotsDrawable.draw(minecraft, 111, 10);
		slotsDrawable.draw(minecraft, 50, 10);
	}

	@Override
	public void drawAnimations(Minecraft minecraft) {
		progress.draw(minecraft, 80, 11);
	}
}
