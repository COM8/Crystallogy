package de.comeight.crystallogy.particles;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleE extends BaseParticleExtended {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation RL_PARTICLE_E = new ResourceLocation("crystallogy:particles/e/e");

	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleE(World worldIn, Vec3d pos) {
		super(ParticleInformation.ID_PARTICLE_E, RL_PARTICLE_E, worldIn, pos);
		
		this.countParticleTextures = 32;
	}

	public ParticleE() {
		super(ParticleInformation.ID_PARTICLE_E, RL_PARTICLE_E);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp) {
		return new ParticleE(worldIn, pos);
	}

}
