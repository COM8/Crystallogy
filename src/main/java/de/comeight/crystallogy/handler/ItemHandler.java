package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.util.Logger;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ItemHandler {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerAllItems() {
        Logger.info("All items got registered.");
    }

    private void registerAllItemBlocks() {
        Logger.info("All itemBlocks got registered.");
    }

    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    public void preInit(FMLPreInitializationEvent e) {
        registerAllItems();
        registerAllItemBlocks();
    }

    //-----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    }
}