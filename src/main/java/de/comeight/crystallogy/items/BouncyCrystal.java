package de.comeight.crystallogy.items;

import de.comeight.crystallogy.entities.EntityBouncyCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class BouncyCrystal extends BaseItem {
    //-----------------------------------------------Attributes:--------------------------------------------
    public static final String ID = "bouncyCrystal";

    //-----------------------------------------------Constructor:-------------------------------------------
    public BouncyCrystal() {
        super(ID);
    }

    //-----------------------------------------------Set-, Get- Methods:------------------------------------


    //-----------------------------------------------Misc Methods:------------------------------------------
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (!playerIn.capabilities.isCreativeMode)
        {
            stack.setCount(stack.getCount() - 1);
        }

        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
            EntityBouncyCrystal entityBouncyCrystal = new EntityBouncyCrystal(worldIn, playerIn);
            entityBouncyCrystal.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.5F, 1.0F);
            worldIn.spawnEntity(entityBouncyCrystal);
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult(EnumActionResult.SUCCESS, stack);
    }

    //-----------------------------------------------Events:------------------------------------------------

}