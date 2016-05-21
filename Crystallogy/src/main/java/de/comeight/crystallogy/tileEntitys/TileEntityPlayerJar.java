package de.comeight.crystallogy.tileEntitys;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.entity.PlayerClientDummy;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.util.Log;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.SoundCategory;
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
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		writeCustomDataToNBT(compound);
		super.writeToNBT(compound);
		return compound;
	}
	
	@Override
	public void readCustomDataToNBT(NBTTagCompound compound) {
		if(compound.getBoolean("hasEntity")){
			String name = compound.getString("name");
			UUID uuid = compound.getUniqueId("uuid");
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
	public void writeCustomDataToNBT(NBTTagCompound compound) {
		if(hasEntity()){
			compound.setBoolean("hasEntity", true);
			compound.setString("name", profile.getName());
			compound.setUniqueId("uuid", profile.getId());
		}
		else{
			compound.setBoolean("hasEntity", false);
		}
	}
	
	@Override
	public void removeEntity(World worldIn, Vec3d pos, boolean release) {
		if(hasEntity()){
			if(release){
				entity = null;
				profile = null;
				sync();
				
				if(worldIn.isRemote){
					for (int i = 0; i < 5; i++) { //Particel:
						ParticleB gP = new ParticleB(worldIn, pos.xCoord + 0.5, pos.yCoord, pos.zCoord + 0.5, 0.0, 0.0, 0.0);
						gP.setParticleMaxAge(120);
						gP.setRBGColorF(Utilities.getRandFloat(0, 100), Utilities.getRandFloat(0, 100), Utilities.getRandFloat(0, 100));
						NetworkParticle nP = new NetworkParticle(gP, gP.name);
						nP.setSize(new Vec3d(1.0, 2.0, 1.0));
						nP.setNumberOfParticle(30);
						NetworkPacketParticle pMtS = new NetworkPacketParticle(nP);
						CommonProxy.NETWORKWRAPPER.sendToServer(pMtS);	
					}
					
					worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, pos.xCoord, pos.yCoord, pos.zCoord, false));
					worldIn.playSound((EntityPlayer)null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.ENTITY_ENDERMEN_STARE, SoundCategory.BLOCKS, 1.0F, 1.0F);
					worldIn.playSound((EntityPlayer)null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.ENTITY_ENDERDRAGON_GROWL, SoundCategory.BLOCKS, 1.0F, 1.0F);
					worldIn.playSound((EntityPlayer)null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.BLOCKS, 1.0F, 0.6F);
				}
				else{
					
				}
			}
		}
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
