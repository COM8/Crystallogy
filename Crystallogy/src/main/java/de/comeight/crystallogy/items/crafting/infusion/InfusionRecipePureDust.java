package de.comeight.crystallogy.items.crafting.infusion;

import java.util.ArrayList;

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
		@Override
		public ArrayList<ArrayList<ItemStack>> getInputsJEI() {
			ArrayList<ArrayList<ItemStack>> ret = new ArrayList<ArrayList<ItemStack>>();
			ret.add(new ArrayList<ItemStack>());
			ret.get(0).add(new ItemStack(Items.WATER_BUCKET));
			ret.add(new ArrayList<ItemStack>());
			ret.get(1).add(new ItemStack(ItemHandler.crystallDust_red));
			ret.get(1).add(new ItemStack(ItemHandler.crystallDust_blue));
			ret.get(1).add(new ItemStack(ItemHandler.crystallDust_green));
			ret.get(1).add(new ItemStack(ItemHandler.crystallDust_yellow));
			return ret;
		}

		@Override
		public ArrayList<ItemStack> getOutputJEI() {
			ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
			ret.add(new ItemStack(ItemHandler.pureCrystallDust));
			return ret;
		}
		
		//-----------------------------------------------Sonstige Methoden:-------------------------------------
		@Override
		public InfusionRecipe getRecipe() {
			return new InfusionRecipePureDust();
		}

		@Override
		public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
			if(centerInput.getItem() != Items.WATER_BUCKET){
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
