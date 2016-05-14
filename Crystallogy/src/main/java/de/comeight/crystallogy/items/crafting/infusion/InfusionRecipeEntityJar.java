package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeEntityJar extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeEntityJar() {
		super("entityJar", 500, new ItemStack(Items.glass_bottle),
				new ItemStack[]{ 	new ItemStack(ItemHandler.pureCrystallDust, 2),
									new ItemStack(Items.ender_eye, 1),
									new ItemStack(Items.blaze_powder, 1),},
				new ItemStack(BlockHandler.entityJar));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeEntityJar();
	}
	
}
