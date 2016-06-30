package de.comeight.crystallogy.network;

import de.comeight.crystallogy.network.handler.Client.MessageHandlerOnClientParticle;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerParticle;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
			this.messageValid = false;
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
		NBTTagCompound compound = new NBTTagCompound();
		nP.toNBT(compound);
		ByteBufUtils.writeTag(buf, compound);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try{
			this.nP = new NetworkParticle();
			this.nP.fromNBT(ByteBufUtils.readTag(buf));
			messageValid = true;
		}
		catch (Exception e) {
			System.err.println("Exception while reading NetworkPacketParticleToServer: " + e);
			messageValid = false;
			return;
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IMessage handleClient(MessageContext msg) {
		MessageHandlerOnClientParticle handler = new MessageHandlerOnClientParticle();
		handler.onMessage(this, msg);
		
		return null;
	}

	@SideOnly(Side.SERVER)
	@Override
	public IMessage handleServer(MessageContext msg) {
		MessageHandlerOnServerParticle handler = new MessageHandlerOnServerParticle();
		handler.onMessage(this, msg);
		
		return null;
	}
	
}
