package de.comeight.crystallogy.itemBlocks;

import java.util.List;
import java.util.Random;

import de.comeight.crystallogy.blocks.CrystalOfHolding;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.util.EntityUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockCrystalOfHolding extends BaseItemBlock {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public ItemBlockCrystalOfHolding(Block block) {
		super(block, CrystalOfHolding.ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public boolean hasEntity(ItemStack stack){
		return true;
	}
	
	@Override
	public boolean hasEffect(ItemStack stack) {
		return EntityUtils.hasEntity(stack.getTagCompound());
	}
	
	public Entity getEntity(ItemStack stack, World worldIn){
		if(!stack.hasTagCompound()){
			return null;
		}
		NBTTagCompound compound = stack.getTagCompound();
		Entity ent = EntityUtils.readEntityFromCompound(compound, worldIn);
		
		stack.setTagCompound(null);
		return ent;
	}

	
	public void setEntity(ItemStack stack, Entity ent){
		NBTTagCompound compound = new NBTTagCompound();
		EntityUtils.writeEntityToCompound(compound, ent);
		stack.setTagCompound(compound);
	}
	
	private NBTTagCompound getEmptyCompound(){
		NBTTagCompound compound = new NBTTagCompound();
		compound.setBoolean("hasEntity", false);
		return compound;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(stack.hasTagCompound()){
			NBTTagCompound tag = stack.getTagCompound();
			if(tag.getBoolean("hasEntity")){
				
				tooltip.add("Has Entity: " + TextFormatting.DARK_GREEN + "Yes");
				tooltip.add(TextFormatting.GOLD + "Name: " + TextFormatting.RESET + tag.getString("id_entity"));
				tooltip.add("");
				tooltip.add("Reight click to plant on Sanstone.");
				tooltip.add("Shift right click to release entity.");
			}
			else{
				tooltip.add("Has Entity: " + TextFormatting.DARK_RED + "No");
				tooltip.add(TextFormatting.GOLD + "Name: " + TextFormatting.RESET + "-");
			}
		}
		else{
			tooltip.add("Has Entity: " + TextFormatting.DARK_RED + "No");
			tooltip.add(TextFormatting.GOLD + "Name: " + TextFormatting.RESET + "-");
		}
		
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(playerIn.isSneaking() && EntityUtils.hasEntity(stack.getTagCompound())){
			pos = pos.offset(facing);
			releaseEntity(stack, worldIn, new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5), playerIn);
			return EnumActionResult.SUCCESS;
		}
		else if(worldIn.getBlockState(pos.up()).getBlock().isReplaceable(worldIn, pos.up()) && BlockHandler.crystalOfHolding.canSustainCrystal(worldIn.getBlockState(pos))){
			return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		}
		else{
			return EnumActionResult.FAIL;
		}
	}
	
	public void releaseEntity(ItemStack stack, World worldIn, Vec3d pos, EntityPlayer playerIn){
		if(!hasEntity(stack)){
			return;
		}
		ItemStack ret = stack.copy();
		ret.stackSize = 1;
		stack.stackSize--;
		Entity ent = getEntity(ret, worldIn);
		if(!playerIn.capabilities.isCreativeMode){
			if (!playerIn.inventory.addItemStackToInventory(ret))
	        {
				playerIn.dropItem(ret, false);
	        }
		}
		
		if(ent != null && !worldIn.isRemote){
			ent.setPosition(pos.xCoord, pos.yCoord, pos.zCoord);
			ent.setUniqueId(MathHelper.getRandomUuid(new Random()));
			worldIn.spawnEntityInWorld(ent);
			
			if(ent instanceof EntityLiving) {
				((EntityLiving)ent).playLivingSound();
			}
		}
	}
	
}
