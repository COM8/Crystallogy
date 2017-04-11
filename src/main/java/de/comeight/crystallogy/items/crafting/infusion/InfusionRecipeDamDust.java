package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeDamDust extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeDamDust() {
		super("damDust", 230, new ItemStack(Items.FERMENTED_SPIDER_EYE),
				new ItemStack[]{ 	new ItemStack(ItemHandler.crystallDust_green, 2),
									new ItemStack(ItemHandler.crystallDust_blue, 2),},
				new ItemStack(ItemHandler.damDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeDamDust();
	}
	
}
