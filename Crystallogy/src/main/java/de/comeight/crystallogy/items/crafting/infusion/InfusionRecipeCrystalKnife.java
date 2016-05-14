package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeCrystalKnife extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeCrystalKnife() {
		super("crystalKnife", 500, new ItemStack(ItemHandler.crystalKnifeBlade),
				new ItemStack[]{ 	new ItemStack(ItemHandler.toolRod, 1),
									new ItemStack(Items.ender_eye, 1),
									new ItemStack(Items.blaze_powder, 1),
									new ItemStack(Items.diamond_sword, 1),},
				new ItemStack(ItemHandler.playerCrystalKnife));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeCrystalKnife();
	}
	
}
