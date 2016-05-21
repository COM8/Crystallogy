package de.comeight.crystallogy.blocks;

import de.comeight.crystallogy.tileEntitys.TileEntityCrystallLight;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CrystalLight extends BaseBlockCutout {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public final static String ID = "crystalLight";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystalLight() {
		super(Material.CARPET, ID);
		this.lightOpacity = 0;
		setLightLevel(1.0F);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		entityIn.setFire(2);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.4, 0.4, 0.4, 0.6, 0.6, 0.6);
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return NULL_AABB;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityCrystallLight();
	}
	
}
