package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.items.ItemHandlerHelper;

public class EventHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String TAG_PLAYER_GOT_BOOK = CrystallogyBase.MODID + "gotSpawnBook";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@SubscribeEvent
	public void onLootLoad(LootTableLoadEvent event) {
		ChestGenHandler.init(event);
	}
	
	@SubscribeEvent
	public void EntityJoinWorldEvent(EntityJoinWorldEvent event){
		if(!event.getWorld().isRemote && event.getEntity() instanceof EntityLiving){
			AiHandler.tryLoadEntitiesAi((EntityLiving) event.getEntity());
		}
	}
	
	@SubscribeEvent
	public void LivingDropsEvent(LivingDropsEvent event){
		Entity entity = event.getEntity();
		if(entity instanceof EntityPlayer || entity instanceof EntityVillager){
			event.getDrops().add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(ItemHandler.entityBrain, 1, 0)));
		}
		else if(entity instanceof EntityMob){
			if(Utilities.getRandInt(0, 2) == 0){
				event.getDrops().add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(ItemHandler.entityBrain, 1, Utilities.getRandInt(1, 4))));
			}
		}
		else if (entity instanceof EntityAgeable){
			if(Utilities.getRandInt(0, 2) == 0){
				event.getDrops().add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(ItemHandler.entityBrain, 1, Utilities.getRandInt(2, 4))));
			}
		}
		else{
			if(Utilities.getRandInt(0, 2) == 0){
				event.getDrops().add(new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, new ItemStack(ItemHandler.entityBrain, 1, Utilities.getRandInt(1, 4))));
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		giveBookOfKnowledgeToPlayer(event);
	}
	
	private void giveBookOfKnowledgeToPlayer(PlayerEvent.PlayerLoggedInEvent event){
		if (ConfigHandler.shouldSpawnWithBook) {
			NBTTagCompound compound = event.player.getEntityData();

			if (!compound.getBoolean(TAG_PLAYER_GOT_BOOK)) {
				ItemHandlerHelper.giveItemToPlayer(event.player, new ItemStack(ItemHandler.bookOfKnowledge));
				compound.setBoolean(TAG_PLAYER_GOT_BOOK, true);
			}
		}
	}
}
