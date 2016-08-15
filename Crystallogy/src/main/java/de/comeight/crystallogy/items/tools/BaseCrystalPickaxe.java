package de.comeight.crystallogy.items.tools;

import java.util.List;

import de.comeight.crystallogy.util.ToolUtils;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BaseCrystalPickaxe extends BaseItemPickaxe {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseCrystalPickaxe(ToolMaterial material, String id) {
		super(material, id);
		setMaxDamage(getMaxDamage() * 10);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public int getAOE(){
		return 3;
	}
	
	protected EnumFacing getDirectionPlayerIsFacing(World worldIn, EntityPlayer player){
		RayTraceResult rTR = rayTrace(worldIn, player, false);
		return rTR.sideHit.getOpposite();
	}
	
	private int getMode(ItemStack itemStackIn){
		if(!itemStackIn.hasTagCompound()){
			itemStackIn.setTagCompound(new NBTTagCompound());
		}
		
		NBTTagCompound compound = itemStackIn.getTagCompound();
		return compound.getInteger("mode");
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if(state.getBlock().isToolEffective("pickaxe", state)){
			breakBlocksArround(stack, worldIn, pos, entityLiving, getMode(stack));
		}
		else{
			if (state.getBlockHardness(worldIn, pos) > 0.0F)
	        {
	            stack.damageItem(1, entityLiving);
	        }
		}
		return true;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if(playerIn.isSneaking()){
			nextMode(itemStackIn);
			if(worldIn.isRemote){
				playerIn.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, Utilities.getRandFloat(0.5F, 1.5F));
				playerIn.addChatComponentMessage(new TextComponentString("New mode: " + TextFormatting.DARK_PURPLE + (getMode(itemStackIn) + 1)));
			}
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
	protected void nextMode(ItemStack itemStackIn){
		int mode = getMode(itemStackIn);
		mode++;
		if(mode > 2){
			mode = 0;
		}
		NBTTagCompound compound = itemStackIn.getTagCompound();
		compound.setInteger("mode", mode);
	}
	
	protected void breakBlocksArround(ItemStack stack, World worldIn, BlockPos pos, EntityLivingBase entityLiving, int mode){
		if(mode == 2){
			return;
		}
		
		EnumFacing direction = getDirectionPlayerIsFacing(worldIn, (EntityPlayer) entityLiving);
		int aOEX = getAOE();
		int aOEY = getAOE();
		int aOEZ = getAOE();
		int aOEHalf = (int) Math.round(((double) aOEX) / 2);
		switch (direction) {
			case DOWN:
				pos = pos.add(0, -aOEHalf, 0);
				if(mode == 1){
					tryMineBlocks(pos, worldIn, stack, entityLiving, -aOEX, aOEX, 2, 3, -aOEZ, aOEZ);
					return;
				}
				break;
			case UP:
				pos = pos.add(0, aOEHalf, 0);
				if(mode == 1){
					tryMineBlocks(pos, worldIn, stack, entityLiving, -aOEX, aOEX, -2, -1, -aOEZ, aOEZ);
					return;
				}
				break;
				
			case WEST:
				pos = pos.add(-aOEHalf, aOEHalf, 0);
				if(mode == 1){
					tryMineBlocks(pos, worldIn, stack, entityLiving, 2, 3, -aOEY, aOEY, -aOEZ, aOEZ);
					return;
				}
				break;
				
			case EAST:
				pos = pos.add(aOEHalf + 1, aOEHalf, 0);
				if(mode == 1){
					tryMineBlocks(pos, worldIn, stack, entityLiving, -3, -2, -aOEY, aOEY, -aOEZ, aOEZ);
					return;
				}
				break;
				
			case NORTH:
				pos = pos.add(0, aOEHalf, -aOEHalf);
				if(mode == 1){
					tryMineBlocks(pos, worldIn, stack, entityLiving, -aOEX, aOEX, -aOEY, aOEY, 2, 3);
					return;
				}
				break;
				
			case SOUTH:
				pos = pos.add(0, aOEHalf, aOEHalf + 1);
				if(mode == 1){
					tryMineBlocks(pos, worldIn, stack, entityLiving, -aOEX, aOEX, -aOEY, aOEY, -3, -2);
					return;
				}
				break;

			default:
				break;
		}
		tryMineBlocks(pos, worldIn, stack, entityLiving, -aOEX, aOEX, -aOEY, aOEY, -aOEZ, aOEZ);
	}
	
	protected void tryMineBlocks(BlockPos pos, World worldIn, ItemStack stack, EntityLivingBase entityLiving, int xMin, int xMax, int yMin, int yMax, int zMin, int zMax){
		for (int i = xMin; i < xMax; i++) {
			for (int j = yMin; j < yMax; j++) {
				for (int j2 = zMin; j2 < zMax; j2++) {
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
		if(state.getBlockHardness(worldIn, pos) >= 0 && state.getBlock().getHarvestLevel(state) <= getHarvestLevel(stack, "pickaxe") && canHarvestBlock(state)){
			if(state.getMaterial() == Material.AIR){
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
		int mode = getMode(stack);
		
		tooltip.add("Mode: " + TextFormatting.DARK_PURPLE + (mode + 1));
		
		switch (mode) {
			case 0:
				tooltip.add("Mines a " + TextFormatting.DARK_PURPLE + aoe * 2 + "x" + aoe * 2 + "x" + aoe * 2 + TextFormatting.RESET + TextFormatting.GRAY + " area.");
				break;
				
			case 1:
				tooltip.add("Mines a " + TextFormatting.DARK_PURPLE + aoe * 2 + "x" + aoe * 2 + "x1" + TextFormatting.RESET + TextFormatting.GRAY + " area.");
				break;

			default:
				tooltip.add("Mines a " + TextFormatting.DARK_PURPLE + "1x1x1" + TextFormatting.RESET + TextFormatting.GRAY + " area.");
				break;
		}
		
		tooltip.add("");
		tooltip.add("" + TextFormatting.GOLD + TextFormatting.BOLD + "SHIFT RIGHT-CLICK" + TextFormatting.RESET + TextFormatting.GRAY + " to change mode.");
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
}
