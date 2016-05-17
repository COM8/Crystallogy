package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeEnderDust extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeEnderDust() {
		super("enderDust", 230, new ItemStack(Items.ender_eye),
				new ItemStack[]{ 	new ItemStack(ItemHandler.crystallDust_blue, 2),
									new ItemStack(ItemHandler.crystallDust_red),
									new ItemStack(Items.ender_pearl),
									},
				new ItemStack(ItemHandler.enderDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeEnderDust();
	}
	
}
