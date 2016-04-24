package de.comeight.crystallogy.handler;

import java.util.ArrayList;

import de.comeight.crystallogy.items.crafting.RecipeCrystalCrusher;
import net.minecraft.item.ItemStack;

public class CrystalCrusherRecipeHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static CrystalCrusherRecipeHandler INSTANCE = new CrystalCrusherRecipeHandler();
	private ArrayList<RecipeCrystalCrusher> crushList = new ArrayList<RecipeCrystalCrusher>();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystalCrusherRecipeHandler() {
		addRecipe(new RecipeCrystalCrusher(new ItemStack(BlockHandler.crystall_blue), new ItemStack(ItemHandler.crystallDust_blue), 1.0F, 200));
		addRecipe(new RecipeCrystalCrusher(new ItemStack(BlockHandler.crystall_green), new ItemStack(ItemHandler.crystallDust_green), 1.0F, 200));
		addRecipe(new RecipeCrystalCrusher(new ItemStack(BlockHandler.crystall_red), new ItemStack(ItemHandler.crystallDust_red), 1.0F, 200));
		addRecipe(new RecipeCrystalCrusher(new ItemStack(BlockHandler.crystall_yellow), new ItemStack(ItemHandler.crystallDust_yellow), 1.0F, 200));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public ArrayList<RecipeCrystalCrusher> getRecipes() {
		return crushList;
	}
	
	public ItemStack getResult(ItemStack  input){
		if(input == null){
			return null;
		}
		for (RecipeCrystalCrusher recipeCrystalCrusher : crushList) {
			if(recipeCrystalCrusher.input.getItem() == input.getItem()){
				return recipeCrystalCrusher.output;
			}
		}
		return null;
	}
	
	public float getExperience(ItemStack  input){
		if(input == null){
			return 0.0F;
		}
		for (RecipeCrystalCrusher recipeCrystalCrusher : crushList) {
			if(recipeCrystalCrusher.input.getItem() == input.getItem()){
				return recipeCrystalCrusher.experience;
			}
		}
		return 0.0F;
	}
	
	public int getTotalCookTime(ItemStack  input){
		if(input == null){
			return 0;
		}
		for (RecipeCrystalCrusher recipeCrystalCrusher : crushList) {
			if(recipeCrystalCrusher.input.getItem() == input.getItem()){
				return recipeCrystalCrusher.totalCookTime;
			}
		}
		return 0;
	}
	
	public ArrayList<ItemStack> getInputs(){
		ArrayList<ItemStack> inputs = new ArrayList<ItemStack>();
		for (RecipeCrystalCrusher recipeCrystalCrusher : crushList) {
			inputs.add(recipeCrystalCrusher.input);
		}
		return inputs;
	}
	
	public ArrayList<ItemStack> getOutputs(){
		ArrayList<ItemStack> outputs = new ArrayList<ItemStack>();
		for (RecipeCrystalCrusher recipeCrystalCrusher : crushList) {
			outputs.add(recipeCrystalCrusher.input);
		}
		return outputs;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public boolean match(ItemStack  input){
		if(input == null){
			return false;
		}
		for (RecipeCrystalCrusher recipeCrystalCrusher : crushList) {
			if(recipeCrystalCrusher.input.getItem() == input.getItem()){
				return true;
			}
		}
		return false;
	}
	
	public void addRecipe(RecipeCrystalCrusher recipe){
		crushList.add(recipe);
	}
	
}
