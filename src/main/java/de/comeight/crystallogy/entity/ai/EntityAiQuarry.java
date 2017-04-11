package de.comeight.crystallogy.entity.ai;

import java.util.List;

import de.comeight.crystallogy.util.EnumCustomAis;
import de.comeight.crystallogy.util.NBTTags;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;

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
		super(aiOwner, new Vec3d(startPos), 1.1);
		this.startPos = startPos;
		this.currentPos = startPos;
		this.endPos = startPos.add(size.getX(), -size.getY(), size.getZ());
		this.done = false;
		this.miningProgress = 0;
		this.forceMoveTo = true;
	}
	
	public EntityAiQuarry(EntityLiving aiOwner) {
		super(aiOwner);
		this.done = false;
		this.miningProgress = 0;
	}
	
	public EntityAiQuarry() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public int getAiID() {
		return EnumCustomAis.QUARRY.ID;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		Utilities.saveBlockPosToNBT(compound, startPos, NBTTags.START_POS);
		Utilities.saveBlockPosToNBT(compound, currentPos, NBTTags.CURRENT_POS);
		Utilities.saveBlockPosToNBT(compound, endPos, NBTTags.END_POS);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		startPos = Utilities.readBlockPosFromNBT(compound, NBTTags.START_POS);
		currentPos = Utilities.readBlockPosFromNBT(compound, NBTTags.CURRENT_POS);
		endPos = Utilities.readBlockPosFromNBT(compound, NBTTags.END_POS);
	}
	
	@Override
	public boolean continueExecutingCustom() {
		if(done && run_continously){
			currentPos = startPos;
			done = false;
		}
		return !done;
		//return true;
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
		if(isNearTargetPosition() || aiOwnerPathfinder.noPath()){
			incMiningProgress();
			if(miningProgress >= MAX_MINING_TICKS){
				mineNextBlock();	
			}
		}
		moveHead();
	}
	
	private void moveHead(){
		Vec3d tPos = getTargetPos();
		if(tPos != null){
			aiOwner.getLookHelper().setLookPosition(tPos.xCoord + 0.5, tPos.yCoord, tPos.zCoord + 0.5, (float)aiOwner.getHorizontalFaceSpeed(), (float)aiOwner.getVerticalFaceSpeed());
		}
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
		
		if(currentPos.getX() >= endPos.getX()){
			if(currentPos.getZ() >= endPos.getZ()){
				if(currentPos.getY() <= endPos.getY()){
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
		return true;
	}
	
	private void mineNextBlock(){
		if(canMineBlock(currentPos)){
			breakBlock(currentPos);
		}
		
		int tryes = 0;
		do {
			if(!incPos()){
				done = true;
				break;
			}
			tryes++;
		} while (!canMineBlock(currentPos) && tryes < 10);
		if(canMineBlock(currentPos)){
			setTargetPos(new Vec3d(currentPos.add(0, 1, 0)));
		}
		saveData(aiOwner);
	}
	
	private boolean canMineBlock(BlockPos pos){
		IBlockState state = world.getBlockState(pos);
		return state.getBlockHardness(world, pos) >= 0 && state.getMaterial() != Material.AIR && !state.getMaterial().isLiquid();
	}
	
	private void breakBlock(BlockPos pos){
		world.destroyBlock(pos, true);
	}
	
	public static void addAdvancedTooltip(ItemStack stack, EntityPlayer playerIn, List<String> tooltip){
		NBTTagCompound compound = stack.getTagCompound();
		BlockPos p1 = Utilities.readBlockPosFromNBT(compound, NBTTags.AREA_MIN);
		BlockPos p2 = Utilities.readBlockPosFromNBT(compound, NBTTags.AREA_MAX);
		
		tooltip.add(TextFormatting.DARK_PURPLE + "Area:");
		if(p1 == null || p2 == null){
			tooltip.add("-");
		}
		else{
			tooltip.add("X: " + TextFormatting.GOLD + p1.getX() + " - " + p2.getX());
			tooltip.add("Y: " + TextFormatting.GOLD + p1.getY() + " - " + p2.getY());
			tooltip.add("Z: " + TextFormatting.GOLD + p1.getZ() + " - " + p2.getZ());
		}
	}
	
}
