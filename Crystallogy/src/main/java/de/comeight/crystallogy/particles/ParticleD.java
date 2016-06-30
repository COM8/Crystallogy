package de.comeight.crystallogy.particles;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleD extends BaseParticleExtended {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation RL_PARTICLE_D = new ResourceLocation("crystallogy:particles/d/d0");

	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleD(World worldIn, Vec3d pos) {
		super(ParticleInformation.ID_PARTICLE_D, RL_PARTICLE_D, worldIn, pos);
		
		this.countParticleTextures = 32;
	}

	public ParticleD() {
		super(ParticleInformation.ID_PARTICLE_D, RL_PARTICLE_D);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp) {
		return new ParticleD(worldIn, pos);
	}

}
