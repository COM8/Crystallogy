package de.comeight.crystallogy.items.threatDusts;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class HungDust extends ThreatDust {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static String ID = "hungDust";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public HungDust() {
		super(0, 0.0F, false, 1, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		if(!worldIn.isRemote){
			player.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 300, 3, true, true));
		}
	}
	
	@Override
	public void castOnEntity(World worldIn, EntityLivingBase entity) {
		if(!worldIn.isRemote){
			entity.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 300, 3, true, true));
		}
	}
	
}
