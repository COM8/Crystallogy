package de.comeight.crystallogy.items.threatDusts;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class DrowDust extends ThreatDust {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static String ID = "drowDust";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public DrowDust() {
		super(0, 0.0F, false, 11, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		if(!worldIn.isRemote){
			player.attackEntityFrom(DamageSource.drown, 11.0F);
		}
	}
	
	@Override
	public void castOnEntity(World worldIn, EntityLivingBase entity, int tick) {
		if(!worldIn.isRemote){
			entity.attackEntityFrom(DamageSource.drown, 1.0F);
		}
	}
	
}
