package de.comeight.crystallogy.tileEntitys;

import java.io.ObjectInputStream.GetField;

import de.comeight.crystallogy.gui.GuiCrystallCrusher;
import de.comeight.crystallogy.handler.CrystalCrusherRecipeHandler;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;

public class TileEntityCrystallCrusher extends TileEntityInventory implements ITickable {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "containerCrystallCrusher";
	
    private ItemStack[] crusherItemStacks = new ItemStack[2];

    private int cookTime;
    private int totalCookTime;
    public boolean crushing;
    
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityCrystallCrusher() {
		super(2);
		crushing = false;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public String getName()
    {
		return "de.comeight.crystallogy.tileEntitys";
    }


	@Override
	public int getSizeInventory() {
		return this.crusherItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return this.crusherItemStacks[index];
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	@Override
	public void setField(int id, int value) {
	}
	
	public int getCookTime(ItemStack stack)
    {
        return CrystalCrusherRecipeHandler.INSTANCE.getTotalCookTime(stack);
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
        	return;
        }
        boolean flag1 = false;
        if(crushing){
        	if(!canCrush()){
        		crushing = false;
        		cookTime = 1;
        	}
        	else{
        		cookTime++;
            	if (cookTime >= totalCookTime) {
    				cookTime = 1;
    				crushItem();
    				crushing = false;
    			}
        	}
        	sync();
        	flag1 = true;
        }
        else{
        	if (crusherItemStacks[0] != null) {
    			if (canCrush()) {
    				flag1 = true;
    				crushing = true;
    				cookTime = 1;
    				totalCookTime = getCookTime(this.crusherItemStacks[0]);
    				sync();
    			}
    		}
        }

		if (flag1) {
			this.markDirty();
		}
    }
	
    private boolean canCrush()
    {
    	ItemStack output = CrystalCrusherRecipeHandler.INSTANCE.getResult(crusherItemStacks[0]);
        if (output == null){
        	return false;
        }
        if (crusherItemStacks[1] == null){
        	return true;
        }
        if(crusherItemStacks[1].getItem() != output.getItem()){
    		return false;
    	}
        
        int result = crusherItemStacks[1].stackSize + output.stackSize;
        if(result >= getInventoryStackLimit() && result >= this.crusherItemStacks[1].getMaxStackSize()){
        	return false;
        }
        return true;
    }

    public void crushItem()
    {
        if (canCrush())
        {
            ItemStack itemstack = CrystalCrusherRecipeHandler.INSTANCE.getResult(crusherItemStacks[0]);

            if (crusherItemStacks[1] == null)
            {
                crusherItemStacks[1] = itemstack.copy();
            }
            else if (crusherItemStacks[1].getItem() == itemstack.getItem())
            {
                crusherItemStacks[1].stackSize += itemstack.stackSize;
            }

            crusherItemStacks[0].stackSize--;

            if (crusherItemStacks[0].stackSize <= 0)
            {
                crusherItemStacks[0] = null;
            }
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        
        readCookTimeFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.crusherItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot");

            if (j >= 0 && j < this.crusherItemStacks.length)
            {
                this.crusherItemStacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        
        writeCookTimeToNBT(compound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.crusherItemStacks.length; ++i)
        {
            if (this.crusherItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                this.crusherItemStacks[i].writeToNBT(nbttagcompound);
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
		if (this.crusherItemStacks[index] != null)
        {
            ItemStack itemstack = this.crusherItemStacks[index];
            this.crusherItemStacks[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.crusherItemStacks[index] = stack;
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
		return new ContainerFurnace(playerInventory, this);
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
		for (int i = 0; i < this.crusherItemStacks.length; ++i)
        {
            this.crusherItemStacks[i] = null;
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
	}
	
	private void writeCookTimeToNBT(NBTTagCompound compound){
		compound.setInteger("CookTime", this.cookTime);
        compound.setInteger("CookTimeTotal", this.totalCookTime);
	}
	
	@Override
	public NetworkPacketTileEntitySync getCustomDataPacket(NBTTagCompound compound) {
		writeCookTimeToNBT(compound);
		return new NetworkPacketTileEntitySync(pos, compound);
	}

}
