package de.comeight.crystallogy.handler;

import java.util.ArrayList;

import de.comeight.crystallogy.items.crafting.RecipeCompressor;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CompressorRecipeHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static CompressorRecipeHandler INSTANCE = new CompressorRecipeHandler();
	private ArrayList<RecipeCompressor> compressList = new ArrayList<RecipeCompressor>();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CompressorRecipeHandler() {
		addRecipe(new RecipeCompressor(new ItemStack(ItemHandler.energyDust, 8), new ItemStack(ItemHandler.energyCrystal, 1, ItemHandler.energyCrystal.getMaxDamage()), 1.0F, 200));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public ArrayList<RecipeCompressor> getRecipes() {
		return compressList;
	}
	
	public int getNumberOfInputItems(ItemStack  input){
		if(input == null){
			return -1;
		}
		for (RecipeCompressor recipeCompressor : compressList) {
			if(recipeCompressor.input.getItem() == input.getItem() && recipeCompressor.input.stackSize <= input.stackSize){
				return recipeCompressor.input.stackSize;
			}
		}
		return -1;
	}
	
	public ItemStack getResult(ItemStack  input){
		if(input == null){
			return null;
		}
		for (RecipeCompressor recipeCompressor : compressList) {
			if(recipeCompressor.input.getItem() == input.getItem() && recipeCompressor.input.stackSize <= input.stackSize){
				return recipeCompressor.output;
			}
		}
		return null;
	}
	
	public float getExperience(ItemStack  input){
		if(input == null){
			return 0.0F;
		}
		for (RecipeCompressor recipeCompressor : compressList) {
			if(recipeCompressor.input.getItem() == input.getItem()){
				return recipeCompressor.experience;
			}
		}
		return 0.0F;
	}
	
	public int getTotalCookTime(ItemStack  input){
		if(input == null){
			return 0;
		}
		for (RecipeCompressor recipeCompressor : compressList) {
			if(recipeCompressor.input.getItem() == input.getItem()){
				return recipeCompressor.totalCookTime;
			}
		}
		return 0;
	}
	
	public ArrayList<ItemStack> getInputs(){
		ArrayList<ItemStack> inputs = new ArrayList<ItemStack>();
		for (RecipeCompressor recipeCompressor : compressList) {
			inputs.add(recipeCompressor.input);
		}
		return inputs;
	}
	
	public ArrayList<ItemStack> getOutputs(){
		ArrayList<ItemStack> outputs = new ArrayList<ItemStack>();
		for (RecipeCompressor recipeCompressor : compressList) {
			outputs.add(recipeCompressor.input);
		}
		return outputs;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public boolean match(ItemStack  input){
		if(input == null){
			return false;
		}
		for (RecipeCompressor recipeCompressor : compressList) {
			if(recipeCompressor.input.getItem() == input.getItem()){
				return true;
			}
		}
		return false;
	}
	
	public void addRecipe(RecipeCompressor recipe){
		compressList.add(recipe);
	}
	
}
