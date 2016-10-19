package de.comeight.crystallogy.compat.jei.charger;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.items.crafting.RecipeCharger;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class ChargerRecipeJEI extends BlankRecipeWrapper {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private List<List<ItemStack>> inputs;
	private List<ItemStack> outputs;
	private int totalCookTime;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ChargerRecipeJEI(RecipeCharger recipe) {
		this.inputs = new ArrayList<List<ItemStack>>();
		this.outputs = new ArrayList<ItemStack>();
		ItemStack[] inputsIS = recipe.input;
		ItemStack[] outputsIS = recipe.getOutput(inputsIS);
		
		this.inputs.add(new ArrayList<ItemStack>());
		this.inputs.add(new ArrayList<ItemStack>());
		
		for(int i = 0; i < inputsIS.length; i++){
			this.inputs.get(i).add(inputsIS[i]);
		}
		for(int i = 0; i < outputsIS.length; i++){
			this.outputs.add(outputsIS[i]);
		}
		this.totalCookTime = recipe.totalCookTime;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public List<List<ItemStack>> getInputs() {
		return inputs;
	}
	
	@Override
	public List getOutputs() {
		return outputs;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, inputs);
		ingredients.setOutputs(ItemStack.class, outputs);
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		minecraft.fontRendererObj.drawString("Total Cook Time: " + totalCookTime, 47, 15, 4210752);
	}
	
}
