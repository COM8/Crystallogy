package net.minecraft.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IItemPropertyGetter
{
    @SideOnly(Side.CLIENT)
    float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn);
}