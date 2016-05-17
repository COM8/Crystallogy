package de.comeight.crystallogy.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class NetworkPacketInfusionRecipeStatus extends BaseNetworkPacket {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected BlockPos centerTilePos;
	protected boolean status;
	protected boolean successfully;
	protected int recipeIndex;
	
	public static final Byte ID_SERVER = 42;
	public static final Byte ID_CLIENT = 43;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public NetworkPacketInfusionRecipeStatus(){
		this.messageValid = false;
	}
	
	public NetworkPacketInfusionRecipeStatus(BlockPos centerTilePos, boolean status, int recipeIndex, boolean successfully){
		this.centerTilePos = centerTilePos;
		this.status = status;
		this.recipeIndex = recipeIndex;
		this.successfully = successfully;
		this.messageValid = true;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public int getRecipeIndex() {
		return recipeIndex;
	}

	public void setRecipeIndex(int recipeIndex) {
		this.recipeIndex = recipeIndex;
	}
	
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public BlockPos getTilePos() {
		return centerTilePos;
	}

	public void setTilePos(BlockPos tilePos) {
		this.centerTilePos = tilePos;
	}
	
	public boolean getSuccessfull(){
		return successfully;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString() {
		return "NetworkPacketInfusionRecipeStatus[Pos;status =" + centerTilePos.toString() + ";" + status + ";" + recipeIndex + successfully +"]";
	}

	@Override
	public void toBytes(ByteBuf buf) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setBoolean("status", status);
		tag.setInteger("posX", centerTilePos.getX());
		tag.setInteger("posY", centerTilePos.getY());
		tag.setInteger("posZ", centerTilePos.getZ());
		tag.setInteger("recipeIndex", recipeIndex);
		tag.setBoolean("successfully", successfully);
		ByteBufUtils.writeTag(buf, tag);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try{
			NBTTagCompound tag = ByteBufUtils.readTag(buf);
			status = tag.getBoolean("status");
			centerTilePos = new BlockPos(tag.getInteger("posX"), tag.getInteger("posY"), tag.getInteger("posZ"));
			recipeIndex = tag.getInteger("recipeIndex");
			successfully = tag.getBoolean("successfully");
			messageValid = true;
		}
		catch (Exception e) {
			System.err.println("Exception while reading NetworkPacketInfusionRecipeStatus: " + e);
			messageValid = false;
			return;
		}
	}
	
}
