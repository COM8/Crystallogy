package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;

public class InfusionRecipeCrystalKnifeBlade extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeCrystalKnifeBlade() {
		super("crystalKnife", 500, new ItemStack(BlockHandler.crystall_red),
				new ItemStack[]{ 	new ItemStack(ItemHandler.crystallDust_red, 3),
									new ItemStack(ItemHandler.crystallSwordBlade, 1),},
				new ItemStack(ItemHandler.crystalKnifeBlade));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeCrystalKnifeBlade();
	}
	
}
