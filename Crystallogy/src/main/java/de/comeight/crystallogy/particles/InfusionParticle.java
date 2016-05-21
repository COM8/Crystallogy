package de.comeight.crystallogy.particles;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class InfusionParticle extends BaseParticle {

	//-----------------------------------------------Variabeln:---------------------------------------------
	public static ResourceLocation[] rL = getTextures("crystallogy:particles/n_color", "n", 32);
	public static final String NAME = "2"; 
	
	private Vec3d targetPos;
	private Vec3d startPos;
	private Vec3d difStartTarget;
	
	private int timeInTicks = 100;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public InfusionParticle(World world, double velocityX, double velocityY, double velocityZ, Vec3d startPos, Vec3d targetPos, int timeInTicks) {
		super(NAME, world, startPos.xCoord, startPos.yCoord, startPos.zCoord, velocityX, velocityY, velocityZ);
		
		this.targetPos = targetPos;
		this.startPos = startPos;
		this.timeInTicks = timeInTicks;
		this.noClip = true;
		this.setParticleMaxAge(timeInTicks);
		
		this.particleScale = Utilities.getRandFloat(3.0F, 5.0F);
		initVariables();
	}
	
	public InfusionParticle(String s) {
		super();
		if(s != null){
			fromString(s);
			initVariables();
		}
		
	}
	
	public InfusionParticle() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void initVariables(){
		this.difStartTarget = startPos.add(new Vec3d(-targetPos.xCoord, -targetPos.yCoord, -targetPos.zCoord));
		double x = difStartTarget.xCoord;
		double y = difStartTarget.yCoord;
		double z = difStartTarget.zCoord;
		if(x != 0){
			x /= timeInTicks;
		}
		if(y != 0){
			y /= timeInTicks;
		}
		if(z != 0){
			z /= timeInTicks;
		}
		this.difStartTarget = new Vec3d(-x, -y, -z);
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
		this.moveEntity(difStartTarget.xCoord, difStartTarget.yCoord, difStartTarget.zCoord);
		timeInTicks--;
		if(timeInTicks == 0){
			this.setExpired();
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
}
