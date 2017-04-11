package de.comeight.crystallogy.particles;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleC extends BaseParticleExtended {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation RL_PARTICLE_C = new ResourceLocation("crystallogy:particles/c/c");

	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleC(World worldIn, Vec3d pos) {
		super(ParticleInformation.ID_PARTICLE_C, RL_PARTICLE_C, worldIn, pos);
		
		this.countParticleTextures = 32;
	}

	public ParticleC() {
		super(ParticleInformation.ID_PARTICLE_C, RL_PARTICLE_C);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp) {
		return new ParticleC(worldIn, pos);
	}

}
