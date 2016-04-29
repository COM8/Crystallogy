package de.comeight.crystallogy.tileEntitys.machines;

import de.comeight.crystallogy.handler.BaseRecipeHandler;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.tileEntitys.TileEntityInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

public abstract class BaseTileEntityMachine extends TileEntityInventory implements ITickable {
	//-----------------------------------------------Variabeln:---------------------------------------------
    protected int cookTime;
    protected int totalCookTime;
    public boolean crafting;
    
    protected BaseRecipeHandler recipeHandler;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseTileEntityMachine(BaseRecipeHandler recipeHandler) {
		super(2);
		
		this.recipeHandler = recipeHandler;
		crafting = false;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
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
	
	public int getCookTime(ItemStack stack)
    {
        return recipeHandler.getTotalCookTime(stack);
    }
	
	public abstract void setBlockState();
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public double fractionOfCookTimeComplete()
	{
		return (double)cookTime / (double)totalCookTime;
	}
	
    protected boolean canCraft()
    {
    	ItemStack output = recipeHandler.getResult(inventory[0]);
        if (output == null){
        	return false;
        }
        if (inventory[1] == null){
        	return true;
        }
        if(inventory[1].stackSize >= inventory[1].getMaxStackSize()){
        	return false;
        }
        if(inventory[1].getItem() != output.getItem()){
    		return false;
    	}
        
        int result = inventory[1].stackSize + output.stackSize;
        if(result >= getInventoryStackLimit() && result >= this.inventory[1].getMaxStackSize()){
        	return false;
        }
        return true;
    }

    public void craftItem()
    {
        if (canCraft())
        {
            ItemStack itemstack = recipeHandler.getResult(inventory[0]);

            if (inventory[1] == null)
            {
                inventory[1] = itemstack.copy();
            }
            else if (inventory[1].getItem() == itemstack.getItem())
            {
                inventory[1].stackSize += itemstack.stackSize;
            }

            inventory[0].stackSize -= recipeHandler.getNumberOfInputItems(inventory[0]);

            if (inventory[0].stackSize <= 0)
            {
                inventory[0] = null;
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        readAdditionalData(compound);
        
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        writeAdditionalData(compound);
    }	
    
    @Override
	public void onCustomDataPacket(NetworkPacketTileEntitySync packet) {
    	readAdditionalData(packet.getNBTTagCompound());
	}
	
	@Override
	public NetworkPacketTileEntitySync getCustomDataPacket(NBTTagCompound compound) {
		writeAdditionalData(compound);
		return new NetworkPacketTileEntitySync(pos, compound);
	}

	protected void readAdditionalData(NBTTagCompound compound){
		this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        
        this.crafting = compound.getBoolean("crushing");
	}
	
	protected void writeAdditionalData(NBTTagCompound compound){
		compound.setInteger("CookTime", this.cookTime);
        compound.setInteger("CookTimeTotal", this.totalCookTime);
        
        compound.setBoolean("crushing", crafting);
	}
	
	@Override
	public void update() {
		if (worldObj.isRemote)
        {
        	if(crafting){
        		cookTime++;
        		if (cookTime >= totalCookTime) {
    				cookTime = 1;
    				crafting = false;
        		}
        	}
        	return;
        }
        boolean flag1 = false;
        if(crafting){
        	if(!canCraft()){
        		crafting = false;
        		cookTime = 1;
        		sync();
        	}
        	else{
        		cookTime++;
            	if (cookTime >= totalCookTime) {
    				cookTime = 1;
    				craftItem();
    				crafting = false;
    			}
        	}
        	flag1 = true;
        }
        else{
        	if (inventory[0] != null) {
    			if (canCraft()) {
    				flag1 = true;
    				crafting = true;
    				cookTime = 1;
    				totalCookTime = getCookTime(this.inventory[0]);
    				sync();
    			}
    		}
        }

		if (flag1) {
			markDirty();
			setBlockState();
		}
	}
	
}
