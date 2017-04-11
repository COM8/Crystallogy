package de.comeight.crystallogy.items.crafting.infusion;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InfusionRecipeMachineBlock extends InfusionRecipeSimple {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionRecipeMachineBlock() {
		super("machineBlock", 150, new ItemStack(Blocks.IRON_BLOCK),
				new ItemStack[]{ 	new ItemStack(Items.REDSTONE, 2),
									new ItemStack(ItemHandler.crystallDust_blue),
									new ItemStack(ItemHandler.crystallDust_red)},
				new ItemStack(BlockHandler.machineBlock));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public InfusionRecipe getRecipe() {
		return new InfusionRecipeMachineBlock();
	}
	
}
