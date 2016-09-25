package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.ServerProxy;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
