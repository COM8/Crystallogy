package de.comeight.crystallogy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;

public class NetworkPacketInfusionRecipeStatusToServer extends BaseNetworkPacket {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected Vec3d tilePos;
	protected boolean status;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public NetworkPacketInfusionRecipeStatusToServer(){
		this.messageValid = false;
	}
	
	public NetworkPacketInfusionRecipeStatusToServer(Vec3d tilePos, boolean status){
		this.tilePos = tilePos;
		this.status = status;
		this.messageValid = true;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString() {
		return null;
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}
	
}
