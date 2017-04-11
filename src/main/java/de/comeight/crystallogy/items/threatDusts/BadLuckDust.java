package de.comeight.crystallogy.items.threatDusts;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BadLuckDust extends ThreatDust {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static String ID = "badLuckDust";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BadLuckDust() {
		super(0, 0.0F, false, 1, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		if(!worldIn.isRemote){
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(27), 300, 3, false, true));
		}
	}
	
	@Override
	public void castOnEntity(World worldIn, EntityLivingBase entity, int tick) {
		if(!worldIn.isRemote){
			entity.addPotionEffect(new PotionEffect(Potion.getPotionById(27), 300, 3, false, true));
		}
	}
	
}
