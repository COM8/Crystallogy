package de.comeight.crystallogy.handler;

import de.comeight.crystallogy.blocks.CrystallCrusher;
import de.comeight.crystallogy.blocks.Crystall_blue;
import de.comeight.crystallogy.blocks.Crystall_green;
import de.comeight.crystallogy.blocks.Crystall_red;
import de.comeight.crystallogy.blocks.Crystall_yellow;
import de.comeight.crystallogy.blocks.InfuserBlock;
import de.comeight.crystallogy.blocks.PlayerJar;
import net.minecraftforge.fml.common.registry.GameRegistry;

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

	//-----------------------------------------------Constructor:-------------------------------------------
	public BlockHandler() {
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void registerBlocks() {
		crystall_blue = new Crystall_blue();
		crystall_green = new Crystall_green();
		crystall_red = new Crystall_red();
		crystall_yellow = new Crystall_yellow();
		crystallCrusher = new CrystallCrusher();
		infuserBlock = new InfuserBlock();
		playerJar = new PlayerJar();
		
		GameRegistry.registerBlock(crystall_blue, crystall_blue.ID);
		GameRegistry.registerBlock(crystall_green, crystall_green.ID);
		GameRegistry.registerBlock(crystall_red, crystall_red.ID);
		GameRegistry.registerBlock(crystall_yellow, crystall_yellow.ID);
		GameRegistry.registerBlock(crystallCrusher, crystallCrusher.ID);
		GameRegistry.registerBlock(infuserBlock, infuserBlock.ID);
		GameRegistry.registerBlock(playerJar, playerJar.ID);
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
