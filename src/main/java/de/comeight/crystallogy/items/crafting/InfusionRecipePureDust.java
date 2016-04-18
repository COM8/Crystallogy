package de.comeight.crystallogy.items.crafting;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipePureDust extends InfusionRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
		//-----------------------------------------------Constructor:-------------------------------------------
		public InfusionRecipePureDust() {
			super("pureDust", 85);
		}
		
		//-----------------------------------------------Set-, Get-Methoden:------------------------------------

		
		//-----------------------------------------------Sonstige Methoden:-------------------------------------
		@Override
		public InfusionRecipe getRecipe() {
			return new InfusionRecipePureDust();
		}

		@Override
		public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
			if(centerInput.getItem() != Items.water_bucket){
				return false;
			}
			
			int waterBucket = 0;
			int crystallDusts = 0;
			
			for (int i = 0; i < ingredients.length; i++) {
				if(ingredients[i] != null){
					if(ingredients[i].getItem() == ItemHandler.crystallDust_blue || ingredients[i].getItem() == ItemHandler.crystallDust_red || ingredients[i].getItem() == ItemHandler.crystallDust_green || ingredients[i].getItem() == ItemHandler.crystallDust_yellow){
						crystallDusts++;
					}
				}
			}
			
			if(crystallDusts < 1){
				return false;
			}
			
			output = new ItemStack(ItemHandler.pureCrystallDust);
			output.stackSize = crystallDusts;
			
			return true;
		}
}
