package de.comeight.crystallogy.tileEntitys;

import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.Constants;

public abstract class TileEntityInventory extends BaseTileEntity implements IInventory {

	//-----------------------------------------------Variabeln:---------------------------------------------
	protected ItemStack[] inventory;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityInventory(int inventorySize) {
		this.inventory = new ItemStack[inventorySize];
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public String getName() {
		return "Bugy Name :P";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString("Bug Display Name :P");
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return inventory[index];
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.inventory[index] = stack;
		if(stack != null && stack.stackSize > getInventoryStackLimit()){
			stack.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if(worldObj.getTileEntity(pos) == this && player.getDistanceSq(pos.getX() +0.5, pos.getY() + 0.5, pos.getZ() + 0.5) < 64){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new SPacketUpdateTileEntity(getPos(), 0, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt){
        super.onDataPacket(net, pkt);
        readFromNBT(pkt.getNbtCompound());
    }
	
	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack stack = getStackInSlot(index);
        if (stack != null)
        {
            if (stack.stackSize <= count)
                setInventorySlotContents(index, null);
            else
            {
                stack = stack.splitStack(count);
                if (stack.stackSize == 0)
                    setInventorySlotContents(index, null);
            }
        }
        return stack;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = getStackInSlot(index);
		if(stack != null){
			setInventorySlotContents(index, null);
		}
		return stack;
	}
	
	@Override
	public void clear() {
		this.inventory = new ItemStack[this.inventory.length];
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}
	
	public void writeInventoryToNBT(NBTTagCompound compound) {
		NBTTagList inventoryList = new NBTTagList();
        for (int i = 0; i < inventory.length; i++)
        {
            if (inventory[i] != null)
            {
            	NBTTagCompound stackTag = new NBTTagCompound();
                stackTag.setByte("Slot", (byte) i);
                inventory[i].writeToNBT(stackTag);
                inventoryList.appendTag(stackTag);
            }
        }
        compound.setTag("Inventory", inventoryList);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		writeInventoryToNBT(compound);
		return compound;
	}
	
	public void readInventoryFromNBT(NBTTagCompound compound) {
		clear();
		NBTTagList invList = compound.getTagList("Inventory", Constants.NBT.TAG_COMPOUND);
        for (int i = 0; i < invList.tagCount(); i++)
        {
            NBTTagCompound stackTag = invList.getCompoundTagAt(i);
            int slot = stackTag.getByte("Slot");

            if (slot >= 0 && slot < inventory.length){
            	inventory[slot] = ItemStack.loadItemStackFromNBT(stackTag);
            }
        }
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		readInventoryFromNBT(compound);
	}
	
	@Override
	public void onCustomDataPacket(NetworkPacketTileEntitySync packet) {
		readInventoryFromNBT(packet.getNBTTagCompound());
	}

	@Override
	public NetworkPacketTileEntitySync getCustomDataPacket(NBTTagCompound compound) {
		writeInventoryToNBT(compound);
		return new NetworkPacketTileEntitySync(pos, compound);
	}
	
}
