package de.comeight.crystallogy.tileEntitys.machines;

import de.comeight.crystallogy.blocks.machines.Charger;
import de.comeight.crystallogy.handler.ChargerRecipeHandler;
import de.comeight.crystallogy.handler.SoundHandler;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

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
	
	@Override
	public int getSoundIntervall() {
		return 80;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void playSound(World worldIn) {
		worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundHandler.LIGHT_WOOSH, SoundCategory.BLOCKS, Utilities.getRandFloat(0.5F, 1.0F), Utilities.getRandFloat(0.2F, 1.0F));
	}
}
