package de.comeight.crystallogy;

import de.comeight.crystallogy.handler.ClientEventHandler;
import de.comeight.crystallogy.handler.ParticleHandler;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
    //-----------------------------------------------Attributes:--------------------------------------------
    //Particles:
    public static ParticleHandler pH = new ParticleHandler();

    //Client Event Handler:
    private static ClientEventHandler cEH = new ClientEventHandler();

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        cEH.preInit(e);
        pH.preInit(e);
    }

    //-----------------------------------------------Init:--------------------------------------------------
    @EventHandler
    public void init(FMLInitializationEvent e) {
        super.init(e);
        cEH.init(e);
        pH.init(e);
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
        cEH.postInit(e);
        pH.postInit(e);
    }
}