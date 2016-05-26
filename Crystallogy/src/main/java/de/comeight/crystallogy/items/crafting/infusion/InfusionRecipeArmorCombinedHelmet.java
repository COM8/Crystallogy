package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.blocks.EnumCrystalColor;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeArmorCombinedHelmet extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeArmorCombinedHelmet() {
		super("armorCombinedHelmet", 1000, new ItemStack(ItemHandler.armorHelmet_red),
				new ItemStack[]{ 	new ItemStack(ItemHandler.armorHelmet_green, 1),
									new ItemStack(Items.DIAMOND_HELMET, 1),
									EnumCrystalColor.GRAY.getStack(new ItemStack(ItemHandler.armorPlate, 2)),},
				new ItemStack(ItemHandler.armorHelmet_hunter));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeArmorCombinedHelmet();
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
	
}
