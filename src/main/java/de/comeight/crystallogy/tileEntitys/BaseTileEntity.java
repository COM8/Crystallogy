package de.comeight.crystallogy.tileEntitys;

import de.comeight.crystallogy.network.NetworkPacketTileEntityRequestSync;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.util.NetworkUtilitis;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BaseTileEntity extends TileEntity {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public abstract void onCustomDataPacket(NetworkPacketTileEntitySync packet);
	
	public abstract NetworkPacketTileEntitySync getCustomDataPacket(NBTTagCompound compound);
	
	public void sync(){
		if(worldObj != null){
			NetworkPacketTileEntitySync packet = getCustomDataPacket(new NBTTagCompound());
			NetworkUtilitis.sendAllAround(packet, worldObj.isRemote);
		}
		markDirty();
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
		return new SPacketUpdateTileEntity(pos, 0, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		readFromNBT(pkt.getNbtCompound());
	}

	@SideOnly(Side.CLIENT)
	public void requestSync(){
		NetworkPacketTileEntityRequestSync packet = new NetworkPacketTileEntityRequestSync(pos);
		NetworkUtilitis.sendToServer(packet);
	}
	
	@Override
	public void onLoad() {
		if(worldObj.isRemote){
			requestSync();
		}
	}
	
}
