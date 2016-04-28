package de.comeight.crystallogy.items.crafting;

import net.minecraft.item.ItemStack;

public class RecipeCompressor {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public ItemStack input;
	public ItemStack output;
	public float experience;
	public int totalCookTime;

	//-----------------------------------------------Constructor:-------------------------------------------
    public RecipeCompressor(ItemStack input, ItemStack output, float experience, int totalCookTime){
    	this.input = input;
    	this.output = output;
    	this.experience = experience;
    	this.totalCookTime = totalCookTime;
    }
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


}
