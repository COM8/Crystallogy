package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.blocks.EnumCrystalColor;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeArmorCombinedChestplate extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeArmorCombinedChestplate() {
		super("armorCombinedChestplate", 1000, new ItemStack(ItemHandler.armorChestplate_red),
				new ItemStack[]{ 	new ItemStack(ItemHandler.armorChestplate_green, 1),
									new ItemStack(Items.DIAMOND_CHESTPLATE, 1),
									EnumCrystalColor.GRAY.getStack(new ItemStack(ItemHandler.armorPlate, 2)),},
				new ItemStack(ItemHandler.armorChestplate_combined));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeArmorCombinedChestplate();
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
	
}
