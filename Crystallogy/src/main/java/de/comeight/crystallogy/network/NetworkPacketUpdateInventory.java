package de.comeight.crystallogy.network;

import de.comeight.crystallogy.network.handler.Client.MessageHandlerOnClientUpdateInventory;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerUpdateInventory;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Deprecated
public class NetworkPacketUpdateInventory extends BaseNetworkPacket {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected BlockPos tilePos;
	protected ItemStack stack;
	protected int slotIndex;
	
	public static final Byte ID_SERVER = 46;
	public static final Byte ID_CLIENT = 47;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public NetworkPacketUpdateInventory() {
		this.messageValid = false;
	}
	
	public NetworkPacketUpdateInventory(BlockPos tilePos, ItemStack stack, int slotIndex) {
		this.tilePos = tilePos;
		this.stack = stack;
		this.slotIndex = slotIndex;
		this.messageValid = true;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public BlockPos getTilePos() {
		return tilePos;
	}

	public void setTilePos(BlockPos tilePos) {
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
		tag.setInteger("posX", tilePos.getX());
		tag.setInteger("posY", tilePos.getY());
		tag.setInteger("posZ", tilePos.getZ());
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
			
			tilePos = new BlockPos(tag.getInteger("posX"), tag.getInteger("posY"), tag.getInteger("posZ"));
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
	
	@SideOnly(Side.CLIENT)
	@Override
	public IMessage handleClient(MessageContext msg) {
		MessageHandlerOnClientUpdateInventory handler = new MessageHandlerOnClientUpdateInventory();
		handler.onMessage(this, msg);
		
		return null;
	}

	@SideOnly(Side.SERVER)
	@Override
	public IMessage handleServer(MessageContext msg) {
		MessageHandlerOnServerUpdateInventory handler = new MessageHandlerOnServerUpdateInventory();
		handler.onMessage(this, msg);
		
		return null;
	}
	
}