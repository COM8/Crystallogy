package de.comeight.crystallogy.network;

import de.comeight.crystallogy.particles.BaseParticle;
import de.comeight.crystallogy.particles.BaseParticleExtended;
import de.comeight.crystallogy.particles.ParticleHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public class NetworkParticle {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private String type;
	private Vec3d size;
	private int numberOfParticle;
	
	private BaseParticle particle;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public NetworkParticle() {
		this.type = "";
		this.particle = new BaseParticle();
		this.size = new Vec3d(0.0, 0.0, 0.0);
		this.numberOfParticle = 1;
	}
	
	public NetworkParticle(BaseParticle p, String type){
		this.particle = p;
		this.type = type;
		this.size = new Vec3d(1.0, 2.0, 1.0);
		this.numberOfParticle = 1;
	}
	
	public NetworkParticle(BaseParticleExtended p, String type){
		this.particle = p;
		this.type = type;
		this.size = new Vec3d(1.0, 2.0, 1.0);
		this.numberOfParticle = 1;
	}
	
	public NetworkParticle(String type, Vec3d coordinates, int maxAge) {
		this.type = type;
		this.particle = ParticleHandler.getBaseParticleFromType(type);
		this.particle.setPosition(coordinates.xCoord, coordinates.yCoord, coordinates.zCoord);
		this.particle.setParticleMaxAge(maxAge);
		this.size = new Vec3d(0.0, 0.0, 0.0);
		this.numberOfParticle = 1;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public int getNumberOfParticle() {
		return numberOfParticle;
	}

	public BaseParticle getParticle() {
		if(this.particle == null){
			System.out.println("nullref");
		}
		return particle;
	}

	public void setParticle(BaseParticle particle) {
		this.particle = particle;
	}

	public void setNumberOfParticle(int numberOfParticle) {
		this.numberOfParticle = numberOfParticle;
	}
	
	public Vec3d getSize() {
		return size;
	}

	public void setSize(Vec3d dimension) {
		this.size = dimension;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString() {
		String s = type + ";";
		s += size.toString() + ";";
		s += numberOfParticle + ";";
		if(particle == null){
			s += "null";
		}
		else{
			s += particle.toString();
		}
		return s;
	}
	
	public void fromString(String s){
		s = getType(s);
		//System.out.println("Type=" + type);
		s = getSize(s);
		//System.out.println("Size=" + size.toString());
		s = getNumberOfParticle(s);
		//System.out.println("NumberOfParticle=" + numberOfParticle);
		if(!s.equals("null")){
			particle = ParticleHandler.getBaseParticleFromType(type, s);
		}
	}
	
	private String getType(String s){
		this.type = s.substring(0, s.indexOf(";"));
		return s.substring(s.indexOf(";") + 1);
	}
	
	private String getSize(String s){
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
		this.size = new Vec3d(x, y, z);
		return s;
	}
	
	private String getNumberOfParticle(String s){
		this.numberOfParticle = Integer.parseInt(s.substring(0, s.indexOf(";")));
		return s.substring(s.indexOf(";") + 1);
	}
	
	public NBTTagCompound toTag(){
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("type", type);
		tag.setDouble("sizeX", size.xCoord);
		tag.setDouble("sizeY", size.yCoord);
		tag.setDouble("sizeZ", size.zCoord);
		tag.setInteger("numberOfParticle", numberOfParticle);
		tag.setString("particle", particle.toString());
		return tag;
	}
	
	public void fromTag(NBTTagCompound tag){
		if(tag != null){
			type = tag.getString("type");
			size = new Vec3d(tag.getDouble("sizeX"), tag.getDouble("sizeY"), tag.getDouble("sizeZ"));
			numberOfParticle = tag.getInteger("numberOfParticle");
			String s = tag.getString("particle");
			if(!s.equals("null")){
				particle = ParticleHandler.getBaseParticleFromType(type, s);
			}
		}
	}
	
}
