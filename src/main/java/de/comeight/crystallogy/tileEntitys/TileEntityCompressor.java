package de.comeight.crystallogy.tileEntitys;

import de.comeight.crystallogy.blocks.container.ContainerCompressor;
import de.comeight.crystallogy.gui.GuiCrystallCrusher;
import de.comeight.crystallogy.handler.CompressorRecipeHandler;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;

public class TileEntityCompressor extends TileEntityInventory implements ITickable {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "tileEntityCompressor";
	
    private ItemStack[] compressorItemStacks = new ItemStack[2];

    private int cookTime;
    private int totalCookTime;
    public boolean compressing;
    
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityCompressor() {
		super(2);
		compressing = false;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public String getName()
    {
		return "de.comeight.crystallogy.tileEntitys";
    }


	@Override
	public int getSizeInventory() {
		return this.compressorItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.compressorItemStacks[index];
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(index != 0){
			return false;
		}
		if(inventory[index] != null){
			return false;
		}
		return true;
	}

	@Override
	public void setField(int id, int value) {
	}
	
	public int getCookTime(ItemStack stack)
    {
        return CompressorRecipeHandler.INSTANCE.getTotalCookTime(stack);
    }
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public double fractionOfCookTimeComplete()
	{
		return (double)cookTime / (double)totalCookTime;
	}
	
	@Override
	public void update()
    {
        if (worldObj.isRemote)
        {
        	if(compressing){
        		cookTime++;
        		if (cookTime >= totalCookTime) {
    				cookTime = 1;
    				compressing = false;
        		}
        	}
        	return;
        }
        boolean flag1 = false;
        if(compressing){
        	if(!canCompress()){
        		compressing = false;
        		cookTime = 1;
        		sync();
        	}
        	else{
        		cookTime++;
            	if (cookTime >= totalCookTime) {
    				cookTime = 1;
    				compressItem();
    				compressing = false;
    			}
        	}
        	flag1 = true;
        }
        else{
        	if (compressorItemStacks[0] != null) {
    			if (canCompress()) {
    				flag1 = true;
    				compressing = true;
    				cookTime = 1;
    				totalCookTime = getCookTime(this.compressorItemStacks[0]);
    				sync();
    			}
    		}
        }

		if (flag1) {
			this.markDirty();
		}
    }
	
    private boolean canCompress()
    {
    	ItemStack output = CompressorRecipeHandler.INSTANCE.getResult(compressorItemStacks[0]);
        if (output == null){
        	return false;
        }
        if (compressorItemStacks[1] == null){
        	return true;
        }
        if(compressorItemStacks[1].stackSize >= compressorItemStacks[1].getMaxStackSize()){
        	return false;
        }
        if(compressorItemStacks[1].getItem() != output.getItem()){
    		return false;
    	}
        
        int result = compressorItemStacks[1].stackSize + output.stackSize;
        if(result >= getInventoryStackLimit() && result >= this.compressorItemStacks[1].getMaxStackSize()){
        	return false;
        }
        return true;
    }

    public void compressItem()
    {
        if (canCompress())
        {
            ItemStack itemstack = CompressorRecipeHandler.INSTANCE.getResult(compressorItemStacks[0]);

            if (compressorItemStacks[1] == null)
            {
                compressorItemStacks[1] = itemstack.copy();
            }
            else if (compressorItemStacks[1].getItem() == itemstack.getItem())
            {
                compressorItemStacks[1].stackSize += itemstack.stackSize;
            }

            compressorItemStacks[0].stackSize -= CompressorRecipeHandler.INSTANCE.getNumberOfInputItems(compressorItemStacks[0]);

            if (compressorItemStacks[0].stackSize <= 0)
            {
                compressorItemStacks[0] = null;
            }
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        
        readCookTimeFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.compressorItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot");

            if (j >= 0 && j < this.compressorItemStacks.length)
            {
                this.compressorItemStacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        
        writeCookTimeToNBT(compound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.compressorItemStacks.length; ++i)
        {
            if (this.compressorItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                this.compressorItemStacks[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        compound.setTag("Items", nbttaglist);
    }	


	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack itemStackInSlot = getStackInSlot(index);
		if (itemStackInSlot == null) return null;

		ItemStack itemStackRemoved;
		if (itemStackInSlot.stackSize <= count) {
			itemStackRemoved = itemStackInSlot;
			setInventorySlotContents(index, null);
		} else {
			itemStackRemoved = itemStackInSlot.splitStack(count);
			if (itemStackInSlot.stackSize == 0) {
				setInventorySlotContents(index, null);
			}
		}
		markDirty();
		return itemStackRemoved;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		if (this.compressorItemStacks[index] != null)
        {
            ItemStack itemstack = this.compressorItemStacks[index];
            this.compressorItemStacks[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.compressorItemStacks[index] = stack;
		if (stack != null && stack.stackSize > getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}


	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
    {
		if (this.worldObj.getTileEntity(this.pos) != this) return false;
		final double X_CENTRE_OFFSET = 0.5;
		final double Y_CENTRE_OFFSET = 0.5;
		final double Z_CENTRE_OFFSET = 0.5;
		final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
		return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
    }

	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerCompressor(playerInventory, this);
	}

	public String getGuiID() {
		return String.valueOf(GuiCrystallCrusher.ID);
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.compressorItemStacks.length; ++i)
        {
            this.compressorItemStacks[i] = null;
        }
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	@Override
	public void onCustomDataPacket(NetworkPacketTileEntitySync packet) {
		readCookTimeFromNBT(packet.getNBTTagCompound());
	}

	private void readCookTimeFromNBT(NBTTagCompound compound){
		this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.compressing = compound.getBoolean("crushing");
	}
	
	private void writeCookTimeToNBT(NBTTagCompound compound){
		compound.setInteger("CookTime", this.cookTime);
        compound.setInteger("CookTimeTotal", this.totalCookTime);
        compound.setBoolean("crushing", compressing);
	}
	
	@Override
	public NetworkPacketTileEntitySync getCustomDataPacket(NBTTagCompound compound) {
		writeCookTimeToNBT(compound);
		return new NetworkPacketTileEntitySync(pos, compound);
	}

}
