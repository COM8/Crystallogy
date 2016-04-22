package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeCrystalKnife extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeCrystalKnife() {
		super("crystalKnife", 500, new ItemStack(ItemHandler.crystallSwordBlade),
				new ItemStack[]{ 	new ItemStack(Items.blaze_powder, 2),
									new ItemStack(Items.ender_eye, 1),
									new ItemStack(Items.diamond_sword, 1),},
				new ItemStack(ItemHandler.crystallKnife));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeCrystalKnife();
	}
	
}
