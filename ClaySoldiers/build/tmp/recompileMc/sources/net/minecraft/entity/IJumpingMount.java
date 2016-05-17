package net.minecraft.entity;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IJumpingMount
{
    @SideOnly(Side.CLIENT)
    void setJumpPower(int jumpPowerIn);

    boolean canJump();

    void func_184775_b(int p_184775_1_);

    void func_184777_r_();
}