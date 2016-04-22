package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.blocks.CrystallCrusher;
import de.comeight.crystallogy.blocks.CrystalLight;
import de.comeight.crystallogy.blocks.Crystall_blue;
import de.comeight.crystallogy.blocks.Crystall_green;
import de.comeight.crystallogy.blocks.Crystall_red;
import de.comeight.crystallogy.blocks.Crystall_yellow;
import de.comeight.crystallogy.blocks.FireCrystall;
import de.comeight.crystallogy.blocks.InfuserBlock;
import de.comeight.crystallogy.blocks.PlayerJar;
import de.comeight.crystallogy.itemBlocks.ItemBlockPlayerJar;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

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
	public static CrystalLight crystalLight;
	public static FireCrystall fireCrystall;

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
		crystall_red = new Crystall_red();
		crystall_yellow = new Crystall_yellow();
		crystallCrusher = new CrystallCrusher();
		infuserBlock = new InfuserBlock();
		playerJar = new PlayerJar();
		crystalLight = new CrystalLight();
		fireCrystall = new FireCrystall();
		
		registerBlock(crystall_blue, crystall_blue.ID);
		registerBlock(crystall_green, crystall_green.ID);
		registerBlock(crystall_red, crystall_red.ID);
		registerBlock(crystall_yellow, crystall_yellow.ID);
		registerBlock(crystallCrusher, crystallCrusher.ID);
		registerBlock(infuserBlock, infuserBlock.ID);
		registerBlock(playerJar, new ItemBlockPlayerJar(playerJar), playerJar.ID);
		registerBlock(crystalLight, crystalLight.ID);
		registerBlock(fireCrystall, fireCrystall.ID);
		
		Utilities.addConsoleText("All blocks are registered.");
	}

	//-----------------------------------------------Pre-Init:----------------------------------------------
	public void preInit(){
		registerBlocks();
	}

	//-----------------------------------------------Init:--------------------------------------------------
	public void init(){
		
	}

	//-----------------------------------------------Post-Init:---------------------------------------------
	public void postInit(){
		
	}
	
}
