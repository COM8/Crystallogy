package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipePoisDust extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipePoisDust() {
		super("poisDust", 230, new ItemStack(Items.SPIDER_EYE),
				new ItemStack[]{ 	new ItemStack(ItemHandler.crystallDust_green, 3),
									new ItemStack(ItemHandler.crystallDust_yellow, 1),},
				new ItemStack(ItemHandler.poisDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipePoisDust();
	}
	
}
