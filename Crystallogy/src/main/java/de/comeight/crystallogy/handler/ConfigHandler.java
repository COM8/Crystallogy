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
	private static final String CATEGORY_DEBUG = "debug";

	//CombinedArmorList:
	public static boolean debugCombinedArmorList = false;
	public static int minIntervalCombinedArmorList = 10000;
	
	//ArmorListEntry:
	public static int timeUnusedArmorListEntry = 60000;
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
		Configuration config = new Configuration(new File(configFolder, CrystallogyBase.MODID + ".cfg"), "1.0");
		
		config.load();
		
		Property prop;
		
		prop = config.get(CATEGORY_DEBUG, "debug", false);
		prop.setComment("If true this enables debug informations for the custom \"Garbage Collector\" for unused Combined Armor entrys. default=false");
		debugCombinedArmorList = prop.getBoolean();
		
		prop = config.get(CATEGORY_DEBUG, "minIntervall", 10000);
		prop.setComment("Defines the minimum interval between two runs of the custom \"Garbage Collector\" in ms. default=10000");
		minIntervalCombinedArmorList = prop.getInt();
		
		prop = config.get(CATEGORY_DEBUG, "timeUnused", 60000);
		prop.setComment("Defines the minimum time until a ArmorListEntry get removed by the custom \"Garbage Collector\" in ms. default=60000");
		timeUnusedArmorListEntry = prop.getInt();
		
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
