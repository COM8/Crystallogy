package de.comeight.crystallogy.util;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StructureAreaDescription {

	//-----------------------------------------------Variabeln:---------------------------------------------
	private int[][] area;
	private ArrayList<StructureMaterialDescription> areaDescription;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public StructureAreaDescription() {
		this.area = new int[5][5];
		this.areaDescription = new ArrayList<StructureMaterialDescription>();
	}
	
	public StructureAreaDescription(int[][] area) {
		this.area = area;
		this.areaDescription = new ArrayList<StructureMaterialDescription>();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public Block getBlockAtPos(int x, int y){
		return getBlockFromType(getTypeAtPos(x, y));
	}
	
	public int getTypeAtPos(int x, int y){
		return area[x][y];
	}
	
	public Block getBlockFromType(int type){
		if(areaDescription != null){
			for(int i = 0; i < areaDescription.size(); i++){
				if(areaDescription.get(i).type == type){
					return areaDescription.get(i).block;
				}
			}
		}
		return null;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString() {
		String s = "{";
		for (int i = 0; i < areaDescription.size(); i++) {
			s += areaDescription.get(i).toString() + ",";
		}
		s += "}";
		return s;
	}
	
	public void addBlockType(int type, Block block){
		areaDescription.add(new StructureMaterialDescription(type, block));
	}
	
	public boolean testForStructure(World worldIn, BlockPos pos, int startX, int startZ){
		BlockPos startPos = new BlockPos(pos.getX() - startX, pos.getY(), pos.getZ() - startZ);
		
		for(int i = 0; i < area.length; i++){
			for(int e = 0; e < area[i].length; e++){
				if(area[i][e] != 0){
					IBlockState bS = worldIn.getBlockState(new BlockPos(startPos.getX() + i, startPos.getY(), startPos.getZ() + e));
					if(!(bS.getBlock() == getBlockFromType(area[i][e]))){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private int getTypeCount(int type){
		int count = 0;
		for(int i = 0; i < area.length; i++){
			for(int e = 0; e < area[i].length; e++){
				if(area[i][e] == type){
					count++;
				}
			}
		}
		return count;
	}
	
}
