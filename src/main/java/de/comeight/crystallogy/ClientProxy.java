package de.comeight.crystallogy;

import de.comeight.crystallogy.handler.BlockRenderHandler;
import de.comeight.crystallogy.handler.ItemRenderHandler;
import de.comeight.crystallogy.handler.MessageHandlerOnClientHandler;
import de.comeight.crystallogy.network.MessageToClient;
import de.comeight.crystallogy.particles.ParticleHandler;
import de.comeight.crystallogy.renderer.RendererInfuserBlockItem;
import de.comeight.crystallogy.renderer.RendererPlayerinJar;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
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
        ItemRenderHandler.registerItemBlockRenderer();
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
    }
    
    private void registerNetworkWrappers() {
    	CommonProxy.NETWORKWRAPPER.registerMessage(MessageHandlerOnClientHandler.class, MessageToClient.class, CommonProxy.PARTICLE_MESSAGE_CLIENT_ID, Side.CLIENT);
    	System.out.println("Clientside MessageHandlerOnClient registriert.");
	}
}
