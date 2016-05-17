package de.comeight.crystallogy.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class JumpParticleBetweenCrystalls extends BaseParticle {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static  ResourceLocation[] rL = getTextures("crystallogy:particles/f", "f", 19);
	public static final String NAME = "1"; 
	
	private Vec3d targetPos;
	private Vec3d startPos;
	
	private double periode;
	private double div;

	//-----------------------------------------------Constructor:-------------------------------------------
	public JumpParticleBetweenCrystalls(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Vec3d targetPos) {
		super(NAME, world, x, y, z, velocityX, velocityY, velocityZ);
		this.targetPos = targetPos;
		this.startPos = new Vec3d(x, y, z);
		this.noClip = true;
		this.particleMaxAge = 2000;
		
		initVariables();
		
	}

	public JumpParticleBetweenCrystalls(String s) {
		super();
		if(s != null){
			fromString(s);
			initVariables();
		}
	}
	
	public JumpParticleBetweenCrystalls() {
		super();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initVariables(){
		this.div = getDifference(posX, targetPos.xCoord);
		this.periode = 2*Math.PI/getDifference(posX, targetPos.xCoord);
	}
	
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
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		moveParticle();
	}

	protected void moveParticle(){
		if(checkTargetReached()){
			this.setExpired();
			return;
		}
		else{
			this.moveEntity(calcMotionX(), calcMotionY(), calcMotionZ());
		}
	}
	
	protected double getSin(double x){
		return 0.15*Math.sin(periode*x);
	}
	
	protected double calcMotionX(){
		if(targetPos.xCoord == posX){
			return 0.0;
		}
		double differece = getDifference(targetPos.xCoord, this.posX);
		
		if(targetPos.xCoord > posX){
			if(differece > 0.1){
				return 0.05;
			}
			else{
				return differece;
			}
		}
		else{
			if(differece > 0.1){
				return -0.05;
			}
			else{
				return -differece;
			}
		}
	}
	protected double calcMotionY(){
		return getSin(div - getDifference(posX, targetPos.xCoord));
	}
	protected double calcMotionZ(){
		if(targetPos.zCoord == posZ){
			return 0.0;
		}
		double differece = getDifference(targetPos.zCoord, this.posZ);
		
		if(targetPos.zCoord > posZ){
			if(differece > 0.1){
				return 0.05;
			}
			else{
				return differece;
			}
		}
		else{
			if(differece > 0.1){
				return -0.05;
			}
			else{
				return -differece;
			}
		}
	}
	
	protected boolean checkTargetReached(){
		if(!checkTargetReachedX()){
			return false;
		}
		if(!checkTargetReachedY()){
			return false;
		}
		if(!checkTargetReachedZ()){
			return false;
		}
		return true;
	}
	
	protected boolean checkTargetReachedX(){
		if(this.posX == targetPos.xCoord){
			return true;
		}
		else{
			return false;
		}
	}
	
	protected boolean checkTargetReachedY(){
		if(posY == prevPosY){
			return true;
		}
		else{
			return false;
		}
	}
	
	protected boolean checkTargetReachedZ(){
		if(this.posZ == targetPos.zCoord){
			return true;
		}
		else{
			return false;
		}
	}

	protected double getDifference(double val1, double val2){
		if(val1 >= 0){
			if(val2 >= 0){
				if(val1 > val2){
					return val1 - val2;
				}
				else{
					return val2 - val1;
				}
			}
			else{
				return val1 - val2;
			}
		}
		else{
			if(val2 >= 0){
				return val2 - val1;
			}
			else{
				if(val1 > val2){
					return val1 - val2;
				}
				else{
					return val2 - val1;
				}
			}
		}
	}
	
	@Override
	public String toString() {
		String s = targetPos.toString() + ";" + super.toString();
		//System.out.println("Cordinates in: " + coordinates.toString());
		return s;
	}
	
	@Override
	public void fromString(String s){
		s = getTargetPos(s);
		super.fromString(s);
	}
	
	protected String getTargetPos(String s){
		String s1 = s.substring(0, s.indexOf(")"));
		s = s.substring(s.indexOf(";") + 1);

		//X:
		double x = Double.parseDouble(s1.substring(1, s1.indexOf(",")));
		s1 = s1.substring(s1.indexOf(" ") + 1);
		
		//Y:		
		double y = Double.parseDouble(s1.substring(0, s1.indexOf(",")));
		s1 = s1.substring(s1.indexOf(" ") + 1);
		
		//Z:
		double z = Double.parseDouble(s1);
		this.targetPos = new Vec3d(x, y, z);
		
		return s;
	}
	
	@SideOnly(Side.CLIENT)
	public static class Factory implements IParticleFactory {
		public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
			return new JumpParticleBetweenCrystalls(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, new Vec3d(xCoordIn + 1, yCoordIn + 1, zCoordIn + 1));
		}
	}
	
}
