package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.client.renderer.entities.RendererEntityBouncyCrystalFactory;
import de.comeight.crystallogy.entities.EntityBouncyCrystal;
import de.comeight.crystallogy.util.Logger;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class EntityRenderHandler {
    //-----------------------------------------------Attributes:--------------------------------------------


    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    private void registerEntityRenderer() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBouncyCrystal.class, new RendererEntityBouncyCrystalFactory());

        Logger.info("All entity renderer got registered.");
    }

    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        registerEntityRenderer();
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