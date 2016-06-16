package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeCombinedArmorCompound extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeCombinedArmorCompound() {
		super("combinedArmorCompound", 230, new ItemStack(Items.LEATHER),
				new ItemStack[]{ 	new ItemStack(ItemHandler.armorPlate, 2, 4),
									new ItemStack(Items.DIAMOND),
									new ItemStack(BlockHandler.crystalGlas, 1, 1)},
				new ItemStack(ItemHandler.combinedArmorCompound));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeCombinedArmorCompound();
	}
	
}
