package de.comeight.crystallogy.tileEntitys;

import java.util.List;
import java.util.UUID;

import de.comeight.crystallogy.handler.AiHandler;
import de.comeight.crystallogy.handler.SoundHandler;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleInformation;
import de.comeight.crystallogy.particles.TransportParticle;
import de.comeight.crystallogy.util.EnumThreats;
import de.comeight.crystallogy.util.NBTTags;
import de.comeight.crystallogy.util.NetworkUtilitis;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
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
	protected boolean newEntity;
	protected NBTTagCompound entityCompound;

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
		if(entity != null && entity instanceof EntityLiving){
			((EntityLiving) entity).enablePersistence();
		}
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
	
	public void addCustomAi(NBTTagCompound compound){
		Entity e = getEntity();
		if(e != null && e instanceof EntityLiving){
			AiHandler.addAiToEntity((EntityLiving) getEntity(), compound);
		}
	}
	
	public void writeCustomDataToNBT(NBTTagCompound compound) {
		if(hasEntity()){
			compound.setBoolean(NBTTags.HAS_ENTITY, true);
			compound.setString(NBTTags.ENTITY_NAME, entity.getName());
			compound.setUniqueId(NBTTags.ENTITY_UUID, entity.getUniqueID());
		}
		else{
			compound.setBoolean(NBTTags.HAS_ENTITY, false);
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		writeCustomDataToNBT(compound);
		super.writeToNBT(compound);
		return compound;
	}
	
	public void readCustomDataFromNBT(NBTTagCompound compound) {
		if(compound.getBoolean(NBTTags.HAS_ENTITY)){
			newEntity = true;
			entityCompound = compound.copy();
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
				entityCompound = null;
				newEntity = false;
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
		if(tick >= threat.getTicksBetweenCalls()){
			tick = 0;
		}
	}
	
	private boolean loadEntityFromCompound(NBTTagCompound compound){
		entity = findEntity(entityCompound.getUniqueId(NBTTags.ENTITY_UUID));
		return entity != null;
	}
	
	@Override
	public void update() {
		if(newEntity || entity == null){ //Load the entity
			if(entityCompound != null && loadEntityFromCompound(entityCompound)){
				newEntity = false;
				worldObj.notifyNeighborsOfStateChange(pos, getBlockType());
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
	        	worldObj.spawnParticle(EnumParticleTypes.WATER_WAKE, pos.getX() + d1, pos.getY() + 0.65, pos.getZ() + d2, 0.0, -0.015, 0.0);
	        }
		}
		else{
			if(threat == null){
				return;
			}
			spawnThreatParticles();
			incTick();
			if(tick == 0){
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
			threat.castOnEntity(entity.getEntityWorld(), entity, threatTick);
		}
	}
	
	protected void spawnThreatParticles(){
		if(threat == null){
			return;
		}
		//WorldServer wS = (WorldServer) worldObj;
		//wS.spawnParticle(EnumParticleTypes.SPELL_MOB_AMBIENT, pos.getX() + 0.5, pos.getY() + 0.7, pos.getZ() + 0.5, 1, threat.getColor().r, threat.getColor().g, threat.getColor().b, 0.1, new int[0]);
	}
	
}
