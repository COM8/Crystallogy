package de.comeight.crystallogy.particles;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleEnderon extends BaseParticle {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation RL_PARTICLE_ENDERON = new ResourceLocation("crystallogy:particles/p/p");

	protected Vec3d targetPos;
	
	protected double speedX;
	protected double speedY;
	protected double speedZ;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleEnderon(World worldIn, Vec3d pos, Vec3d targetPos) {
		super(ParticleInformation.ID_PARTICLE_ENDERON, RL_PARTICLE_ENDERON, worldIn, pos);
		
		this.targetPos = targetPos;
		this.particleMaxAge = Utilities.getRandInt(20, 50);
		this.particleScale = Utilities.getRandFloat(0.5F, 1.5F);
		
		calcSpeed();
	}

	public ParticleEnderon() {
		super(ParticleInformation.ID_PARTICLE_ENDERON, RL_PARTICLE_ENDERON);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp) {
		return new ParticleEnderon(worldIn, pos, tp.targetPos);
	}
	
	protected void calcSpeed(){
		//X:
		double temp = posX - targetPos.xCoord;
		if(temp < 0){
			temp *= -1;
		}
		speedX = temp / particleMaxAge;

		//Y:
		temp = posY - targetPos.yCoord;
		if(temp < 0){
			temp *= -1;
		}
		speedY = temp / particleMaxAge;
		
		//Z:
		temp = posZ - targetPos.zCoord;
		if(temp < 0){
			temp *= -1;
		}
		speedZ = temp / particleMaxAge;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		moveToTarget();
	}
	
	private void moveToTarget(){
		double xTemp = 0.0;
		double yTemp = 0.0;
		double zTemp = 0.0;
		
		double speed = 0.1;
		if(targetPos.xCoord - posX > 0.25){
			xTemp = speedX;
		}
		else if(this.posX - targetPos.xCoord > 0.25){
			xTemp = -speedX;
		}
		
		if(targetPos.yCoord - this.posY > 0.25){
			yTemp = speedY;
		}
		else if(this.posY - targetPos.yCoord > 0.25){
			yTemp = -speedY;
		}
		
		if(targetPos.zCoord - this.posZ > 0.25){
			zTemp = speedZ;
		}
		else if(this.posZ - targetPos.zCoord > 0.25){
			zTemp = -speedZ;
		}
		if(xTemp == 0 && yTemp == 0 && zTemp == 0){
			this.setExpired();
		}
		
		moveEntity(xTemp, yTemp, zTemp);
	}

}
