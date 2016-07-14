package de.comeight.crystallogy.handler;

import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.event.LootTableLoadEvent;

public class ChestGenHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	private static LootFunction[] getCount(int min, int max){
		return new LootFunction[] {new SetCount(new LootCondition[0], new RandomValueRange(min, max))};
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private static void addBonusChestLoot(LootTableLoadEvent event){
		LootPool pool = event.getTable().getPool("main");
		
		if(pool != null){
			pool.addEntry(new LootEntryItem(Item.getItemFromBlock(BlockHandler.crystall_green), 7, 10, getCount(1, 5), new LootCondition[0], BlockHandler.crystall_green.ID));
			pool.addEntry(new LootEntryItem(Item.getItemFromBlock(BlockHandler.crystall_red), 7, 10, getCount(1, 5), new LootCondition[0], BlockHandler.crystall_red.ID));
			pool.addEntry(new LootEntryItem(Item.getItemFromBlock(BlockHandler.crystall_blue), 7, 10, getCount(1, 5), new LootCondition[0], BlockHandler.crystall_blue.ID));
			pool.addEntry(new LootEntryItem(Item.getItemFromBlock(BlockHandler.crystall_yellow), 7, 10, getCount(1, 5), new LootCondition[0], BlockHandler.crystall_yellow.ID));
			
			pool.addEntry(new LootEntryItem(ItemHandler.enderonCrystal, 10, 10, getCount(1, 1), new LootCondition[0], ItemHandler.enderonCrystal.ID));
		}
	}

	private static void addDungeonChestLoot(LootTableLoadEvent event){
		LootPool pool = event.getTable().getPool("main");
		
		if(pool != null){
			pool.addEntry(new LootEntryItem(Item.getItemFromBlock(BlockHandler.crystall_green), 7, 10, getCount(1, 5), new LootCondition[0], BlockHandler.crystall_green.ID));
			pool.addEntry(new LootEntryItem(Item.getItemFromBlock(BlockHandler.crystall_red), 7, 10, getCount(1, 5), new LootCondition[0], BlockHandler.crystall_red.ID));
			pool.addEntry(new LootEntryItem(Item.getItemFromBlock(BlockHandler.crystall_blue), 7, 10, getCount(1, 5), new LootCondition[0], BlockHandler.crystall_blue.ID));
			pool.addEntry(new LootEntryItem(Item.getItemFromBlock(BlockHandler.crystall_yellow), 7, 10, getCount(1, 5), new LootCondition[0], BlockHandler.crystall_yellow.ID));
			
			pool.addEntry(new LootEntryItem(ItemHandler.badLuckDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.badLuckDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.blindDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.blindDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.damDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.damDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.enderDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.enderDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.energyDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.energyDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.fireDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.fireDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.glowDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.glowDust.ID));
		}
	}
	
	private static void addStrongholdChestLoot(LootTableLoadEvent event){
		LootPool pool = event.getTable().getPool("main");
		
		if(pool != null){
			pool.addEntry(new LootEntryItem(Item.getItemFromBlock(BlockHandler.crystall_red), 7, 10, getCount(1, 5), new LootCondition[0], BlockHandler.crystall_red.ID));
			
			pool.addEntry(new LootEntryItem(ItemHandler.badLuckDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.badLuckDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.blindDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.blindDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.damDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.damDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.enderDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.enderDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.energyDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.energyDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.fireDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.fireDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.glowDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.glowDust.ID));
		}
	}
	
	private static void addPyramidDesertChestLoot(LootTableLoadEvent event){
		LootPool pool = event.getTable().getPool("main");
		
		if(pool != null){
			pool.addEntry(new LootEntryItem(Item.getItemFromBlock(BlockHandler.crystall_yellow), 7, 10, getCount(1, 5), new LootCondition[0], BlockHandler.crystall_yellow.ID));
		}
	}
	
	private static void addPyramidJungleChestLoot(LootTableLoadEvent event){
		LootPool pool = event.getTable().getPool("main");
		
		if(pool != null){
			pool.addEntry(new LootEntryItem(Item.getItemFromBlock(BlockHandler.crystall_green), 7, 10, getCount(1, 5), new LootCondition[0], BlockHandler.crystall_green.ID));
		}
	}
	
	private static void addVillageChestLoot(LootTableLoadEvent event){
		LootPool pool = event.getTable().getPool("main");
		
		if(pool != null){
			pool.addEntry(new LootEntryItem(ItemHandler.enderonCrystal, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.enderonCrystal.ID));
			
			pool.addEntry(new LootEntryItem(ItemHandler.badLuckDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.badLuckDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.blindDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.blindDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.damDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.damDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.enderDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.enderDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.energyDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.energyDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.fireDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.fireDust.ID));
			pool.addEntry(new LootEntryItem(ItemHandler.glowDust, 5, 10, getCount(1, 1), new LootCondition[0], ItemHandler.glowDust.ID));
			
		}
	}
	
	//-----------------------------------------------Pre-Init:----------------------------------------------
	public static void preInit(){
			
	}

	//-----------------------------------------------Init:--------------------------------------------------
	public static void init(LootTableLoadEvent event){
		if(event.getName().equals(LootTableList.CHESTS_ABANDONED_MINESHAFT)){
			
		}
		else if(event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID)){
			addPyramidDesertChestLoot(event);
		}
		else if(event.getName().equals(LootTableList.CHESTS_END_CITY_TREASURE)){
					
		}
		else if(event.getName().equals(LootTableList.CHESTS_IGLOO_CHEST)){
			
		}
		else if(event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE)){
			addPyramidJungleChestLoot(event);
		}
		else if(event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE_DISPENSER)){
			
		}
		else if(event.getName().equals(LootTableList.CHESTS_NETHER_BRIDGE)){
			
		}
		else if(event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)){
			addDungeonChestLoot(event);
		}
		else if(event.getName().equals(LootTableList.CHESTS_SPAWN_BONUS_CHEST)){
			addBonusChestLoot(event);
		}
		else if(event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR)){
			addStrongholdChestLoot(event);
		}
		else if(event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CROSSING)){
			addStrongholdChestLoot(event);
		}
		else if(event.getName().equals(LootTableList.CHESTS_STRONGHOLD_LIBRARY)){
			addStrongholdChestLoot(event);
		}
		else if(event.getName().equals(LootTableList.CHESTS_VILLAGE_BLACKSMITH)){
			addVillageChestLoot(event);
		}
	}

	//-----------------------------------------------Post-Init:---------------------------------------------
	public static void postInit(){
			
	}
		
}
