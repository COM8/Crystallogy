package de.comeight.crystallogy.blocks.machines;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.handler.GuiHandler;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCrystallCrusher;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CrystallCrusher extends BaseMachine{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public final static String ID = "crystallCrusher";

	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystallCrusher(){
		super(ID);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean isFullCube(IBlockState state)
    {
        return false;
    }

	@SideOnly(Side.CLIENT)
	@Override
	public boolean isFullyOpaque(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote)
        {	
			playerIn.openGui(CrystallogyBase.INSTANCE, GuiHandler.CRYSTAL_CRUSHER_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
		return true;
	}
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityCrystallCrusher();
    }
	
}
