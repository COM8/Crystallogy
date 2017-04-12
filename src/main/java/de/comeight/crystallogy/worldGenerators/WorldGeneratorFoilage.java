package de.comeight.crystallogy.worldGenerators;

import java.util.Random;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.TempCategory;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGeneratorFoilage implements IWorldGenerator{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private Block block;
	private int chancesToSpawn;
	private WorldGenCrystorya wgC;

	//-----------------------------------------------Constructor:-------------------------------------------
	public WorldGeneratorFoilage(int chancesToSpawn) {
		this.chancesToSpawn = chancesToSpawn;
		this.wgC = new WorldGenCrystorya();
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void runGenerator(Random random, World world, int chunkX, int chunkZ) {
		if(Utilities.getRandInt(0, 1) != 0){
			return;
		}
		chunkX *= 16;
		chunkZ *= 16;
		Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);
	    for (int i = 0; i < chancesToSpawn; i ++) {
	        int x = chunkX + random.nextInt(16);
	        int z = chunkZ + random.nextInt(16);
	        BlockPos pos = world.getHeight(new BlockPos(x, 0, z));
	        
	        TempCategory temp = chunk.getBiome(pos, world.getBiomeProvider()).getTempCategory();
	        if(temp != TempCategory.COLD && temp != TempCategory.WARM){
	        	wgC.generate(world, random, pos);
	        }
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
