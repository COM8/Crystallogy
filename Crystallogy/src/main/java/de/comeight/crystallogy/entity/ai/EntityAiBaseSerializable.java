package de.comeight.crystallogy.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.nbt.NBTTagCompound;

public abstract class EntityAiBaseSerializable extends EntityAIBase {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityAiBaseSerializable() {
		
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	protected abstract int getAiID();
	
	protected NBTTagCompound getCompound(EntityLiving entity){
		NBTTagCompound compound = entity.getEntityData().getCompoundTag("customAi_" + getAiID());
		if(compound == null){
			setCompound(entity, new NBTTagCompound());
			getCompound(entity);
		}
		return compound;
	}
	
	protected void setCompound(EntityLiving entity, NBTTagCompound compound){
		entity.getEntityData().setTag("customAi_" + getAiID(), compound);
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public abstract void writeToNBT(NBTTagCompound compound);
	
	public abstract void redFromNBT(NBTTagCompound compound);
	
	public void saveData(EntityLiving entity){
		NBTTagCompound compound = getCompound(entity);
		writeToNBT(compound);
		setCompound(entity, compound);
	}
	
	public void readData(EntityLiving entity){
		NBTTagCompound compound = getCompound(entity);
		redFromNBT(compound);
	}
	
}
