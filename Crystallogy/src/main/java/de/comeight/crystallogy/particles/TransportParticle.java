package de.comeight.crystallogy.particles;

import de.comeight.crystallogy.util.RGBColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public class TransportParticle {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public int maxAge;
	public float scale;
	
	public Vec3d pos;
	public RGBColor color;
	public boolean randomColor;
	
	public Vec3d targetPos;
	public Vec3d startPos;
	public int timeInTicks = 100;
	public boolean reverse;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public TransportParticle(Vec3d pos) {
		this.pos = pos;
		this.maxAge = 60;
		this.scale = 1.0F;
		this.color = new RGBColor();
		this.randomColor = false;
		this.reverse = false;
	}
	
	public TransportParticle() {
		this.pos = new Vec3d(0, 0, 0);
		this.maxAge = 60;
		this.scale = 1.0F;
		this.color = new RGBColor();
		this.randomColor = false;
		this.reverse = false;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
	//-----------------------------------------------toString:----------------------------------------------
	public void fromNBT(NBTTagCompound compound){
		this.pos = new Vec3d(compound.getDouble("tPposX"), compound.getDouble("tPposY"), compound.getDouble("tPposZ"));
		this.color = new RGBColor(compound.getFloat("tPcolorR"), compound.getFloat("tPcolorG"), compound.getFloat("tPcolorB"));
		this.randomColor = compound.getBoolean("randomColor");
		this.maxAge = compound.getInteger("tPmaxAge");
		this.scale = compound.getFloat("tPscale");
		this.reverse = compound.getBoolean("reverse");
		
		this.startPos = new Vec3d(compound.getDouble("startPposX"), compound.getDouble("startPposY"), compound.getDouble("startPposZ"));
		this.targetPos = new Vec3d(compound.getDouble("targetPposX"), compound.getDouble("targetPposY"), compound.getDouble("targetPposZ"));
		
		this.timeInTicks = compound.getInteger("timeInTicks");
	}
	
	public void toNBT(NBTTagCompound compound){
		compound.setDouble("tPposX", pos.xCoord);
		compound.setDouble("tPposY", pos.yCoord);
		compound.setDouble("tPposZ", pos.zCoord);
		compound.setBoolean("randomColor", randomColor);
		compound.setBoolean("reverse", reverse);
		
		compound.setFloat("tPcolorR", color.r);
		compound.setFloat("tPcolorG", color.g);
		compound.setFloat("tPcolorB", color.b);
		
		compound.setInteger("tPmaxAge", maxAge);
		
		compound.setFloat("tPscale", scale);
		
		if(startPos != null){
			compound.setDouble("startPposX", startPos.xCoord);
			compound.setDouble("startPposY", startPos.yCoord);
			compound.setDouble("startPposZ", startPos.zCoord);
		}
		
		if(targetPos != null){
			compound.setDouble("targetPposX", targetPos.xCoord);
			compound.setDouble("targetPposY", targetPos.yCoord);
			compound.setDouble("targetPposZ", targetPos.zCoord);
		}
		
		compound.setInteger("timeInTicks", timeInTicks);
	}
	
	@Override
	public String toString() {
		NBTTagCompound compound = new NBTTagCompound();
		toNBT(compound);
		return compound.toString();
	}
}
