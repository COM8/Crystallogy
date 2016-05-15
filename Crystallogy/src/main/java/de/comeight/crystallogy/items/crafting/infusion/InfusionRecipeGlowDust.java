package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeGlowDust extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeGlowDust() {
		super("glowDust", 230, new ItemStack(Items.glowstone_dust),
				new ItemStack[]{ 	new ItemStack(ItemHandler.crystallDust_yellow, 2),
									new ItemStack(Blocks.glowstone, 1),
									new ItemStack(Blocks.gold_block, 1)},
				new ItemStack(ItemHandler.glowDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeGlowDust();
	}
	
}
