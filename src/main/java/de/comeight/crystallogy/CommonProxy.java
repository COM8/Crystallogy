package de.comeight.crystallogy;

import de.comeight.crystallogy.gui.GuiCrystallCrusher;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.GuiHandler;
import de.comeight.crystallogy.handler.GuiHandlerRegistry;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.items.Vaporizer;
import de.comeight.crystallogy.items.crafting.RecipeVaporizer;
import de.comeight.crystallogy.network.NetworkPacketInfuserBlockEnabled;
import de.comeight.crystallogy.network.NetworkPacketInfusionRecipeStatus;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketUpdateInventory;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerInfuserBlockEnabled;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerInfusionRecipeStatus;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerParticle;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerUpdateInventory;
import de.comeight.crystallogy.tabs.CrystallogyMainTab;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.tileEntitys.TileEntityCrystallCrusher;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CommonProxy {
	// -----------------------------------------------Variabeln:---------------------------------------------
	// Creative Tabs:
	public static CrystallogyMainTab crystallogyMainTab = new CrystallogyMainTab();
	
	// Network:
	public static final SimpleNetworkWrapper NETWORKWRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(CrystallogyBase.MODID);
	
	//Blocks:
	private static BlockHandler bH = new BlockHandler();
	
	//Items:
	private static ItemHandler iH = new ItemHandler();
	
	//Recipes:
	public static RecipeVaporizer recipeVaporizer = new RecipeVaporizer();
	
	// -----------------------------------------------Constructor:-------------------------------------------
	
	
	// -----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	// -----------------------------------------------Sonstige Methoden:-------------------------------------
	private void registerNetworkWrappers() {
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerInfuserBlockEnabled.class, NetworkPacketInfuserBlockEnabled.class, NetworkPacketInfuserBlockEnabled.ID_SERVER, Side.SERVER);
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerInfusionRecipeStatus.class, NetworkPacketInfusionRecipeStatus.class, NetworkPacketInfusionRecipeStatus.ID_SERVER, Side.SERVER);
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerUpdateInventory.class, NetworkPacketUpdateInventory.class, NetworkPacketUpdateInventory.ID_SERVER, Side.SERVER);
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerParticle.class, NetworkPacketParticle.class, NetworkPacketParticle.ID_SERVER, Side.SERVER);
		System.out.println("Serverside MessageHandlerOnServer registriert.");
	}
	
	private void registerGuiHandlers() {
		NetworkRegistry.INSTANCE.registerGuiHandler(CrystallogyBase.INSTANCE, GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler(), GuiCrystallCrusher.ID);
	}
	
	private void registerWorldGens() {
		GameRegistry.registerWorldGenerator(new WorldGenerator(BlockHandler.crystall_green, 20, 2, 64, 128), 2);
		GameRegistry.registerWorldGenerator(new WorldGenerator(BlockHandler.crystall_blue, 10, 2, 48, 64), 2);
		GameRegistry.registerWorldGenerator(new WorldGenerator(BlockHandler.crystall_yellow, 10, 2, 16, 48), 2);
		GameRegistry.registerWorldGenerator(new WorldGenerator(BlockHandler.crystall_red, 10, 2, 0, 16), 2);
	}

	private void registerTileEntitys() {
		GameRegistry.registerTileEntity(TileEntityCrystallCrusher.class, BlockHandler.crystallCrusher.ID);
		GameRegistry.registerTileEntity(TileEnityInfuserBlock.class, BlockHandler.infuserBlock.ID);
		GameRegistry.registerTileEntity(TileEntityPlayerJar.class, BlockHandler.playerJar.ID);
	}
	
	private void registerRecipes() {
		GameRegistry.addRecipe(recipeVaporizer);
		
		ItemStack s =  new ItemStack(ItemHandler.vaporizer);
		s = ((Vaporizer)s.getItem()).saveNBT(s);
		
		IRecipe vaporicerRecipe = new ShapedOreRecipe(ItemHandler.vaporizer.getItemStack(), new Object[]{
				"III",
				"PI_",
				"_G_",
				
				'I', Items.iron_ingot,
				'P', "plankWood",
				'G', "blockGlassLime",
		});
		GameRegistry.addRecipe(vaporicerRecipe);
	}
	
	// -----------------------------------------------Pre-Init:----------------------------------------------
	public void preInit(FMLPreInitializationEvent e) {
		bH.preInit();
		iH.preInit();
		registerNetworkWrappers();
		registerGuiHandlers();
    }

	// -----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {
    	bH.init();
		iH.init();
    	
    	registerTileEntitys();
		registerWorldGens();
		registerRecipes();
		
    }

	// -----------------------------------------------Post-Init:----------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    	bH.postInit();
		iH.postInit();
    }
    
    
}