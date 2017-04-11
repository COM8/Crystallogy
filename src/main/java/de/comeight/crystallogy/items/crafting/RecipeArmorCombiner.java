package de.comeight.crystallogy.items.crafting;

import de.comeight.crystallogy.items.armor.Armor_combined;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class RecipeArmorCombiner extends BaseRecipe{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public Armor_combined armor;
	public int numCatalyst;
	
	//-----------------------------------------------Constructor:-------------------------------------------
    public RecipeArmorCombiner(int numCatalyst, Armor_combined armor, float experience, int totalCookTime){
    	super(null, null, experience, totalCookTime);
    	this.armor = armor;
    	this.numCatalyst = numCatalyst;
    }
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
    @Override
    public ItemStack[] getOutput(ItemStack[] input) {
    	if(input[0].getItem() instanceof ItemArmor){
    		ItemStack[] ret = new ItemStack[]{input[2].copy()};
    		return ret;
    	}
    	return null;
    }
    
    //-----------------------------------------------Sonstige Methoden:-------------------------------------
    
}
