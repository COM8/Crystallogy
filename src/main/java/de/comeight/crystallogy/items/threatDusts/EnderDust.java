package de.comeight.crystallogy.items.threatDusts;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
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
		
		teleportEntity(worldIn, player);
	}
	
	@Override
	public void castOnEntity(World worldIn, EntityLivingBase entity, int tick) {
		teleportEntity(worldIn, entity);
	}
	
	private void teleportEntity(World worldIn, EntityLivingBase entity){
		if (!worldIn.isRemote)
        {
            double d0 = entity.posX;
            double d1 = entity.posY;
            double d2 = entity.posZ;

            for (int i = 0; i < 16; ++i)
            {
                double d3 = entity.posX + (entity.getRNG().nextDouble() - 0.5D) * 16.0D;
                double d4 = MathHelper.clamp_double(entity.posY + (double)(entity.getRNG().nextInt(16) - 8), 0.0D, (double)(worldIn.getActualHeight() - 1));
                double d5 = entity.posZ + (entity.getRNG().nextDouble() - 0.5D) * 16.0D;
                if (entity.attemptTeleport(d3, d4, d5))
                {
                    worldIn.playSound(null, d0, d1, d2, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    entity.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                    break;
                }
            }
        }
	}
	
}
