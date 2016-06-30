package de.comeight.crystallogy.particles;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleN_Color extends BaseParticleExtended {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation RL_PARTICLE_N_COLOR = new ResourceLocation("crystallogy:particles/n_color/n0");

	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleN_Color(World worldIn, Vec3d pos) {
		super(ParticleInformation.ID_PARTICLE_N_COLOR, RL_PARTICLE_N_COLOR, worldIn, pos);
		
		this.countParticleTextures = 32;
	}

	public ParticleN_Color() {
		super(ParticleInformation.ID_PARTICLE_N_COLOR, RL_PARTICLE_N_COLOR);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp) {
		return new ParticleN_Color(worldIn, pos);
	}

}
