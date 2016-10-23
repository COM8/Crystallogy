package de.comeight.crystallogy.blocks.container;

import de.comeight.crystallogy.blocks.container.slots.CrusherOutputSlot;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCrystallCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerCrystallCrusher extends BaseContainer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private int inputSlot = 0;
	private int outputSlot = 1;
	
	private TileEntityCrystallCrusher tileEntity;

	//-----------------------------------------------Constructor:-------------------------------------------
	public ContainerCrystallCrusher(InventoryPlayer playerInventory, TileEntityCrystallCrusher tileEntity) {
		super(playerInventory);
		
		this.tileEntity = tileEntity;
		addCrystallCrusherSlots(playerInventory);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tileEntity.isUseableByPlayer(playerIn);
	}
	
	private void addCrystallCrusherSlots(InventoryPlayer playerInventory){
        this.addSlotToContainer(new Slot(tileEntity, inputSlot, 56, 35));
        this.addSlotToContainer(new CrusherOutputSlot(tileEntity, outputSlot, 116, 35));
	}

}
