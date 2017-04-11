package de.comeight.crystallogy.particles;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleInfusion extends BaseParticle {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private Vec3d targetPos;
	private Vec3d startPos;
	private Vec3d difStartTarget;
	
	private int timeInTicks = 100;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleInfusion(World worldIn, Vec3d pos, Vec3d targetPos, int timeInTicks) {
		super(ParticleInformation.ID_PARTICLE_INFUSION, ParticleN_Color.RL_PARTICLE_N_COLOR, worldIn, pos);
		
		this.countParticleTextures = 32;
		
		this.targetPos = targetPos;
		this.startPos = pos;
		this.timeInTicks = timeInTicks;
		this.setMaxAge(timeInTicks);
		
		this.particleScale = Utilities.getRandFloat(3.0F, 5.0F);
		initVariables();
	}

	public ParticleInfusion() {
		super(ParticleInformation.ID_PARTICLE_N_COLOR, ParticleN_Color.RL_PARTICLE_N_COLOR);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp) {
		return new ParticleInfusion(worldIn, pos, tp.targetPos, tp.timeInTicks);
	}
	
	private void initVariables(){
		this.difStartTarget = startPos.add(new Vec3d(-targetPos.xCoord, -targetPos.yCoord, -targetPos.zCoord));
		double x = difStartTarget.xCoord;
		double y = difStartTarget.yCoord;
		double z = difStartTarget.zCoord;
		if(x != 0){
			x /= timeInTicks;
		}
		if(y != 0){
			y /= timeInTicks;
		}
		if(z != 0){
			z /= timeInTicks;
		}
		this.difStartTarget = new Vec3d(-x, -y, -z);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		moveParticle();
	}

	protected void moveParticle(){
		this.moveEntity(difStartTarget.xCoord, difStartTarget.yCoord, difStartTarget.zCoord);
		timeInTicks--;
		if(timeInTicks == 0){
			this.setExpired();
		}
	}

}
