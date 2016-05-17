package net.minecraft.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockButtonWood extends BlockButton
{
    protected BlockButtonWood()
    {
        super(true);
    }

    protected void func_185615_a(EntityPlayer p_185615_1_, World player, BlockPos pos)
    {
        player.playSound(p_185615_1_, pos, SoundEvents.block_wood_button_click_on, SoundCategory.BLOCKS, 0.3F, 0.6F);
    }

    protected void func_185617_b(World worldIn, BlockPos pos)
    {
        worldIn.playSound((EntityPlayer)null, pos, SoundEvents.block_wood_button_click_off, SoundCategory.BLOCKS, 0.3F, 0.5F);
    }
}