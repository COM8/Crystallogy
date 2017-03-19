package de.comeight.crystallogy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Crystallogy.MOD_ID, version = Crystallogy.VERSION, name = Crystallogy.MOD_NAME)
public class Crystallogy {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static  final String MOD_ID = "crystallogy";
    public static  final String MOD_NAME = "Crystallogy 2";
    public static  final String VERSION = "2.0_prev_1";

    @SidedProxy(clientSide="de.comeight.crystallogy.ClientProxy", serverSide= "de.comeight.crystallogy.ServerProxy")
    public static CommonProxy proxy;

    @Instance(MOD_ID)
    public static Crystallogy INSTANCE;

    //-----------------------------------------------Constructor:-------------------------------------------
    public Crystallogy() {
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------
    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
    }

    //-----------------------------------------------Pre-Init:----------------------------------------------
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }

    //-----------------------------------------------Init:--------------------------------------------------
    @EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}