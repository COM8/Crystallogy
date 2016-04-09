package de.comeight.crystallogy.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
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
	public void spawnParticles(Vec3d coords, ItemStack stack, World worldIn) {
		System.out.println(stack.getItemDamage());
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
