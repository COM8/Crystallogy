package net.minecraft.client.renderer.debug;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DebugRendererWater
{
    private final Minecraft minecraft;

    public DebugRendererWater(Minecraft minecraftIn)
    {
        this.minecraft = minecraftIn;
    }
}