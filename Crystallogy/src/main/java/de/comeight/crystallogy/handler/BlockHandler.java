package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.blocks.CrystalGlas;
import de.comeight.crystallogy.blocks.CrystalLight;
import de.comeight.crystallogy.blocks.CrystalOfHolding;
import de.comeight.crystallogy.blocks.Crystall_blue;
import de.comeight.crystallogy.blocks.Crystall_green;
import de.comeight.crystallogy.blocks.Crystall_red;
import de.comeight.crystallogy.blocks.Crystall_yellow;
import de.comeight.crystallogy.blocks.Crystorya;
import de.comeight.crystallogy.blocks.EntityJar;
import de.comeight.crystallogy.blocks.FarmersGreen;
import de.comeight.crystallogy.blocks.FireCrystall;
import de.comeight.crystallogy.blocks.InfuserBlock;
import de.comeight.crystallogy.blocks.PlayerJar;
import de.comeight.crystallogy.blocks.machines.ArmorCombiner;
import de.comeight.crystallogy.blocks.machines.Charger;
import de.comeight.crystallogy.blocks.machines.Compressor;
import de.comeight.crystallogy.blocks.machines.CrystallCrusher;
import de.comeight.crystallogy.blocks.machines.MachineBlock;
import de.comeight.crystallogy.itemBlocks.ItemBlockCrystalGlas;
import de.comeight.crystallogy.itemBlocks.ItemBlockCrystalOfHolding;
import de.comeight.crystallogy.itemBlocks.ItemBlockEntityJar;
import de.comeight.crystallogy.itemBlocks.ItemBlockFarmersGreen;
import de.comeight.crystallogy.itemBlocks.ItemBlockPlayerJar;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class BlockHandler {
	//-----------------------------------------------Variabeln:---------------------------------------------
	//Bloecke:
	public static Crystall_red crystall_red;
	public static Crystall_blue crystall_blue;
	public static Crystall_yellow crystall_yellow;
	public static Crystall_green crystall_green;
	public static CrystallCrusher crystallCrusher;
	public static InfuserBlock infuserBlock;
	public static PlayerJar playerJar;
	public static EntityJar entityJar;
	public static CrystalLight crystalLight;
	public static FireCrystall fireCrystall;
	public static Compressor compressor;
	public static Charger charger;
	public static FarmersGreen farmersGreen;
	public static Crystorya crystorya;
	public static CrystalGlas crystalGlas;
	public static CrystalOfHolding crystalOfHolding;
	public static ArmorCombiner armorCombiner;
	public static MachineBlock machineBlock;

	//-----------------------------------------------Constructor:-------------------------------------------
	public BlockHandler() {
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void registerBlock(Block block, String id){
		GameRegistry.register(block);
		GameRegistry.register(new ItemBlock(block).setRegistryName(id));
	}
	
	private void registerBlock(Block block, ItemBlock itemBlock, String id){
		GameRegistry.register(block);
		GameRegistry.register(itemBlock.setRegistryName(id));
	}
	
	private void registerBlocks() {
		crystall_blue = new Crystall_blue();
		crystall_green = new Crystall_green();
		crystall_yellow = new Crystall_yellow();
		crystall_red = new Crystall_red();
		fireCrystall = new FireCrystall();
		infuserBlock = new InfuserBlock();
		playerJar = new PlayerJar();
		entityJar = new EntityJar();
		crystalLight = new CrystalLight();
		crystallCrusher = new CrystallCrusher();
		compressor = new Compressor();
		charger = new Charger();
		farmersGreen = new FarmersGreen();
		crystorya = new Crystorya();
		crystalGlas = new CrystalGlas();
		crystalOfHolding = new CrystalOfHolding();
		armorCombiner = new ArmorCombiner();
		machineBlock = new MachineBlock();
		
		registerBlock(machineBlock, machineBlock.ID);
		registerBlock(armorCombiner, armorCombiner.ID);
		registerBlock(crystall_blue, crystall_blue.ID);
		registerBlock(crystall_green, crystall_green.ID);
		registerBlock(crystall_red, crystall_red.ID);
		registerBlock(crystall_yellow, crystall_yellow.ID);
		registerBlock(fireCrystall, fireCrystall.ID);
		registerBlock(crystallCrusher, crystallCrusher.ID);
		registerBlock(compressor, compressor.ID);
		registerBlock(charger, charger.ID);
		registerBlock(infuserBlock, infuserBlock.ID);
		registerBlock(crystalLight, crystalLight.ID);
		registerBlock(farmersGreen, new ItemBlockFarmersGreen(farmersGreen), farmersGreen.ID);
		registerBlock(playerJar, new ItemBlockPlayerJar(playerJar), playerJar.ID);
		registerBlock(entityJar, new ItemBlockEntityJar(entityJar), entityJar.ID);
		registerBlock(crystorya, crystorya.ID);
		registerBlock(crystalGlas, new ItemBlockCrystalGlas(crystalGlas), crystalGlas.ID);
		registerBlock(crystalOfHolding, new ItemBlockCrystalOfHolding(crystalOfHolding), crystalOfHolding.ID);
		
		Utilities.addConsoleText("All blocks are registered.");
	}
	
	private void registerOreDictionary(){
		OreDictionary.registerOre("blockGlass", new ItemStack(crystalGlas, 1, 0));
		OreDictionary.registerOre("blockGlass", new ItemStack(crystalGlas, 1, 1));
		OreDictionary.registerOre("blockGlass", new ItemStack(crystalGlas, 1, 2));
		OreDictionary.registerOre("blockGlass", new ItemStack(crystalGlas, 1, 3));
	}

	//-----------------------------------------------Pre-Init:----------------------------------------------
	public void preInit(){
		registerBlocks();
	}

	//-----------------------------------------------Init:--------------------------------------------------
	public void init(){
		registerOreDictionary();
	}

	//-----------------------------------------------Post-Init:---------------------------------------------
	public void postInit(){
		
	}
	
}
