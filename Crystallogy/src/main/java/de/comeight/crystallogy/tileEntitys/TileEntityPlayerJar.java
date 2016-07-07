package de.comeight.crystallogy.tileEntitys;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleInformation;
import de.comeight.crystallogy.particles.TransportParticle;
import de.comeight.crystallogy.util.Log;
import de.comeight.crystallogy.util.NetworkUtilitis;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
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
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			this.profile = player.getGameProfile();
			
			super.setEntity(entity);
		}
		else{
			Log.error("Entity is no instance of EntityPlayer!");
		}
	}
	
	@Override
	public boolean hasEntity() {
		if(profile != null){
			return true;
		}
		return false;
	}
	
	public GameProfile getProfile() {
		return profile;
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
					entity = new EntityPlayer(worldObj, profile) {
						
						@Override
						public boolean isSpectator() {
							return false;
						}
						
						@Override
						public boolean isCreative() {
							return false;
						}
					};
				}
			}
		}
		else{
			entity = null;
			profile = null;
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
					TransportParticle tP = new TransportParticle(new Vec3d(pos.xCoord + 0.5, pos.yCoord, pos.zCoord + 0.5));
					tP.maxAge = 120;
					tP.randomColor = true;
					NetworkParticle nP = new NetworkParticle(tP, ParticleInformation.ID_PARTICLE_B);
					nP.setSize(new Vec3d(1.0, 2.0, 1.0));
					nP.setNumberOfParticle(70);
					NetworkPacketParticle pMtS = new NetworkPacketParticle(nP);
					NetworkUtilitis.sendToServer(pMtS);
					
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
