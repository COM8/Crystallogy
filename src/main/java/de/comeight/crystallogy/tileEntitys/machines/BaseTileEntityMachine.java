package de.comeight.crystallogy.tileEntitys.machines;

import de.comeight.crystallogy.blocks.machines.BaseMachine;
import de.comeight.crystallogy.handler.BaseRecipeHandler;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.tileEntitys.TileEntityInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public abstract class BaseTileEntityMachine extends TileEntityInventory implements ITickable, ISidedInventory{
	//-----------------------------------------------Variabeln:---------------------------------------------
    protected int cookTime;
    protected int totalCookTime;
    public boolean crafting;
    
    protected int soundPlayedLast;
    
    protected BaseRecipeHandler recipeHandler;
    
    protected int slotsInput;
    protected int slotsOutput;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseTileEntityMachine(BaseRecipeHandler recipeHandler, int slotsInput, int slotsOutput) {
		super(slotsInput + slotsOutput);
		
		this.recipeHandler = recipeHandler;
		this.slotsInput = slotsInput;
		this.slotsOutput = slotsOutput;
		crafting = false;
		soundPlayedLast = 0;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(index >= slotsInput){
			return false;
		}
		if(inventory[index] != null){
            return inventory[index].getItem() == stack.getItem() && inventory[index].stackSize < inventory[index].getMaxStackSize();
        }
		return true;
	}
	
	public int getCookTime(ItemStack stack[])
    {
        return recipeHandler.getTotalCookTime(stack);
    }
	
	public abstract void setBlockState();
	
	public ItemStack[] getInputSlots(){
		ItemStack[] ret = new ItemStack[slotsInput];
		for (int i = 0; i < slotsInput; i++) {
			ret[i] = getStackInSlot(i);
		}
		return ret;
	}
	
	public ItemStack[] getOutputSlots(){
		ItemStack[] ret = new ItemStack[slotsOutput];
		for (int i = 0; i < slotsOutput; i++) {
			ret[i] = getStackInSlot(slotsInput + i);
		}
		return ret;
	}
	
	public int getSoundIntervall(){
		return 20;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public double fractionOfCookTimeComplete()
	{
		return (double)cookTime / (double)totalCookTime;
	}
	
    protected boolean canCraft()
    {
    	ItemStack output[] = recipeHandler.getResults(getInputSlots());
        if (output == null){
        	return false;
        }
        boolean flag1 = true;
        for (int i = 0; i < slotsOutput; i++) {
			if(inventory[slotsInput + i] != null){
				flag1 = false;
				if(inventory[slotsInput + i].stackSize >= inventory[slotsInput + i].getMaxStackSize()){
		        	return false;
		        }
			}
		}
        if(flag1){
        	return true;
        }
        
        for (int i = 0; i < slotsOutput; i++) {
        	if(inventory[slotsInput + i].getItem() != output[i].getItem()){
        		return false;
        	}
        }
        
        int[] resultStackSize = new int[slotsOutput];
        for (int i = 0; i < slotsOutput; i++) {
        	resultStackSize[i] = inventory[slotsInput + i].stackSize + output[i].stackSize;
        }
        for (int i = 0; i < slotsOutput; i++) {
        	if(resultStackSize[i] > getInventoryStackLimit() || resultStackSize[i] > inventory[slotsInput + i].getMaxStackSize()){
        		return false;
        	}
        }
        
        return true;
    }

    public void craftItem()
    {
        if (canCraft())
        {
            ItemStack[] res = recipeHandler.getResults(getInputSlots());

            for (int i = 0; i < slotsOutput; i++) {
            	if(inventory[slotsInput + i] == null){
            		inventory[slotsInput + i] = res[i].copy();
            	}
            	else if(inventory[slotsInput + i].getItem() == res[i].getItem()){
            		inventory[slotsInput + i].stackSize += res[i].stackSize;
            	}
            }

            int[] numOfInputs = recipeHandler.getNumberOfInputItems(getInputSlots());
            for (int i = 0; i < slotsInput; i++) {
            	if(inventory[i] != null && numOfInputs != null){
            		inventory[i].stackSize -= numOfInputs[i];
                	if(inventory[i].stackSize <= 0){
                		inventory[i] = null;
                	}
            	}
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
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        writeAdditionalData(compound);
        return compound;
    }	
    
    @Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
		return new SPacketUpdateTileEntity(pos, 0, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		readFromNBT(pkt.getNbtCompound());
	}
    
    @Override
	public void onCustomDataPacket(NetworkPacketTileEntitySync packet) {
    	readFromNBT(packet.getNBTTagCompound());
    	readAdditionalData(packet.getNBTTagCompound());
	}
	
	@Override
	public NetworkPacketTileEntitySync getCustomDataPacket(NBTTagCompound compound) {
		writeToNBT(compound);
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
	
	private void manageSounds(World worldIn){
		if(soundPlayedLast <= 0 && (totalCookTime - cookTime) > getSoundIntervall() * 0.75){
			playSound(worldIn);
			soundPlayedLast = getSoundIntervall();
			
		}
		else{
			soundPlayedLast--;
		}
	}
	
	@Override
	public void update() {
		if(cookTime == 2){
			onStartedCrafting(worldObj);
		}
		
		if (worldObj.isRemote)
        {
        	if(crafting){
        		if(!worldObj.getBlockState(pos).getValue(BaseMachine.ENABLED)){
        			setBlockState();
        		}
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
        		manageSounds(worldObj);
            	if (cookTime >= totalCookTime) {
    				cookTime = 1;
    				craftItem();
    				crafting = false;
    				onFinishedCrafting(worldObj);
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
    				totalCookTime = getCookTime(getInputSlots());
    				sync();
    			}
    		}
        }

		if (flag1) {
			markDirty();
			if(!worldObj.isRemote){
				setBlockState();
			}
		}
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		int[] ret = new int[slotsInput + slotsOutput];
		for(int i = 0; i < (slotsInput + slotsOutput); i++){
			ret[i] = i;
		}
		return ret;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return index > (slotsInput - 1);
    }
	
	public void onFinishedCrafting(World worldIn){
		
	}
	
	public void onStartedCrafting(World worldIn){
		
	}
	
	public void playSound(World worldIn){
		
	}
	
}
