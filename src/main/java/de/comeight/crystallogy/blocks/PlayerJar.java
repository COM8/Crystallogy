package de.comeight.crystallogy.blocks;

import de.comeight.crystallogy.materials.Material;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerJar extends BaseBlockTileEntity {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "playerJar";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public PlayerJar() {
		super(Material.glass, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.685, 0.75);
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityPlayerJar(world);
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
}
