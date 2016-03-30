package de.comeight.crystallogy.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ParticleC extends BaseParticleExtended {
	
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static ResourceLocation[] rL = getTextures("crystallogy:particles/c", "c", 32);
	public static final String NAME = "c"; 

	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleC(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
		super(NAME, world, x, y, z, velocityX, velocityY, velocityZ);
	}
	
	public ParticleC(String s) {
		super();
		if(s != null){
			fromString(s);
		}
	}

	public ParticleC() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void updateTexture(){
		//System.out.println(tIndex);
		tIndex += annimationSpeed;
		if(tIndex >= rL.length){
			tIndex = 0;
		}
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(rL[(int)tIndex].toString());
		this.setParticleTexture(sprite);
	}
	
	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new ParticleC(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		}
	}
	
}
