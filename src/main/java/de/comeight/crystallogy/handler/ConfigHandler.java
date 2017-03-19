package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.Crystallogy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class ConfigHandler {
    //-----------------------------------------------Attributes:--------------------------------------------
    private static final String CONFIG_VERSION = "2.0";

    private static final String CATEGORY_DEBUG = "debug";
    private static final String CATEGORY_MISC = "misc";
    private static final String CATEGORY_WORLD_GENERATION = "worldGeneration";
    private static final String CATEGORY_GAMEPLAY = "gameplay";

    //Debug:
    public static boolean enableDebugMessagesInLog = false;

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------
    private void createConfigs(FMLPreInitializationEvent e){
        File configFolder = new File(e.getModConfigurationDirectory() + "/" + Crystallogy.MOD_ID.toUpperCase());
        if(!configFolder.exists()){
            configFolder.mkdirs();
        }
        createBasicConfig(configFolder);
    }

    private void createBasicConfig(File configFolder) {
        Configuration config = new Configuration(new File(configFolder, Crystallogy.MOD_ID + ".cfg"), CONFIG_VERSION);
        config.load();

        Property p;
        //---------------Settings:------------------
        //Debug:
        p = config.get(CATEGORY_DEBUG, "enableDebugMessagesInLog", false);
        p.setComment("Whether Crystallogy should show debug messages in the log. default=false");
        enableDebugMessagesInLog = p.getBoolean();

        //Word Generation:

        //Gameplay:

        //Misc:

        //------------------------------------------
        config.save();
    }

    //-----------------------------------------------Pre-Init:----------------------------------------------
    public void preInit(FMLPreInitializationEvent e) {
        createConfigs(e);
    }

    //-----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    }
}