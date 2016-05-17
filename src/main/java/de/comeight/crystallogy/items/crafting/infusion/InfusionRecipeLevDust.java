package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeLevDust extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeLevDust() {
		super("levDust", 230, new ItemStack(Items.feather),
				new ItemStack[]{ 	new ItemStack(ItemHandler.crystallDust_yellow, 3),
									new ItemStack(Items.ghast_tear, 1),},
				new ItemStack(ItemHandler.levDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeLevDust();
	}
	
}
