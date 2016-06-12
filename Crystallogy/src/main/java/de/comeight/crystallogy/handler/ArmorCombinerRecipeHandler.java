package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.items.armor.Armor_combined;
import de.comeight.crystallogy.items.crafting.BaseRecipe;
import de.comeight.crystallogy.items.crafting.RecipeArmorCombiner;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ArmorCombinerRecipeHandler extends BaseRecipeHandler{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static BaseRecipeHandler INSTANCE = new ArmorCombinerRecipeHandler();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ArmorCombinerRecipeHandler() {
		addRecipe(new RecipeArmorCombiner(1, ItemHandler.armorHelmet_combined, 1.0F, 200));
		addRecipe(new RecipeArmorCombiner(2, ItemHandler.armorChestplate_combined, 2.0F, 400));
		addRecipe(new RecipeArmorCombiner(2, ItemHandler.armorLeggins_combined, 2.0F, 400));
		addRecipe(new RecipeArmorCombiner(1, ItemHandler.armorBoots_combined, 1.0F, 200));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public int getNumberOfCatalys(ItemStack  input[]){
		if(input == null){
			return 0;
		}
		
		RecipeArmorCombiner recipe = (RecipeArmorCombiner) findRecipe(input);
		if(recipe == null){
			return 0;
		}
		return recipe.numCatalyst;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseRecipe findRecipe(ItemStack[] input) {
		for (BaseRecipe baseRecipe : craftingList) {
			RecipeArmorCombiner recipe = (RecipeArmorCombiner) baseRecipe;
			if(input[0] != null && input[0].getItem() instanceof ItemArmor && !(input[0].getItem() instanceof Armor_combined)){
				if(input[1] != null && input[1].getItem() == Items.DIAMOND && input[1].stackSize >= recipe.numCatalyst){
					if(input[2] != null && input[2].getItem() == recipe.armor && ((ItemArmor) input[0].getItem()).armorType == ((ItemArmor) input[2].getItem()).armorType){
						return baseRecipe;
					}
				}
			}
		}
		return null;
	}
	
}
