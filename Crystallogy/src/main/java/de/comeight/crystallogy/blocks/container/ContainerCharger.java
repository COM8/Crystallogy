package de.comeight.crystallogy.blocks.container;

import com.sun.istack.internal.Nullable;

import de.comeight.crystallogy.tileEntitys.machines.TileEntityCharger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCharger extends BaseContainer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private int inputSlot0 = 0;
	private int inputSlot1 = 1;
	private int outputSlot = 2;
	
	private TileEntityCharger tileEntity;

	//-----------------------------------------------Constructor:-------------------------------------------
	public ContainerCharger(InventoryPlayer playerInventory, TileEntityCharger tileEntity) {
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
		this.addSlotToContainer(new Slot(tileEntity, inputSlot0, 56, 24));
		this.addSlotToContainer(new Slot(tileEntity, inputSlot1, 56, 45));
        this.addSlotToContainer(new ChargerOutputSlot(tileEntity, outputSlot, 116, 35));
	}
	
	@Nullable
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
        	ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index > 35)
            {
                if (!this.mergeItemStack(itemstack1, 0, 35, true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 35, 38, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
            	slot.putStack(itemstack1);
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
	
}
