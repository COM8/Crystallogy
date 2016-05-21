package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeToolRod extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeToolRod() {
		super("toolRod", 200, new ItemStack(Items.STICK),
				new ItemStack[]{ new ItemStack(ItemHandler.pureCrystallDust, 2),},
				new ItemStack(ItemHandler.toolRod));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeToolRod();
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
