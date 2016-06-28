package de.comeight.crystallogy;

import org.lwjgl.input.Keyboard;

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
import de.comeight.crystallogy.renderer.RendererEntityInCrystal;
import de.comeight.crystallogy.renderer.RendererEntityinJar;
import de.comeight.crystallogy.renderer.RendererInfuserBlockItem;
import de.comeight.crystallogy.renderer.RendererPlayerInJar;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.tileEntitys.TileEntityCrystalOfHolding;
import de.comeight.crystallogy.tileEntitys.TileEntityEntityJar;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy{
	//-----------------------------------------------Variabeln:---------------------------------------------
	//KeyBinding:
	public static KeyBinding jetpackKey;
	
	//Particles:
	private de.comeight.crystallogy.particles2.ParticleHandler pH = new de.comeight.crystallogy.particles2.ParticleHandler();
	
	
	//-----------------------------------------------Pre-Init:----------------------------------------------
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        MinecraftForge.EVENT_BUS.register(new ParticleHandler());
        registerNetworkWrappers();
        
        pH.preInit();
    }
	
	//-----------------------------------------------Init:--------------------------------------------------
    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        BlockRenderHandler.registerBlockRenderer();
        ItemRenderHandler.registerItemRenderer();
        registerSpecialRenderers();
        registerKeybinding();
        registerEventHandlerClient();
        
        pH.init();
    }

  //-----------------------------------------------Post-Init:----------------------------------------------
    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
        
        pH.postInit();
    }
    
  //-----------------------------------------------Sonstige Methoden:-------------------------------------
    private void registerSpecialRenderers(){
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEnityInfuserBlock.class, new RendererInfuserBlockItem());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlayerJar.class, new RendererPlayerInJar());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEntityJar.class, new RendererEntityinJar());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystalOfHolding.class, new RendererEntityInCrystal());
    	
    	Utilities.addConsoleText("All specialRenderers are registered.");
    }
    
    private void registerNetworkWrappers() {
    	CommonProxy.NETWORKWRAPPER.registerMessage(MessageHandlerOnClientInfuserBlockEnabled.class, NetworkPacketInfuserBlockEnabled.class, NetworkPacketInfuserBlockEnabled.ID_CLIENT, Side.CLIENT);
    	CommonProxy.NETWORKWRAPPER.registerMessage(MessageHandlerOnClientInfusionRecipeStatus.class, NetworkPacketInfusionRecipeStatus.class, NetworkPacketInfusionRecipeStatus.ID_CLIENT, Side.CLIENT);
    	CommonProxy.NETWORKWRAPPER.registerMessage(MessageHandlerOnClientUpdateInventory.class, NetworkPacketUpdateInventory.class, NetworkPacketUpdateInventory.ID_CLIENT, Side.CLIENT);
    	CommonProxy.NETWORKWRAPPER.registerMessage(MessageHandlerOnClientParticle.class, NetworkPacketParticle.class, NetworkPacketParticle.ID_CLIENT, Side.CLIENT);
    	CommonProxy.NETWORKWRAPPER.registerMessage(MessageHandlerOnClientTileEntitySync.class, NetworkPacketTileEntitySync.class, NetworkPacketTileEntitySync.ID_CLIENT, Side.CLIENT);
    	
    	Utilities.addConsoleText("Clientside: MessageHandlerOnClient registered.");
	}
    
    private void registerKeybinding(){
    	jetpackKey = new KeyBinding("keyBinding.space.name", Keyboard.KEY_SPACE, "Crystallogy");
    	
    	//ClientRegistry.registerKeyBinding(jetpackKey);
    	
    	Utilities.addConsoleText("All keyBindings are registered.");
    }
    
    private void registerEventHandlerClient(){
    	Utilities.addConsoleText("All eventHandler are registered.");
    }
}
