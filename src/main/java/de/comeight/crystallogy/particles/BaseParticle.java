package de.comeight.crystallogy.particles;

import de.comeight.crystallogy.util.Log;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class BaseParticle extends Particle{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public String id;
	
	private boolean canTickToDeath;
	
	protected double tIndex;
	protected double annimationSpeed;
	public ResourceLocation rL;
	private int renderIndex;
	protected int countParticleTextures;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseParticle(String id, ResourceLocation rL, World worldIn, Vec3d pos) {
		super(worldIn, pos.xCoord, pos.yCoord, pos.zCoord);
		
		this.id = id;
		
		this.canTickToDeath = true;
		this.annimationSpeed = 1.0;
		this.rL = rL;
		this.renderIndex = 0;
		this.countParticleTextures = 32;
		
		this.motionX = 0.075;
		this.motionY = 0.075;
		this.motionZ = 0.075;
		
		setTexture();
	}
	
	public BaseParticle(String id, ResourceLocation rL) {
		super(null, 0.0, 0.0, 0.0);
		
		this.id = id;
		this.rL = rL;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public int getBrightnessForRender(float partialTicks) {
		final int FULL_BRIGHTNESS_VALUE = 0xf000f0;
	    return FULL_BRIGHTNESS_VALUE;
	}
	
	private void setTexture(){
		if(rL != null){
			particleTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(rL.toString());
		}
		else{
			Log.error("Unable to find the TextureAtlasSprite for: " + id + "!");
			particleTexture = Minecraft.getMinecraft().getTextureMapBlocks().getMissingSprite();
		}
	}
	
	@Override
	public int getFXLayer() {
		return 1;
	}
	
	public void setParticleAge(int particleAge) {
		this.particleAge = particleAge;
	}
	
	public void setCanTickToDeath(boolean canTickToDeath){
		this.canTickToDeath = canTickToDeath;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public abstract BaseParticle clone(World worldIn, Vec3d pos, TransportParticle tp);
	
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
		
        if(canTickToDeath){
        	if(this.particleMaxAge <= this.particleAge){
    			this.setExpired();
    		}
    		this.particleAge++;
        }
	}
}
