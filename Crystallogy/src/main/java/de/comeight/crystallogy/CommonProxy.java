package de.comeight.crystallogy;

import de.comeight.crystallogy.gui.GuiArmorCombiner;
import de.comeight.crystallogy.gui.GuiCharger;
import de.comeight.crystallogy.gui.GuiCompressor;
import de.comeight.crystallogy.gui.GuiCrystallCrusher;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ChestGenHandler;
import de.comeight.crystallogy.handler.ConfigHandler;
import de.comeight.crystallogy.handler.GuiHandler;
import de.comeight.crystallogy.handler.GuiHandlerRegistry;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.handler.RecipeHandler;
import de.comeight.crystallogy.handler.SoundHandler;
import de.comeight.crystallogy.network.NetworkPacketInfuserBlockEnabled;
import de.comeight.crystallogy.network.NetworkPacketInfusionRecipeStatus;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketTileEntityRequestSync;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.network.NetworkPacketUpdateInventory;
import de.comeight.crystallogy.network.handler.Client.MessageHandlerOnClientInfuserBlockEnabled;
import de.comeight.crystallogy.network.handler.Client.MessageHandlerOnClientInfusionRecipeStatus;
import de.comeight.crystallogy.network.handler.Client.MessageHandlerOnClientParticle;
import de.comeight.crystallogy.network.handler.Client.MessageHandlerOnClientTileEntitySync;
import de.comeight.crystallogy.network.handler.Client.MessageHandlerOnClientUpdateInventory;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerInfuserBlockEnabled;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerInfusionRecipeStatus;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerParticle;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerTileEntityRequestSync;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerTileEntitySync;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerUpdateInventory;
import de.comeight.crystallogy.tabs.CrystallogyMainTab;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.tileEntitys.TileEntityCrystalOfHolding;
import de.comeight.crystallogy.tileEntitys.TileEntityCrystallLight;
import de.comeight.crystallogy.tileEntitys.TileEntityEntityJar;
import de.comeight.crystallogy.tileEntitys.TileEntityFarmersGreen;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityArmorCombiner;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCharger;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCompressor;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCrystallCrusher;
import de.comeight.crystallogy.util.Utilities;
import de.comeight.crystallogy.worldGenerators.WorldGenerator;
import de.comeight.crystallogy.worldGenerators.WorldGeneratorFoilage;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy {
	// -----------------------------------------------Variabeln:---------------------------------------------
	//Creative Tabs:
	public static CrystallogyMainTab crystallogyMainTab = new CrystallogyMainTab();
	
	//Network:
	//public static final CustomNetworkWrapper NETWORKWRAPPER = new CustomNetworkWrapper(CrystallogyBase.MODID);
	public static final SimpleNetworkWrapper NETWORKWRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel(CrystallogyBase.MODID);
	
	//Blocks:
	private static BlockHandler bH = new BlockHandler();
	
	//Items:
	private static ItemHandler iH = new ItemHandler();
	
	//Recipes:
	private static RecipeHandler rH = new RecipeHandler();
	
	//Config:
	private static ConfigHandler cH = new ConfigHandler();
	
	//Sounds:
	private static SoundHandler sH = new SoundHandler();
	
	//ChestGen:
	private static ChestGenHandler cGH = new ChestGenHandler();
	
	private static int networkId = 0;
	
	// -----------------------------------------------Constructor:-------------------------------------------
	
	
	// -----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	// -----------------------------------------------Sonstige Methoden:-------------------------------------
	private void registerNetworkMessages() {
		//Server:
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerInfuserBlockEnabled.class, NetworkPacketInfuserBlockEnabled.class, networkId++, Side.SERVER);
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerInfusionRecipeStatus.class, NetworkPacketInfusionRecipeStatus.class, networkId++, Side.SERVER);
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerUpdateInventory.class, NetworkPacketUpdateInventory.class, networkId++, Side.SERVER);
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerParticle.class, NetworkPacketParticle.class, networkId++, Side.SERVER);
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerTileEntitySync.class, NetworkPacketTileEntitySync.class, networkId++, Side.SERVER);
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerTileEntityRequestSync.class, NetworkPacketTileEntityRequestSync.class, networkId++, Side.SERVER);
		
		//Client:
		NETWORKWRAPPER.registerMessage(MessageHandlerOnClientInfuserBlockEnabled.class, NetworkPacketInfuserBlockEnabled.class, networkId++, Side.CLIENT);
    	NETWORKWRAPPER.registerMessage(MessageHandlerOnClientInfusionRecipeStatus.class, NetworkPacketInfusionRecipeStatus.class, networkId++, Side.CLIENT);
    	NETWORKWRAPPER.registerMessage(MessageHandlerOnClientUpdateInventory.class, NetworkPacketUpdateInventory.class, networkId++, Side.CLIENT);
    	NETWORKWRAPPER.registerMessage(MessageHandlerOnClientParticle.class, NetworkPacketParticle.class, networkId++, Side.CLIENT);
    	NETWORKWRAPPER.registerMessage(MessageHandlerOnClientTileEntitySync.class, NetworkPacketTileEntitySync.class, networkId++, Side.CLIENT);
		
		Utilities.addConsoleText("All Network Messages are registered.");
	}
	
	private void registerGuiHandlers() {
		NetworkRegistry.INSTANCE.registerGuiHandler(CrystallogyBase.INSTANCE, GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(GuiHandler.INSTANCE, GuiCrystallCrusher.ID);
		GuiHandlerRegistry.getInstance().registerGuiHandler(GuiHandler.INSTANCE, GuiCompressor.ID);
		GuiHandlerRegistry.getInstance().registerGuiHandler(GuiHandler.INSTANCE, GuiCharger.ID);
		GuiHandlerRegistry.getInstance().registerGuiHandler(GuiHandler.INSTANCE, GuiArmorCombiner.ID);
		
		Utilities.addConsoleText("All guis are registered.");
	}
	
	private void registerWorldGens() {
		GameRegistry.registerWorldGenerator(new WorldGenerator(BlockHandler.crystall_green, ConfigHandler.greenChancesToSpawn, ConfigHandler.greenMaxSize, ConfigHandler.greenCrystalSpawnMin, ConfigHandler.greenCrystalSpawnMax), 0);
		GameRegistry.registerWorldGenerator(new WorldGenerator(BlockHandler.crystall_blue, ConfigHandler.blueChancesToSpawn, ConfigHandler.blueMaxSize, ConfigHandler.blueCrystalSpawnMin, ConfigHandler.blueCrystalSpawnMax), 0);
		GameRegistry.registerWorldGenerator(new WorldGenerator(BlockHandler.crystall_yellow, ConfigHandler.yellowChancesToSpawn, ConfigHandler.yellowMaxSize, ConfigHandler.yellowCrystalSpawnMin, ConfigHandler.yellowCrystalSpawnMax), 0);
		GameRegistry.registerWorldGenerator(new WorldGenerator(BlockHandler.crystall_red, ConfigHandler.redChancesToSpawn, ConfigHandler.redMaxSize, ConfigHandler.redCrystalSpawnMin, ConfigHandler.redCrystalSpawnMax), 0);
		GameRegistry.registerWorldGenerator(new WorldGeneratorFoilage(3), 20);
		
		Utilities.addConsoleText("All worldgens are registered.");
	}

	private void registerTileEntitys() {
		GameRegistry.registerTileEntity(TileEntityCrystallCrusher.class, "Crystallogy:" + BlockHandler.crystallCrusher.ID);
		GameRegistry.registerTileEntity(TileEnityInfuserBlock.class, "Crystallogy:" + BlockHandler.infuserBlock.ID);
		GameRegistry.registerTileEntity(TileEntityPlayerJar.class, "Crystallogy:" + BlockHandler.playerJar.ID);
		GameRegistry.registerTileEntity(TileEntityEntityJar.class, "Crystallogy:" + BlockHandler.entityJar.ID);
		GameRegistry.registerTileEntity(TileEntityCrystallLight.class, "Crystallogy:" + BlockHandler.crystalLight.ID);
		GameRegistry.registerTileEntity(TileEntityCompressor.class, "Crystallogy:" + BlockHandler.compressor.ID);
		GameRegistry.registerTileEntity(TileEntityCharger.class, "Crystallogy:" + BlockHandler.charger.ID);
		GameRegistry.registerTileEntity(TileEntityFarmersGreen.class, "Crystallogy:" + BlockHandler.farmersGreen.ID);
		GameRegistry.registerTileEntity(TileEntityCrystalOfHolding.class, "Crystallogy:" + BlockHandler.crystalOfHolding.ID);
		GameRegistry.registerTileEntity(TileEntityArmorCombiner.class, "Crystallogy:" + BlockHandler.armorCombiner.ID);
		
		Utilities.addConsoleText("All tileEntitys are registered.");
		
	}
	
	// -----------------------------------------------Pre-Init:----------------------------------------------
	public void preInit(FMLPreInitializationEvent e) {
		cH.preInit(e);
		bH.preInit();
		iH.preInit();
		rH.preInit();
		sH.preInit();
		cGH.preInit();
		
		registerNetworkMessages();
		registerGuiHandlers();
    }

	// -----------------------------------------------Init:--------------------------------------------------
    public void init(FMLInitializationEvent e) {
    	cH.init(e);
    	bH.init();
		iH.init();
		rH.init();
		sH.init();
		cGH.init();
		
    	registerTileEntitys();
		registerWorldGens();
    }

	// -----------------------------------------------Post-Init:----------------------------------------------
    public void postInit(FMLPostInitializationEvent e) {
    	cH.postInit(e);
    	bH.postInit();
		iH.postInit();
		rH.postInit();
		sH.postInit();
		cGH.postInit();
    }
    
}
