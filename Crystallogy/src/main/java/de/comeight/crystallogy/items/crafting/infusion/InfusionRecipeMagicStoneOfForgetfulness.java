package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class InfusionRecipeMagicStoneOfForgetfulness extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeMagicStoneOfForgetfulness() {
		super("magicStoneOfForgetfulness", 500, new ItemStack(Blocks.COBBLESTONE),
				new ItemStack[]{ new ItemStack(ItemHandler.pureCrystallDust, 4),},
				new ItemStack(ItemHandler.magicStoneOfForgetfulness, 8));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeMagicStoneOfForgetfulness();
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
