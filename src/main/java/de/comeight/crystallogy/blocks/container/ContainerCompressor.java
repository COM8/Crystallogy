package de.comeight.crystallogy.blocks.container;

import de.comeight.crystallogy.blocks.container.slots.CompressorOutputSlot;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCompressor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

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
        this.addSlotToContainer(new CompressorOutputSlot(tileEntity, outputSlot, 116, 35));
	}

}
