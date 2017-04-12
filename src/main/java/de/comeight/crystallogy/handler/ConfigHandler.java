package de.comeight.crystallogy.handler;

import java.io.File;

import de.comeight.crystallogy.CrystallogyBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	private static final String CONFIG_VERSION = "1.5";
	
	private static final String CATEGORY_DEBUG = "debug";
	private static final String WORLD_GENERATION = "worldGeneration";
	private static final String CATEGORY_INFUSION = "infusionCrafting";
	private static final String CATEGORY_GAMEPLAY = "gameplay";
	private static final String MOB_DROPS = "mobDrops";

	//CombinedArmorList:
	public static boolean debugCombinedArmorList = false;
	public static int minIntervalCombinedArmorList = 10000;
	
	//ArmorListEntry:
	public static int timeUnusedArmorListEntry = 60000;
	
	//World Generation:
	public static boolean disableRedCrystalWorldGeneration = false;
	public static boolean disableBlueCrystalWorldGeneration = false;
	public static boolean disableGreenCrystalWorldGeneration = false;
	public static boolean disableYellowCrystalWorldGeneration = false;
	public static boolean disableCrystoryaWorldGeneration = false;

	public static int crystoryaChancesToSpawn = 3;

	public static int yellowCrystalSpawnMin = 16;
	public static int yellowCrystalSpawnMax = 48;
	public static int yellowChancesToSpawn = 4;
	public static int yellowMaxSize = 5;
	
	public static int blueCrystalSpawnMin = 48;
	public static int blueCrystalSpawnMax = 64;
	public static int blueChancesToSpawn = 7;
	public static int blueMaxSize = 5;
	
	public static int greenCrystalSpawnMin = 64;
	public static int greenCrystalSpawnMax = 128;
	public static int greenChancesToSpawn = 8;
	public static int greenMaxSize = 5;
	
	public static int redCrystalSpawnMin = 0;
	public static int redCrystalSpawnMax = 16;
	public static int redChancesToSpawn = 7;
	public static int redMaxSize = 3;

	//Infusion:
	public static double infusionTimeMultiplier = 1.0;
	
	//Debug Tool:
	public static boolean enableDebugTool = false;
	
	//Book of Knowledge:
	public static boolean shouldSpawnWithBook = true;
	
	//Debug Misc:
	public static boolean enableDebugMessagesInLog = false;

	//Mob Drops:
	public static boolean disableDropEntityBrainNormal = false;
	public static boolean disableDropEntityBrainSmall = false;
	public static boolean disableDropEntityBrainTiny = false;
	public static boolean disableDropEntityBrainWalnut = false;
	
	//-----------------------------------------------Constructor:-------------------------------------------


	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void createConfigs(FMLPreInitializationEvent e){
		File configFolder = new File(e.getModConfigurationDirectory() + "/" + CrystallogyBase.MODID.toUpperCase());
		if(!configFolder.exists()){
			configFolder.mkdirs();
		}
		
		createBasicConfig(configFolder);
	}
	
	private void createBasicConfig(File configFolder){
		Configuration config = new Configuration(new File(configFolder, CrystallogyBase.MODID + ".cfg"), CONFIG_VERSION);
		
		config.load();
		
		Property prop;
		
		//Debug:
		prop = config.get(CATEGORY_DEBUG, "debug", false);
		prop.setComment("If true this enables debug information for the custom \"Garbage Collector\" for unused Combined Armor entries. default=false");
		debugCombinedArmorList = prop.getBoolean();
		
		prop = config.get(CATEGORY_DEBUG, "minInterval", 10000);
		prop.setComment("Defines the minimum interval between two runs of the custom \"Garbage Collector\" in ms. default=10000");
		minIntervalCombinedArmorList = prop.getInt();
		
		prop = config.get(CATEGORY_DEBUG, "timeUnused", 60000);
		prop.setComment("Defines the minimum time until a ArmorListEntry get removed by the custom \"Garbage Collector\" in ms. default=60000");
		timeUnusedArmorListEntry = prop.getInt();
		
		prop = config.get(CATEGORY_DEBUG, "enableDebugTool", false);
		prop.setComment("Whether the debug tool should be available. default=false");
		enableDebugTool = prop.getBoolean();
		
		prop = config.get(CATEGORY_DEBUG, "enableDebugMessagesInLog", false);
		prop.setComment("Whether there should be debug messages added to the log. default=false");
		enableDebugMessagesInLog = prop.getBoolean();

		//World Generation:
		prop = config.get(WORLD_GENERATION, "disableRedCrystalWorldGeneration", false);
		prop.setComment("Disable world generation for red crystals. default=false");
		disableRedCrystalWorldGeneration = prop.getBoolean();

		prop = config.get(WORLD_GENERATION, "disableBlueCrystalWorldGeneration", false);
		prop.setComment("Disable world generation for blue crystals. default=false");
		disableBlueCrystalWorldGeneration = prop.getBoolean();

		prop = config.get(WORLD_GENERATION, "disableGreenCrystalWorldGeneration", false);
		prop.setComment("Disable world generation for green crystals. default=false");
		disableGreenCrystalWorldGeneration = prop.getBoolean();

		prop = config.get(WORLD_GENERATION, "disableYellowCrystalWorldGeneration", false);
		prop.setComment("Disable world generation for yellow crystals. default=false");
		disableYellowCrystalWorldGeneration = prop.getBoolean();

		prop = config.get(WORLD_GENERATION, "disableCrystoryaWorldGeneration", false);
		prop.setComment("Disable world generation crystorya. default=false");
		disableCrystoryaWorldGeneration = prop.getBoolean();


		prop = config.get(WORLD_GENERATION, "crystoryaChancesToSpawn", 3);
		prop.setComment("How often should the generator try to spawn the crystorya plant? default = 3");
		crystoryaChancesToSpawn = prop.getInt();


		prop = config.get(WORLD_GENERATION, "yellowCrystalSpawnMin", 16);
		prop.setComment("The minimum height where Yellow Crystals can spawn. default = 16");
		yellowCrystalSpawnMin = prop.getInt();
		
		prop = config.get(WORLD_GENERATION, "yellowCrystalSpawnMax", 48);
		prop.setComment("The maximum height where Yellow Crystals can spawn. default = 48");
		yellowCrystalSpawnMax = prop.getInt();
		
		prop = config.get(WORLD_GENERATION, "yellowChancesToSpawn", 5);
		prop.setComment("How often should the generator try to spawn Yellow Crystals? default = 5");
		yellowMaxSize = prop.getInt();
		
		prop = config.get(WORLD_GENERATION, "yellowMaxSize", 4);
		prop.setComment("How much Yellow Crystals should get generated per spawn? default = 4");
		yellowChancesToSpawn = prop.getInt();

		
		prop = config.get(WORLD_GENERATION, "greenCrystalSpawnMin", 64);
		prop.setComment("The minimum height where Green Crystals can spawn. default = 64");
		greenCrystalSpawnMin = prop.getInt();
		
		prop = config.get(WORLD_GENERATION, "greenCrystalSpawnMax", 128);
		prop.setComment("The maximum height where Green Crystals can spawn. default = 128");
		greenCrystalSpawnMax = prop.getInt();
		
		prop = config.get(WORLD_GENERATION, "greenChancesToSpawn", 8);
		prop.setComment("How often should the generator try to spawn Green Crystals? default = 8");
		greenChancesToSpawn = prop.getInt();
		
		prop = config.get(WORLD_GENERATION, "greenMaxSize", 5);
		prop.setComment("How much Green Crystals should get generated per spawn? default = 5");
		greenMaxSize = prop.getInt();
		
		
		prop = config.get(WORLD_GENERATION, "redCrystalSpawnMin", 0);
		prop.setComment("The minimum height where Red Crystals can spawn. default = 0");
		redCrystalSpawnMin = prop.getInt();
		
		prop = config.get(WORLD_GENERATION, "redCrystalSpawnMax", 16);
		prop.setComment("The maximum height where Red Crystals can spawn. default = 16");
		redCrystalSpawnMax = prop.getInt();
		
		prop = config.get(WORLD_GENERATION, "redChancesToSpawn", 7);
		prop.setComment("How often should the generator try to spawn Red Crystals? default = 7");
		redChancesToSpawn = prop.getInt();
		
		prop = config.get(WORLD_GENERATION, "redMaxSize", 3);
		prop.setComment("How much Red Crystals should get generated per spawn? default = 3");
		redMaxSize = prop.getInt();

		
		prop = config.get(WORLD_GENERATION, "blueCrystalSpawnMin", 48);
		prop.setComment("The minimum height where Blue Crystals can spawn. default = 48");
		blueCrystalSpawnMin = prop.getInt();
		
		prop = config.get(WORLD_GENERATION, "blueCrystalSpawnMax", 64);
		prop.setComment("The maximum height where Blue Crystals can spawn. default = 64");
		blueCrystalSpawnMax = prop.getInt();
		
		prop = config.get(WORLD_GENERATION, "blueChancesToSpawn", 7);
		prop.setComment("How often should the generator try to spawn Blue Crystals? default = 7");
		blueChancesToSpawn = prop.getInt();
		
		prop = config.get(WORLD_GENERATION, "blueMaxSize", 5);
		prop.setComment("How much Blue Crystals should get generated per spawn? default = 5");
		blueMaxSize = prop.getInt();
		
		//Infusion Crafting:
		prop = config.get(CATEGORY_INFUSION, "infusionTimeMultiplier", 1.0);
		prop.setComment("Infusion Crafting multiplier. default = 1.0");
		infusionTimeMultiplier = prop.getDouble();
		
		//Gameplay:
		prop = config.get(CATEGORY_GAMEPLAY, "shouldSpawnWithBook", true);
		prop.setComment("Whether the Player should get the Book of Knowledge, if he enters the world for the first time. default=true");
		shouldSpawnWithBook = prop.getBoolean();

		//Mob Drops:
		prop = config.get(MOB_DROPS, "disableDropEntityBrainNormal", false);
		prop.setComment("Disable Entity Brain drop if a mob dies. default=false");
		disableDropEntityBrainNormal = prop.getBoolean();

		prop = config.get(MOB_DROPS, "disableDropEntityBrainSmall", false);
		prop.setComment("Disable Small Entity Brain drop if a mob dies. default=false");
		disableDropEntityBrainSmall = prop.getBoolean();

		prop = config.get(MOB_DROPS, "disableDropEntityBrainTiny", false);
		prop.setComment("Disable Tiny Entity Brain drop if a mob dies. default=false");
		disableDropEntityBrainTiny = prop.getBoolean();

		prop = config.get(MOB_DROPS, "disableDropEntityBrainWalnut", false);
		prop.setComment("Disable Walnut Entity Brain drop if a mob dies. default=false");
		disableDropEntityBrainWalnut = prop.getBoolean();

		config.save();
	}
	
	//-----------------------------------------------Pre-Init:----------------------------------------------
	public void preInit(FMLPreInitializationEvent e){
		createConfigs(e);
	}

	//-----------------------------------------------Init:--------------------------------------------------
	public void init(FMLInitializationEvent e){
	}

	//-----------------------------------------------Post-Init:---------------------------------------------
	public void postInit(FMLPostInitializationEvent e){
	}
}
