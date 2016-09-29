package de.comeight.crystallogy.entity.ai;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityAiQuarry extends EntityAiMoveToPos {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private BlockPos startPos;
	private BlockPos endPos;
	private BlockPos currentPos;
	private boolean done;
	private int miningProgress;
	private final int MAX_MINING_TICKS = 30;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityAiQuarry(EntityLiving aiOwner, BlockPos startPos, BlockPos size) {
		super(aiOwner, new Vec3d(startPos), 1.0);
		this.startPos = startPos;
		this.currentPos = startPos;
		if((startPos.getY() - size.getY()) >= 0){
			this.endPos = startPos.add(size.getX() - 1, -size.getY() + 1, size.getZ() - 1);	
		}
		else{
			this.endPos = startPos.add(size.getX() - 1, -startPos.getY() + 1, size.getZ() - 1);
		}
		this.done = false;
		this.miningProgress = 0;
		this.forceMoveTo = true;
	}
	
	public EntityAiQuarry(EntityLiving aiOwner) {
		super(aiOwner);
		this.done = false;
		this.miningProgress = 0;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected int getAiID() {
		return 3;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		Utilities.saveBlockPosToNBT(compound, startPos, "startPos");
		Utilities.saveBlockPosToNBT(compound, currentPos, "currentPos");
		Utilities.saveBlockPosToNBT(compound, endPos, "endPos");
	}
	
	@Override
	public void redFromNBT(NBTTagCompound compound) {
		super.redFromNBT(compound);
		startPos = Utilities.readBlockPosFromNBT(compound, "startPos");
		currentPos = Utilities.readBlockPosFromNBT(compound, "currentPos");
		endPos = Utilities.readBlockPosFromNBT(compound, "endPos");
	}
	
	@Override
	public boolean continueExecuting() {
		return !done;
	}
	
	@Override
	public boolean shouldExecute() {
		return !done;
	}
	
	@Override
	protected boolean shouldRecalcPath() {
		if(super.shouldRecalcPath() || aiOwnerPathfinder.noPath()){
			return true;
		}
		return false;
	}
	
	@Override
	public void updateTask(){
		super.updateTask();
		if(isNearTargetPosition()|| aiOwnerPathfinder.noPath()){
			incMiningProgress();
			if(miningProgress == MAX_MINING_TICKS){
				mineNextBlock();	
			}
		}
		moveHead();
	}
	
	private void moveHead(){
		Vec3d tPos = getTargetPos();
		aiOwner.getLookHelper().setLookPosition(tPos.xCoord + 0.5, tPos.yCoord, tPos.zCoord + 0.5, (float)aiOwner.getHorizontalFaceSpeed(), (float)aiOwner.getVerticalFaceSpeed());
	}
	
	private void incMiningProgress(){
		if(miningProgress >= MAX_MINING_TICKS){
			miningProgress = 0;
		}
		miningProgress++;
	}
	
	private boolean incPos(){
		if(currentPos.equals(endPos)){
			return false;
		}
		
		if(currentPos.getX() == endPos.getX()){
			if(currentPos.getZ() == endPos.getZ()){
				if(currentPos.getY() == endPos.getY()){
					return false;
				}
				currentPos = new BlockPos(startPos.getX(), currentPos.getY() -1, startPos.getZ());
			}
			else if(startPos.getZ() < endPos.getZ()){
				currentPos = new BlockPos(startPos.getX(), currentPos.getY(), currentPos.getZ() + 1);
			}
			else{
				currentPos = new BlockPos(startPos.getX(), currentPos.getY(), currentPos.getZ() - 1);
			}
		}
		else if(startPos.getX() < endPos.getX()){
			currentPos = currentPos.add(1, 0, 0);
		}
		else{
			currentPos = currentPos.add(-1, 0, 0);
		}
		setTargetPos(new Vec3d(currentPos.add(0, 1, 0)));
		return true;
	}
	
	private void mineNextBlock(){
		if(canMineBlock(currentPos)){
			breakBlock(currentPos);
		}
 
		do {
			if(!incPos()){
				done = true;
				aiOwner.tasks.removeTask(this);
				break;
			}
			else{
				saveData(aiOwner);
			}
		} while (!canMineBlock(currentPos));
	}
	
	private boolean canMineBlock(BlockPos pos){
		IBlockState state = world.getBlockState(pos);
		return state.getBlockHardness(world, pos) >= 0 && state.getMaterial() != Material.AIR && !state.getMaterial().isLiquid();
	}
	
	private void breakBlock(BlockPos pos){
		world.destroyBlock(pos, true);
	}
	
}
