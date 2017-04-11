package de.comeight.crystallogy.particles;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleB extends BaseParticleExtended {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation RL_PARTICLE_B = new ResourceLocation("crystallogy:particles/b/b");

	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleB(World worldIn, Vec3d pos) {
		super(ParticleInformation.ID_PARTICLE_B, RL_PARTICLE_B, worldIn, pos);
		
		this.countParticleTextures = 32;
	}

	public ParticleB() {
		super(ParticleInformation.ID_PARTICLE_B, RL_PARTICLE_B);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp) {
		return new ParticleB(worldIn, pos);
	}

}
