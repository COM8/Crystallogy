package de.comeight.crystallogy.handler;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import de.comeight.crystallogy.entity.ai.EntityAiBaseSerializable;
import de.comeight.crystallogy.entity.ai.EntityAiFollowPlayer;
import de.comeight.crystallogy.entity.ai.EntityAiMoveToPos;
import de.comeight.crystallogy.entity.ai.EntityAiPickupItems;
import de.comeight.crystallogy.entity.ai.EntityAiQuarry;
import de.comeight.crystallogy.util.EnumCustomAis;
import de.comeight.crystallogy.util.NBTTags;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;


public class AiHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static boolean isCustomAiEnabled = true;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public AiHandler() {

	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void tryLoadEntitiesAi(EntityLiving entity){
		NBTTagCompound compound = entity.getEntityData();
		if(compound !=  null && compound.hasKey(NBTTags.HAS_CUSTOM_AI)){
			if(compound.hasKey(NBTTags.CUSTOM_AI_TYPE)){
				switch (EnumCustomAis.fromID(compound.getInteger(NBTTags.CUSTOM_AI_TYPE))) {
					case EMPTY:
						entity.tasks.taskEntries.clear();
						break;
						
					case MOVE_TO_POS:
						entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiMoveToPos(entity));
						break;
						
					case FOLLOW_PLAYER:
						entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiFollowPlayer(entity));
						break;
						
					case QUARRY:
						entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiQuarry(entity));
						break;
						
					case PICKUP_ITEMS:
						entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiPickupItems(entity));
						break;
						
					default:
						break;
				}
			}
		}
	}
	
	public static void removeEntityAi(EntityLiving entity){
		entity.tasks.taskEntries.clear();
		addedCustomAi(EnumCustomAis.EMPTY.ID, entity);
	}
	
	public static void removeCustomAi(EntityAiBaseSerializable ai, EntityLiving entity) {
		for (Iterator<EntityAITaskEntry> iterator = entity.tasks.taskEntries.iterator(); iterator.hasNext(); ) {
			EntityAITaskEntry task = iterator.next();
		    if (task.action == ai) {
		        iterator.remove();
		    }
		}
	}
	
	public static void addedCustomAi(int id, EntityLiving entity){
		NBTTagCompound compound = entity.getEntityData();
		if(compound == null){
			return;
		}
		compound.setBoolean(NBTTags.HAS_CUSTOM_AI, true);
		compound.setInteger(NBTTags.CUSTOM_AI_TYPE, id);
	}
	
	public static void addAdvancedTooltip(ItemStack stack, EntityPlayer playerIn, List<String> tooltip){
		NBTTagCompound compound = stack.getTagCompound();
		if(compound !=  null && compound.hasKey(NBTTags.CUSTOM_AI_TYPE)){
			switch (EnumCustomAis.fromID(compound.getInteger(NBTTags.CUSTOM_AI_TYPE))) {
				case MOVE_TO_POS:
					EntityAiMoveToPos.addAdvancedTooltip(stack, playerIn, tooltip);
					break;
					
				case FOLLOW_PLAYER:
					EntityAiFollowPlayer.addAdvancedTooltip(stack, playerIn, tooltip);
					break;
					
				case QUARRY:
					EntityAiQuarry.addAdvancedTooltip(stack, playerIn, tooltip);
					break;
					
				case PICKUP_ITEMS:
					EntityAiPickupItems.addAdvancedTooltip(stack, playerIn, tooltip);
					break;
					
				default:
					break;
			}
		}
	}
	
	public static void addAiToEntity(EntityLiving entity, NBTTagCompound compound){
		if(compound !=  null && compound.hasKey(NBTTags.CUSTOM_AI_TYPE)){
			switch (EnumCustomAis.fromID(compound.getInteger(NBTTags.CUSTOM_AI_TYPE))) {
				case MOVE_TO_POS:
					EntityAiMoveToPos aiMtP = new EntityAiMoveToPos(entity, new Vec3d(Utilities.readBlockPosFromNBT(compound, NBTTags.TARGET_POS)), 1.1);
					aiMtP.setForceMoveTo(compound.getBoolean(NBTTags.FORCE_MOVE_TO));
					aiMtP.setRun_continously(compound.getBoolean(NBTTags.RUN_CONTINUOUSLY));
					entity.tasks.addTask(Integer.MIN_VALUE, aiMtP);
					break;
					
				case FOLLOW_PLAYER:
					UUID uuid = compound.getUniqueId(NBTTags.ENTITY_UUID);
					for(EntityPlayer player: entity.getEntityWorld().playerEntities){
						if(player.getUniqueID().equals(uuid)){
							EntityAiFollowPlayer aiFP = new EntityAiFollowPlayer(entity, uuid, 1.1F);
							aiFP.setForceMoveTo(compound.getBoolean(NBTTags.FORCE_MOVE_TO));
							aiFP.setRun_continously(compound.getBoolean(NBTTags.RUN_CONTINUOUSLY));
							entity.tasks.addTask(Integer.MIN_VALUE, aiFP);
						}
					}
					break;
					
				case QUARRY:
					BlockPos pMin = Utilities.readBlockPosFromNBT(compound, NBTTags.AREA_MIN);
					BlockPos pMax = Utilities.readBlockPosFromNBT(compound, NBTTags.AREA_MAX);
					BlockPos area = pMax.add(-pMin.getX(), -pMin.getY(), -pMin.getZ());
					area = new BlockPos(area.getX(), -area.getY(), area.getZ());
					EntityAiQuarry aiQ = new EntityAiQuarry(entity, pMin, area);
					aiQ.setForceMoveTo(compound.getBoolean(NBTTags.FORCE_MOVE_TO));
					aiQ.setRun_continously(compound.getBoolean(NBTTags.RUN_CONTINUOUSLY));
					entity.tasks.addTask(Integer.MIN_VALUE, aiQ);
					break;
					
				case PICKUP_ITEMS:
					pMin = Utilities.readBlockPosFromNBT(compound, NBTTags.AREA_MIN);
					pMax = Utilities.readBlockPosFromNBT(compound, NBTTags.AREA_MAX);
					area = pMax.add(-pMin.getX(), -pMin.getY(), -pMin.getZ());
					area = new BlockPos(area.getX(), -area.getY(), area.getZ());
					BlockPos itemsTargetPos = Utilities.readBlockPosFromNBT(compound, NBTTags.ITEMS_TARGET_POS);
					EntityAiPickupItems aiPI = new EntityAiPickupItems(entity, itemsTargetPos, pMin, area);
					aiPI.setForceMoveTo(compound.getBoolean(NBTTags.FORCE_MOVE_TO));
					aiPI.setRun_continously(compound.getBoolean(NBTTags.RUN_CONTINUOUSLY));
					entity.tasks.addTask(Integer.MIN_VALUE, aiPI);
					break;
					
				default:
					break;
			}
		}
	}
	
}
