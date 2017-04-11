package de.comeight.crystallogy.compat.jei.charger;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.compat.jei.CrystallogyPlugin;
import de.comeight.crystallogy.handler.ChargerRecipeHandler;
import de.comeight.crystallogy.items.crafting.BaseRecipe;
import de.comeight.crystallogy.items.crafting.RecipeCharger;
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

public class ChargerCraftingCategory implements IRecipeCategory {
	//-----------------------------------------------Variabeln:---------------------------------------------
	@Nonnull
	public static final String ID = CrystallogyBase.MODID + ":charger.JEI";
	@Nonnull
	private static final String TITEL = "Charger";
	@Nonnull
	private final IDrawable background; 
	@Nonnull
	private final IDrawable slotsDrawable;
	@Nonnull
	private final IDrawable progressBackground;
	@Nonnull
	private final IDrawableAnimated progress;
	
	public static final ResourceLocation rL = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/GuiCompressor.png");
	
	private static final int INPUT_SLOT1 = 0;
	private static final int INPUT_SLOT2 = 1;
	private static final int OUTPUT_SLOT = 2;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ChargerCraftingCategory() {
		background = CrystallogyPlugin.jeiHelper.getGuiHelper().createBlankDrawable(172, 50);
		progressBackground = CrystallogyPlugin.jeiHelper.getGuiHelper().createDrawable(rL, 80, 35, 22, 15);
		IDrawableStatic temp = CrystallogyPlugin.jeiHelper.getGuiHelper().createDrawable(rL, 177, 14, 22, 16);
		progress = CrystallogyPlugin.jeiHelper.getGuiHelper().createAnimatedDrawable(temp, 20, StartDirection.LEFT, false);
		slotsDrawable = CrystallogyPlugin.jeiHelper.getGuiHelper().getSlotDrawable();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------	
	@Override
	public IDrawable getIcon() {
		return null;
	}
	
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
	
	public static ArrayList<ChargerRecipeJEI> getRecipes(){
		ArrayList<ChargerRecipeJEI> recipes = new ArrayList<ChargerRecipeJEI>();
		
		for (BaseRecipe recipeCharger : ChargerRecipeHandler.INSTANCE.getRecipes()) {
			recipes.add(new ChargerRecipeJEI((RecipeCharger)recipeCharger));
		}
		
		return recipes;
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup group = recipeLayout.getItemStacks();
		group.init(INPUT_SLOT1, true, 56, 14);
		group.init(INPUT_SLOT2, true, 56, 34);
		group.init(OUTPUT_SLOT, false, 116, 25);
		group.set(ingredients);
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawExtras(Minecraft minecraft) {
		progressBackground.draw(minecraft, 80, 26);
		slotsDrawable.draw(minecraft, 116, 25);
		slotsDrawable.draw(minecraft, 56, 14);
		slotsDrawable.draw(minecraft, 56, 34);
	}

	@Override
	public void drawAnimations(Minecraft minecraft) {
		progress.draw(minecraft, 80, 25);
	}
	
}
