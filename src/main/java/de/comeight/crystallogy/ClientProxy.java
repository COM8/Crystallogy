package de.comeight.crystallogy;

import org.lwjgl.input.Keyboard;

import de.comeight.crystallogy.entity.EntityMagicStoneOfForgetfulness;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.handler.BlockRenderHandler;
import de.comeight.crystallogy.handler.ItemRenderHandler;
import de.comeight.crystallogy.handler.ParticleHandler;
import de.comeight.crystallogy.renderer.RendererDissectingTable;
import de.comeight.crystallogy.renderer.RendererEntityInCrystal;
import de.comeight.crystallogy.renderer.RendererEntityinJar;
import de.comeight.crystallogy.renderer.RendererInfuserBlockItem;
import de.comeight.crystallogy.renderer.RendererPlayerInJar;
import de.comeight.crystallogy.renderer.entity.RendererEntityMagicStoneOfForgetfulnessFactory;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.tileEntitys.TileEntityCrystalOfHolding;
import de.comeight.crystallogy.tileEntitys.TileEntityEntityJar;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityDissectingTable;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
        registerEventHandlerClient();
        registerEntityRenderer();
        
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
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDissectingTable.class, new RendererDissectingTable());
    	
    	Utilities.addConsoleText("All specialRenderers are registered.");
    }
    
    private void registerKeybinding(){
    	jetpackKey = new KeyBinding("Unused", Keyboard.KEY_SPACE, "Crystallogy");
    	
    	ClientRegistry.registerKeyBinding(jetpackKey);
    	
    	Utilities.addConsoleText("All keyBindings are registered.");
    }
    
    private void registerEventHandlerClient(){
    	MinecraftForge.EVENT_BUS.register(pH);
    	Utilities.addConsoleText("All client event Handler are registered.");
    }
    
    private void registerEntityRenderer(){
    	RenderingRegistry.registerEntityRenderingHandler(EntityMagicStoneOfForgetfulness.class, new RendererEntityMagicStoneOfForgetfulnessFactory());
    }
}
