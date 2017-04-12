package de.comeight.crystallogy.worldGenerators;

import java.util.Random;

import com.google.common.base.Predicate;

import de.comeight.crystallogy.blocks.Crystall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCrystal implements IWorldGenerator{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private final Crystall CRYSTAL;
	private final int MAX_SPOTS;
	private final int MAX_SPAWN_HEIGHT;
	private final int MIN_SPAWN_HEIGHT;
	private final int MAX_SPAWN_SIZE;
	
	private final Predicate<IBlockState> predicate;

	//-----------------------------------------------Constructor:-------------------------------------------
	public WorldGenCrystal(Crystall crystal, int chancesToSpawn, int maxSpawnSize, int minSpawnHeight, int maxSpawnHeight) {
		this.CRYSTAL = crystal;
		this.MAX_SPOTS = chancesToSpawn;
		this.MAX_SPAWN_SIZE = maxSpawnSize;
		this.MAX_SPAWN_HEIGHT = maxSpawnHeight;
		this.MIN_SPAWN_HEIGHT = minSpawnHeight;
		
		this.predicate = BlockMatcher.forBlock(Blocks.STONE);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
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
	
	private void runGenerator(Random random, World world, int chunkX, int chunkZ) throws IllegalArgumentException{
		chunkX *= 16;
		chunkZ *= 16;
	    if (MIN_SPAWN_HEIGHT < 0 || MAX_SPAWN_HEIGHT > 256 || MIN_SPAWN_HEIGHT > MAX_SPAWN_HEIGHT){
	    	throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator! minHeight=" + MIN_SPAWN_HEIGHT + ", maxheight=" + MAX_SPAWN_HEIGHT + ", block=" + CRYSTAL.toString());
	    }
	    int x;
	    int y;
	    int z;
	    int height;
	    for (int i = 0; i < MAX_SPOTS; i ++) {
	        x = chunkX + random.nextInt(16);
	        z = chunkZ + random.nextInt(16);
	        height = world.getHeight(new BlockPos(x, 0, z)).getY() - 1;
	        if(MAX_SPAWN_HEIGHT > height){
	        	if(MIN_SPAWN_HEIGHT >= height){
	        		return;
	        	}
	        }
	        else{
	        	height = MAX_SPAWN_HEIGHT;
	        }
	        y = MIN_SPAWN_HEIGHT + random.nextInt(height - MIN_SPAWN_HEIGHT);
	        generateCrystalsAt(world, random, new BlockPos(x, y, z));
	    }
	}
	
	private void generateCrystalsAt(World worldIn, Random rand, BlockPos position){
		float f = rand.nextFloat() * (float)Math.PI;
        double d0 = (double)((float)(position.getX() + 8) + MathHelper.sin(f) * (float)MAX_SPAWN_SIZE / 8.0F);
        double d1 = (double)((float)(position.getX() + 8) - MathHelper.sin(f) * (float)MAX_SPAWN_SIZE / 8.0F);
        double d2 = (double)((float)(position.getZ() + 8) + MathHelper.cos(f) * (float)MAX_SPAWN_SIZE / 8.0F);
        double d3 = (double)((float)(position.getZ() + 8) - MathHelper.cos(f) * (float)MAX_SPAWN_SIZE / 8.0F);
        double d4 = (double)(position.getY() + rand.nextInt(3) - 2);
        double d5 = (double)(position.getY() + rand.nextInt(3) - 2);

        for (int i = 0; i < MAX_SPAWN_SIZE; ++i)
        {
            float f1 = (float)i / (float)MAX_SPAWN_SIZE;
            double d6 = d0 + (d1 - d0) * (double)f1;
            double d7 = d4 + (d5 - d4) * (double)f1;
            double d8 = d2 + (d3 - d2) * (double)f1;
            double d9 = rand.nextDouble() * (double)MAX_SPAWN_SIZE / 16.0D;
            double d10 = (double)(MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            double d11 = (double)(MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            int j = MathHelper.floor_double(d6 - d10 / 2.0D);
            int k = MathHelper.floor_double(d7 - d11 / 2.0D);
            int l = MathHelper.floor_double(d8 - d10 / 2.0D);
            int i1 = MathHelper.floor_double(d6 + d10 / 2.0D);
            int j1 = MathHelper.floor_double(d7 + d11 / 2.0D);
            int k1 = MathHelper.floor_double(d8 + d10 / 2.0D);

            for (int l1 = j; l1 <= i1; ++l1)
            {
                double d12 = ((double)l1 + 0.5D - d6) / (d10 / 2.0D);

                if (d12 * d12 < 1.0D)
                {
                    for (int i2 = k; i2 <= j1; ++i2)
                    {
                        double d13 = ((double)i2 + 0.5D - d7) / (d11 / 2.0D);

                        if (d12 * d12 + d13 * d13 < 1.0D)
                        {
                            for (int j2 = l; j2 <= k1; ++j2)
                            {
                                double d14 = ((double)j2 + 0.5D - d8) / (d10 / 2.0D);

                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D)
                                {
                                    BlockPos blockpos = new BlockPos(l1, i2, j2);

                                    IBlockState state = worldIn.getBlockState(blockpos);
                                    if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, predicate))
                                    {
                                    	worldIn.setBlockState(blockpos, CRYSTAL.getDefaultState().withProperty(Crystall.FACING, EnumFacing.random(rand)), 2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
	}

}
