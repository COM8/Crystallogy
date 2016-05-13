package de.comeight.crystallogy.tileEntitys;

import de.comeight.crystallogy.CommonProxy;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.util.NetworkUtilitis;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

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

}
