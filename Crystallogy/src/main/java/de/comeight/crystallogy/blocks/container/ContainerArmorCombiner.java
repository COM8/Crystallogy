package de.comeight.crystallogy.blocks.container;

import de.comeight.crystallogy.tileEntitys.machines.TileEntityArmorCombiner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerArmorCombiner extends BaseContainer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private int inputSlot = 0;
	private int catalyst = 1;
	private int slotArmorCombined = 2;
	
	private TileEntityArmorCombiner tileEntity;

	//-----------------------------------------------Constructor:-------------------------------------------
	public ContainerArmorCombiner(InventoryPlayer playerInventory, TileEntityArmorCombiner tileEntity) {
		super(playerInventory);
		
		this.tileEntity = tileEntity;
		addArmorCombiner(playerInventory);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tileEntity.isUseableByPlayer(playerIn);
	}
	
	private void addArmorCombiner(InventoryPlayer playerInventory){
        this.addSlotToContainer(new Slot(tileEntity, inputSlot, 44, 35));
        this.addSlotToContainer(new Slot(tileEntity, slotArmorCombined, 116, 35));
        this.addSlotToContainer(new Slot(tileEntity, catalyst, 80, 59));
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
