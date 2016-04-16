package de.comeight.crystallogy.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class DamDust extends ThreatDust {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static String ID = "damDust";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public DamDust() {
		super(0, 0.0F, false, 21, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		if(!worldIn.isRemote){
			player.attackEntityFrom(DamageSource.magic, 10.5F);
		}
	}
	
	@Override
	public void castOnPlayer(ItemStack stack, World worldIn, EntityPlayer player) {
		if(!worldIn.isRemote){
			player.attackEntityFrom(DamageSource.magic, 0.5F);
		}
	}
	
}
