package net.minecraft.client.renderer.debug;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DebugRenderer
{
    public final DebugRendererPathfinding debugRendererPathfinding;
    public final DebugRendererWater debugRendererWater;

    public DebugRenderer(Minecraft clientIn)
    {
        this.debugRendererPathfinding = new DebugRendererPathfinding(clientIn);
        this.debugRendererWater = new DebugRendererWater(clientIn);
    }
}