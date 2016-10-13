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

public class EntityAiFollowPlayer extends EntityAiMoveToPos {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private EntityPlayer playerTarget;
	private UUID playerUUID;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityAiFollowPlayer(EntityLiving aiOwner, UUID playerUUID, double movementSpeed) {
		super(aiOwner, null, movementSpeed);
		this.playerTarget = findPlayerInWorld(playerUUID);
		this.playerUUID = playerUUID;
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
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean continueExecuting() {
		updatePlayerPos();
		if(isPlayerReachable() &&  super.continueExecuting() && playerTarget != null && playerTarget.getEntityWorld() == aiOwner.getEntityWorld()){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public boolean shouldExecute() {
		if(playerTarget == null){
			playerTarget = findPlayerInWorld(playerUUID);
		}
		
		if(playerTarget == null){
			return false;
		}
		setTargetPos(playerTarget.getPositionVector());
		if(isPlayerReachable()){
			updatePlayerPos();
			if(isPlayerReachable() &&  super.shouldExecute()){
				return playerTarget.getEntityWorld() == aiOwner.getEntityWorld();
			}
		}
		return false;
	}
	
	private void updatePlayerPos(){
		if(playerTarget != null){
			setTargetPos(playerTarget.getPositionVector());
			saveData(aiOwner);
		}
	}
	
	@Override
	protected boolean shouldRecalcPath() {
		if(super.shouldRecalcPath()){
			return true;
		}
		else{
			if(getTargetPos().distanceTo(playerTarget.getPositionVector()) > 2){
				timeSincePathRecalc = 0;
				return true;
			}
			return false;
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
		tooltip.add("§5Player:");
		tooltip.add("Name: §6" + compound.getString("playerName"));
		tooltip.add("UUID: §6" + compound.getUniqueId("playerUUID"));
	}
	
}
