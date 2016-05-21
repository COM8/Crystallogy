package de.comeight.crystallogy.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleNColor extends BaseParticleExtended {
	
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static ResourceLocation[] rL = getTextures("crystallogy:particles/n_color", "n", 32);
	public static final String NAME = "n_c"; 

	//-----------------------------------------------Constructor:-------------------------------------------
	public ParticleNColor(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
		super(NAME, world, x, y, z, velocityX, velocityY, velocityZ);
	}

	public ParticleNColor(String s) {
		super();
		if(s != null){
			fromString(s);
		}
	}
	
	public ParticleNColor() {
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

}