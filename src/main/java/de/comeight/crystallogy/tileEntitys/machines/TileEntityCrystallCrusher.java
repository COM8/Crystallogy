package de.comeight.crystallogy.tileEntitys.machines;

import de.comeight.crystallogy.blocks.machines.CrystallCrusher;
import de.comeight.crystallogy.handler.CrystalCrusherRecipeHandler;

public class TileEntityCrystallCrusher extends BaseTileEntityMachine {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "tileEntityCrystallCrusher";
    
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityCrystallCrusher() {
		super(CrystalCrusherRecipeHandler.INSTANCE);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public String getName()
    {
		return "de.comeight.crystallogy.tileEntityCrystallCrusher";
    }
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void setBlockState(){
		CrystallCrusher.setBlockState(crafting, worldObj, pos);
	}

}
