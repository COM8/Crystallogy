package de.comeight.crystallogy.worldGen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class BaseWorldGen implements IWorldGenerator{
    //-----------------------------------------------Attributes:--------------------------------------------
    protected final Block BLOCK;
    protected final int CHANCES_TO_SPAWN;
    protected final int MAX_SPAWN_HEIGHT;
    protected final int MIN_SPAWN_HEIGHT;
    protected final int MAX_SPAWN_SIZE;
    protected final WorldGenMinable WGM;

    //-----------------------------------------------Constructor:-------------------------------------------
    public BaseWorldGen(Block block, int chancesToSpawn, int maxSize, int minHeight, int maxHeight) {
        this.BLOCK = block;
        this.CHANCES_TO_SPAWN = chancesToSpawn;
        this.MAX_SPAWN_HEIGHT = maxHeight;
        this.MIN_SPAWN_HEIGHT = minHeight;
        this.MAX_SPAWN_SIZE = maxSize;
        this.WGM = new WorldGenMinable(BLOCK.getDefaultState(), MAX_SPAWN_SIZE);
    }

    public BaseWorldGen(Block block, int chancesToSpawn, int maxSize, int minHeight, int maxHeight, WorldGenMinable wgm) {
        this.BLOCK = block;
        this.CHANCES_TO_SPAWN = chancesToSpawn;
        this.MAX_SPAWN_HEIGHT = maxHeight;
        this.MIN_SPAWN_HEIGHT = minHeight;
        this.MAX_SPAWN_SIZE = maxSize;
        this.WGM = wgm;
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
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

    protected void runGenerator(Random random, World world, int chunkX, int chunkZ) {
        chunkX *= 16;
        chunkZ *= 16;
        if (MIN_SPAWN_HEIGHT < 0 || MAX_SPAWN_HEIGHT > 256 || MIN_SPAWN_HEIGHT > MAX_SPAWN_HEIGHT){
            throw new IllegalArgumentException("Illegal height arguments for WorldGenerator! MIN_SPAWN_HEIGHT=" + MIN_SPAWN_HEIGHT + ", MAX_SPAWN_HEIGHT=" + MAX_SPAWN_HEIGHT + ", BLOCK=" + BLOCK.toString());
        }

        for (int i = 0; i < CHANCES_TO_SPAWN; i ++) {
            int x = chunkX + random.nextInt(16);
            int y = MIN_SPAWN_HEIGHT + random.nextInt(MAX_SPAWN_HEIGHT - MIN_SPAWN_HEIGHT);
            int z = chunkZ + random.nextInt(16);
            WGM.generate(world, random, new BlockPos(x, y, z));
        }
    }

    //-----------------------------------------------Events:------------------------------------------------

}