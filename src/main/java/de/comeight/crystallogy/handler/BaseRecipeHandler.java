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
	
	public int getNumberOfInputItems(ItemStack  input){
		if(input == null){
			return -1;
		}
		for (BaseRecipe baseRecipe : craftingList) {
			if(baseRecipe.input.getItem() == input.getItem() && baseRecipe.input.stackSize <= input.stackSize){
				return baseRecipe.input.stackSize;
			}
		}
		return -1;
	}
	
	public ItemStack getResult(ItemStack  input){
		if(input == null){
			return null;
		}
		for (BaseRecipe baseRecipe : craftingList) {
			if(baseRecipe.input.getItem() == input.getItem() && baseRecipe.input.stackSize <= input.stackSize){
				return baseRecipe.output;
			}
		}
		return null;
	}
	
	public float getExperience(ItemStack  input){
		if(input == null){
			return 0.0F;
		}
		for (BaseRecipe baseRecipe : craftingList) {
			if(baseRecipe.input.getItem() == input.getItem()){
				return baseRecipe.experience;
			}
		}
		return 0.0F;
	}
	
	public int getTotalCookTime(ItemStack  input){
		if(input == null){
			return 0;
		}
		for (BaseRecipe baseRecipe : craftingList) {
			if(baseRecipe.input.getItem() == input.getItem()){
				return baseRecipe.totalCookTime;
			}
		}
		return 0;
	}
	
	public ArrayList<ItemStack> getInputs(){
		ArrayList<ItemStack> inputs = new ArrayList<ItemStack>();
		for (BaseRecipe baseRecipe : craftingList) {
			inputs.add(baseRecipe.input);
		}
		return inputs;
	}
	
	public ArrayList<ItemStack> getOutputs(){
		ArrayList<ItemStack> outputs = new ArrayList<ItemStack>();
		for (BaseRecipe baseRecipe : craftingList) {
			outputs.add(baseRecipe.input);
		}
		return outputs;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public boolean match(ItemStack  input){
		if(input == null){
			return false;
		}
		for (BaseRecipe baseRecipe : craftingList) {
			if(baseRecipe.input.getItem() == input.getItem()){
				return true;
			}
		}
		return false;
	}
	
	public void addRecipe(BaseRecipe recipe){
		craftingList.add(recipe);
	}
	
}
