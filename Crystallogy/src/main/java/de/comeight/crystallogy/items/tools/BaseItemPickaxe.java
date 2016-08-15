package de.comeight.crystallogy.items.tools;

import java.util.ArrayList;

import de.comeight.crystallogy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class BaseItemPickaxe extends ItemPickaxe {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseItemPickaxe(ToolMaterial material, String ID) {
		super(material);
		setCreativeTab(CommonProxy.crystallogyMainTab);
		setUnlocalizedName(ID);
		setRegistryName(ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	protected void canHarvestBlock(ItemStack stack, World worldIn, BlockPos pos, ArrayList<BlockPos> list){
		IBlockState state = worldIn.getBlockState(pos);
		if(state.getBlockHardness(worldIn, pos) >= 0 && state.getBlock().getHarvestLevel(state) <= getHarvestLevel(stack, "pickaxe") && canHarvestBlock(state)){
			list.add(pos);
		}
	}
	
	protected void breakBlocksHammer(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving){
		Block block = state.getBlock();
		if(state.getMaterial() == Material.AIR){
			return;
		}
		RayTraceResult rTR = rayTrace(worldIn, (EntityPlayer) entityLiving, false);
		ArrayList<BlockPos> list = calcAOE(stack, rTR, worldIn, pos);
		for(BlockPos p : list){
			mineBlock(worldIn, worldIn.getBlockState(p), p);
		}
		
		if ((double)state.getBlockHardness(worldIn, pos) != 0.0D)
        {
            stack.damageItem(list.size() + 1, entityLiving);
        }
	}
	
	protected void breakBlocksPickaxe(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving){
		
	}
	
	protected void mineBlock(World worldIn, IBlockState state, BlockPos pos){
		worldIn.destroyBlock(pos, true);
		state.getBlock().onBlockDestroyedByPlayer(worldIn, pos, state);
	}
	
	protected ArrayList<BlockPos> calcAOE(ItemStack stack, RayTraceResult rTR, World worldIn, BlockPos pos){
		if(rTR == null){
			return new ArrayList<BlockPos>();
		}
		
		int refHarvestLevel = worldIn.getBlockState(pos).getBlock().getHarvestLevel(worldIn.getBlockState(pos));
		ArrayList<BlockPos> list = new ArrayList<BlockPos>();
		
		if(rTR.sideHit == EnumFacing.EAST || rTR.sideHit == EnumFacing.WEST){
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 1), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() - 1), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() + 1), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() - 1), list);
		}
		else if(rTR.sideHit == EnumFacing.SOUTH || rTR.sideHit == EnumFacing.NORTH){
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ()), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ()), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ()), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ()), list);
		}
		else if(rTR.sideHit == EnumFacing.UP || rTR.sideHit == EnumFacing.DOWN){
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1), list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1), list);
		}
		return list;
	}
	
}
