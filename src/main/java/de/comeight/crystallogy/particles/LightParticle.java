package de.comeight.crystallogy.particles;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class LightParticle extends BaseParticle {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static ResourceLocation[] rL = getTextures("crystallogy:particles/l_color", "l", 32);
	public static final String NAME = "l_color"; 
	
	private double speed;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public LightParticle(World world, double x, double y, double z) {
		super(NAME, world, x, y, z, 0.0, 0.0, 0.0);
		this.particleMaxAge = Utilities.getRandInt(20, 40);
		this.particleScale = Utilities.getRandFloat(0.75F, 2.5F);
		this.speed = Utilities.getRandDouble(0.1, 0.2);
		
		setAlphaF(0.5F);
	}

	public LightParticle() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void updateTexture(){
		tIndex += annimationSpeed;
		if(tIndex >= rL.length){
			tIndex = 0;
		}
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(rL[(int)tIndex].toString());
		this.setParticleTexture(sprite);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		move();
		updateSize();
		updateAlpha();
	}
	
	private void updateSize(){
		particleScale *= 0.975F;
	}
	
	private void move(){
		moveEntity(0.0, 0.05 * speed, 0.0);
	}
	
	private void updateAlpha(){
		setAlphaF(0.5F - (particleAge / particleMaxAge));
	}
	
}
