package de.comeight.crystallogy.items.threatDusts;

import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkParticle;
import de.comeight.crystallogy.particles.ParticleInformation;
import de.comeight.crystallogy.particles.TransportParticle;
import de.comeight.crystallogy.util.EnumThreats;
import de.comeight.crystallogy.util.NetworkUtilitis;
import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AiRemoverDust extends ThreatDust {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "aiRemoverDust";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public AiRemoverDust() {
		super(0, 0, false, 2000, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public int getTicksBetweenCalls() {
		return 0;
	}
	private Vec3d getRandomParticleSpawn(Vec3d targetPos){
		return new Vec3d(targetPos.xCoord + Utilities.getRandDouble(-4.0, 4.0), targetPos.yCoord + Utilities.getRandDouble(-4.0, 4.0), targetPos.zCoord + Utilities.getRandDouble(-4.0, 4.0));
	}
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void castOnEntity(World worldIn, EntityLivingBase entity, int tick) {
		if(entity instanceof EntityPlayer){
			return;
		}
		if(entity instanceof EntityLiving){
			if(tick == numOfCalls){
				lastCast((EntityLiving) entity);
			}
			if(tick % 10 == 0 && ((double)tick / (double)numOfCalls) < 0.9){
				spawnParticles(worldIn, entity, false, (int) (5 + tick * 0.05), new RGBColor(Utilities.getRandFloat(0.0F, 0.3F), Utilities.getRandFloat(0.0F, 0.3F), Utilities.getRandFloat(0.0F, 0.3F)));
			}
			if(tick % 20 == 0){
				entity.playSound(SoundEvents.ENTITY_ENDERMITE_STEP, Utilities.getRandFloat(0.5F, 0.75F), Utilities.getRandFloat(0.75F, 1.75F));
			}
			if(((double)tick / (double)numOfCalls) >= 0.9){
				spawnParticles(worldIn, entity, true, 20, new RGBColor(Utilities.getRandFloat(0.0F, 0.3F), Utilities.getRandFloat(0.0F, 0.3F), Utilities.getRandFloat(0.0F, 0.3F)));
				if(tick % 10 == 0){
					entity.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, Utilities.getRandFloat(0.5F, 0.75F), Utilities.getRandFloat(0.75F, 1.75F));
				}
			}
		}
	}
	
	private void spawnParticles(World worldIn, EntityLivingBase entity, boolean reverse, int numberOfParticles, RGBColor color) {
		TransportParticle tP = new TransportParticle(getRandomParticleSpawn(entity.getPositionVector()));
		tP.maxAge = 100;
		tP.reverse = reverse;
		tP.targetPos = entity.getPositionVector().addVector(0.0, entity.getEyeHeight() * 0.5, 0.0);
		tP.color = color;
		NetworkParticle nP = new NetworkParticle(tP, ParticleInformation.ID_PARTICLE_ENDERON);
		nP.setSize(new Vec3d(4.0, 4.0, 4.0));
		nP.setNumberOfParticle(numberOfParticles);
		NetworkPacketParticle pMtS = new NetworkPacketParticle(nP);
		NetworkUtilitis.sendToServer(pMtS);
	}
	
	private void lastCast(EntityLiving entity){
		if(Utilities.getRandInt(0, 10) > 6){
			removeAiFailure(entity);
			spawnParticles(entity.worldObj, entity, true, 200, new RGBColor(1.0F, 0.2F, 0.2F));
			entity.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, Utilities.getRandFloat(0.5F, 0.75F), Utilities.getRandFloat(0.75F, 1.75F));
		}
		else{
			removeAiSuccess(entity);
			spawnParticles(entity.worldObj, entity, true, 200, new RGBColor(0.2F, 1.0F, 0.2F));
			entity.playSound(SoundEvents.ENTITY_ENDERDRAGON_GROWL, Utilities.getRandFloat(0.5F, 0.75F), Utilities.getRandFloat(0.75F, 1.75F));
		}
	}
	
	private void removeAiFailure(EntityLiving entity){
		entity.onKillCommand();
	}
	
	private void removeAiSuccess(EntityLiving entity){
		entity.tasks.taskEntries.clear();
	}
	
}
