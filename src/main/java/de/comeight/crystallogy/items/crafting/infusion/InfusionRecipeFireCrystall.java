package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InfusionRecipeFireCrystall extends InfusionRecipe {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeFireCrystall() {
		super("fireCrystall", 200);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeFireCrystall();
	}

	@Override
	public boolean match(ItemStack centerInput, ItemStack[] ingredients) {
		if(centerInput.getItem() != Item.getItemFromBlock(BlockHandler.crystall_red)){
			return false;
		}
		
		int lavaBucket = 0;
		int flintNSteel = 0;
		int blazePowder = 0;
		
		for (int i = 0; i < ingredients.length; i++) {
			if(ingredients[i] != null){
				if(ingredients[i].getItem() == Items.lava_bucket){
					lavaBucket++;
				}
				if(ingredients[i].getItem() == Items.flint_and_steel){
					flintNSteel++;
				}
				if(ingredients[i].getItem() == Items.blaze_powder){
					blazePowder++;
				}
			}
		}
		
		if(blazePowder != 2 || flintNSteel != 1 || lavaBucket != 1){
			return false;
		}
		
		output = new ItemStack(BlockHandler.fireCrystall); //TODO Return empty bucket 
		
		return true;
	}
}
