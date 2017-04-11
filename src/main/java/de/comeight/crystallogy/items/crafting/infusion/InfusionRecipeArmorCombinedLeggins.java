package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.blocks.EnumCrystalColor;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeArmorCombinedLeggins extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeArmorCombinedLeggins() {
		super("armorCombinedLeggins", 1000, new ItemStack(ItemHandler.armorLeggins_red),
				new ItemStack[]{ 	new ItemStack(ItemHandler.armorLeggins_green, 1),
									new ItemStack(Items.DIAMOND_LEGGINGS, 1),
									EnumCrystalColor.GRAY.getStack(new ItemStack(ItemHandler.armorPlate, 2)),},
				new ItemStack(ItemHandler.armorLeggins_hunter));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeArmorCombinedLeggins();
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
	
}
