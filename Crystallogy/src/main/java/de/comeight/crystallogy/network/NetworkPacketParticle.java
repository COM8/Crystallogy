package de.comeight.crystallogy.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class NetworkPacketParticle extends BaseNetworkPacket {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected NetworkParticle nP;
	
	public static final Byte ID_SERVER = 44;
	public static final Byte ID_CLIENT = 45;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public NetworkPacketParticle() {
		this.messageValid = false;
	}
	
	public NetworkPacketParticle(NetworkParticle nP){
		if(nP == null){
			this.messageValid =false;
		}
		else{
			this.nP = nP;
			this.messageValid = true;
		}
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public NetworkParticle getNetworkParticle() {
		return nP;
	}
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString() {
		return "NetworkPacketParticleToServer[NetworkParticle=" + nP.toString() + "]";
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!messageValid && nP != null) {
			return;
		}
		ByteBufUtils.writeTag(buf, nP.toTag());
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try{
			this.nP = new NetworkParticle();
			this.nP.fromTag(ByteBufUtils.readTag(buf));
			messageValid = true;
		}
		catch (Exception e) {
			System.err.println("Exception while reading NetworkPacketParticleToServer: " + e);
			messageValid = false;
			return;
		}
	}
	
}
