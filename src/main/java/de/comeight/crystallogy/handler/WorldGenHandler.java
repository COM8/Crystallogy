package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.util.Logger;
import de.comeight.crystallogy.worldGen.WorldGenCrystal;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class WorldGenHandler {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerWorldGens() {
        GameRegistry.registerWorldGenerator(new WorldGenCrystal(BlockHandler.CRYSTAL_ORE_RED, 10, 30, 5, 256), 0);

        Logger.info("All world generators got registered.");
    }

    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    }

    //-----------------------------------------------Init:--------------------------------------------------
    @EventHandler
    public void init(FMLInitializationEvent e) {
        registerWorldGens();
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    }
}