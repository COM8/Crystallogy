package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.blocks.EnumCrystalColor;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeArmorCombinedBoots extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeArmorCombinedBoots() {
		super("armorCombinedBoots", 1000, new ItemStack(ItemHandler.armorBoots_red),
				new ItemStack[]{ 	new ItemStack(ItemHandler.armorBoots_green, 1),
									new ItemStack(Items.DIAMOND_BOOTS, 1),
									EnumCrystalColor.GRAY.getStack(new ItemStack(ItemHandler.armorPlate, 2)),},
				new ItemStack(ItemHandler.armorBoots_combined));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeArmorCombinedBoots();
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
	
}
