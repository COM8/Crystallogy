package de.comeight.crystallogy.blocks.machines;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.handler.GuiHandler;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityDissectingTable;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DissectingTable extends BaseMachine{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public final static String ID = "dissectingTable";

	//-----------------------------------------------Constructor:-------------------------------------------
	public DissectingTable(){
		super(ID);
		this.setHarvestLevel("axe", 0);
		this.setHardness(5.0F);
		this.setSoundType(SoundType.WOOD);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
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
			playerIn.openGui(CrystallogyBase.INSTANCE, GuiHandler.DISSECTING_TABLE_ID, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }
		return true;
	}
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityDissectingTable();
    }
	
}
