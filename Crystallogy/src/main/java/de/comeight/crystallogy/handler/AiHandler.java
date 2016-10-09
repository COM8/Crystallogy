package de.comeight.crystallogy.handler;

import java.util.ArrayList;
import java.util.List;

import de.comeight.crystallogy.entity.ai.EntityAiBaseSerializable;
import de.comeight.crystallogy.entity.ai.EntityAiFollowPlayer;
import de.comeight.crystallogy.entity.ai.EntityAiMoveToPos;
import de.comeight.crystallogy.entity.ai.EntityAiPickupItems;
import de.comeight.crystallogy.entity.ai.EntityAiQuarry;
import de.comeight.crystallogy.util.EnumCustomAis;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;


public class AiHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static String CUSTOM_AI = "customAi";
	private static String AI_ID = "AiStatus";
	
	public static boolean isCustomAiEnabled = true;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public AiHandler() {

	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void tryLoadEntitiesAi(EntityLiving entity){
		NBTTagCompound compound = entity.getEntityData();
		if(compound !=  null && compound.hasKey(CUSTOM_AI)){
			if(compound.hasKey(AI_ID)){
				switch (EnumCustomAis.fromID(compound.getInteger(AI_ID))) {
					case EMPTY:
						entity.tasks.taskEntries.clear();
						break;
						
					case MOVE_TO_POS:
						entity.tasks.taskEntries.clear();
						entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiMoveToPos(entity));
						break;
						
					case FOLLOW_PLAYER:
						entity.tasks.taskEntries.clear();
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
	
	public static void addedCustomAi(int id, EntityLiving entity){
		NBTTagCompound compound = entity.getEntityData();
		if(compound == null){
			return;
		}
		compound.setBoolean(CUSTOM_AI, true);
		compound.setInteger(AI_ID, id);
	}
	
	public static void addAdvancedTooltip(ItemStack stack, EntityPlayer playerIn, List<String> tooltip){
		NBTTagCompound compound = stack.getTagCompound();
		if(compound !=  null && compound.hasKey("aiType")){
			switch (EnumCustomAis.fromID(compound.getInteger("aiType"))) {
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
	
}
