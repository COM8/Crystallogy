package de.comeight.crystallogy.items.crafting;

import net.minecraft.item.ItemStack;

public class RecipeCrystalCrusher {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public ItemStack input;
	public ItemStack output;
	public float experience;
	public int totalCookTime;

	//-----------------------------------------------Constructor:-------------------------------------------
    public RecipeCrystalCrusher(ItemStack input, ItemStack output, float experience, int totalCookTime){
    	this.input = input;
    	this.output = output;
    	this.experience = experience;
    	this.totalCookTime = totalCookTime;
    }
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


}
