package de.comeight.crystallogy.blocks;

import de.comeight.crystallogy.tileEntitys.TileEntityCrystallLight;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CrystallLight extends BaseBlockCutout {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public final static String ID = "crystalLlight";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystallLight() {
		super(Material.carpet, ID);
		this.lightOpacity = 0;
		setLightLevel(1.0F);
		Block s = Blocks.glowstone;
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
