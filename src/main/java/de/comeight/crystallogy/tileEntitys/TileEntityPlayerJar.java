package de.comeight.crystallogy.tileEntitys;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import de.comeight.crystallogy.util.Log;
import de.comeight.crystallogy.util.NBTTags;
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
        return profile != null;
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
	public void readCustomDataFromNBT(NBTTagCompound compound) {
		if(compound.getBoolean(NBTTags.HAS_ENTITY)){
			String name = compound.getString(NBTTags.ENTITY_NAME);
			UUID uuid = compound.getUniqueId(NBTTags.ENTITY_UUID);
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
			compound.setBoolean(NBTTags.HAS_ENTITY, true);
			compound.setString(NBTTags.ENTITY_NAME, profile.getName());
			compound.setUniqueId(NBTTags.ENTITY_UUID, profile.getId());
		}
		else{
			compound.setBoolean(NBTTags.HAS_ENTITY, false);
		}
	}
	
	@Override
	public void removeEntity(World worldIn, Vec3d pos, boolean release) {
		if(hasEntity()){
			if(release){
				entity = null;
				profile = null;
				entityCompound = null;
				newEntity = false;
				addEffects(worldIn, pos);
				
				if(!worldIn.isRemote){
					sync();
				}
			}
		}
	}
	
	@Override
	protected void tryCastOnEntity(){
		EntityPlayer ePlayer;
		if((ePlayer = getPlayerFromServer()) != null){
			threat.castOnEntity(ePlayer.getEntityWorld(), ePlayer, threatTick);
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
