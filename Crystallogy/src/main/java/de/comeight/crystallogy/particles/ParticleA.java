package de.comeight.crystallogy.particles;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleA extends BaseParticleExtended {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation RL_PARTICLE_A = new ResourceLocation("crystallogy:particles/a/a");

	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleA(World worldIn, Vec3d pos) {
		super(ParticleInformation.ID_PARTICLE_A, RL_PARTICLE_A, worldIn, pos);
		
		this.countParticleTextures = 32;
	}

	public ParticleA() {
		super(ParticleInformation.ID_PARTICLE_A, RL_PARTICLE_A);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp) {
		return new ParticleA(worldIn, pos);
	}

}
