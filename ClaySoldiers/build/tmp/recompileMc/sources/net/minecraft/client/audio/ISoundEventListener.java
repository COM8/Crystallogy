package net.minecraft.client.audio;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface ISoundEventListener
{
    void func_184067_a(ISound soundIn, SoundEventAccessor accessor);
}