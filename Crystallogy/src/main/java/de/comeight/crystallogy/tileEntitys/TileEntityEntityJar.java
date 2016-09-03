package de.comeight.crystallogy.tileEntitys;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.handler.SoundHandler;
import de.comeight.crystallogy.items.threatDusts.ThreatDust;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleB;
import de.comeight.crystallogy.particles.ParticleInformation;
import de.comeight.crystallogy.particles.TransportParticle;
import de.comeight.crystallogy.util.NetworkUtilitis;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityEntityJar extends BaseTileEntity implements ITickable{

	//-----------------------------------------------Variabeln:---------------------------------------------
	protected EntityLivingBase entity;
	protected EnumThreats threat;
	protected int threatTick; 
	protected int tick;
	protected boolean newEntity;
	protected NBTTagCompound entityCompound;
	
	public enum EnumThreats{
		POISON(new RGBColor(0.0F, 1.0F, 0.0F), ItemHandler.poisDust, 0),
		DAMAGE(new RGBColor(1.0F, 0.2F, 0.2F), ItemHandler.damDust, 1),
		FIRE(new RGBColor(1.0F, 0.0F, 0.0F), ItemHandler.fireDust, 2),
		DROWN(new RGBColor(0.0F, 0.0F, 1.0F), ItemHandler.drowDust, 3),
		HUNGER(new RGBColor(0.5F, 0.5F, 0.5F), ItemHandler.hungDust, 4),
		BADLUCK(new RGBColor(0.2F, 0.1F, 0.0F), ItemHandler.badLuckDust, 5),
		BLIND(new RGBColor(0.1F, 0.1F, 0.1F), ItemHandler.blindDust, 6),
		ENDER(new RGBColor(0.5F, 0.0F, 0.5F), ItemHandler.enderDust, 7),
		GLOW(new RGBColor(0.5F, 0.1F, 0.5F), ItemHandler.glowDust, 8),
		LEVITATION(new RGBColor(0.3F, 0.3F, 1.0F), ItemHandler.levDust, 9);
		
		private final int id;
		private final RGBColor color;
		private ThreatDust threatDust;
		private static ArrayList<EnumThreats> list = new ArrayList<TileEntityEntityJar.EnumThreats>();
		
		private EnumThreats(RGBColor color, ThreatDust threatDust, int id){
			this.color = color;
			this.threatDust = threatDust;
			this.id = id;			
		}
		
		public static EnumThreats getThreatDust(ItemStack stack){
			if(!(stack.getItem() instanceof ThreatDust)){
				return null;
			}
			
			for(EnumThreats threat : values()){
				if(threat.threatDust == stack.getItem()){
					return threat;
				}
			}
			return null;
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
		newEntity = false;
		entityCompound = null;
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
		return entity != null;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void addThreat(EnumThreats threat){
		threatTick = 0;
		this.threat = threat;
	}
	
	public void writeCustomDataToNBT(NBTTagCompound compound) {
		if(hasEntity()){
			compound.setBoolean("hasEntity", true);
			compound.setString("name", entity.getName());
			compound.setUniqueId("uuid", entity.getUniqueID());
		}
		else{
			compound.setBoolean("hasEntity", false);
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		writeCustomDataToNBT(compound);
		super.writeToNBT(compound);
		return compound;
	}
	
	public void readCustomDataFromNBT(NBTTagCompound compound) {
		if(compound.getBoolean("hasEntity")){
			newEntity = true;
			entityCompound = (NBTTagCompound) compound.copy();
		}
		else{
			this.entity = null;
		}
	}
	
	protected EntityLivingBase findEntity(UUID uuid){
		List<Entity> list = worldObj.loadedEntityList;
		for (Entity entity : list) {
			if(uuid.equals(entity.getUniqueID())){
				return (EntityLivingBase) entity;
			}
		}
		return null;
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
		readCustomDataFromNBT(compound);
		super.readFromNBT(compound);
	}
	
	public void removeEntity(World worldIn, Vec3d pos, boolean release){
		if(hasEntity()){
			if(release){
				entity = null;
				addEffects(worldIn, pos);
				
				if(!worldIn.isRemote){
					sync();
				}
			}
		}
	}
	
	protected void addEffects(World worldIn, Vec3d pos){
		if(worldIn.isRemote){
			for (int i = 0; i < 5; i++) {
				
				TransportParticle tP = new TransportParticle(new Vec3d(pos.xCoord + 0.5, pos.yCoord, pos.zCoord + 0.5));
				tP.maxAge = 120;
				tP.color = new RGBColor(Utilities.getRandFloat(0, 100), Utilities.getRandFloat(0, 100), Utilities.getRandFloat(0, 100));
				NetworkParticle nP = new NetworkParticle(tP, ParticleInformation.ID_PARTICLE_B);
				nP.setSize(new Vec3d(1.0, 2.0, 1.0));
				nP.setNumberOfParticle(30);
				NetworkPacketParticle pMtS = new NetworkPacketParticle(nP);
				NetworkUtilitis.sendToServer(pMtS);
			}
		}
		
		worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, pos.xCoord, pos.yCoord, pos.zCoord, false));
		worldIn.playSound(null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.ENTITY_ENDERMEN_STARE, SoundCategory.BLOCKS, 1.0F, 1.0F);
		worldIn.playSound(null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.ENTITY_ENDERDRAGON_GROWL, SoundCategory.BLOCKS, 1.0F, 1.0F);
		worldIn.playSound(null, pos.xCoord, pos.yCoord, pos.zCoord, SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.BLOCKS, 1.0F, 0.6F);
		if(Utilities.getRandInt(0, 2) == 0){
			worldIn.playSound(null, pos.xCoord, pos.yCoord, pos.zCoord, SoundHandler.EVIL_LAUGH_MALE_1, SoundCategory.BLOCKS, Utilities.getRandFloat(0.5F, 1.0F), Utilities.getRandFloat(0.2F, 1.0F));
		}
		else{
			worldIn.playSound(null, pos.xCoord, pos.yCoord, pos.zCoord, SoundHandler.EVIL_LAUGH_MALE_2, SoundCategory.BLOCKS, Utilities.getRandFloat(0.5F, 1.0F), Utilities.getRandFloat(0.2F, 1.0F));
		}
	}

	protected void incTick(){
		tick++;
		if(tick >= 20){
			tick = 0;
		}
	}
	
	private boolean loadEntityFromCompound(NBTTagCompound compound){
		entity = findEntity(entityCompound.getUniqueId("uuid"));
		return entity != null;
	}
	
	@Override
	public void update() {
		if(newEntity){ //Load the entity
			System.out.println("newm");
			if(entityCompound != null && loadEntityFromCompound(entityCompound)){
				newEntity = false;
				entityCompound = null;
			}
			else{
				newEntity = true;
			}
		}
		
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
	        	
	        	spawnThreatParticles();
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
	
	@SideOnly(Side.CLIENT)
	protected void spawnThreatParticles(){
		if(threat == null){
			return;
		}
		ParticleB gP = new ParticleB(worldObj, new Vec3d(pos.getX() + Utilities.getRandDouble(0.3, 0.7), pos.getY() + Utilities.getRandDouble(0.2, 0.6), pos.getZ() + Utilities.getRandDouble(0.25, 0.9)));
		RGBColor color = threat.getColor();
		gP.setRBGColorF(color.r, color.g, color.b);
		Minecraft.getMinecraft().effectRenderer.addEffect(gP);
	}
	
}
