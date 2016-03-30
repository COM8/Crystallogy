package de.comeight.crystallogy.particles;

import java.io.File;
import java.util.Vector;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ParticleB extends BaseParticleExtended{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static ResourceLocation[] rL = getTextures("crystallogy:particles/b", "b", 32);
	public static final String NAME = "b"; 
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleB(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
		super(NAME, world, x, y, z, velocityX, velocityY, velocityZ);
	}

	public ParticleB(String s) {
		super();
		if(s != null){
			fromString(s);
		}
	}
	
	public ParticleB() {
		super();
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
			return new ParticleB(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		}
	}
	
}
