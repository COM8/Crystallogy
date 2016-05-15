package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeBadLuckDust extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeBadLuckDust() {
		super("badLuckDust", 230, new ItemStack(Items.fishing_rod),
				new ItemStack[]{ 	new ItemStack(ItemHandler.crystallDust_yellow, 3),
									new ItemStack(Items.carrot)},
				new ItemStack(ItemHandler.badLuckDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeBadLuckDust();
	}
	
}
