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
		IGuiItemStackGroup group = recipeLayout.getItemStacks();
		group.init(CENTERSLOT, true, 52, 42);
		group.init(SURROUNDING_1, true, 24, 42);
		group.init(SURROUNDING_2, true, 52, 70);
		group.init(SURROUNDING_3, true, 80, 42);
		group.init(SURROUNDING_4, true, 52, 12);
		
		if(recipeWrapper instanceof InfusionRecipeJEI){
			InfusionRecipeJEI recipe = (InfusionRecipeJEI) recipeWrapper;
			
			for (int i = 0; i < recipe.getInputs().size(); i++) {
				group.setFromRecipe(i, recipe.getInputs().get(i));
			}
			
			group.init(OUTPUT, true, 122, 42);
			group.setFromRecipe(OUTPUT, recipe.getOutputs());
		}
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
		slotsDrawable.draw(minecraft, 52, 42);
		slotsDrawable.draw(minecraft, 24, 42);
		slotsDrawable.draw(minecraft, 52, 70);
		slotsDrawable.draw(minecraft, 80, 42);
		slotsDrawable.draw(minecraft, 52, 12);
		
		slotsDrawable.draw(minecraft, 122, 42);
	}

	@Override
	public void drawAnimations(Minecraft minecraft) {
	}
	
}
