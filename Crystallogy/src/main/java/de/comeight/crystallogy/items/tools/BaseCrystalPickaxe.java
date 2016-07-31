package de.comeight.crystallogy.items.tools;

import java.util.List;

import de.comeight.crystallogy.util.ToolUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BaseCrystalPickaxe extends BaseItemPickaxe {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseCrystalPickaxe(ToolMaterial material, String id) {
		super(material, id);
		setMaxDamage(getMaxDamage() * 4);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public int getAOE(){
		return 3;
	}
	
	protected EnumFacing getDirectionPlayerIsFacing(World worldIn, EntityPlayer player){
		RayTraceResult rTR = rayTrace(worldIn, player, false);
		return rTR.sideHit.getOpposite();
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if(!entityLiving.isSneaking()){
			breakBlocksArround(stack, worldIn, pos, entityLiving);
		}
		else{
			if (state.getBlockHardness(worldIn, pos) > 0.0F)
	        {
	            stack.damageItem(1, entityLiving);
	        }
		}
		return true;
	}
	
	protected void breakBlocksArround(ItemStack stack, World worldIn, BlockPos pos, EntityLivingBase entityLiving){
		EnumFacing direction = getDirectionPlayerIsFacing(worldIn, (EntityPlayer) entityLiving);
		int aOE = getAOE();
		int aOEHalf = (int) Math.round(((double) aOE)/2);
		switch (direction) {
			case DOWN:
				pos = pos.add(0, -aOEHalf, 0);
				break;
			case UP:
				pos = pos.add(0, aOEHalf, 0);
				break;
				
			case WEST:
				pos = pos.add(-aOEHalf, aOEHalf, 0);
				break;
				
			case EAST:
				pos = pos.add(aOEHalf, aOEHalf, 0);
				break;
				
			case NORTH:
				pos = pos.add(0, aOEHalf, -aOEHalf);
				break;
				
			case SOUTH:
				pos = pos.add(0, aOEHalf, aOEHalf);
				break;

			default:
				break;
		}
		
		for (int i = -aOE; i < aOE; i++) {
			for (int j = -aOE; j < aOE; j++) {
				for (int j2 = -aOE; j2 < aOE; j2++) {
					tryMineBlock(worldIn, pos.add(i, j, j2), stack, entityLiving);
				}
			}
		}
	}
	
	protected void tryMineBlock(World worldIn, BlockPos pos, ItemStack stack, EntityLivingBase entityLiving){
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		if(stack == null || stack.getItemDamage() >= stack.getMaxDamage()){
			stack.damageItem(1, entityLiving);
			return;
		}
		if(state.getBlock().getHarvestLevel(state) <= getHarvestLevel(stack, "pickaxe") && canHarvestBlock(state)){
			if(block.getMaterial(state) == Material.AIR){
				return;
			}
			
			ToolUtils.mineBlock(worldIn, worldIn.getBlockState(pos), pos);
			
			if (state.getBlockHardness(worldIn, pos) > 0.0F)
	        {
				stack.damageItem(1, entityLiving);
	        }
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		int aoe = getAOE();
		tooltip.add("Mines a " + TextFormatting.DARK_PURPLE + aoe + "x" + aoe + "x" + aoe + TextFormatting.RESET + " area.");
		tooltip.add("");
		tooltip.add("Hold " + TextFormatting.GOLD + TextFormatting.BOLD + "SHIFT" + TextFormatting.RESET + " to mine a " + TextFormatting.DARK_PURPLE + "1x1 " + TextFormatting.RESET + "area!");
		
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
}
