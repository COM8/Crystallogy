package de.comeight.crystallogy.particles;

import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class InfuserBlockActiveParticle extends BaseParticle {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static ResourceLocation[] rL = getTextures("crystallogy:particles/i_color", "i", 32);
	public static final String NAME = "i_color";
	
	private int colorStatus;
	private int color;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfuserBlockActiveParticle(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
		super(NAME, world, x, y, z, velocityX, velocityY, velocityZ);
		this.particleScale = 3.0F;
		this.colorStatus = 0;
		this.color = Utilities.getRandInt(0, 16777215);
	}

	public InfuserBlockActiveParticle() {
		super();
	}
	
	public InfuserBlockActiveParticle(String s) {
		super();
		if(s != null){
			fromString(s);
		}
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
		updateColor();
	}
	
	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new InfuserBlockActiveParticle(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		}
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
