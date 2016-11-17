package de.comeight.crystallogy.entity.ai;

import java.util.List;

import de.comeight.crystallogy.handler.AiHandler;
import de.comeight.crystallogy.util.NBTTags;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class EntityAiBaseSerializable extends EntityAIBase {
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected boolean saved;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityAiBaseSerializable() {
		saved = false;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public abstract int getAiID();
	
	protected NBTTagCompound getCompound(EntityLiving entity){
		NBTTagCompound compound = entity.getEntityData().getCompoundTag(NBTTags.CUSTOM_AI_TYPE + getAiID());
		if(compound == null){
			setCompound(entity, new NBTTagCompound());
			getCompound(entity);
		}
		return compound;
	}
	
	protected void setCompound(EntityLiving entity, NBTTagCompound compound){
		entity.getEntityData().setTag(NBTTags.CUSTOM_AI_TYPE + getAiID(), compound);
	}
	
	@Override
	public boolean shouldExecute() {
		return AiHandler.isCustomAiEnabled;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public abstract void writeToNBT(NBTTagCompound compound);
	
	public abstract void readFromNBT(NBTTagCompound compound);
	
	public void saveData(EntityLiving entity){
		NBTTagCompound compound = getCompound(entity);
		writeToNBT(compound);
		setCompound(entity, compound);
		saved = true;
	}
	
	public void readData(EntityLiving entity){
		NBTTagCompound compound = getCompound(entity);
		readFromNBT(compound);
	}
	
	public static void addAdvancedTooltip(ItemStack stack, EntityPlayer playerIn, List<String> tooltip){
		
	}
	
}
