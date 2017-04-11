package de.comeight.crystallogy.compat.jei.infusion;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.compat.jei.CrystallogyPlugin;
import de.comeight.crystallogy.handler.InfusionRecipeHandler;
import de.comeight.crystallogy.items.crafting.infusion.InfusionRecipe;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class InfusionCraftingCategory implements IRecipeCategory {
	//-----------------------------------------------Variabeln:---------------------------------------------
	@Nonnull
	public static final String ID = CrystallogyBase.MODID + ":infusion.JEI";
	@Nonnull
	private static final String TITEL = "Infusion Crafting";
	@Nonnull
	private final IDrawable background; 
	@Nonnull
	private final IDrawable slotsDrawable; 
	
	private static final ResourceLocation rL = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/jei/GuiInfusion.png");
	
	private static final int CENTERSLOT = 0;
	private static final int SURROUNDING_1 = 1;
	private static final int SURROUNDING_2 = 2;
	private static final int SURROUNDING_3 = 3;
	private static final int SURROUNDING_4 = 4;
	private static final int OUTPUT = 5;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionCraftingCategory() {
		background = CrystallogyPlugin.jeiHelper.getGuiHelper().createBlankDrawable(200, 100);
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
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper) {
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup group = recipeLayout.getItemStacks();
		
		group.init(CENTERSLOT, true, 67, 42);
		group.init(SURROUNDING_1, true, 39, 42);
		group.init(SURROUNDING_2, true, 67, 70);
		group.init(SURROUNDING_3, true, 95, 42);
		group.init(SURROUNDING_4, true, 67, 12);
		group.init(OUTPUT, false, 137, 42);
		
		group.set(ingredients);
	}
	
	public static ArrayList<InfusionRecipeJEI> getRecipes(){
		ArrayList<InfusionRecipeJEI> recipes = new ArrayList<InfusionRecipeJEI>();
		
		for (InfusionRecipe infusionRecipe : InfusionRecipeHandler.getRecipes()) {
			recipes.add(new InfusionRecipeJEI(infusionRecipe));
		}
		
		return recipes;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawExtras(Minecraft minecraft) {
		slotsDrawable.draw(minecraft, 67, 42);
		slotsDrawable.draw(minecraft, 39, 42);
		slotsDrawable.draw(minecraft, 67, 70);
		slotsDrawable.draw(minecraft, 95, 42);
		slotsDrawable.draw(minecraft, 67, 12);
		
		slotsDrawable.draw(minecraft, 137, 42);
	}

	@Override
	public void drawAnimations(Minecraft minecraft) {
	}
	
}
