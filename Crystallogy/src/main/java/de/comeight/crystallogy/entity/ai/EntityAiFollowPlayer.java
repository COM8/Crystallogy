package de.comeight.crystallogy.entity.ai;

import java.util.List;
import java.util.UUID;

import de.comeight.crystallogy.util.EnumCustomAis;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;

public class EntityAiFollowPlayer extends EntityAiMoveToPos {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private EntityPlayer playerTarget;
	private UUID playerUUID;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityAiFollowPlayer(EntityLiving aiOwner, UUID playerUUID, double movementSpeed) {
		super(aiOwner, null, movementSpeed);
		this.playerTarget = findPlayerInWorld(playerUUID);
		this.playerUUID = playerUUID;
		this.forceMoveTo = true;
		if(playerTarget != null){
			setTargetPos(playerTarget.getPositionVector());
		}
	}
	
	public EntityAiFollowPlayer(EntityLiving aiOwner) {
		super(aiOwner);
	}
	
	public EntityAiFollowPlayer() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	private boolean isPlayerReachable(){
		return playerTarget != null && aiOwner.dimension == playerTarget.dimension;
	}
	
	@Override
	public int getAiID() {
		return EnumCustomAis.FOLLOW_PLAYER.ID;
	}
	
	private void setPlayerPosition(){
		BlockPos pos = playerTarget.getPosition();
		BlockPos p = null;
		for(int i = pos.getY(); i >= 0; i--){
			p = new BlockPos(pos.getX(), i, pos.getZ());
			if(aiOwnerPathfinder.canEntityStandOnPos(p)){
				if(getTargetPos().distanceTo(new Vec3d(p)) < 1.5){
					return;
				}
				setTargetPos(new Vec3d(p));
				return;
			}
		}
		setTargetPos(playerTarget.getPositionVector());
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean continueExecuting() {
		updatePlayerPos();
		return isPlayerReachable() && super.continueExecuting();
	}
	
	@Override
	public boolean shouldExecute() {
		if(playerTarget == null){
			playerTarget = findPlayerInWorld(playerUUID);
			if(playerTarget == null){
				return false;
			}
		}		
		
		setPlayerPosition();
		if(isPlayerReachable()){
			updatePlayerPos();
			if(isPlayerReachable()){
				return super.shouldExecute();
			}
		}
		return false;
	}
	
	private void updatePlayerPos(){
		if(playerTarget != null){
			setPlayerPosition();
			saveData(aiOwner);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setUniqueId("playerUUID", playerTarget.getUniqueID());
		compound.setUniqueId("playerUUID", playerUUID);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.playerUUID = compound.getUniqueId("playerUUID");
		playerTarget = findPlayerInWorld(playerUUID);
	}
	
	private EntityPlayer findPlayerInWorld(UUID playerUUID){
		for(EntityPlayer eP: world.playerEntities){
			if(eP.getUniqueID().equals(playerUUID)){
				return eP;
			}
		}
		return null;
	}
	
	public static void addAdvancedTooltip(ItemStack stack, EntityPlayer playerIn, List<String> tooltip){
		NBTTagCompound compound = stack.getTagCompound();
		BlockPos p1 = Utilities.readBlockPosFromNBT(compound, "targetPos");
		tooltip.add(TextFormatting.DARK_PURPLE + "Player:");
		tooltip.add("Name: " + TextFormatting.GOLD + compound.getString("playerName"));
		tooltip.add("UUID: " + TextFormatting.GOLD + compound.getUniqueId("playerUUID"));
	}
	
}
