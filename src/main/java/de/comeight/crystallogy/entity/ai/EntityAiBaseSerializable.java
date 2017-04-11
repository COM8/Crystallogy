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
	protected boolean run_continously;
	protected EntityLiving aiOwner;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EntityAiBaseSerializable(EntityLiving aiOwner) {
		this.saved = false;
		this.run_continously = false;
		this.aiOwner = aiOwner;
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
	
	public boolean isRun_continously() {
		return run_continously;
	}

	public void setRun_continously(boolean run_continously) {
		this.run_continously = run_continously;
	}
	
	protected boolean continueExecutingCustom(){
		return false;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean continueExecuting() {
		if(!continueExecutingCustom()){
			if(!run_continously){
				AiHandler.removeCustomAi(this, aiOwner);
			}
			return false;
		}
		return true;
	}
	
	public void writeToNBT(NBTTagCompound compound){
		compound.setBoolean(NBTTags.RUN_CONTINUOUSLY, run_continously);
	}
	
	public void readFromNBT(NBTTagCompound compound){
		run_continously = compound.getBoolean(NBTTags.RUN_CONTINUOUSLY);
	}
	
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
