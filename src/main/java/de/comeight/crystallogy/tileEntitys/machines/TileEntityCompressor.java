package de.comeight.crystallogy.tileEntitys.machines;

import de.comeight.crystallogy.blocks.machines.Compressor;
import de.comeight.crystallogy.handler.CompressorRecipeHandler;

public class TileEntityCompressor extends BaseTileEntityMachine {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "tileEntityCompressor";
    
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityCompressor() {
		super(CompressorRecipeHandler.INSTANCE, 1, 1);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public String getName()
    {
		return "de.comeight.crystallogy.tileEntityCompressor";
    }
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void setBlockState(){
		Compressor.setBlockState(crafting, worldObj, pos);
	}

}
