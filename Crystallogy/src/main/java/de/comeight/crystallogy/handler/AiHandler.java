package de.comeight.crystallogy.handler;

import java.util.List;
import java.util.UUID;

import de.comeight.crystallogy.entity.ai.EntityAiFollowPlayer;
import de.comeight.crystallogy.entity.ai.EntityAiMoveToPos;
import de.comeight.crystallogy.entity.ai.EntityAiPickupItems;
import de.comeight.crystallogy.entity.ai.EntityAiQuarry;
import de.comeight.crystallogy.util.EnumCustomAis;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;


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
	
	public static void addAiToEntity(EntityLiving entity, NBTTagCompound compound){
		if(compound !=  null && compound.hasKey("aiType")){
			switch (EnumCustomAis.fromID(compound.getInteger("aiType"))) {
				case MOVE_TO_POS:
					entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiMoveToPos(entity, new Vec3d(Utilities.readBlockPosFromNBT(compound, "targetPos")), 1.0F));
					break;
					
				case FOLLOW_PLAYER:
					UUID uuid = compound.getUniqueId("playerUUID");
					for(EntityPlayer player: entity.getEntityWorld().playerEntities){
						if(player.getUniqueID().equals(uuid)){
							entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiFollowPlayer(entity, uuid, 1.0F));
						}
					}
					break;
					
				case QUARRY:
					BlockPos pMin = Utilities.readBlockPosFromNBT(compound, "areaMin");
					BlockPos pMax = Utilities.readBlockPosFromNBT(compound, "areaMax");
					BlockPos area = pMax.add(-pMin.getX(), -pMin.getY(), -pMin.getZ());
					area = new BlockPos(area.getX(), -area.getY(), area.getZ());
					entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiQuarry(entity, pMin, area));
					break;
					
				case PICKUP_ITEMS:
					pMin = Utilities.readBlockPosFromNBT(compound, "areaMin");
					pMax = Utilities.readBlockPosFromNBT(compound, "areaMax");
					area = pMax.add(-pMin.getX(), -pMin.getY(), -pMin.getZ());
					area = new BlockPos(area.getX(), -area.getY(), area.getZ());
					BlockPos itemsTargetPos = Utilities.readBlockPosFromNBT(compound, "itemsTargetPos");
					entity.tasks.addTask(Integer.MIN_VALUE, new EntityAiPickupItems(entity, itemsTargetPos, pMin, area));
					break;
					
				default:
					break;
			}
		}
	}
	
}
