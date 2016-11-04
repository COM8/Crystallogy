package de.comeight.crystallogy.entity.ai;

import java.util.List;

import de.comeight.crystallogy.util.EnumCustomAis;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;

public class EntityAiPickupItems extends EntityAiMoveToPos{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BlockPos itemsTargetPos;
	private BlockPos areaRefPos;
	private BlockPos area;
	private EntityItem currentTarget;
	private boolean deliveringItems;
	private int waitCounter;
	private final int MAX_ITEM_STACK_SIZE = 64;
	private boolean maxStackSizeReached;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityAiPickupItems(EntityLiving aiOwner, BlockPos itemsTargetPos, BlockPos areaStartPos, BlockPos area) {
		super(aiOwner, new Vec3d(itemsTargetPos.add(0.5, 0.5, 0.5)), 1.0);
		this.itemsTargetPos = itemsTargetPos;
		this.areaRefPos = areaStartPos;
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
	
	public EntityAiPickupItems() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public int getAiID() {
		return EnumCustomAis.PICKUP_ITEMS.ID;
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
		int xMin = areaRefPos.getX();
		int yMin = areaRefPos.getY() - 1;
		int zMin = areaRefPos.getZ();
		
		int xMax = areaRefPos.getX() + area.getX() + 1;
		int yMax = areaRefPos.getY() + area.getY() + 1;
		int zMax = areaRefPos.getZ() + area.getZ() + 1;
		if(area.getX() < 0){
			xMin += area.getX();
			xMax = areaRefPos.getX() + 1;
		}
		if(area.getY() < 0){
			yMin += area.getY() - 1;
			yMax = areaRefPos.getY() + 1;
		}
		
		if(area.getZ() < 0){
			zMin += area.getZ();
			zMax = areaRefPos.getZ() + 1;
		}
		
		return new AxisAlignedBB(xMin, yMin, zMin, xMax, yMax, zMax);
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		Utilities.saveBlockPosToNBT(compound, itemsTargetPos, "itemsTargetPos");
		Utilities.saveBlockPosToNBT(compound, areaRefPos, "areaRefPos");
		Utilities.saveBlockPosToNBT(compound, area, "area");
		compound.setBoolean("deliveringItems", deliveringItems);
		compound.setBoolean("maxStackSizeReached", maxStackSizeReached);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		itemsTargetPos = Utilities.readBlockPosFromNBT(compound, "itemsTargetPos");
		areaRefPos = Utilities.readBlockPosFromNBT(compound, "areaRefPos");
		area = Utilities.readBlockPosFromNBT(compound, "area");
		deliveringItems = compound.getBoolean("deliveringItems");
		maxStackSizeReached = compound.getBoolean("maxStackSizeReached");
	}
	
	@Override
	public boolean continueExecuting() {
		return !(isDone() && aiOwner.getDistanceSqToCenter(itemsTargetPos) < 2.5);
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
	
	public static void addAdvancedTooltip(ItemStack stack, EntityPlayer playerIn, List<String> tooltip){
		NBTTagCompound compound = stack.getTagCompound();
		BlockPos p1 = Utilities.readBlockPosFromNBT(compound, "areaMin");
		BlockPos p2 = Utilities.readBlockPosFromNBT(compound, "areaMax");
		BlockPos p3 = Utilities.readBlockPosFromNBT(compound, "itemsTargetPos");
		
		tooltip.add(TextFormatting.DARK_PURPLE + "Area:");
		if(p1 == null || p2 == null){
			tooltip.add("-");
		}
		else{
			tooltip.add("X: " + TextFormatting.GOLD + p1.getX() + " - " + p2.getX());
			tooltip.add("Y: " + TextFormatting.GOLD + p1.getY() + " - " + p2.getY());
			tooltip.add("Z: " + TextFormatting.GOLD + p1.getZ() + " - " + p2.getZ());
		}
		
		tooltip.add("");
		if(p3 == null){
			tooltip.add(TextFormatting.DARK_PURPLE + "Items target Position: " + TextFormatting.GOLD + "-");
		}
		else{
			tooltip.add(TextFormatting.DARK_PURPLE + "Items target Position: " + TextFormatting.GOLD + "X=" + p3.getX() + " Y=" + p3.getY() + " Z=" + p3.getZ());
		}
	}
	
}
