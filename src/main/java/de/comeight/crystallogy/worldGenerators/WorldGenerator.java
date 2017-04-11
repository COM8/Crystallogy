package de.comeight.crystallogy.worldGenerators;

import java.util.Random;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenerator implements IWorldGenerator{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private Block BLOCK;
	private final int CHANCES_TO_SPAWN;
	private final int MAX_SPAWN_HEIGHT;
	private final int MIN_SPAWN_HEIGHT;
	private final int MAX_SPAWN_SIZE;

	//-----------------------------------------------Constructor:-------------------------------------------
	public WorldGenerator(Block block, int chancesToSpawn, int maxSize, int minHeight, int maxHeight) {
		this.BLOCK = block;
		this.CHANCES_TO_SPAWN = chancesToSpawn;
		this.MAX_SPAWN_HEIGHT = maxHeight;
		this.MIN_SPAWN_HEIGHT = minHeight;
		this.MAX_SPAWN_SIZE = maxSize;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void runGenerator(Random random, World world, int chunkX, int chunkZ) {
		chunkX *= 16;
		chunkZ *= 16;
		if (MIN_SPAWN_HEIGHT < 0 || MAX_SPAWN_HEIGHT > 256 || MIN_SPAWN_HEIGHT > MAX_SPAWN_HEIGHT){
	    	throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator! minHeight=" + MIN_SPAWN_HEIGHT + ", maxheight=" + MAX_SPAWN_HEIGHT + ", block=" + BLOCK.toString());
	    }

	    for (int i = 0; i < CHANCES_TO_SPAWN; i ++) {
	    	int x = chunkX + Utilities.getRandInt(0, 16, random);
	        int y = Utilities.getRandInt(MIN_SPAWN_HEIGHT, MAX_SPAWN_HEIGHT, random);
	        int z = chunkZ + Utilities.getRandInt(0, 16, random);
	        BlockPos pos = new BlockPos(x, y, z);
	        
	        WorldGenMinable wgM = new WorldGenMinable(BLOCK.getDefaultState(), MAX_SPAWN_SIZE);
	        wgM.generate(world, random, pos);
	    }
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()){
			case -1 :	//Nether
				break;
			case 1 : 	//End
				break;
			default:
				runGenerator(random, world, chunkX, chunkZ);
				break;
			}
	}

}
