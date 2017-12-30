package de.comeight.crystallogy;

import de.comeight.crystallogy.handler.*;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    //-----------------------------------------------Attributes:--------------------------------------------
    //Client Event Handler:
    private static CommonEventHandler cEH = new CommonEventHandler();

    //Config:
    private static ConfigHandler cH = new ConfigHandler();

    //Blocks:
    private static BlockHandler bH = new BlockHandler();

    //Items:
    private static ItemHandler iH = new ItemHandler();

    //Entities:
    private static EntityHandler eH = new EntityHandler();

    //World Generators:
    private static WorldGenHandler wH = new WorldGenHandler();

    //Creative Tabs:
    private static CreativeTabHandler cTH = new CreativeTabHandler();

    //Recipes:
    private static RecipeHandler rH = new RecipeHandler();

    //Sounds:
    private static SoundHandler sH = new SoundHandler();

    //Network:
    private static NetworkHandler nH = new NetworkHandler();

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        cEH.preInit(e);
        cH.preInit(e);
        bH.preInit(e);
        iH.preInit(e);
        eH.preInit(e);
        wH.preInit(e);
        cTH.preInit(e);
        rH.preInit(e);
        sH.preInit(e);
        nH.preInit(e);
    }

    //-----------------------------------------------Init:--------------------------------------------------
    @EventHandler
    public void init(FMLInitializationEvent e) {
        cEH.init(e);
        cH.init(e);
        bH.init(e);
        iH.init(e);
        eH.init(e);
        wH.init(e);
        cTH.init(e);
        rH.init(e);
        sH.init(e);
        nH.init(e);
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        cEH.postInit(e);
        cH.postInit(e);
        bH.postInit(e);
        iH.postInit(e);
        eH.postInit(e);
        wH.postInit(e);
        cTH.postInit(e);
        rH.postInit(e);
        sH.postInit(e);
        nH.postInit(e);
    }
}