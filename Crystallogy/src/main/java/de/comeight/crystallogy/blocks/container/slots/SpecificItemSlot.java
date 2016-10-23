package de.comeight.crystallogy.blocks.container.slots;

import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SpecificItemSlot extends Slot {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected Item item;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public SpecificItemSlot(IInventory inventoryIn, int index, int xPosition, int yPosition, Item item) {
		super(inventoryIn, index, xPosition, yPosition);
		this.item = item;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	protected int getMaxStackSize(){
		return 64;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean isItemValid(ItemStack stack) {
		if(stack != null && stack.getItem() != null && stack.getItem() == item){
			if(stack.stackSize <= getMaxStackSize()){
				return true;
			}
		}
		return false;
	}
	
}
