package de.comeight.crystallogy.items.threatDusts;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FireDust extends ThreatDust {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static String ID = "fireDust";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public FireDust() {
		super(0, 0.0F, false, 1, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		if(!worldIn.isRemote){
			player.setFire(10);
		}
	}
	
	@Override
	public void castOnEntity(World worldIn, EntityLivingBase entity) {
		if(!worldIn.isRemote){
			entity.setFire(7);
		}
	}
	
}
