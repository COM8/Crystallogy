package de.comeight.crystallogy.blocks;

import java.util.Random;

import de.comeight.crystallogy.CommonProxy;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BaseBlockGlass extends BlockGlass {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseBlockGlass(Material material, String id){
		super(Material.GLASS, false);
		
		setUnlocalizedName(id);
		setRegistryName(id);
		setCreativeTab(CommonProxy.crystallogyMainTab);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@SideOnly(Side.CLIENT)
	@Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }
    
    @Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
		if(willHarvest){
			return true;
		}
		return super.removedByPlayer(state, world, pos, player, willHarvest);
	}
	
	@Override
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
		super.harvestBlock(worldIn, player, pos, state, te, stack);
		worldIn.setBlockToAir(pos);
	}
	
}
