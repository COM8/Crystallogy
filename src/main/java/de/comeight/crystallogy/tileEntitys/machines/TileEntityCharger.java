package de.comeight.crystallogy.tileEntitys.machines;

import de.comeight.crystallogy.blocks.machines.Charger;
import de.comeight.crystallogy.handler.ChargerRecipeHandler;

public class TileEntityCharger extends BaseTileEntityMachine {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "tileEntityCharger";
    
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityCharger() {
		super(ChargerRecipeHandler.INSTANCE, 2, 1);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public String getName()
    {
		return "de.comeight.crystallogy.tileEntityCharger";
    }
	
	public void setBlockState(){
		Charger.setBlockState(crafting, worldObj, pos);
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

}
