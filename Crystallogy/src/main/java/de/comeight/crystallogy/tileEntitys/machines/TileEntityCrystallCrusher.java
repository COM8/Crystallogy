package de.comeight.crystallogy.tileEntitys.machines;

import de.comeight.crystallogy.blocks.machines.CrystallCrusher;
import de.comeight.crystallogy.handler.CrystalCrusherRecipeHandler;
import de.comeight.crystallogy.handler.SoundHandler;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class TileEntityCrystallCrusher extends BaseTileEntityMachine {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "tileEntityCrystallCrusher";
    
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityCrystallCrusher() {
		super(CrystalCrusherRecipeHandler.INSTANCE, 1, 1);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public String getName()
    {
		return "de.comeight.crystallogy.tileEntityCrystallCrusher";
    }
	
	@Override
	public int getSoundIntervall() {
		return 40;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void setBlockState(){
		CrystallCrusher.setBlockState(crafting, worldObj, pos);
	}
	
	@Override
	public void playSound(World worldIn) {
		worldIn.playSound((EntityPlayer)null, pos.getX(), pos.getY(), pos.getZ(), SoundHandler.CRUSHER, SoundCategory.BLOCKS, Utilities.getRandFloat(0.5F, 1.0F), Utilities.getRandFloat(0.2F, 1.0F));
	}

}
