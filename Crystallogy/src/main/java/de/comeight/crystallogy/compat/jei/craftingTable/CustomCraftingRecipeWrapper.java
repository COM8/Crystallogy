package de.comeight.crystallogy.compat.jei.craftingTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class CustomCraftingRecipeWrapper implements IShapedCraftingRecipeWrapper {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private final List<List<ItemStack>> inputs;
	private final ItemStack output;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CustomCraftingRecipeWrapper() {
		List<ItemStack> crystals = new ArrayList<ItemStack>();
		crystals.add(new ItemStack(BlockHandler.crystall_red));
		crystals.add(new ItemStack(BlockHandler.crystall_blue));
		crystals.add(new ItemStack(BlockHandler.crystall_green));
		crystals.add(new ItemStack(BlockHandler.crystall_yellow));
		List<ItemStack> book = new ArrayList<ItemStack>();
		book.add(new ItemStack(Items.BOOK));
		
		this.output = new ItemStack(ItemHandler.bookOfKnowledge);
		this.inputs = new ArrayList<List<ItemStack>>();
		inputs.add(crystals);
		inputs.add(book);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public List getInputs() {
		return inputs;
	}

	@Override
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(this.output);
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, this.inputs);
		ingredients.setOutput(ItemStack.class, this.output);
	}

	@Override
	public List<FluidStack> getFluidInputs() {
		return null;
	}

	@Override
	public List<FluidStack> getFluidOutputs() {
		return null;
	}
	
	@Override
	public int getWidth() {
		return 2;
	}

	@Override
	public int getHeight() {
		return 2;
	}
	
	@Override
	public List<String> getTooltipStrings(int mouseX, int mouseY) {
		return null;
	}
	
	public static List<CustomCraftingRecipeWrapper> getRecipes(){
		List<CustomCraftingRecipeWrapper> recipes = new ArrayList<CustomCraftingRecipeWrapper>();
		recipes.add(new CustomCraftingRecipeWrapper());
		return recipes;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean handleClick(Minecraft minecraft, int mouseX, int mouseY, int mouseButton) {
		return false;
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
	}

	@Override
	public void drawAnimations(Minecraft minecraft, int recipeWidth, int recipeHeight) {
	}
	
}
