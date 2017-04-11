package de.comeight.crystallogy.network;

import de.comeight.crystallogy.particles.TransportParticle;
import de.comeight.crystallogy.util.Log;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;

public class NetworkParticle {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private String type;
	private Vec3d size;
	private int numberOfParticle;
	
	private TransportParticle transportParticle;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public NetworkParticle() {
		this.type = "";
		this.transportParticle = new TransportParticle();
		this.size = new Vec3d(0.0, 0.0, 0.0);
		this.numberOfParticle = 1;
	}
	
	public NetworkParticle(TransportParticle transportParticle, String type){
		this.transportParticle = transportParticle;
		this.type = type;
		this.size = new Vec3d(1.0, 2.0, 1.0);
		this.numberOfParticle = 1;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public int getNumberOfParticle() {
		return numberOfParticle;
	}

	public TransportParticle getTransporterParticle() {
		if(this.transportParticle == null){
			Log.error("null ref. in NetworkParticle getParticle!");
		}
		return transportParticle;
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
		NBTTagCompound compound = new NBTTagCompound();
		toNBT(compound);
		return compound.toString();
	}
	
	public void toNBT(NBTTagCompound compound){
		compound.setString("type", type);
		compound.setDouble("sizeX", size.xCoord);
		compound.setDouble("sizeY", size.yCoord);
		compound.setDouble("sizeZ", size.zCoord);
		compound.setInteger("numberOfParticle", numberOfParticle);

		transportParticle.toNBT(compound);
	}
	
	public void fromNBT(NBTTagCompound compound){
		type = compound.getString("type");
		size = new Vec3d(compound.getDouble("sizeX"), compound.getDouble("sizeY"), compound.getDouble("sizeZ"));
		numberOfParticle = compound.getInteger("numberOfParticle");
		
		transportParticle = new TransportParticle();
		transportParticle.fromNBT(compound);
	}
	
}
