package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeArmorCatalyst extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeArmorCatalyst() {
		super("armorCatalyst", 200, new ItemStack(ItemHandler.crystallDust_green),
				new ItemStack[]{ 	new ItemStack(ItemHandler.energyDust, 2),
									new ItemStack(ItemHandler.crystallDust_yellow),
									new ItemStack(Items.DIAMOND)},
				new ItemStack(ItemHandler.armorCatalys));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeArmorCatalyst();
	}
	
}
