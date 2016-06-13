package de.comeight.crystallogy.blocks.container;

import de.comeight.crystallogy.tileEntitys.machines.TileEntityArmorCombiner;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

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
	
}
