package de.comeight.crystallogy.worldGenerators;

import java.util.Random;

import de.comeight.crystallogy.blocks.Crystall;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCaveCrystal implements IWorldGenerator{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private final Crystall CRYSTAL;
	private final int MAX_SPOTS;
	private final int MAX_TRYS;
	private final int MAX_SPAWN_HEIGHT;
	private final int MIN_SPAWN_HEIGHT;
	private final int MAX_SPAWN_SIZE;

	//-----------------------------------------------Constructor:-------------------------------------------
	public WorldGenCaveCrystal(Crystall crystal, int chancesToSpawn, int maxSpawnSize, int minSpawnHeight, int maxSpawnHeight) {
		this.CRYSTAL = crystal;
		this.MAX_SPOTS = chancesToSpawn;
		this.MAX_TRYS = MAX_SPOTS * 3;
		this.MAX_SPAWN_SIZE = maxSpawnSize;
		this.MAX_SPAWN_HEIGHT = maxSpawnHeight;
		this.MIN_SPAWN_HEIGHT = minSpawnHeight;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	private EnumFacing getCrystalRotation(World world, BlockPos pos){
		if(world.getBlockState(pos.add(0, 1, 0)).isSideSolid(world, pos, EnumFacing.DOWN)){
			return EnumFacing.DOWN;
		}
		else if(world.getBlockState(pos.add(0, -1, 0)).isSideSolid(world, pos, EnumFacing.UP)){
			return EnumFacing.UP;
		}
		else if(world.getBlockState(pos.add(1, 0, 0)).isSideSolid(world, pos, EnumFacing.WEST)){
			return EnumFacing.WEST;
		}
		else if(world.getBlockState(pos.add(-1, 0, 0)).isSideSolid(world, pos, EnumFacing.EAST)){
			return EnumFacing.EAST;
		}
		else if(world.getBlockState(pos.add(0, 0, 1)).isSideSolid(world, pos, EnumFacing.SOUTH)){
			return EnumFacing.NORTH;
		}
		else if(world.getBlockState(pos.add(0, 0, -1)).isSideSolid(world, pos, EnumFacing.NORTH)){
			return EnumFacing.SOUTH;
		}
		return null;
	}
	
	private BlockPos getPosFromFacing(EnumFacing facing, BlockPos pos){
		if(facing == EnumFacing.UP){
			return pos.up();
		}
		else if(facing == EnumFacing.DOWN){
			return pos.down();
		}
		else if(facing == EnumFacing.SOUTH){
			return pos.south();
		}
		else if(facing == EnumFacing.NORTH){
			return pos.north();
		}
		else if(facing == EnumFacing.EAST){
			return pos.east();
		}
		else if(facing == EnumFacing.WEST){
			return pos.west();
		}
		else{
			return pos;
		}
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void runGenerator(Random random, World world, int chunkX, int chunkZ) {
		chunkX *= 16;
		chunkZ *= 16;
	    if (MIN_SPAWN_HEIGHT < 0 || MAX_SPAWN_HEIGHT > 256 || MIN_SPAWN_HEIGHT > MAX_SPAWN_HEIGHT){
	    	throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator! minHeight=" + MIN_SPAWN_HEIGHT + ", maxheight=" + MAX_SPAWN_HEIGHT + ", block=" + CRYSTAL.toString());
	    }
	    
	    int spots = 0;
	    for (int i = 0; i < MAX_TRYS; i ++) {
	    	if(spots >= MAX_SPOTS){
	    		return;
	    	}
	    	
	        int x = chunkX + Utilities.getRandInt(0, 16, random);
	        int z = chunkZ + Utilities.getRandInt(0, 16, random);
	        int height =  world.getHeight(new BlockPos(x, 0, z)).getY() - 1;
	        if(MAX_SPAWN_HEIGHT > height){
	        	if(MIN_SPAWN_HEIGHT >= height){
	        		return;
	        	}
	        }
	        else{
	        	height = MAX_SPAWN_HEIGHT;
	        }
	        int y = Utilities.getRandInt(MIN_SPAWN_HEIGHT, height, random);
	        
	        BlockPos pos = new BlockPos(x, y, z);
	        if(canBePlacedAt(world, pos)){
	        	generateCrystalsAroundPos(random, world, pos, chunkX, chunkZ, height);
	        	spots++;
	        }
	    }
	}
	
	private void generateCrystalsAroundPos(Random random, World world, BlockPos pos, int chunkX, int chunkZ, int heightMax){
        int count = 0;
        int trys = 0;
        
        while (count < MAX_SPAWN_SIZE && trys < MAX_SPAWN_SIZE) {
			for(int facingIndex = 0; facingIndex < 6; facingIndex++){
				EnumFacing facing = EnumFacing.getFront(facingIndex);
				if(canBePlacedAt(world, getPosFromFacing(facing, pos))){
					placeCrytal(world, getPosFromFacing(facing, pos));
					count++;
					break;
				}		
			}
        	pos = getPosFromFacing(EnumFacing.getFront(Utilities.getRandInt(0, 6, random)), pos);
        	int newChunkX = pos.getX() / 16;
        	int newChunkZ = pos.getZ() / 16;
        	if(newChunkX > chunkX){
        		pos.add(-2, 0, 0);
        	}
        	else if(newChunkX < chunkX){
        		pos.add(2, 0, 0);
        	}
        	
        	if(newChunkZ < chunkZ){
        		pos.add(0, 0, -2);
        	}
        	else if(newChunkZ < chunkZ){
        		pos.add(0, 0, 2);
        	}
        	
        	if(pos.getY() <= 0){
        		pos.add(0, 2, 0);
        	}
        	else if(pos.getY() > heightMax){
        		pos.add(0, -2, 0);
        	}
        	trys++;
		}
	}
	
	private boolean canBePlacedAt(World world, BlockPos pos){
		EnumFacing facing = getCrystalRotation(world, pos);
		IBlockState state = world.getBlockState(pos);
		if(state.getBlock() == Blocks.AIR && facing != null){
			BlockPos newPos = getPosFromFacing(facing.getOpposite(), pos);
			if(state.getBlock().isReplaceableOreGen(world.getBlockState(newPos), world, newPos, BlockMatcher.forBlock(Blocks.STONE))){
				return true;
			}
		}
		return false;
	}
	
	private void placeCrytal(World world, BlockPos pos){
		world.setBlockState(pos, CRYSTAL.getDefaultState().withProperty(Crystall.FACING, getCrystalRotation(world, pos)), 2);
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
