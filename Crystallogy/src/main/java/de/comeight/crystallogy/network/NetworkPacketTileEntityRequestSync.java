package de.comeight.crystallogy.network;

import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerTileEntityRequestSync;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class NetworkPacketTileEntityRequestSync extends BaseNetworkPacket {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected BlockPos tilePos;
	
	public static final Byte ID_SERVER = 50;
	public static final Byte ID_CLIENT = 51;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public NetworkPacketTileEntityRequestSync() {
		this.messageValid = false;
	}
	
	public NetworkPacketTileEntityRequestSync(BlockPos tilePos) {
		this.tilePos = tilePos;
		this.messageValid = true;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public BlockPos getTilePos() {
		return tilePos;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString() {
		String s1;
		if(tilePos == null){
			s1 = "tilePos";
		}
		else{
			s1 = tilePos.toString();
		}
		return "NetworkPacketTileEntityRequestSync[Pos=" + s1 + "]";
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("posX", tilePos.getX());
		tag.setInteger("posY", tilePos.getY());
		tag.setInteger("posZ", tilePos.getZ());
		
		ByteBufUtils.writeTag(buf, tag);
		
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try{
			NBTTagCompound tag = ByteBufUtils.readTag(buf);
			
			tilePos = new BlockPos(tag.getInteger("posX"), tag.getInteger("posY"), tag.getInteger("posZ"));
			messageValid = true;
		}
		catch (Exception e) {
			System.err.println("Exception while reading NetworkPacketTileEntityRequestSync: " + e);
			messageValid = false;
			return;
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IMessage handleClient(MessageContext msg) {
		
		return null;
	}

	@SideOnly(Side.SERVER)
	@Override
	public IMessage handleServer(MessageContext msg) {
		MessageHandlerOnServerTileEntityRequestSync handler = new MessageHandlerOnServerTileEntityRequestSync();
		handler.onMessage(this, msg);
		
		return null;
	}
	
}