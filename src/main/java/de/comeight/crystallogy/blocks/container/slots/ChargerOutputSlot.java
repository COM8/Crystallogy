package de.comeight.crystallogy.blocks.container.slots;

import de.comeight.crystallogy.handler.ChargerRecipeHandler;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ChargerOutputSlot extends BaseOutputSlot {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ChargerOutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected float getXPFromRecipeHandler(ItemStack stack) {
		return ChargerRecipeHandler.INSTANCE.getExperience(new ItemStack[]{stack});
	}
	
}
