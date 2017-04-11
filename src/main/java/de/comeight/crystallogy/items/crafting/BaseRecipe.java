package de.comeight.crystallogy.items.crafting;

import net.minecraft.item.ItemStack;

public abstract class BaseRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public ItemStack input[];
	protected ItemStack output[];
	public float experience;
	public int totalCookTime;

	//-----------------------------------------------Constructor:-------------------------------------------
    public BaseRecipe(ItemStack input[], ItemStack output[], float experience, int totalCookTime){
    	this.input = input;
    	this.output = output;
    	this.experience = experience;
    	this.totalCookTime = totalCookTime;
    }
    
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
    public ItemStack[] getOutput(ItemStack[] input){
    	return output;
    }

    //-----------------------------------------------Sonstige Methoden:-------------------------------------
    
}
