package net.minecraft.client.renderer.tileentity;

import net.minecraft.tileentity.TileEntityStructure;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityStructureRenderer extends TileEntitySpecialRenderer<TileEntityStructure>
{
    public void renderTileEntityAt(TileEntityStructure te, double x, double y, double z, float partialTicks, int destroyStage)
    {
    }

    public boolean isGlobalRenderer(TileEntityStructure p_188185_1_)
    {
        return true;
    }
}