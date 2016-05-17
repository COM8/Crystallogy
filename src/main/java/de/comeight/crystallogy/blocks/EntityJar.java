package de.comeight.crystallogy.blocks;

import de.comeight.crystallogy.tileEntitys.TileEntityEntityJar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class EntityJar extends BaseBlockEntityJar {
	//-----------------------------------------------Variabeln:---------------------------------------------
		public static final String ID = "entityJar";
		
		//-----------------------------------------------Constructor:-------------------------------------------
		public EntityJar() {
			super(ID);
		}
		
		//-----------------------------------------------Set-, Get-Methoden:------------------------------------

		
		//-----------------------------------------------Sonstige Methoden:-------------------------------------
		@Override
		public TileEntity createTileEntity(World world, IBlockState state) {
			return new TileEntityEntityJar();
		}
	
}
