package de.comeight.crystallogy.entity.ai;

import java.util.List;

import de.comeight.crystallogy.handler.AiHandler;
import de.comeight.crystallogy.util.EnumCustomAis;
import de.comeight.crystallogy.util.NBTTags;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class EntityAiMoveToPos extends EntityAiBaseSerializable {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private Vec3d targetPos;
	protected World world;
	protected double movementSpeed;
	protected final PathNavigate aiOwnerPathfinder;
	protected int timeSincePathRecalc;
	protected boolean forceMoveTo;
	private int noMotionTicks;
	protected Vec3d prevPos;
	private boolean newTargetPos;
	private int teleportCooldown;
	private boolean requestedTeleport;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityAiMoveToPos(EntityLiving aiOwner, Vec3d targetPos, double movementSpeed) {
		super(aiOwner);
		this.aiOwner = aiOwner;
		this.targetPos = targetPos;
		this.movementSpeed = movementSpeed;
		this.aiOwnerPathfinder = aiOwner.getNavigator();
		this.timeSincePathRecalc = 0;
		this.world = aiOwner.worldObj;
		this.forceMoveTo = false;
		this.noMotionTicks = 0;
		this.prevPos = aiOwner.getPositionVector();
		this.newTargetPos = false;
		this.teleportCooldown = 0;
		this.requestedTeleport = false;
		setMutexBits(1);
		AiHandler.addedCustomAi(getAiID(), aiOwner);
	}
	
	public EntityAiMoveToPos(EntityLiving aiOwner){
		super(aiOwner);
		this.aiOwner = aiOwner;
		this.aiOwnerPathfinder = aiOwner.getNavigator();
		this.timeSincePathRecalc = 0;
		this.world = aiOwner.worldObj;
		this.noMotionTicks = 0;
		this.prevPos = aiOwner.getPositionVector();
		this.newTargetPos = false;
		this.teleportCooldown = 0;
		this.requestedTeleport = false;
		readData(aiOwner);
		setMutexBits(1);
	}
	
	public EntityAiMoveToPos() {
		super(null);
		this.aiOwnerPathfinder = null;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	protected boolean isNearTargetPosition(){
		return aiOwner.getPositionVector().squareDistanceTo(targetPos) <= 2;
	}
	
	protected void setTargetPos(Vec3d targetPos){
		this.targetPos = targetPos;
		saveData(aiOwner);
		newTargetPos = true;
	}
	
	protected Vec3d getTargetPos(){
		return targetPos;
	}
	
	@Override
	public int getAiID() {
		return EnumCustomAis.MOVE_TO_POS.ID;
	}
	
	private boolean isEmptyBlock(BlockPos pos)
    {
        IBlockState iblockstate = world.getBlockState(pos);
        return iblockstate.getMaterial() == Material.AIR || !iblockstate.isFullCube();
    }
	
	public boolean isForceMoveTo() {
		return forceMoveTo;
	}

	public void setForceMoveTo(boolean forceMoveTo) {
		this.forceMoveTo = forceMoveTo;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean shouldExecute() {
		if(!saved){
			saveData(aiOwner);
		}
		if(!super.shouldExecute() || targetPos == null){
			return false;
		}
		if(aiOwner instanceof EntityTameable){
			EntityTameable pet = (EntityTameable) aiOwner;
			if(pet.isSitting()){
				return false;
			}
		}
        return aiOwnerPathfinder.canEntityStandOnPos(new BlockPos(targetPos)) && !isNearTargetPosition();
    }
	
	@Override
	public boolean continueExecutingCustom() {
		return !(aiOwnerPathfinder.noPath() && !newTargetPos) || (targetPos == null && newTargetPos);
	}
	
	@Override
	public void startExecuting() {
		timeSincePathRecalc = 0;
		recalcPath();
	}
	
	@Override
	public void resetTask() {
		aiOwnerPathfinder.clearPathEntity();
	}
	
	@Override
	public void updateTask() {
		decTeleportCooldown();
		if(aiOwner instanceof EntityTameable){
			EntityTameable pet = (EntityTameable) aiOwner;
			if(pet.isSitting()){
				return;
			}
		}
		if (shouldRecalcPath())
        {
			recalcPath();
        }
		prevPos = aiOwner.getPositionVector();
	}
	
	private void decTeleportCooldown(){
		if(teleportCooldown > 0){
			teleportCooldown--;
		}
	}
	
	protected void recalcPath(){
		if(targetPos == null){
			return;
		}
		if(requestedTeleport && teleportCooldown <= 0){
			teleportNearTarget();
			requestedTeleport = false;
			noMotionTicks = 0;
			return;
		}
		if(targetPos.distanceTo(aiOwner.getPositionVector()) > 16 && forceMoveTo){
			requestedTeleport = true;
		}
		else{
			if(noMotionTicks > 10){
				noMotionTicks = 0;
				if(forceMoveTo){
					requestedTeleport = true;
				}
			}
			else {
				aiOwnerPathfinder.tryMoveToXYZ(targetPos.xCoord, targetPos.yCoord, targetPos.zCoord, movementSpeed);
			}
		}
	}
	
	protected void teleportNearTarget(){
		teleportCooldown = 20;
		int i = MathHelper.floor_double(targetPos.xCoord) - 2;
        int j = MathHelper.floor_double(targetPos.zCoord) - 2;
        int k = MathHelper.floor_double(targetPos.yCoord);

        for (int l = 0; l <= 4; ++l)
        {
            for (int i1 = 0; i1 <= 4; ++i1)
            {
            	BlockPos p = new BlockPos(i + l, k - 1, j + i1);
            	IBlockState state = world.getBlockState(p);
            	
                if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && state.isSideSolid(world, p, EnumFacing.UP) && isEmptyBlock(new BlockPos(i + l, k, j + i1)) && isEmptyBlock(new BlockPos(i + l, k + 1, j + i1)))
                {
                    aiOwner.setLocationAndAngles((double)((float)(i + l) + 0.5F), (double)k, (double)((float)(j + i1) + 0.5F), aiOwner.rotationYaw, aiOwner.rotationPitch);
                    aiOwnerPathfinder.clearPathEntity();
                    return;
                }
            }
        }
	}
	
	protected boolean shouldRecalcPath(){
		if(prevPos.distanceTo(aiOwner.getPositionVector()) <= 0 && !isNearTargetPosition() && aiOwner.onGround){
			noMotionTicks++;
		}
		else{
			noMotionTicks = 0;
		}
		prevPos = aiOwner.getPositionVector();
		
		++timeSincePathRecalc;
		if (timeSincePathRecalc >= 20 || newTargetPos)
        {
			newTargetPos = false;
            timeSincePathRecalc = 0;
            return !aiOwner.getLeashed() && aiOwner.onGround;
        }
		return false;
	}

	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		Utilities.saveVec3dToNBT(compound, targetPos, NBTTags.TARGET_POS);
		compound.setDouble(NBTTags.MOVEMENT_SPEED, movementSpeed);
		compound.setBoolean(NBTTags.FORCE_MOVE_TO, forceMoveTo);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		targetPos = Utilities.readVec3dFromNBT(compound, NBTTags.TARGET_POS);
		movementSpeed = compound.getDouble(NBTTags.MOVEMENT_SPEED);
		forceMoveTo = compound.getBoolean(NBTTags.FORCE_MOVE_TO);
	}
	
	public static void addAdvancedTooltip(ItemStack stack, EntityPlayer playerIn, List<String> tooltip){
		NBTTagCompound compound = stack.getTagCompound();
		BlockPos p1 = Utilities.readBlockPosFromNBT(compound, NBTTags.TARGET_POS);
		tooltip.add(TextFormatting.DARK_PURPLE + "Target Position: " + TextFormatting.GOLD + "X=" + p1.getX() + " Y=" + p1.getY() + " Z=" + p1.getZ());
	}
	
}
