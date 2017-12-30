package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.Crystallogy;
import de.comeight.crystallogy.network.handler.NetworkMessageParticle;
import de.comeight.crystallogy.network.handler.client.MessageHandlerClientParticle;
import de.comeight.crystallogy.network.handler.server.MessageHandlerServerParticle;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static SimpleNetworkWrapper networkWrapper;
    private static int networkId = 0;

    //-----------------------------------------------Constructor:-------------------------------------------


    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------


    //-----------------------------------------------Events:------------------------------------------------


    //-----------------------------------------------Pre-Init:----------------------------------------------
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Crystallogy.MOD_ID);

        //Server:
        networkWrapper.registerMessage(MessageHandlerServerParticle.class, NetworkMessageParticle.class, networkId++, Side.SERVER);

        //Client:
        networkWrapper.registerMessage(MessageHandlerClientParticle.class, NetworkMessageParticle.class, networkId++, Side.CLIENT);
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