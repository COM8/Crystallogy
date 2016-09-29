package de.comeight.crystallogy.entity.ai;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAiPickupItems extends EntityAiMoveToPos{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BlockPos itemsTargetPos;
	private BlockPos area;
	private EntityItem currentTarget;
	private boolean deliveringItems;
	private int waitCounter;
	private final int MAX_ITEM_STACK_SIZE = 64;
	private boolean maxStackSizeReached;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityAiPickupItems(EntityLiving aiOwner, BlockPos itemsTargetPos, BlockPos area) {
		super(aiOwner, new Vec3d(itemsTargetPos.add(0.5, 0.5, 0.5)), 1.0);
		this.itemsTargetPos = itemsTargetPos;
		this.area = area;
		this.currentTarget = null;
		this.deliveringItems = false;
		this.waitCounter = 0;
		this.forceMoveTo = true;
		this.maxStackSizeReached = false;
	}
	
	public EntityAiPickupItems(EntityLiving aiOwner) {
		super(aiOwner);
		this.currentTarget = null;
		this.waitCounter = 0;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected int getAiID() {
		return 4;
	}
	
	private EntityItem getNextTarget(){
		return (EntityItem) world.findNearestEntityWithinAABB(EntityItem.class, getAreaBoundingBox(), aiOwner);
	}
	
	private EntityItem getNearestEqualStack(ItemStack stack){
		for (EntityItem entityitem : world.getEntitiesWithinAABB(EntityItem.class, getAreaBoundingBox()))
        {
			ItemStack iStack = entityitem.getEntityItem();
            if(ItemStack.areItemsEqual(stack, iStack) && ItemStack.areItemStackTagsEqual(stack, iStack)){
            	return entityitem;
            }
        }
		return null;
	}
	
	private boolean isDone(){
		return !deliveringItems && currentTarget == null && getNextTarget() == null;
	}
	
	private AxisAlignedBB getAreaBoundingBox(){
		int xMin = itemsTargetPos.getX();
		int yMin = itemsTargetPos.getY();
		int zMin = itemsTargetPos.getZ();
		
		int xMax = itemsTargetPos.getX() + area.getX();
		int yMax = itemsTargetPos.getY() + area.getY();
		int zMax = itemsTargetPos.getZ() + area.getZ();
		if(area.getX() < 0){
			xMin += area.getX();
			xMax = itemsTargetPos.getX();
		}
		if(area.getY() < 0){
			yMin += area.getY();
			yMax = itemsTargetPos.getY();
		}
		
		if(area.getZ() < 0){
			zMin += area.getZ();
			zMax = itemsTargetPos.getZ();
		}
		
		return new AxisAlignedBB(xMin, yMin, zMin, xMax, yMax, zMax);
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		Utilities.saveBlockPosToNBT(compound, itemsTargetPos, "itemsTargetPos");
		Utilities.saveBlockPosToNBT(compound, area, "area");
		compound.setBoolean("deliveringItems", deliveringItems);
		compound.setBoolean("maxStackSizeReached", maxStackSizeReached);
	}
	
	@Override
	public void redFromNBT(NBTTagCompound compound) {
		super.redFromNBT(compound);
		itemsTargetPos = Utilities.readBlockPosFromNBT(compound, "itemsTargetPos");
		area = Utilities.readBlockPosFromNBT(compound, "area");
		deliveringItems = compound.getBoolean("deliveringItems");
		maxStackSizeReached = compound.getBoolean("maxStackSizeReached");
	}
	
	@Override
	public boolean continueExecuting() {
		return !(isDone() && aiOwner.getDistanceSqToCenter(itemsTargetPos) < 2);
	}
	
	@Override
	public boolean shouldExecute() {
		return super.shouldExecute() || !isDone() || aiOwner.getDistanceSqToCenter(itemsTargetPos) > 2;
	}
	
	@Override
	protected boolean shouldRecalcPath() {
		if(super.shouldRecalcPath() || (aiOwnerPathfinder.noPath() && !isDone())){
			return true;
		}
		return false;
	}
	
	@Override
	public void updateTask() {
		super.updateTask();
		if(currentTarget == null){ // Searching for a stack:
			if(deliveringItems){ // Next stack:
				if(isNearItemsTargetPos()){ // Reached target position for delivering
					dropItem();
					return;
				}
				else if(!maxStackSizeReached){
					currentTarget = getNearestEqualStack(getItemStackOnCompound());
					
					if(currentTarget == null || currentTarget.getDistanceSqToEntity(aiOwner) > aiOwner.getDistanceSqToCenter(itemsTargetPos)){
						currentTarget = null;
						return;
					}
				}
			}
			else{ //First stack:
				currentTarget = getNextTarget();
			}
			
			if(currentTarget != null){
				setTargetPos(currentTarget.getPositionVector());
			}
		}
		else{ // Pick up stack:
			if(currentTarget.getPositionVector().squareDistanceTo(getTargetPos()) > 0.25){
				setTargetPos(currentTarget.getPositionVector());
				return;
			}
			if(isNearTargetPosition()){
				pickUpItem(getRemoveItemStackOnNBT());
				setTargetPos(new Vec3d(itemsTargetPos).addVector(0.5, 0.5, 0.5));
			}
		}
	}
	
	private boolean isNearItemsTargetPos(){
		return aiOwner.getDistanceSqToCenter(itemsTargetPos)  <= 2;
	}
	
	private void dropItem(){
		if(tryInsertingInInventorry()){
			deliveringItems = false;
			maxStackSizeReached = false;
		}
	}
	
	private void pickUpItem(ItemStack storedStack){
		ItemStack targetStack = currentTarget.getEntityItem();
		if(storedStack == null){
			setItemStackOnNBT(targetStack);
		}
		else{
			int stackLimit = 0;
			if(MAX_ITEM_STACK_SIZE > targetStack.getMaxStackSize()){
				stackLimit = targetStack.getMaxStackSize();
			}
			else{
				stackLimit = MAX_ITEM_STACK_SIZE;
			}
			int e = (storedStack.stackSize + targetStack.stackSize) - stackLimit;
			if(e >= 0){
				maxStackSizeReached = true;
				targetStack.stackSize = e;
				storedStack.stackSize = stackLimit;
				world.spawnEntityInWorld(new EntityItem(world, currentTarget.posX, currentTarget.posY, currentTarget.posZ, targetStack));
				setItemStackOnNBT(storedStack);
			}
			else{
				storedStack.stackSize = storedStack.stackSize + targetStack.stackSize;
				setItemStackOnNBT(storedStack);
			}
		}
		world.removeEntity(currentTarget);
		currentTarget = null;
		deliveringItems = true;
	}
	
	private void setItemStackOnNBT(ItemStack stack){
		NBTTagCompound compound = getCompound(aiOwner);
		NBTTagCompound itemsCompound = new NBTTagCompound();
		stack.writeToNBT(itemsCompound);
		compound.setTag("itemsCompound", itemsCompound);
		setCompound(aiOwner, compound);
	}
	
	private ItemStack getRemoveItemStackOnNBT(){
		NBTTagCompound compound = getCompound(aiOwner);
		NBTTagCompound itemsCompound = (NBTTagCompound) compound.getTag("itemsCompound");
		if(itemsCompound == null){
			return null;
		}
		ItemStack stack = ItemStack.loadItemStackFromNBT(itemsCompound);
		compound.setTag("itemsCompound", new NBTTagCompound());
		setCompound(aiOwner, compound);
		return stack;
	}
	
	private ItemStack getItemStackOnCompound(){
		NBTTagCompound compound = getCompound(aiOwner);
		NBTTagCompound itemsCompound = (NBTTagCompound) compound.getTag("itemsCompound");
		if(itemsCompound == null){
			return null;
		}
		return ItemStack.loadItemStackFromNBT(itemsCompound);
	}
	
	private boolean tryInsertingInInventorry(){
		TileEntity tE = world.getTileEntity(itemsTargetPos);
		if(tE != null && tE instanceof IInventory){
			IInventory iTE = (IInventory)tE;
			ItemStack stack = getRemoveItemStackOnNBT();
			if(stack == null){
				return true;
			}
			
			int stackLimit = 0;
			if(iTE.getInventoryStackLimit() < stack.getMaxStackSize()){
				stackLimit = iTE.getInventoryStackLimit();
			}
			else{
				stackLimit = stack.getMaxStackSize();
			}
			
			for(int i = 0; i < iTE.getSizeInventory();i++){
				ItemStack iStack = iTE.getStackInSlot(i); 
				if(iStack == null){
					iTE.setInventorySlotContents(i, stack);
					return true;
				}
				
				if(ItemStack.areItemsEqual(iStack, stack) && ItemStack.areItemStackTagsEqual(iStack, stack) && iStack.stackSize < stackLimit){
					int e = (iStack.stackSize + stack.stackSize) - stackLimit;
					if(e > 0){
						iStack.stackSize = stackLimit;
						iTE.setInventorySlotContents(i, iStack);
						stack.stackSize -= e;
					}
					else{
						iStack.stackSize += stack.stackSize;
						iTE.setInventorySlotContents(i, iStack);
						return true;
					}
				}
			}
			setItemStackOnNBT(stack);
		}
		return false;
	}
	
}
