package de.comeight.crystallogy.particles;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleF extends BaseParticleExtended {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation RL_PARTICLE_F = new ResourceLocation("crystallogy:particles/f/f0");

	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleF(World worldIn, Vec3d pos) {
		super(ParticleInformation.ID_PARTICLE_F, RL_PARTICLE_F, worldIn, pos);
		
		this.countParticleTextures = 32;
	}

	public ParticleF() {
		super(ParticleInformation.ID_PARTICLE_F, RL_PARTICLE_F);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp) {
		return new ParticleF(worldIn, pos);
	}

}
