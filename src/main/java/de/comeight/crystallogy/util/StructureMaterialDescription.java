package de.comeight.crystallogy.util;

import net.minecraft.block.Block;

public class StructureMaterialDescription {

	//-----------------------------------------------Variabeln:---------------------------------------------
	public int type;
	public Block block;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public StructureMaterialDescription() {
	}
	
	public StructureMaterialDescription(int type, Block block) {
		this.type = type;
		this.block = block;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public String toString() {
		return "(" + type + "," + block + ")";
	}
}
