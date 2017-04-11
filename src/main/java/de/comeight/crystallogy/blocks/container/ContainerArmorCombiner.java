package de.comeight.crystallogy.blocks.container;

import javax.annotation.Nullable;

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
            else if (!this.mergeItemStack(itemstack1, 35, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }
	
}
