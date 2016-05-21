package de.comeight.crystallogy.items.tools;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class BaseItemHammer extends BaseItemPickaxe {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseItemHammer(ToolMaterial material, String ID) {
		super(material, ID);
		
		setMaxDamage(getMaxDamage() * 5);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos, EntityLivingBase entityLiving) {
		if(!entityLiving.isSneaking()){
			breakBlocksArround(stack, worldIn, blockIn, pos, entityLiving);
		}
		else{
			if ((double)blockIn.getBlockHardness(worldIn, pos) != 0.0D)
	        {
	            stack.damageItem(1, entityLiving);
	        }
		}
		return true;
	}
	
	protected void breakBlocksArround(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving){
		Block block = state.getBlock();
		if(block.getMaterial(state) == Material.AIR){
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
	
	protected void mineBlock(World worldIn, IBlockState state, BlockPos pos){
		worldIn.destroyBlock(pos, true);
		state.getBlock().onBlockDestroyedByPlayer(worldIn, pos, state);
	}
	
	protected ArrayList<BlockPos> calcAOE(ItemStack stack, RayTraceResult rTR, World worldIn, BlockPos pos){
		int refHarvestLevel = worldIn.getBlockState(pos).getBlock().getHarvestLevel(worldIn.getBlockState(pos));
		ArrayList<BlockPos> list = new ArrayList<BlockPos>();
		
		if(rTR.sideHit == EnumFacing.EAST || rTR.sideHit == EnumFacing.WEST){
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 1), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() - 1), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() + 1), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() - 1), refHarvestLevel, list);
		}
		else if(rTR.sideHit == EnumFacing.SOUTH || rTR.sideHit == EnumFacing.NORTH){
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ()), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ()), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ()), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ()), refHarvestLevel, list);
		}
		else if(rTR.sideHit == EnumFacing.UP || rTR.sideHit == EnumFacing.DOWN){
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1), refHarvestLevel, list);
			canHarvestBlock(stack, worldIn, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1), refHarvestLevel, list);
		}
		return list;
	}
	
	protected void canHarvestBlock(ItemStack stack, World worldIn, BlockPos pos, int refHarvestLevel, ArrayList<BlockPos> list){
		IBlockState state = worldIn.getBlockState(pos);
		if(state.getBlock().getHarvestLevel(state) <= getHarvestLevel(stack, "pickaxe") && canHarvestBlock(state)){
			list.add(pos);
		}
	}
	
}
