package de.comeight.crystallogy.items.threatDusts;

import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnderDust extends ThreatDust {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static String ID = "enderDust";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public EnderDust() {
		super(0, 0.0F, false, 5, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		if(!worldIn.isRemote){
			double x = Utilities.getRandDouble(-5.0, 5.0);
			double y = player.getPosition().getY();
			double z = Utilities.getRandDouble(-5.0, 5.0);
			player.moveEntity(x, y, z);
		}
	}
	
	@Override
	public void castOnEntity(World worldIn, EntityLivingBase entity) {
		if(!worldIn.isRemote){
			double x = Utilities.getRandDouble(-5.0, 5.0);
			double y = entity.getPosition().getY();
			double z = Utilities.getRandDouble(-5.0, 5.0);
			if(entity instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) entity;
				player.moveEntity(x, y, z);
			}
			else{
				entity.moveEntity(x, y, z);
			}
		}
	}
	
}
