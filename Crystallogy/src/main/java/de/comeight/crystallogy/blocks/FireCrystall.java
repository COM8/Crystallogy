package de.comeight.crystallogy.blocks;

import java.util.Random;

import de.comeight.crystallogy.util.RGBColor;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireCrystall extends Crystall {

	//-----------------------------------------------Variabeln:---------------------------------------------
	public final static String ID = "fireCrystall";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public FireCrystall() {
		super(ID);
		setParticleColor(new RGBColor(1.0F, 0.1F, 0.1F));
		setTickRandomly(true);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		entityIn.setFire(2);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(worldIn.isRemote){
			spawnParticles(worldIn, pos);
		}
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
	}
	
	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
		super.randomTick(worldIn, pos, state, rand);
	}
	
	@Override
	public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
		if(worldIn.isRemote){
			spawnParticles(worldIn, pos);
		}
		super.randomDisplayTick(state, worldIn, pos, rand);
	}
	
	private void spawnParticles(World worldIn, BlockPos pos){
		if(Utilities.getRandInt(0, 10) == 0){
			worldIn.spawnParticle(EnumParticleTypes.LAVA, pos.getX() + Utilities.getRandDouble(0.3, 0.7), pos.getY() + Utilities.getRandDouble(0.0, 0.7), pos.getZ() +Utilities.getRandDouble(0.25, 0.9), 0.0D, 0.0D, 0.0D, new int[0]);
		}
		if(Utilities.getRandInt(0, 3) == 0){
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX() + Utilities.getRandDouble(0.3, 0.7), pos.getY() + Utilities.getRandDouble(0.0, 0.7), pos.getZ() +Utilities.getRandDouble(0.25, 0.9), 0.0D, 0.0D, 0.0D, new int[0]);
		}
		if(Utilities.getRandInt(0, 3) == 0){
			worldIn.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + Utilities.getRandDouble(0.3, 0.7), pos.getY() + Utilities.getRandDouble(0.0, 0.7), pos.getZ() +Utilities.getRandDouble(0.25, 0.9), 0.0D, 0.0D, 0.0D, new int[0]);
		}
	}
	
}
