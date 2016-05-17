package de.comeight.crystallogy.blocks.machines;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.gui.GuiCompressor;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCompressor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Compressor extends BaseMachine {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "compressor";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public Compressor() {
		super(ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote)
        {	
			playerIn.openGui(CrystallogyBase.INSTANCE, GuiCompressor.ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCompressor();
	}
    
}
