package de.comeight.crystallogy.tileEntitys.machines;

import de.comeight.crystallogy.blocks.machines.Compressor;
import de.comeight.crystallogy.handler.CompressorRecipeHandler;
import de.comeight.crystallogy.handler.SoundHandler;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

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
	
	@Override
	public int getSoundIntervall() {
		return 40;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void setBlockState(){
		Compressor.setBlockState(crafting, worldObj, pos);
	}
	
	@Override
	public void playSound(World worldIn) {
		worldIn.playSound(null, pos.getX(), pos.getY() + 2, pos.getZ(), SoundHandler.COMPRESSOR, SoundCategory.BLOCKS, Utilities.getRandFloat(0.5F, 1.0F), Utilities.getRandFloat(0.2F, 1.0F));
	}

}
