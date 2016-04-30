package de.comeight.crystallogy;

import de.comeight.crystallogy.handler.BlockRenderHandler;
import de.comeight.crystallogy.handler.ItemRenderHandler;
import de.comeight.crystallogy.handler.ParticleHandler;
import de.comeight.crystallogy.network.NetworkPacketInfuserBlockEnabled;
import de.comeight.crystallogy.network.NetworkPacketInfusionRecipeStatus;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.network.NetworkPacketUpdateInventory;
import de.comeight.crystallogy.network.handler.Client.MessageHandlerOnClientInfuserBlockEnabled;
import de.comeight.crystallogy.network.handler.Client.MessageHandlerOnClientInfusionRecipeStatus;
import de.comeight.crystallogy.network.handler.Client.MessageHandlerOnClientParticle;
import de.comeight.crystallogy.network.handler.Client.MessageHandlerOnClientTileEntitySync;
import de.comeight.crystallogy.network.handler.Client.MessageHandlerOnClientUpdateInventory;
import de.comeight.crystallogy.renderer.RendererInfuserBlockItem;
import de.comeight.crystallogy.renderer.RendererPlayerinJar;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import de.comeight.crystallogy.util.Utilities;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy{
	
	//-----------------------------------------------Pre-Init:----------------------------------------------
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        MinecraftForge.EVENT_BUS.register(new ParticleHandler());
        registerNetworkWrappers();
    }
	
	//-----------------------------------------------Init:--------------------------------------------------
    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        BlockRenderHandler.registerBlockRenderer();
        ItemRenderHandler.registerItemRenderer();
        registerSpecialRenderers();
    }

  //-----------------------------------------------Post-Init:----------------------------------------------
    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
    
    private void registerSpecialRenderers(){
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEnityInfuserBlock.class, new RendererInfuserBlockItem());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlayerJar.class, new RendererPlayerinJar());
    	
    	Utilities.addConsoleText("All specialRenderers are registered.");
    }
    
    private void registerNetworkWrappers() {
    	CommonProxy.NETWORKWRAPPER.registerMessage(MessageHandlerOnClientInfuserBlockEnabled.class, NetworkPacketInfuserBlockEnabled.class, NetworkPacketInfuserBlockEnabled.ID_CLIENT, Side.CLIENT);
    	CommonProxy.NETWORKWRAPPER.registerMessage(MessageHandlerOnClientInfusionRecipeStatus.class, NetworkPacketInfusionRecipeStatus.class, NetworkPacketInfusionRecipeStatus.ID_CLIENT, Side.CLIENT);
    	CommonProxy.NETWORKWRAPPER.registerMessage(MessageHandlerOnClientUpdateInventory.class, NetworkPacketUpdateInventory.class, NetworkPacketUpdateInventory.ID_CLIENT, Side.CLIENT);
    	CommonProxy.NETWORKWRAPPER.registerMessage(MessageHandlerOnClientParticle.class, NetworkPacketParticle.class, NetworkPacketParticle.ID_CLIENT, Side.CLIENT);
    	CommonProxy.NETWORKWRAPPER.registerMessage(MessageHandlerOnClientTileEntitySync.class, NetworkPacketTileEntitySync.class, NetworkPacketTileEntitySync.ID_CLIENT, Side.CLIENT);
    	
    	Utilities.addConsoleText("Clientside: MessageHandlerOnClient registriert.");
	}
}
