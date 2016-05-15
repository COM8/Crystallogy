package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeBlindDust extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeBlindDust() {
		super("blindDust", 230, new ItemStack(Items.iron_helmet),
				new ItemStack[]{ 	new ItemStack(ItemHandler.crystallDust_blue, 3),
									new ItemStack(Blocks.obsidian)},
				new ItemStack(ItemHandler.blindDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeBlindDust();
	}
	
}
