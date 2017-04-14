package de.comeight.crystallogy;

import de.comeight.crystallogy.handler.BlockRenderHandler;
import de.comeight.crystallogy.handler.ItemRenderHandler;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
    //-----------------------------------------------Attributes:--------------------------------------------
    ItemRenderHandler iRH = new ItemRenderHandler();
    BlockRenderHandler bRH = new BlockRenderHandler();

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

        iRH.preInit(e);
        bRH.preInit(e);
    }

    //-----------------------------------------------Init:--------------------------------------------------
    @EventHandler
    public void init(FMLInitializationEvent e) {
        super.init(e);

        iRH.init(e);
        bRH.init(e);
    }

    //-----------------------------------------------Post-Init:---------------------------------------------
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);

        iRH.postInit(e);
        bRH.postInit(e);
    }
}