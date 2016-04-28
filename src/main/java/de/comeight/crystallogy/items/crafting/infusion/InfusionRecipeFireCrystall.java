package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InfusionRecipeFireCrystall extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeFireCrystall() {
		super("fireCrystal", 200, new ItemStack(Item.getItemFromBlock(BlockHandler.crystall_red)),
				new ItemStack[]{ 	new ItemStack(Items.lava_bucket, 1),
									new ItemStack(Items.flint_and_steel, 1),
									new ItemStack(Items.blaze_powder, 2),},
				new ItemStack(BlockHandler.fireCrystall));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeFireCrystall();
	}

}
