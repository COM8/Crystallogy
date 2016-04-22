package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeFireDust extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeFireDust() {
		super("fireDust", 230, new ItemStack(Items.blaze_powder),
				new ItemStack[]{ 	new ItemStack(ItemHandler.crystallDust_red, 1),
									new ItemStack(ItemHandler.fireDust, 3),},
				new ItemStack(ItemHandler.fireDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeFireDust();
	}
	
}
