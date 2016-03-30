package de.comeight.crystallogy.network;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.util.Utilities;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class MessageToServer implements IMessage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected NetworkParticle nP;
	protected Vec3d tilePos;
	protected boolean status;
	protected ItemStack stack;

	protected boolean messageValid;
	public int messageType;
	
	public static final int PARTICLE = 0;
	public static final int TILEENTITYPARTICLEUPDATERECIPE = 1;
	public static final int ITEMSTACK = 2;
	public static final int TILEENTITYPARTICLEUPDATE = 3;

	//-----------------------------------------------Constructor:-------------------------------------------
	public MessageToServer(NetworkParticle nP) {
		this.nP = nP;
		this.messageType = PARTICLE;
		
		this.messageValid = true;
	}
	
	public MessageToServer(int messageType, Vec3d tilePos, boolean status) {
		this.status = status;
		this.tilePos = tilePos;
		this.messageType = messageType;
		
		this.messageValid = true;
	}
	
	public MessageToServer(int messageType, Vec3d tilePos, ItemStack stack) {
		this.tilePos = tilePos;
		this.messageType = messageType;
		this.stack = stack;
		
		this.messageValid = true;
	}

	public MessageToServer(Vec3d tilePos) {
		this.messageValid = true;
	}
	
	public MessageToServer() {
		this.messageValid = false;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public boolean isStatus() {
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
	
	public NetworkParticle getNetworkParticle() {
		return nP;
	}

	public void setNetworkParticle(NetworkParticle nP) {
		this.nP = nP;
	}

	public boolean isMessageValid() {
		return messageValid;
	}

	public void setMessageIsValid(boolean messageIsValid) {
		this.messageValid = messageIsValid;
	}

	public ItemStack getStack() {
		return stack;
	}

	public void setStack(ItemStack stack) {
		this.stack = stack;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			String s = ByteBufUtils.readUTF8String(buf);
			this.messageType = getMessageType(s);
			s = removePart(s);
			
			switch (messageType) {
				case PARTICLE:
					this.nP = new NetworkParticle();
					this.nP.fromString(s);
					
					this.messageValid = true;
					break;
					
				case TILEENTITYPARTICLEUPDATERECIPE:
					this.tilePos = Utilities.vec3FormString(getNextPart(s));
					s = removePart(s);
					this.status = Boolean.parseBoolean(s);
					
					this.messageValid = true;
					break;
					
				case TILEENTITYPARTICLEUPDATE:
					this.tilePos = Utilities.vec3FormString(getNextPart(s));
					s = removePart(s);
					this.status = Boolean.parseBoolean(s);
					
					this.messageValid = true;
					break;
					
				case ITEMSTACK:
					this.tilePos = Utilities.vec3FormString(getNextPart(s));
					this.stack = ByteBufUtils.readItemStack(buf);
					this.messageValid = true;
					break;
					
				default:
					System.out.println("Unknown messageType in fromByte");
					break;
			}
		} catch (Exception e) {
			System.err.println("Exception while reading ParticleMessageToServer: " + e);
			return;
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!messageValid) {
			return;
		}
		switch (messageType) {
			case MessageToServer.PARTICLE:
				if(nP == null){
					System.out.println("nP = null");
					ByteBufUtils.writeUTF8String(buf, messageType + ";");
					return;
				}
				ByteBufUtils.writeUTF8String(buf, messageType + ";" + nP.toString());
				break;
				
			case MessageToServer.TILEENTITYPARTICLEUPDATERECIPE:
				ByteBufUtils.writeUTF8String(buf, messageType + ";" + tilePos.toString() + ";" + status);
				break;
				
			case MessageToServer.TILEENTITYPARTICLEUPDATE:
				ByteBufUtils.writeUTF8String(buf, messageType + ";" + tilePos.toString() + ";" + status);
				break;
				
			case ITEMSTACK:
				ByteBufUtils.writeUTF8String(buf, messageType + ";" + tilePos.toString() + ";");
				ByteBufUtils.writeItemStack(buf, stack);
				break;
				
			default:
				System.out.println("Unknown messageType in toBytes");
				break;
		}
	}

	@Override
	public String toString() {
		switch (messageType) {
			case PARTICLE:
				return "ParticleMessageToServer[NetworkParticle=" + nP.toString() + "]";
				
			case TILEENTITYPARTICLEUPDATERECIPE:
				return "TileEntityUpdateMessageToServer[Pos;status =" + tilePos.toString() + ";" + status + "]";
				
			case TILEENTITYPARTICLEUPDATE:
				return "TileEntityUpdateMessageToServer[Pos;status =" + tilePos.toString() + ";" + status + "]";
				
			case ITEMSTACK:
				return "RemoveItemToServer[Pos=" + tilePos.toString() + "]";
				
			default:
				return "Unknown messageType in toString";
		}
	}
	
	protected int getMessageType(String s){
		s = s.substring(0, s.indexOf(';'));
		return Integer.parseInt(s);
	}
	
	protected String removePart(String s){
		return s.substring(s.indexOf(";") + 1);
	}
	
	protected String getNextPart(String s){
		return s.substring(0, s.indexOf(';'));
	}
}
