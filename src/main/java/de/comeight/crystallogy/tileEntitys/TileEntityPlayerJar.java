package de.comeight.crystallogy.tileEntitys;

import java.util.ArrayList;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.entity.PlayerClientDummy;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.items.ThreatDust;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
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
import net.minecraft.server.management.PlayerList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TileEntityPlayerJar extends BaseTileEntity implements ITickable{

	//-----------------------------------------------Variabeln:---------------------------------------------
	private PlayerClientDummy player;
	private GameProfile profile;
	private EnumThreats threat;
	private int threatTick; 
	private int tick;
	
	public enum EnumThreats{
		POISON(new RGBColor(0.0F, 1.0F, 0.0F), ItemHandler.poisDust, 0),
		DAMAGE(new RGBColor(1.0F, 0.2F, 0.2F), ItemHandler.damDust, 1),
		FIRE(new RGBColor(1.0F, 0.0F, 0.0F), ItemHandler.fireDust, 2),
		DROWN(new RGBColor(0.0F, 0.0F, 1.0F), ItemHandler.drowDust, 3),
		HUNGER(new RGBColor(0.5F, 0.5F, 0.5F), ItemHandler.hungDust, 4);
		
		private final int id;
		private final RGBColor color;
		private ThreatDust threatDust;
		private static ArrayList<EnumThreats> list = new ArrayList<TileEntityPlayerJar.EnumThreats>();
		
		private EnumThreats(RGBColor color, ThreatDust threatDust, int id){
			this.color = color;
			this.threatDust = threatDust;
			this.id = id;			
		}
		
		public int getNumOfCalls(){
			return threatDust.numOfCalls;
		}
		
		public void castOnPlayer(World worldIn, EntityPlayer player){
			threatDust.castOnPlayer(worldIn, player);
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
			return fromID(_id);
		}
		
		public static EnumThreats fromID(int _id){
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
		tick = 0;
		threatTick = 0;
		threat = null;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public PlayerClientDummy getPlayer() {
		return player;
	}

	public void setPlayer(PlayerClientDummy player) {
		this.player = player;
		this.profile = player.getGameProfile();
		sync();
	}
	
	public void setThreat(EnumThreats threat){
		this.threat = threat;
	}
	
	public EnumThreats getThreat(){
		return threat;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void addThreat(EnumThreats threat){
		threatTick = 0;
		this.threat = threat;
	}
	
	public boolean hasPlayer(){
		if(profile != null){
			return true;
		}
		return false;
	}
	
	public void writeCustomDataToNBT(NBTTagCompound compound) {
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
	public void writeToNBT(NBTTagCompound compound) {
		writeCustomDataToNBT(compound);
		super.writeToNBT(compound);
	}
	
	public void readCustomDataToNBT(NBTTagCompound compound) {
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
		else{
			this.player = null;
			this.profile = null;
		}
	}
	
	@Override
	public NetworkPacketTileEntitySync getCustomDataPacket(NBTTagCompound compound) {
		writeToNBT(compound);
		return new NetworkPacketTileEntitySync(pos, compound);
	}
	
	@Override
	public void onCustomDataPacket(NetworkPacketTileEntitySync packet) {
		readFromNBT(packet.getNBTTagCompound());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		readCustomDataToNBT(compound);
		super.readFromNBT(compound);
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
					worldIn.playSound((EntityPlayer)null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.entity_endermen_stare, SoundCategory.BLOCKS, 1.0F, 1.0F);
					worldIn.playSound((EntityPlayer)null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.entity_enderdragon_growl, SoundCategory.BLOCKS, 1.0F, 1.0F);
					worldIn.playSound((EntityPlayer)null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.entity_wither_spawn, SoundCategory.BLOCKS, 1.0F, 0.6F);
				}
			}
		}
	}

	private void incTick(){
		tick++;
		if(tick >= 20){
			tick = 0;
		}
	}
	
	@Override
	public void update() {
		if(!hasPlayer()){
			return;
		}
		incTick();
		
		if(worldObj.isRemote){
			if(Utilities.getRandInt(0, 5) == 0){
				double d1 = Utilities.getRandDouble(0.5, 0.7);
				double d2 = Utilities.getRandDouble(0.5, 0.7);
				double d3 = Utilities.getRandDouble(0.2, 0.6);
	        	worldObj.spawnParticle(EnumParticleTypes.WATER_WAKE, pos.getX() + d1, pos.getY() + 0.65, pos.getZ() + d2, 0.0, -0.015, 0.0, new int[0]);
	        }
		}
		else{
			if(tick == 10 && threat != null){
				threatTick++;
				tryCastOnPlayer();
				if(threat.getNumOfCalls() <= threatTick){
					threat = null;
					threatTick = 0;
				}
			}
		}
	}
	
	private void tryCastOnPlayer(){
		EntityPlayer ePlayer;
		if((ePlayer = getPlayerFromServer()) != null){
			threat.castOnPlayer(ePlayer.getEntityWorld(), ePlayer);
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
