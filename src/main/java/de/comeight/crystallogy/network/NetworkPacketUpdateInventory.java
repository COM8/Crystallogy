package de.comeight.crystallogy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class NetworkPacketUpdateInventory extends BaseNetworkPacket {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected Vec3d tilePos;
	protected ItemStack stack;
	protected int slotIndex;
	
	public static final Byte ID_SERVER = 46;
	public static final Byte ID_CLIENT = 47;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public NetworkPacketUpdateInventory() {
		this.messageValid = false;
	}
	
	public NetworkPacketUpdateInventory(Vec3d tilePos, ItemStack stack, int slotIndex) {
		this.tilePos = tilePos;
		this.stack = stack;
		this.slotIndex = slotIndex;
		this.messageValid = true;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public Vec3d getTilePos() {
		return tilePos;
	}

	public void setTilePos(Vec3d tilePos) {
		this.tilePos = tilePos;
	}
	
	public ItemStack getStack() {
		return stack;
	}

	public void setStack(ItemStack stack) {
		this.stack = stack;
	}

	public int getSlotIndex() {
		return slotIndex;
	}

	public void setSlotIndex(int slotIndex) {
		this.slotIndex = slotIndex;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString() {
		String s1, s2;
		if(tilePos == null){
			s1 = "tilePos";
		}
		else{
			s1 = tilePos.toString();
		}
		if(stack == null){
			s2 = "stack";
		}
		else{
			s2 = stack.toString();
		}
		return "NetworkPacketUpdateInventory[Pos=" + s1 + ", " + s2 + ", " + slotIndex + "]";
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setDouble("posX", tilePos.xCoord);
		tag.setDouble("posY", tilePos.yCoord);
		tag.setDouble("posZ", tilePos.zCoord);
		tag.setInteger("slotIndex", slotIndex);
		
		if(stack == null){
			tag.setBoolean("emptyStack", true);
			ByteBufUtils.writeTag(buf, tag);
		}
		else{
			tag.setBoolean("emptyStack", false);
			ByteBufUtils.writeTag(buf, tag);
			ByteBufUtils.writeItemStack(buf, stack);
		}
		
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try{
			NBTTagCompound tag = ByteBufUtils.readTag(buf);
			
			tilePos = new Vec3d(tag.getDouble("posX"), tag.getDouble("posY"), tag.getDouble("posZ"));
			if(tag.getBoolean("emptyStack")){
				stack = null;
			}
			else{
				stack = ByteBufUtils.readItemStack(buf);
			}
			slotIndex = tag.getInteger("slotIndex");
			messageValid = true;
		}
		catch (Exception e) {
			System.err.println("Exception while reading NetworkPacketUpdateInventory: " + e);
			messageValid = false;
			return;
		}
	}
	
}
