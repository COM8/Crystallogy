package de.comeight.crystallogy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class NetworkPacketTileEntitySync extends BaseNetworkPacket {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private NBTTagCompound compound;
	private BlockPos pos;
	
	public static final Byte ID_SERVER = 48;
	public static final Byte ID_CLIENT = 49;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public NetworkPacketTileEntitySync() {
		messageValid = false;
	}
	
	public NetworkPacketTileEntitySync(BlockPos pos, NBTTagCompound compound){
		this.pos = pos;
		this.compound = compound;
		messageValid =true;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public NBTTagCompound getNBTTagCompound() {
		return compound;
	}

	public BlockPos getPos() {
		return pos;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString() {
		return "NetworkPacketTileEntitySync[Pos =" + pos.toString() + "; NBTTagCompound =" + compound.toString() + "]";
	}

	@Override
	public void toBytes(ByteBuf buf) {
		compound.setInteger("posX", pos.getX());
		compound.setInteger("posY", pos.getY());
		compound.setInteger("posZ", pos.getZ());
		
		ByteBufUtils.writeTag(buf, compound);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try{
			compound = ByteBufUtils.readTag(buf);
			pos = new BlockPos(compound.getInteger("posX"), compound.getInteger("posY"), compound.getInteger("posZ"));
			messageValid = true;
		}
		catch (Exception e) {
			System.err.println("Exception while reading NetworkPacketTileEntitySync: " + e);
			messageValid = false;
			return;
		}
	}
	
}
