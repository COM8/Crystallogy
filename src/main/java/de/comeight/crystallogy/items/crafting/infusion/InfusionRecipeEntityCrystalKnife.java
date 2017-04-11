package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeEntityCrystalKnife extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeEntityCrystalKnife() {
		super("entityCrystalKnife", 500, new ItemStack(ItemHandler.crystalKnifeBlade),
				new ItemStack[]{ 	new ItemStack(ItemHandler.toolRod, 1),
									new ItemStack(Items.ENDER_EYE, 1),
									new ItemStack(ItemHandler.crystallDust_blue, 1),
									new ItemStack(Items.IRON_SWORD, 1),},
				new ItemStack(ItemHandler.entityCrystalKnife));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeEntityCrystalKnife();
	}
	
}
