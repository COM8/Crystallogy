package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class VaporizerDirection extends Vaporizer {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "vaporizerDirection";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public VaporizerDirection() {
		super(ID);
		setMaxDamage(200);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void spawnParticles(Vec3d coords, ItemStack stack, World worldIn, EntityPlayer playerIn) { //TODO Fix Fire Animation
		double x = coords.xCoord;
		double y = coords.yCoord + 1.1;
		double z = coords.zCoord;
		Float f = playerIn.getRotationYawHead() / 360;
		int i = (int) ((double)f);
		f -= i;
		if(f < 0){
			f *= -1;
		}
		double moveX = 0.35 * f;
		double moveZ = 0.35 * f;
		for (int j = 0; j < 5; j++) {
			double r = Utilities.getRandDouble(-0.05, 0.05);
			if(f < 0.25){
				worldIn.spawnParticle(EnumParticleTypes.FLAME, x - 0.35, y, z + 0.75, moveX + r, 0.0D + r, moveZ + r, new int[0]);
			}
			else if(f < 0.5){
				worldIn.spawnParticle(EnumParticleTypes.FLAME, x - 0.75, y, z - 0.35, -moveX + r, 0.0D + r, moveZ + r, new int[0]);	
			}
			else if(f < 0.75){
				worldIn.spawnParticle(EnumParticleTypes.FLAME, x + 0.35, y, z -0.75, moveX + r, 0.0D + r, -moveZ + r, new int[0]);
			}
			else{
				worldIn.spawnParticle(EnumParticleTypes.FLAME, x + 0.75, y, z + 0.35, moveX + r, 0.0D + r, moveZ + r, new int[0]);
			}	
		}
		if(stack.getItemDamage() < 200){
			stack.setItemDamage(stack.getItemDamage() + 1);
		}
	}
	
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(stack.getItemDamage() < 200){
			stack.setItemDamage(stack.getItemDamage() + 1);
			return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		}
		return EnumActionResult.FAIL;
	}
}
