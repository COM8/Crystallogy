package de.comeight.crystallogy.blocks.container.slots;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class BrainSlot extends SpecificItemSlot {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BrainSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition, ItemHandler.entityBrain);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected int getMaxStackSize(){
		return 1;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean isItemValid(ItemStack stack) {
        return super.isItemValid(stack) && stack.getItemDamage() == 0;
    }
	
}
