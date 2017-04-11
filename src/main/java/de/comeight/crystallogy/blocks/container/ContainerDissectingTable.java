package de.comeight.crystallogy.blocks.container;

import de.comeight.crystallogy.blocks.container.slots.BrainSlot;
import de.comeight.crystallogy.blocks.container.slots.SpecificItemSlot;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityDissectingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerDissectingTable extends BaseContainer{
	//-----------------------------------------------Variabeln:---------------------------------------------	
	private int miniBrainSlot = 0;
	private int knifeSlot = 1;
	private int mainBrainSlot = 2;
	
	private TileEntityDissectingTable tileEntity;

	//-----------------------------------------------Constructor:-------------------------------------------
	public ContainerDissectingTable(InventoryPlayer playerInventory, TileEntityDissectingTable tileEntity) {
		super(playerInventory);
		
		this.tileEntity = tileEntity;
		addDissectingTableSlots(playerInventory);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tileEntity.isUseableByPlayer(playerIn);
	}
	
	private void addDissectingTableSlots(InventoryPlayer playerInventory){
        this.addSlotToContainer(new SpecificItemSlot(tileEntity, miniBrainSlot, 8, 56, ItemHandler.entityBrain));
        this.addSlotToContainer(new SpecificItemSlot(tileEntity, knifeSlot, 152, 56, ItemHandler.entityCrystalKnife));
        this.addSlotToContainer(new BrainSlot(tileEntity, mainBrainSlot, 80, 37));
	}

}
