package de.comeight.crystallogy.structures;

import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.util.StructureAreaDescription;
import net.minecraft.util.math.BlockPos;

public class StructureInfuser {

	//-----------------------------------------------Variabeln:---------------------------------------------
	public StructureAreaDescription infuserArea; 
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public StructureInfuser() {
		setupInfuserArea();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	private void setupInfuserArea(){
		int[][] area = {
				{0,0,1,0,0},
				{0,0,0,0,0},
				{1,0,1,0,1},
				{0,0,0,0,0},
				{0,0,1,0,0}
		};
		
		infuserArea = new StructureAreaDescription(area);
		infuserArea.addBlockType(1, BlockHandler.infuserBlock);
	}
	
	public static BlockPos[] getSurroundingPedals(BlockPos pos){
		BlockPos[] poses = {
				new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ()),
				new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ()),
				new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 2),
				new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 2),
		};
		return poses;
	}
}
