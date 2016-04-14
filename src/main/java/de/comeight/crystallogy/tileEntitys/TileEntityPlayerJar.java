package de.comeight.crystallogy.tileEntitys;

import java.util.ArrayList;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.entity.PlayerClientDummy;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TileEntityPlayerJar extends TileEntity implements ITickable{

	//-----------------------------------------------Variabeln:---------------------------------------------
	private PlayerClientDummy player;
	private GameProfile profile;
	private EnumThreats threat; 
	
	public enum EnumThreats{
		POISON(new RGBColor(0.0F, 1.0F, 0.0F), 0),
		DAMAGE(new RGBColor(1.0F, 0.2F, 0.2F), 1),
		FIRE(new RGBColor(1.0F, 0.0F, 0.0F), 2),
		DROWN(new RGBColor(0.0F, 0.0F, 1.0F), 3);
		
		private final int id;
		private final RGBColor color;
		private static ArrayList<EnumThreats> list = new ArrayList<TileEntityPlayerJar.EnumThreats>();
		
		private EnumThreats(RGBColor color, int id){
			this.color = color;
			this.id = id;
			
		}
		
		public int getID(){
			return id;
		}
		
		public RGBColor getColor(){
			return color;
		}
		
		public NBTTagCompound toNBTTagCompound(NBTTagCompound compound, String key){
			compound.setInteger(key + "_EnumThreat", id);
			
			return compound;
		}
		
		public static EnumThreats fromNBTTagCompound(NBTTagCompound compound, String key){
			int _id = compound.getInteger(key + "_EnumThreat");
			for(EnumThreats threat : values()){
				if(threat.getID() == _id){
					return threat;
				}
			}
			return null;
		}
		
	}

	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityPlayerJar() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public PlayerClientDummy getPlayer() {
		return player;
	}

	public void setPlayer(PlayerClientDummy player) {
		this.player = player;
		this.profile = player.getGameProfile();
		markDirty();
	}
	
	public void setThreat(EnumThreats threat){
		this.threat = threat;
	}
	
	public EnumThreats getThreat(){
		return threat;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public boolean hasPlayer(){
		if(profile != null){
			return true;
		}
		return false;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if(profile != null){
			compound.setBoolean("hasPlayer", true);
			compound.setString("playerName", profile.getName());
			compound.setUniqueId("playerUUID", profile.getId());
		}
		else{
			compound.setBoolean("hasPlayer", false);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if(compound.getBoolean("hasPlayer")){
			String name = compound.getString("playerName");
			UUID uuid = compound.getUniqueId("playerUUID");
			if(!name.equals("")){
				profile = new GameProfile(uuid, name);
				if(worldObj != null){
					player = new PlayerClientDummy(worldObj, profile);
				}
			}
		}
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		readFromNBT(pkt.getNbtCompound());
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
		return new SPacketUpdateTileEntity(pos, 0, nbt);
	}
	
	public void removePlayer(World worldIn, Vec3d pos, boolean release){
		if(hasPlayer()){
			if(release){
				player = null;
				profile = null;
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
					worldIn.playSound((EntityPlayer)null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.entity_endermen_stare, SoundCategory.BLOCKS, 1.0F, 1.0F);
					worldIn.playSound((EntityPlayer)null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.entity_enderdragon_growl, SoundCategory.BLOCKS, 1.0F, 1.0F);
					worldIn.playSound((EntityPlayer)null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.entity_wither_spawn, SoundCategory.BLOCKS, 1.0F, 0.6F);
				}
			}
		}
	}

	@Override
	public void update() {
		if(!hasPlayer()){
			return;
		}
		if(Utilities.getRandInt(0, 5) == 0){
			double d1 = Utilities.getRandDouble(0.5, 0.7);
			double d2 = Utilities.getRandDouble(0.5, 0.7);
			double d3 = Utilities.getRandDouble(0.2, 0.6);
        	worldObj.spawnParticle(EnumParticleTypes.WATER_WAKE, pos.getX() + d1, pos.getY() + 0.65, pos.getZ() + d2, 0.0, -0.015, 0.0, new int[0]);
        }
	}
	
}
