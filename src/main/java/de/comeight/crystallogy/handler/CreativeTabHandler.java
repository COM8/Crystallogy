package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.client.creativeTabs.CrystallogyMainTab;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabHandler {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static CrystallogyMainTab crystallogyMainTab = new CrystallogyMainTab();

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    }

    //-----------------------------------------------Init:--------------------------------------------------
    @EventHandler
    public void init(FMLInitializationEvent e) {
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    }
}