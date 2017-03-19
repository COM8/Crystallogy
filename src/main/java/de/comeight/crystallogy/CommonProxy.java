package de.comeight.crystallogy;

import de.comeight.crystallogy.handler.*;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    //-----------------------------------------------Attributes:--------------------------------------------
    //Config:
    private static ConfigHandler cH = new ConfigHandler();

    //Blocks:
    private static BlockHandler bH = new BlockHandler();

    //Items:
    private static ItemHandler iH = new ItemHandler();

    //Recipes:
    private static RecipeHandler rH = new RecipeHandler();

    //Sounds:
    private static SoundHandler sH = new SoundHandler();

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        cH.preInit(e);
        bH.preInit(e);
        iH.preInit(e);
        rH.preInit(e);
        sH.preInit(e);
    }

    //-----------------------------------------------Init:--------------------------------------------------
    @EventHandler
    public void init(FMLInitializationEvent e) {
        cH.init(e);
        bH.init(e);
        iH.init(e);
        rH.init(e);
        sH.init(e);
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        cH.postInit(e);
        bH.postInit(e);
        iH.postInit(e);
        rH.postInit(e);
        sH.postInit(e);
    }
}