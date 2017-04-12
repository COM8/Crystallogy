package de.comeight.crystallogy.handler;

import java.util.ArrayList;

import de.comeight.crystallogy.items.crafting.BaseRecipe;
import net.minecraft.item.ItemStack;

public abstract class BaseRecipeHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected ArrayList<BaseRecipe> craftingList = new ArrayList<BaseRecipe>();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseRecipeHandler() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public ArrayList<BaseRecipe> getRecipes() {
		return craftingList;
	}
	
	public int[] getNumberOfInputItems(ItemStack  input[]){
		if(input == null){
			return null;
		}
		
		BaseRecipe recipe = findRecipe(input);
		if(recipe == null){
			return null;
		}
		int[] ret = new int[recipe.input.length];
		for(int i = 0; i < recipe.input.length; i++){
			ret[i] = recipe.input[i].stackSize;
		}
		
		return ret;
	}
	
	public ItemStack[] getResults(ItemStack  input[]){
		if(input == null){
			return null;
		}
		BaseRecipe recipe = findRecipe(input);
		if(recipe != null){
			return recipe.getOutput(input);
		}
		return null;
	}
	
	public float getExperience(ItemStack output[]){
		if(output == null){
			return 0.0F;
		}
		BaseRecipe recipe = findRecipeByOutput(output);
		if(recipe != null){
			return recipe.experience;
		}
		return 0.0F;
	}
	
	public int getTotalCookTime(ItemStack  input[]){
		if(input == null){
			return 0;
		}
		BaseRecipe recipe = findRecipe(input);
		if(recipe != null){
			return recipe.totalCookTime;
		}
		return 0;
	}
	
	public ArrayList<ArrayList<ItemStack>> getInputs(){
		ArrayList<ArrayList<ItemStack>> inputs = new ArrayList<ArrayList<ItemStack>>();
		int e = 0;
		for (BaseRecipe baseRecipe : craftingList) {
			inputs.add(new ArrayList<ItemStack>());
			for(int i = 0; i< baseRecipe.input.length; i++){
				inputs.get(e).add(baseRecipe.input[i]);
			}
			e++;
		}
		return inputs;
	}
	
	public ArrayList<ArrayList<ItemStack>> getOutputs(){
		ArrayList<ArrayList<ItemStack>> outputs = new ArrayList<ArrayList<ItemStack>>();
		int e = 0;
		for (BaseRecipe baseRecipe : craftingList) {
			outputs.add(new ArrayList<ItemStack>());
			for(int i = 0; i< baseRecipe.getOutput(baseRecipe.input).length; i++){
				outputs.get(e).add(baseRecipe.getOutput(baseRecipe.input)[i]);
			}
			e++;
		}
		return outputs;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public BaseRecipe findRecipe(ItemStack input[]){
		for (BaseRecipe baseRecipe : craftingList) {
			boolean wrong = false;
			for(int i = 0; i < baseRecipe.input.length; i++){
				if(input[i] == null || baseRecipe.input[i].getItem() != input[i].getItem() || baseRecipe.input[i].stackSize > input[i].stackSize){
					wrong = true;
				}
			}
			if(!wrong){
				return baseRecipe;
			}
		}
		return null;
	}
	
	public BaseRecipe findRecipeByOutput(ItemStack output[]){
		for (BaseRecipe baseRecipe : craftingList) {
			boolean wrong = false;
			for(int i = 0; i < baseRecipe.getOutput(null).length; i++){
				if(output[i] == null || baseRecipe.getOutput(null)[i].getItem() != output[i].getItem()){
					wrong = true;
				}
			}
			if(!wrong){
				return baseRecipe;
			}
		}
		return null;
	}
	
	public boolean match(ItemStack  input[]){
		if(input == null){
			return false;
		}
        return findRecipe(input) != null;
    }
	
	public void addRecipe(BaseRecipe recipe){
		craftingList.add(recipe);
	}
	
}
