package de.comeight.crystallogy;

import org.lwjgl.input.Keyboard;

import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.handler.BlockRenderHandler;
import de.comeight.crystallogy.handler.EventHandler;
import de.comeight.crystallogy.handler.ItemRenderHandler;
import de.comeight.crystallogy.particles.ParticleHandler;
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

public class ClientProxy extends CommonProxy{
	//-----------------------------------------------Variabeln:---------------------------------------------
	//KeyBinding:
	public static KeyBinding jetpackKey;
	
	//Particles:
	private ParticleHandler pH = new ParticleHandler();
	
	
	//-----------------------------------------------Pre-Init:----------------------------------------------
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        MinecraftForge.EVENT_BUS.register(pH);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        
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
        
        PageRegistry.registerAllPages();
    }
    
  //-----------------------------------------------Sonstige Methoden:-------------------------------------
    private void registerSpecialRenderers(){
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEnityInfuserBlock.class, new RendererInfuserBlockItem());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlayerJar.class, new RendererPlayerInJar());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEntityJar.class, new RendererEntityinJar());
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystalOfHolding.class, new RendererEntityInCrystal());
    	
    	Utilities.addConsoleText("All specialRenderers are registered.");
    }
    
    private void registerKeybinding(){
    	jetpackKey = new KeyBinding("Unused", Keyboard.KEY_SPACE, "Crystallogy");
    	
    	ClientRegistry.registerKeyBinding(jetpackKey);
    	
    	Utilities.addConsoleText("All keyBindings are registered.");
    }
    
    private void registerEventHandlerClient(){
    	Utilities.addConsoleText("All eventHandler are registered.");
    }
}
