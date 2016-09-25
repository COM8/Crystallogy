package de.comeight.crystallogy.handler;

import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;


public class AiHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private int tick;
	private static String CUSTOM_AI = "customAi";
	private static String AI_STATUS = "AiStatus";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public AiHandler() {
		this.tick = 0;
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

					default:
						break;
				}
			}
		}
	}
	
	public static void removeEntityAi(EntityLiving entity){
		NBTTagCompound compound = entity.getEntityData();
		if(compound == null){
			return;
		}
		compound.setBoolean(CUSTOM_AI, true);
		compound.setInteger(AI_STATUS, 0);
		entity.tasks.taskEntries.clear();
	}
	
}
