package de.comeight.crystallogy.blocks.container;

import de.comeight.crystallogy.tileEntitys.machines.TileEntityCharger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;

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
        this.addSlotToContainer(new SlotFurnaceOutput(playerInventory.player, tileEntity, outputSlot, 116, 35));
	}
	
}
