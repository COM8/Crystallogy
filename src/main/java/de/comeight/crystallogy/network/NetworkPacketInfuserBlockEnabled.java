package de.comeight.crystallogy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class NetworkPacketInfuserBlockEnabled extends BaseNetworkPacket {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected Vec3d tilePos;
	protected boolean status;
	
	public static final Byte ID_SERVER = 40;
	public static final Byte ID_CLIENT = 41;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public NetworkPacketInfuserBlockEnabled(){
		this.messageValid = false;
	}
	
	public NetworkPacketInfuserBlockEnabled(Vec3d tilePos, boolean status){
		this.tilePos = tilePos;
		this.status = status;
		this.messageValid = true;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Vec3d getTilePos() {
		return tilePos;
	}

	public void setTilePos(Vec3d tilePos) {
		this.tilePos = tilePos;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString() {
		return "NetworkPacketInfuserBlockEnabled[Pos;status =" + tilePos.toString() + ";" + status + "]";
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("status", status);
		tag.setDouble("posX", tilePos.xCoord);
		tag.setDouble("posY", tilePos.yCoord);
		tag.setDouble("posZ", tilePos.zCoord);
		ByteBufUtils.writeTag(buf, tag);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try{
			NBTTagCompound tag = ByteBufUtils.readTag(buf);
			status = tag.getBoolean("status");
			tilePos = new Vec3d(tag.getDouble("posX"), tag.getDouble("posY"), tag.getDouble("posZ"));
			messageValid = true;
		}
		catch (Exception e) {
			System.err.println("Exception while reading NetworkPacketInfuserBlockEnabled: " + e);
			messageValid = false;
			return;
		}
	}
	
}
