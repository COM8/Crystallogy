package de.comeight.crystallogy.particles;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleLight extends BaseParticle {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation RL_PARTICLE_LIGHT = new ResourceLocation("crystallogy:particles/l_color/l");
	
	private double speed;

	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleLight(World worldIn, Vec3d pos) {
		super(ParticleInformation.ID_PARTICLE_LIGHT, RL_PARTICLE_LIGHT, worldIn, pos);
		
		this.countParticleTextures = 32;
		
		this.particleMaxAge = Utilities.getRandInt(20, 40);
		this.particleScale = Utilities.getRandFloat(0.75F, 2.5F);
		this.speed = Utilities.getRandDouble(0.1, 0.2);
		
		setAlphaF(0.5F);
	}

	public ParticleLight() {
		super(ParticleInformation.ID_PARTICLE_LIGHT, RL_PARTICLE_LIGHT);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp) {
		return new ParticleLight(worldIn, pos);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		move();
		updateSize();
		updateAlpha();
	}
	
	private void updateSize(){
		particleScale *= 0.975F;
	}
	
	private void move(){
		moveEntity(0.0, 0.05 * speed, 0.0);
	}
	
	private void updateAlpha(){
		setAlphaF(0.5F - (particleAge / particleMaxAge));
	}

}
