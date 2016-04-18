package de.comeight.crystallogy;

import de.comeight.crystallogy.blocks.EnumCrystalColor;
import de.comeight.crystallogy.gui.GuiCrystallCrusher;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.GuiHandler;
import de.comeight.crystallogy.handler.GuiHandlerRegistry;
import de.comeight.crystallogy.handler.InfusionRecipeHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.items.Tools.Vaporizer;
import de.comeight.crystallogy.items.crafting.InfusionRecipeCrystallLight;
import de.comeight.crystallogy.items.crafting.InfusionRecipeFireCrystall;
import de.comeight.crystallogy.items.crafting.InfusionRecipeHammer;
import de.comeight.crystallogy.items.crafting.InfusionRecipePureDust;
import de.comeight.crystallogy.items.crafting.InfusionRecipeRefulelVaporizer;
import de.comeight.crystallogy.items.crafting.InfusionRecipeSword;
import de.comeight.crystallogy.items.crafting.InfusionRecipeToolRod;
import de.comeight.crystallogy.items.crafting.InfusionRecipeVaporizer;
import de.comeight.crystallogy.network.NetworkPacketInfuserBlockEnabled;
import de.comeight.crystallogy.network.NetworkPacketInfusionRecipeStatus;
import de.comeight.crystallogy.network.NetworkPacketParticle;
import de.comeight.crystallogy.network.NetworkPacketTileEntitySync;
import de.comeight.crystallogy.network.NetworkPacketUpdateInventory;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerInfuserBlockEnabled;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerInfusionRecipeStatus;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerParticle;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerTileEntitySync;
import de.comeight.crystallogy.network.handler.Server.MessageHandlerOnServerUpdateInventory;
import de.comeight.crystallogy.tabs.CrystallogyMainTab;
import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import de.comeight.crystallogy.tileEntitys.TileEntityCrystallCrusher;
import de.comeight.crystallogy.tileEntitys.TileEntityCrystallLight;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
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
	private static InfusionRecipeVaporizer infusionRecipeVaporizer = new InfusionRecipeVaporizer(); 
	private static InfusionRecipeCrystallLight infusionRecipeCrystallLight = new InfusionRecipeCrystallLight();
	private static InfusionRecipeFireCrystall infusionRecipeFireCrystall = new InfusionRecipeFireCrystall();
	private static InfusionRecipeRefulelVaporizer infusionRecipeRefulelVaporizer = new InfusionRecipeRefulelVaporizer();
	private static InfusionRecipeToolRod infusionRecipeToolRod = new InfusionRecipeToolRod();
	private static InfusionRecipePureDust infusionRecipePureDust = new InfusionRecipePureDust();
	private static InfusionRecipeHammer infusionRecipeHammer = new InfusionRecipeHammer();
	private static InfusionRecipeSword infusionRecipeSword = new InfusionRecipeSword();
	
	// -----------------------------------------------Constructor:-------------------------------------------
	
	
	// -----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	// -----------------------------------------------Sonstige Methoden:-------------------------------------
	private void registerNetworkWrappers() {
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerInfuserBlockEnabled.class, NetworkPacketInfuserBlockEnabled.class, NetworkPacketInfuserBlockEnabled.ID_SERVER, Side.SERVER);
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerInfusionRecipeStatus.class, NetworkPacketInfusionRecipeStatus.class, NetworkPacketInfusionRecipeStatus.ID_SERVER, Side.SERVER);
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerUpdateInventory.class, NetworkPacketUpdateInventory.class, NetworkPacketUpdateInventory.ID_SERVER, Side.SERVER);
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerParticle.class, NetworkPacketParticle.class, NetworkPacketParticle.ID_SERVER, Side.SERVER);
		NETWORKWRAPPER.registerMessage(MessageHandlerOnServerTileEntitySync.class, NetworkPacketTileEntitySync.class, NetworkPacketTileEntitySync.ID_SERVER, Side.SERVER);
		Utilities.addConsoleText("Serverside: MessageHandlerOnServer registered.");
	}
	
	private void registerGuiHandlers() {
		NetworkRegistry.INSTANCE.registerGuiHandler(CrystallogyBase.INSTANCE, GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandler(), GuiCrystallCrusher.ID);
		Utilities.addConsoleText("All guis are registered.");
	}
	
	private void registerWorldGens() {
		GameRegistry.registerWorldGenerator(new WorldGenerator(BlockHandler.crystall_green, 20, 2, 64, 128), 2);
		GameRegistry.registerWorldGenerator(new WorldGenerator(BlockHandler.crystall_blue, 10, 2, 48, 64), 2);
		GameRegistry.registerWorldGenerator(new WorldGenerator(BlockHandler.crystall_yellow, 10, 2, 16, 48), 2);
		GameRegistry.registerWorldGenerator(new WorldGenerator(BlockHandler.crystall_red, 10, 2, 0, 16), 2);
		Utilities.addConsoleText("All worldgens are registered.");
	}

	private void registerTileEntitys() {
		GameRegistry.registerTileEntity(TileEntityCrystallCrusher.class, BlockHandler.crystallCrusher.ID);
		GameRegistry.registerTileEntity(TileEnityInfuserBlock.class, BlockHandler.infuserBlock.ID);
		GameRegistry.registerTileEntity(TileEntityPlayerJar.class, BlockHandler.playerJar.ID);
		GameRegistry.registerTileEntity(TileEntityCrystallLight.class, BlockHandler.crystallLight.ID);
		Utilities.addConsoleText("All tileEntitys are registered.");
		
	}
	
	private void registerRecipes() {
		InfusionRecipeHandler.addRecipe(infusionRecipeVaporizer);
		InfusionRecipeHandler.addRecipe(infusionRecipeCrystallLight);
		InfusionRecipeHandler.addRecipe(infusionRecipeFireCrystall);
		InfusionRecipeHandler.addRecipe(infusionRecipeRefulelVaporizer);
		InfusionRecipeHandler.addRecipe(infusionRecipePureDust);
		InfusionRecipeHandler.addRecipe(infusionRecipeSword);
		InfusionRecipeHandler.addRecipe(infusionRecipeHammer);
		InfusionRecipeHandler.addRecipe(infusionRecipeToolRod);
		
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
		
		IRecipe infuserBlockRecipe = new ShapedOreRecipe(BlockHandler.infuserBlock, new Object[]{
				"CIC",
				"_C_",
				"WWW",
				
				'C', Blocks.cobblestone,
				'I', Blocks.crafting_table,
				'W', Blocks.heavy_weighted_pressure_plate,
		});
		GameRegistry.addRecipe(infuserBlockRecipe);
		
		ItemStack r = new ItemStack(BlockHandler.crystall_red);
		IRecipe crystallHammerHead_red = new ShapedRecipes(3, 2, new ItemStack[]{
				r,r,r,
				r,r,r,
		}, EnumCrystalColor.RED.getStack(new ItemStack(ItemHandler.crystallHammerHead)));
		GameRegistry.addRecipe(crystallHammerHead_red);
		
		IRecipe crystallSwordBlade_red = new ShapedRecipes(1, 2, new ItemStack[]{
				r,
				r,
		}, EnumCrystalColor.RED.getStack(new ItemStack(ItemHandler.crystallSwordBlade)));
		GameRegistry.addRecipe(crystallSwordBlade_red);
		
		ItemStack g = new ItemStack(BlockHandler.crystall_green);
		IRecipe crystallHammerHead_green = new ShapedRecipes(3, 2, new ItemStack[]{
				g,g,g,
				g,g,g,
		}, EnumCrystalColor.GREEN.getStack(new ItemStack(ItemHandler.crystallHammerHead)));
		GameRegistry.addRecipe(crystallHammerHead_green);
		
		IRecipe crystallSwordBlade_green = new ShapedRecipes(1, 2, new ItemStack[]{
				g,
				g,
		}, EnumCrystalColor.GREEN.getStack(new ItemStack(ItemHandler.crystallSwordBlade)));
		GameRegistry.addRecipe(crystallSwordBlade_green);
		
		ItemStack b = new ItemStack(BlockHandler.crystall_blue);
		IRecipe crystallHammerHead_blue = new ShapedRecipes(3, 2, new ItemStack[]{
				b,b,b,
				b,b,b,
		}, EnumCrystalColor.BLUE.getStack(new ItemStack(ItemHandler.crystallHammerHead)));
		GameRegistry.addRecipe(crystallHammerHead_blue);
		
		IRecipe crystallSwordBlade_blue = new ShapedRecipes(1, 2, new ItemStack[]{
				b,
				b,
		}, EnumCrystalColor.BLUE.getStack(new ItemStack(ItemHandler.crystallSwordBlade)));
		GameRegistry.addRecipe(crystallSwordBlade_blue);
		
		ItemStack y = new ItemStack(BlockHandler.crystall_yellow);
		IRecipe crystallHammerHead_yellow = new ShapedRecipes(3, 2, new ItemStack[]{
				y,y,y,
				y,y,y,
		}, EnumCrystalColor.YELLOW.getStack(new ItemStack(ItemHandler.crystallHammerHead)));
		GameRegistry.addRecipe(crystallHammerHead_yellow);
		
		IRecipe crystallSwordBlade_yellow = new ShapedRecipes(1, 2, new ItemStack[]{
				y,
				y,
		}, EnumCrystalColor.YELLOW.getStack(new ItemStack(ItemHandler.crystallSwordBlade)));
		GameRegistry.addRecipe(crystallSwordBlade_yellow);
		
		Utilities.addConsoleText("All recipes are registered.");
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
