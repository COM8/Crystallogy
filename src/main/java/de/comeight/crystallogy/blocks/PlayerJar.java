package de.comeight.crystallogy.blocks;

import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PlayerJar extends BaseBlockEntityJar {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "playerJar";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public PlayerJar() {
		super(ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityPlayerJar();
	}
	
}
