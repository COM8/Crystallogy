package de.comeight.crystallogy.tileEntitys;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import de.comeight.crystallogy.entity.PlayerClientDummy;
import de.comeight.crystallogy.util.Log;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TileEntityPlayerJar extends TileEntityEntityJar {

	//-----------------------------------------------Variabeln:---------------------------------------------
	protected GameProfile profile;

	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityPlayerJar() {
		super();
		profile = null;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public void setEntity(EntityLivingBase entity) {
		if(entity instanceof PlayerClientDummy){
			PlayerClientDummy player = (PlayerClientDummy) entity;
			this.profile = player.getGameProfile();
			
			super.setEntity(entity);
		}
		else{
			Log.error("Entity is no instance of PlayerClientDummy!");
		}
	}
	
	@Override
	public boolean hasEntity() {
		if(profile != null){
			return true;
		}
		return false;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void writeCustomDataToNBT(NBTTagCompound compound) {
		if(profile != null){
			compound.setBoolean("hasEntity", true);
			compound.setString("playerName", profile.getName());
			compound.setUniqueId("playerUUID", profile.getId());
		}
		else{
			compound.setBoolean("hasPlayer", false);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		writeCustomDataToNBT(compound);
		super.writeToNBT(compound);
	}
	
	@Override
	public void readCustomDataToNBT(NBTTagCompound compound) {
		if(compound.getBoolean("hasEntity")){
			String name = compound.getString("playerName");
			UUID uuid = compound.getUniqueId("playerUUID");
			if(!name.equals("")){
				profile = new GameProfile(uuid, name);
				if(worldObj != null){
					entity = new PlayerClientDummy(worldObj, profile);
				}
			}
		}
		else{
			this.entity = null;
			this.profile = null;
		}
	}
	
	@Override
	public void removeEntity(World worldIn, Vec3d pos, boolean release) {
		profile = null;
		super.removeEntity(worldIn, pos, release);
	}
	
	@Override
	protected void tryCastOnEntity(){
		EntityPlayer ePlayer;
		if((ePlayer = getPlayerFromServer()) != null){
			threat.castOnEntity(ePlayer.getEntityWorld(), ePlayer);
		}
	}
	
	private EntityPlayer getPlayerFromServer(){
		WorldServer server = (WorldServer) worldObj;
		PlayerList playerList = server.getMinecraftServer().getPlayerList();
		EntityPlayer ePlayer = null;
		if((ePlayer = playerList.getPlayerByUUID(profile.getId())) == null){
			ePlayer = playerList.getPlayerByUsername(profile.getName());
		}
		
		return ePlayer;
	}
	
}
