package de.comeight.crystallogy.particles;

import de.comeight.crystallogy.util.RGBColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BaseParticle extends Particle{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String NAME = "baseParticle";
	public String name;
	protected boolean noClip;
	
	private boolean canTickToDeath;
	
	protected double tIndex;
	protected double annimationSpeed;
	private static ResourceLocation[] rL;

	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseParticle(String name, World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
		super(world, x, y, z, velocityX, velocityY, velocityZ);
		this.name = name;
		this.tIndex = 0;
		this.annimationSpeed = 1.0;
        this.particleMaxAge = 60;
        this.noClip = false; //TODO Fix NoClip
        this.canTickToDeath = true;
        updateTexture();
	}
	
	public BaseParticle(){
		super(null, 0.0, 0.0, 0.0);
	}
	
	public BaseParticle(String s){
		super(null, 0.0, 0.0, 0.0);
		if(s != null){
			fromString(s);
		}
	}
	
	public Vec3d getPos(){
		return new Vec3d(this.posX, this.posY, this.posZ);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	protected static ResourceLocation[] getTextures(String dirPath, String name, int count){
		try{
			ResourceLocation[] temp = new ResourceLocation[count];
			for(int i = 0; i < count; i++){
				temp[i] = new ResourceLocation(dirPath + "/" + name + i);
				//System.out.println("Partikel Geladen: " + dirPath + "/" + name + i);
			}
			return temp;
		}
		catch(Exception e){
			System.err.println(e);
			return null;
		}
	}
	
	public boolean isCanTickToDeath() {
		return canTickToDeath;
	}

	public void setCanTickToDeath(boolean canTickToDeath) {
		this.canTickToDeath = canTickToDeath;
	}

	public void setParticleMaxAge(int ticks){
		this.particleMaxAge = ticks;
	}
	
	public int getParticleMaxAge(){
		return this.particleMaxAge;
	}
	
	public void setParticleAge(int ticks){
		this.particleAge = ticks;
	}
	
	public int getParticleAge(){
		return this.particleAge;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	protected void updateTexture(){
		//System.out.println(tIndex);
		tIndex += annimationSpeed;
		if(tIndex >= rL.length){
			tIndex = 0;
		}
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(rL[(int)tIndex].toString());
		this.setParticleTexture(sprite);
	}
	
	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public int getBrightnessForRender(float partialTicks) {
		final int FULL_BRIGHTNESS_VALUE = 0xf000f0;
	    return FULL_BRIGHTNESS_VALUE;
	}
	
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
		
		updateTexture();
	}
	
	@Override
	public void moveEntity(double x, double y, double z) {
		super.moveEntity(x, y, z);
	}
	
	//-----------------------------------------------toString:----------------------------------------------
	@Override
	public String toString() {
		String s = new Vec3d(this.posX, this.posY, this.posZ).toString() + ";" + this.getRedColorF() + ";" + this.getGreenColorF() + ";"+ this.getBlueColorF() + ";" + this.particleMaxAge + ";" + this.particleScale + ";" + this.noClip + ";";
		return s;
	}
	
	public void fromString(String s){
		s = getCoordinates(s);
		//System.out.println("Coordinates=" + getPosition().toString());
		s = getRGB(s);
		//System.out.println("RGB=" + r + "," + g + "," + b);
		s = getMaxAge(s);
		//System.out.println("MaxAge=" + maxAge);
		s = getScale(s);
		
		s = getNoClip(s);
	}
	
	protected String getCoordinates(String s){
		String s1 = s.substring(0, s.indexOf(")"));
		s = s.substring(s.indexOf(";") + 1);
		//System.out.println(s);
		//X:
		this.posX= Double.parseDouble(s1.substring(1, s1.indexOf(",")));
		s1 = s1.substring(s1.indexOf(" ") + 1);
		
		//Y:		
		this.posY = Double.parseDouble(s1.substring(0, s1.indexOf(",")));
		s1 = s1.substring(s1.indexOf(" ") + 1);
		
		//Z:
		this.posZ = Double.parseDouble(s1);
		return s;
	}
	
	protected String getRGB(String s){
		RGBColor c = new RGBColor();
		c.r = Float.parseFloat(s.substring(0, s.indexOf(";")));
		s = s.substring(s.indexOf(";") + 1);
		
		c.g = Float.parseFloat(s.substring(0, s.indexOf(";")));
		s = s.substring(s.indexOf(";") + 1);
		
		c.b = Float.parseFloat(s.substring(0, s.indexOf(";")));
		s = s.substring(s.indexOf(";") + 1);
		this.setRBGColorF(c.r, c.g, c.b);
		return s;
	}
	
	protected String getMaxAge(String s){
		this.particleMaxAge = Integer.parseInt(s.substring(0, s.indexOf(";")));
		return s.substring(s.indexOf(";") + 1);
	}
	
	protected String getScale(String s){
		this.particleScale = Float.parseFloat(s.substring(0, s.indexOf(";")));
		return s.substring(s.indexOf(";") + 1);
	}
	
	protected String getNoClip(String s){
		this.noClip = Boolean.parseBoolean(s.substring(0, s.indexOf(";")));
		return s.substring(s.indexOf(";") + 1);
	}
	
}
