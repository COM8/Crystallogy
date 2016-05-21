package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipePlayerCrystalKnife extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipePlayerCrystalKnife() {
		super("playerCrystalKnife", 500, new ItemStack(ItemHandler.crystalKnifeBlade),
				new ItemStack[]{ 	new ItemStack(ItemHandler.toolRod, 1),
									new ItemStack(Items.ENDER_EYE, 1),
									new ItemStack(Items.BLAZE_POWDER, 1),
									new ItemStack(Items.DIAMOND_SWORD, 1),},
				new ItemStack(ItemHandler.playerCrystalKnife));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipePlayerCrystalKnife();
	}
	
}
