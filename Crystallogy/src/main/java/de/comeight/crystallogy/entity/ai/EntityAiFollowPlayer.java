package de.comeight.crystallogy.entity.ai;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityAiFollowPlayer extends EntityAiMoveToPos {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private EntityPlayer playerTarget;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityAiFollowPlayer(EntityLiving aiOwner, EntityPlayer playerTarget, double movementSpeed) {
		super(aiOwner, playerTarget.getPositionVector(), movementSpeed);
		this.playerTarget = playerTarget;
	}
	
	public EntityAiFollowPlayer(EntityLiving aiOwner) {
		super(aiOwner);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	private boolean isPlayerReachable(){
		return playerTarget != null && aiOwner.dimension == playerTarget.dimension;
	}
	
	@Override
	protected int getAiID() {
		return 2;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean continueExecuting() {
		updatePlayerPos();
		if(isPlayerReachable() &&  super.continueExecuting()){
			return playerTarget.getEntityWorld() == aiOwner.getEntityWorld();
		}
		else{
			return false;
		}
	}
	
	@Override
	public boolean shouldExecute() {
		if(isPlayerReachable()){
			updatePlayerPos();
			if(isPlayerReachable() &&  super.shouldExecute()){
				return playerTarget.getEntityWorld() == aiOwner.getEntityWorld();
			}
		}
		return false;
	}
	
	private void updatePlayerPos(){
		setTargetPos(playerTarget.getPositionVector());
		saveData(aiOwner);
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
	}
	
	@Override
	public void redFromNBT(NBTTagCompound compound) {
		super.redFromNBT(compound);
		playerTarget = findPlayerInWorld(compound.getUniqueId("playerUUID"));
	}
	
	private EntityPlayer findPlayerInWorld(UUID playerUUID){
		for(EntityPlayer eP: world.playerEntities){
			if(eP.getUniqueID().equals(playerUUID)){
				return eP;
			}
		}
		return null;
	}
	
}
