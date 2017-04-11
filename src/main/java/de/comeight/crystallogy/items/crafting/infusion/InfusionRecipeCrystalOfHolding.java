package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeCrystalOfHolding extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeCrystalOfHolding() {
		super("crystalOfHolding", 450, new ItemStack(Items.WHEAT_SEEDS),
				new ItemStack[]{ 	new ItemStack(BlockHandler.crystall_red),
									new ItemStack(BlockHandler.crystall_blue),
									new ItemStack(BlockHandler.crystall_green),
									new ItemStack(BlockHandler.crystall_yellow)},
				new ItemStack(BlockHandler.crystalOfHolding));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeCrystalOfHolding();
	}
	
}
