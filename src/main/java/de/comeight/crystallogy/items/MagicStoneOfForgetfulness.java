package de.comeight.crystallogy.items;

import de.comeight.crystallogy.entity.EntityMagicStoneOfForgetfulness;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class MagicStoneOfForgetfulness extends BaseItem {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "magicStoneOfForgetfulness";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public MagicStoneOfForgetfulness() {
		super(ID);
		this.maxStackSize = 16;
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!playerIn.capabilities.isCreativeMode)
        {
            --itemStackIn.stackSize;
        }

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
        	EntityMagicStoneOfForgetfulness entityMagicStoneOfForgetfulness = new EntityMagicStoneOfForgetfulness(worldIn, playerIn);
        	entityMagicStoneOfForgetfulness.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntityInWorld(entityMagicStoneOfForgetfulness);
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}
	
}
