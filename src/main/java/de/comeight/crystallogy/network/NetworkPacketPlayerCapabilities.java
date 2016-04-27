package de.comeight.crystallogy.network;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class NetworkPacketPlayerCapabilities extends BaseNetworkPacket {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private UUID uuid;
	private int enumId;
	private boolean enabled;
	
	public static final Byte ID_SERVER = 50;
	public static final Byte ID_CLIENT = 51;

	//-----------------------------------------------Constructor:-------------------------------------------
	public NetworkPacketPlayerCapabilities() {
		super();
	}
	
	public NetworkPacketPlayerCapabilities(UUID uuid, int enumId, boolean enabled){
		this.uuid = uuid;
		this.enumId = enumId;
		this.enabled = enabled;
		this.messageValid = true;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public UUID getUuid() {
		return uuid;
	}

	public int getEnumId() {
		return enumId;
	}
	
	public boolean isEnabled(){
		return enabled;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString() {
		return "NetworkPacketPlayerCapabilities[Player-UUID:" + uuid.toString() + "; enumId:" + enumId + "; enabled:" + enabled + "]";
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound compound = new NBTTagCompound();
		compound.setInteger("enumId", enumId);
		compound.setUniqueId("uuid", uuid);
		compound.setBoolean("enabled", enabled);
		
		ByteBufUtils.writeTag(buf, compound);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try{
			NBTTagCompound tag = ByteBufUtils.readTag(buf);
			enumId = tag.getInteger("enumId");
			uuid = tag.getUniqueId("uuid");
			enabled = tag.getBoolean("enabled");
			messageValid = true;
		}
		catch (Exception e) {
			System.err.println("Exception while reading NetworkPacketPlayerCapabilities: " + e);
			messageValid = false;
			return;
		}
	}

}
