package de.comeight.crystallogy.compat.jei.compressor;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.items.crafting.RecipeCompressor;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class CompressorRecipeJEI extends BlankRecipeWrapper {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private List<ItemStack> inputs;
	private List<ItemStack> outputs;
	private int totalCookTime;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CompressorRecipeJEI(RecipeCompressor recipe) {
		this.inputs = new ArrayList<ItemStack>();
		this.outputs = new ArrayList<ItemStack>();
		ItemStack[] inputsIS = recipe.input;
		ItemStack[] outputsIS = recipe.getOutput(inputsIS);
		
		for(int i = 0; i < inputsIS.length; i++){
			this.inputs.add(inputsIS[i]);
		}
		for(int i = 0; i < outputsIS.length; i++){
			this.outputs.add(outputsIS[i]);
		}
		this.totalCookTime = recipe.totalCookTime;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public List<ItemStack> getInputs() {
		return inputs;
	}
	
	@Override
	public List getOutputs() {
		return outputs;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputs(ItemStack.class, inputs);
		ingredients.setOutputs(ItemStack.class, outputs);
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.fontRendererObj.drawString("Total Cook Time: " + totalCookTime, 47, 15, 4210752);
	}
	
}
