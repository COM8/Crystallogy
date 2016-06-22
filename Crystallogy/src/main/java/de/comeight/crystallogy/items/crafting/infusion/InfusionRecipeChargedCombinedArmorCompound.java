package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeChargedCombinedArmorCompound extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeChargedCombinedArmorCompound() {
		super("chargedCombinedArmorCompound", 235, new ItemStack(ItemHandler.combinedArmorMesh),
				new ItemStack[]{ 	new ItemStack(ItemHandler.armorPlate, 1, 4),
									new ItemStack(Items.DIAMOND),
									new ItemStack(Items.LEATHER),
									new ItemStack(Items.REDSTONE)},
				new ItemStack(ItemHandler.chargedCombinedArmorMesh));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeChargedCombinedArmorCompound();
	}
	
}
