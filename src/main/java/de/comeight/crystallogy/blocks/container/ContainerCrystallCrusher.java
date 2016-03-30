package de.comeight.crystallogy.blocks.container;

import de.comeight.crystallogy.tileEntitys.TileEntityCrystallCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCrystallCrusher extends Container{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private int inputSlot;
	private int outputSlot;
	private int progressSlot;
	
	private TileEntityCrystallCrusher tileEntity;

	//-----------------------------------------------Constructor:-------------------------------------------
	public ContainerCrystallCrusher(InventoryPlayer playerInventory, TileEntityCrystallCrusher tileEntity) {
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
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.crafters.size(); i++){
			ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.inputSlot != this.tileEntity.getField(0))
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.getField(0));
            }

            if (this.outputSlot != this.tileEntity.getField(1))
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntity.getField(1));
            }

            if (this.progressSlot != this.tileEntity.getField(2))
            {
                icrafting.sendProgressBarUpdate(this, 3, this.tileEntity.getField(2));
            }
		}
		
		this.inputSlot = tileEntity.getField(0);
		this.outputSlot = tileEntity.getField(1);
		this.progressSlot = tileEntity.getField(2);
	}

	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.tileEntity.setField(id, data);
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
        Slot slotObject = (Slot) inventorySlots.get(index);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
                ItemStack stackInSlot = slotObject.getStack();
                stack = stackInSlot.copy();

                //merges the item into player inventory since its in the tileEntity
                if (index < tileEntity.getSizeInventory()) {
                        if (!this.mergeItemStack(stackInSlot, tileEntity.getSizeInventory(), 36+tileEntity.getSizeInventory(), true)) {
                                return null;
                        }
                }
                //places it into the tileEntity is possible since its in the player inventory
                else if (!this.mergeItemStack(stackInSlot, 0, tileEntity.getSizeInventory(), false)) {
                        return null;
                }

                if (stackInSlot.stackSize == 0) {
                        slotObject.putStack(null);
                } else {
                        slotObject.onSlotChanged();
                }

                if (stackInSlot.stackSize == stack.stackSize) {
                        return null;
                }
                slotObject.onPickupFromSlot(playerIn, stackInSlot);
        }
        return stack;
	}

	
}
