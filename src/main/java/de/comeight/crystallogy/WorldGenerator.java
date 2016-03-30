package de.comeight.crystallogy;

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
	private Block block;
	private int chancesToSpawn;
	private int maxHeight;
	private int minHeight;
	private int maxSize;

	//-----------------------------------------------Constructor:-------------------------------------------
	public WorldGenerator(Block block, int chancesToSpawn, int maxSize, int minHeight, int maxHeight) {
		this.block = block;
		this.chancesToSpawn = chancesToSpawn;
		this.maxHeight = maxHeight;
		this.minHeight = minHeight;
		this.maxSize = maxSize;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void runGenerator(Random random, World world, int chunkX, int chunkZ) {
		chunkX *= 16;
		chunkZ *= 16;
	    if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
	        throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

	    for (int i = 0; i < chancesToSpawn; i ++) {
	        int x = chunkX + random.nextInt(16);
	        int y = Utilities.getRandInt(minHeight, maxHeight);
	        int z = chunkZ + random.nextInt(16);
	        WorldGenMinable wgM = new WorldGenMinable(block.getDefaultState(), maxSize);
	        wgM.generate(world, random, new BlockPos(x, y, z));
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
