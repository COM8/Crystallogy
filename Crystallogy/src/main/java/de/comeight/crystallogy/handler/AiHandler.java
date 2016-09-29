package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.entity.ai.EntityAiFollowPlayer;
import de.comeight.crystallogy.entity.ai.EntityAiMoveToPos;
import de.comeight.crystallogy.entity.ai.EntityAiPickupItems;
import de.comeight.crystallogy.entity.ai.EntityAiQuarry;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;


public class AiHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static String CUSTOM_AI = "customAi";
	private static String AI_STATUS = "AiStatus";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public AiHandler() {

	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void tryLoadEntitiesAi(EntityLiving entity){
		NBTTagCompound compound = entity.getEntityData();
		if(compound !=  null && compound.hasKey(CUSTOM_AI)){
			if(compound.hasKey(AI_STATUS)){
				switch (compound.getInteger(AI_STATUS)) {
					case 0:
						entity.tasks.taskEntries.clear();
						break;
					case 1:
						entity.tasks.taskEntries.clear();
						entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiMoveToPos(entity));
						break;
					case 2:
						entity.tasks.taskEntries.clear();
						entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiFollowPlayer(entity));
						break;
					case 3:
						entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiQuarry(entity));
						break;
					case 4:
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
		addedCustomAi(0, entity);
	}
	
	public static void addedCustomAi(int id, EntityLiving entity){
		NBTTagCompound compound = entity.getEntityData();
		if(compound == null){
			return;
		}
		compound.setBoolean(CUSTOM_AI, true);
		compound.setInteger(AI_STATUS, id);
	}
	
}
