package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipePlayerJar extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipePlayerJar() {
		super("playerJar", 500, new ItemStack(BlockHandler.entityJar),
				new ItemStack[]{ 	new ItemStack(ItemHandler.armorPlate, 2, 4),
									new ItemStack(Items.ENDER_EYE, 1),
									new ItemStack(Items.BLAZE_POWDER, 1),},
				new ItemStack(BlockHandler.playerJar));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipePlayerJar();
	}
	
}
