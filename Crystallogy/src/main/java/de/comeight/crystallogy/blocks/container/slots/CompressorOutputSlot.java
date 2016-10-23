package de.comeight.crystallogy.blocks.container.slots;

import de.comeight.crystallogy.handler.CompressorRecipeHandler;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class CompressorOutputSlot extends BaseOutputSlot {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CompressorOutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected float getXPFromRecipeHandler(ItemStack stack) {
		return CompressorRecipeHandler.INSTANCE.getExperience(new ItemStack[]{stack});
	}
	
}
