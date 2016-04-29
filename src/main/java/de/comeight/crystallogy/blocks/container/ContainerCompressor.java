package de.comeight.crystallogy.blocks.container;

import de.comeight.crystallogy.tileEntitys.machines.TileEntityCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;

public class ContainerCompressor extends Container{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private int inputSlot = 36;
	private int outputSlot = 37;
	
	private int[] cachedFields;
	private TileEntityCompressor tileEntity;

	//-----------------------------------------------Constructor:-------------------------------------------
	public ContainerCompressor(InventoryPlayer playerInventory, TileEntityCompressor tileEntity) {
		this.tileEntity = tileEntity;
		addCrystallCrusherSlots(playerInventory);
		addSlostPlayerInventorry(playerInventory);
		addSlotsPlayerHotbar(playerInventory);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tileEntity.isUseableByPlayer(playerIn);
	}
	
	private void addSlostPlayerInventorry(InventoryPlayer playerInventory){
		for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
	}
	
	private void addSlotsPlayerHotbar(InventoryPlayer playerInventory){
		for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
	}
	
	private void addCrystallCrusherSlots(InventoryPlayer playerInventory){
        this.addSlotToContainer(new Slot(tileEntity, 0, 56, 35));
        this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, tileEntity, 1, 116, 35));
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack stack = null;
        Slot slot = (Slot) inventorySlots.get(index);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slot != null && slot.getHasStack()) {
                ItemStack stackInSlot = slot.getStack();
                stack = stackInSlot.copy();

                //merges the item into player inventory since its in the tileEntity
                if (index < tileEntity.getSizeInventory()) {
                        if (!this.mergeItemStack(stackInSlot, tileEntity.getSizeInventory(), 36+tileEntity.getSizeInventory(), true)) {
                                return null;
                        }
                }
                //places it into the tileEntity is possible since its in the player inventory
                else if (!this.mergeItemStack(stackInSlot, 0, 2, false)) {
                        return null;
                }

                if (stackInSlot.stackSize == 0) {
                        slot.putStack(null);
                } else {
                        slot.onSlotChanged();
                }

                if (stackInSlot.stackSize == stack.stackSize) {
                        return null;
                }
                slot.onPickupFromSlot(playerIn, stackInSlot);
        }
        return stack;
	}

	
}
