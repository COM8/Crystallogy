package de.comeight.crystallogy.worldGenerators;

import java.util.Random;

import de.comeight.crystallogy.blocks.Crystorya;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCrystorya extends WorldGenerator {
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------
	public WorldGenCrystorya() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos) {
		if (worldIn.isAirBlock(pos) && BlockHandler.crystorya.canPlaceBlockAt(worldIn, pos) && pos.getY() > 0)
        {
        	worldIn.setBlockState(pos, BlockHandler.crystorya.getDefaultState(), 3);
        	return true;
        }
		return false;
	}
	
}
