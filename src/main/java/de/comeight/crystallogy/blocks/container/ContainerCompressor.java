package de.comeight.crystallogy.blocks.container;

import de.comeight.crystallogy.tileEntitys.machines.TileEntityCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;

public class ContainerCompressor extends BaseContainer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private int inputSlot = 0;
	private int outputSlot = 1;
	
	private TileEntityCompressor tileEntity;

	//-----------------------------------------------Constructor:-------------------------------------------
	public ContainerCompressor(InventoryPlayer playerInventory, TileEntityCompressor tileEntity) {
		super(playerInventory);
		
		this.tileEntity = tileEntity;
		addCompressorSlots(playerInventory);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tileEntity.isUseableByPlayer(playerIn);
	}
	
	private void addCompressorSlots(InventoryPlayer playerInventory){
        this.addSlotToContainer(new Slot(tileEntity, inputSlot, 56, 35));
        this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, tileEntity, outputSlot, 116, 35));
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
