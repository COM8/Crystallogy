package de.comeight.crystallogy.tileEntitys;

import java.util.ArrayList;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.items.threatDusts.ThreatDust;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TileEntityEntityJar extends BaseTileEntity implements ITickable{

	//-----------------------------------------------Variabeln:---------------------------------------------
	protected EntityLivingBase entity;
	protected EnumThreats threat;
	protected int threatTick; 
	protected int tick;
	
	public enum EnumThreats{
		POISON(new RGBColor(0.0F, 1.0F, 0.0F), ItemHandler.poisDust, 0),
		DAMAGE(new RGBColor(1.0F, 0.2F, 0.2F), ItemHandler.damDust, 1),
		FIRE(new RGBColor(1.0F, 0.0F, 0.0F), ItemHandler.fireDust, 2),
		DROWN(new RGBColor(0.0F, 0.0F, 1.0F), ItemHandler.drowDust, 3),
		HUNGER(new RGBColor(0.5F, 0.5F, 0.5F), ItemHandler.hungDust, 4);
		
		private final int id;
		private final RGBColor color;
		private ThreatDust threatDust;
		private static ArrayList<EnumThreats> list = new ArrayList<TileEntityEntityJar.EnumThreats>();
		
		private EnumThreats(RGBColor color, ThreatDust threatDust, int id){
			this.color = color;
			this.threatDust = threatDust;
			this.id = id;			
		}
		
		public int getNumOfCalls(){
			return threatDust.numOfCalls;
		}
		
		public void castOnEntity(World worldIn, EntityLivingBase entity){
			threatDust.castOnEntity(worldIn, entity);
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
	public TileEntityEntityJar() {
		tick = 0;
		threatTick = 0;
		threat = null;
		entity = null;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public EntityLivingBase getEntity() {
		return entity;
	}

	public void setEntity(EntityLivingBase entity) {
		this.entity = entity;
		sync();
	}
	
	public void setThreat(EnumThreats threat){
		this.threat = threat;
	}
	
	public EnumThreats getThreat(){
		return threat;
	}
	
	public boolean hasEntity(){
		if(entity != null){
			return true;
		}
		return false;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void addThreat(EnumThreats threat){
		threatTick = 0;
		this.threat = threat;
	}
	
	public void writeCustomDataToNBT(NBTTagCompound compound) {
		if(hasEntity()){
			compound.setBoolean("hasEntity", true);
			compound.setInteger("entityId", entity.getEntityId());
			compound.setString("name", entity.getName());
		}
		else{
			compound.setBoolean("hasEntity", false);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		writeCustomDataToNBT(compound);
		super.writeToNBT(compound);
	}
	
	public void readCustomDataToNBT(NBTTagCompound compound) {
		if(compound.getBoolean("hasEntity")){
			Entity ent = worldObj.getEntityByID(compound.getInteger("entityId"));
			if(ent instanceof EntityLivingBase){
				entity = (EntityLivingBase) ent;
			}
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
	
	public void removeEntity(World worldIn, Vec3d pos, boolean release){
		if(hasEntity()){
			if(release){
				entity = null;
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
				else{
					
				}
			}
		}
	}

	protected void incTick(){
		tick++;
		if(tick >= 20){
			tick = 0;
		}
	}
	
	@Override
	public void update() {
		if(!hasEntity()){
			if(threat != null){
				threat = null;
			}
			return;
		}
		incTick();
		
		if(entity != null && entity.isDead){
			entity = null;
			if(!worldObj.isRemote){
				sync();
			}
			return;
		}
		
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
				tryCastOnEntity();
				if(threat.getNumOfCalls() <= threatTick){
					threat = null;
					threatTick = 0;
				}
			}
		}
	}
	
	protected void tryCastOnEntity(){
		if(hasEntity()){
			threat.castOnEntity(entity.getEntityWorld(), entity);
		}
	}
	
}
