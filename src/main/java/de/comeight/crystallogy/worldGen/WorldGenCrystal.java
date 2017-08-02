package de.comeight.crystallogy.worldGen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WorldGenCrystal extends BaseWorldGen {
    //-----------------------------------------------Attributes:--------------------------------------------
    private WorldGenCrystalCave crystalCave;

    //-----------------------------------------------Constructor:-------------------------------------------
    public WorldGenCrystal(Block block, int chancesToSpawn, int maxSize, int minHeight, int maxHeight) {
        super(block, chancesToSpawn, maxSize, minHeight, maxHeight);
        crystalCave = new WorldGenCrystalCave(maxSize);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------

    @Override
    protected void runGenerator(Random random, World world, int chunkX, int chunkZ) {
        chunkX *= 16;
        chunkZ *= 16;
        if (MIN_SPAWN_HEIGHT < 0 || MAX_SPAWN_HEIGHT > 256 || MIN_SPAWN_HEIGHT > MAX_SPAWN_HEIGHT){
            throw new IllegalArgumentException("Illegal height arguments for WorldGenerator! MIN_SPAWN_HEIGHT=" + MIN_SPAWN_HEIGHT + ", MAX_SPAWN_HEIGHT=" + MAX_SPAWN_HEIGHT + ", BLOCK=" + BLOCK.toString());
        }
        if(random.nextInt(CHANCES_TO_SPAWN) != 0) {
            return;
        }
        int x = chunkX + 1 + random.nextInt(5);
        int z = chunkZ + 1 + random.nextInt(5);
        int height = world.getHeight(new BlockPos(x, 0, z)).getY() - 1;
        if(MAX_SPAWN_HEIGHT > height){
            if(MIN_SPAWN_HEIGHT >= height){
                return;
            }
        }
        else{
            height = MAX_SPAWN_HEIGHT;
        }
        int y = MIN_SPAWN_HEIGHT + random.nextInt(height - MIN_SPAWN_HEIGHT);
        crystalCave.generateCrystalCave(random, new BlockPos(x, y, z), world);
    }


    //-----------------------------------------------Events:------------------------------------------------

}