package de.comeight.crystallogy.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ToolUtils {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public static void mineBlock(World worldIn, IBlockState state, BlockPos pos){
		worldIn.destroyBlock(pos, true);
		state.getBlock().onBlockDestroyedByPlayer(worldIn, pos, state);
	}
	
}
