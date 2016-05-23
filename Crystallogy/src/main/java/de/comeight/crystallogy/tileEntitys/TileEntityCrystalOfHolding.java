package de.comeight.crystallogy.tileEntitys;

import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.util.EntityUtils;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityCrystalOfHolding extends BaseTileEntity {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private Entity entity;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityCrystalOfHolding() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public boolean hasEntity(){
		if(entity != null){
			return true;
		}
		return false;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void writeCustomDataToNBT(NBTTagCompound compound) {
		EntityUtils.writeEntityToCompound(compound, entity);
	}
	
	public void readCustomDataFromNBT(NBTTagCompound compound) {
		entity = EntityUtils.readEntityFromCompound(compound, worldObj);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		writeCustomDataToNBT(compound);
		super.writeToNBT(compound);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		readCustomDataFromNBT(compound);
		super.readFromNBT(compound);
	}
	
	@Override
	public NetworkPacketTileEntitySync getCustomDataPacket(NBTTagCompound compound) {
		writeToNBT(compound);
		return new NetworkPacketTileEntitySync(pos, compound);
	}
	
	@Override
	public void onCustomDataPacket(NetworkPacketTileEntitySync packet) {
		readFromNBT(packet.getNBTTagCompound());
	}
	
}
