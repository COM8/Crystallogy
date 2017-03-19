package de.comeight.crystallogy;

import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy extends CommonProxy{
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    //-----------------------------------------------Init:--------------------------------------------------
    @EventHandler
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}