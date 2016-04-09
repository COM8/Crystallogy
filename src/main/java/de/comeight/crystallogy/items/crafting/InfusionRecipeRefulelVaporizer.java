package de.comeight.crystallogy.items.crafting;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InfusionRecipeRefulelVaporizer extends InfusionRecipe {
		//-----------------------------------------------Variabeln:---------------------------------------------
	
	
		//-----------------------------------------------Constructor:-------------------------------------------
		public InfusionRecipeRefulelVaporizer() {
			super("refulelVaporizer", 640);
		}
		
		//-----------------------------------------------Set-, Get-Methoden:------------------------------------

		
		//-----------------------------------------------Sonstige Methoden:-------------------------------------
		@Override
		public InfusionRecipe getRecipe() {
			return new InfusionRecipeRefulelVaporizer();
		}

		@Override
		public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
			if(centerInput.getItem() != ItemHandler.vaporizerDirection){
				return false;
			}
			
			int fireCrystall = 0;
			
			for (int i = 0; i < ingredients.length; i++) {
				if(ingredients[i] != null){
					if(ingredients[i].getItem() == Item.getItemFromBlock(BlockHandler.fireCrystall)){
						fireCrystall++;
					}
				}
			}
			
			if(fireCrystall != 1){
				return false;
			}
			
			output = centerInput;
			output.setItemDamage(0);
			
			return true;
		}

}
