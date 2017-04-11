package de.comeight.crystallogy.particles;

import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleInfuserBlockStatus extends BaseParticle {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation RL_PARTICLE_INFUSER_BLOCK_STATUS = new ResourceLocation("crystallogy:particles/i/i");

	private int colorStatus;
	private int color;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleInfuserBlockStatus(World worldIn, Vec3d pos) {
		super(ParticleInformation.ID_PARTICLE_INFUSER_BLOCK_STATUS, RL_PARTICLE_INFUSER_BLOCK_STATUS, worldIn, pos);
		
		this.countParticleTextures = 32;
		
		this.particleScale = 3.0F;
		this.colorStatus = 0;
		this.color = Utilities.getRandInt(0, 16777215);
	}

	public ParticleInfuserBlockStatus() {
		super(ParticleInformation.ID_PARTICLE_INFUSER_BLOCK_STATUS, RL_PARTICLE_INFUSER_BLOCK_STATUS);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp) {
		return new ParticleInfuserBlockStatus(worldIn, pos);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		updateColor();
	}
	
	private void updateColor() {
		if(colorStatus == 0){
			color += 500;
		}
		else{
			color -= 500;
		}
		RGBColor c = new RGBColor(color);
		setRBGColorF(c.r, c.g, c.b);
		if(color >= 16777215){
			colorStatus = 1;
		}
		else if(color <= 0){
			colorStatus = 0;
		}
	}

}
