package de.comeight.crystallogy.renderer;

import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererInfuserBlockItem extends TileEntitySpecialRenderer<TileEnityInfuserBlock>
{
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
    @Override
    public void renderTileEntityAt(TileEnityInfuserBlock tileInfuserBlock, double x, double y, double z, float partialTicks, int destroyStage)
    {
        ItemStack inputStack = tileInfuserBlock.getStackInSlot(0);

        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        renderItem(inputStack);
        GlStateManager.popMatrix();
    }

    private void renderItem(ItemStack stack)
    {
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        if (stack != null)
        {
            GlStateManager.pushMatrix();
            
            GlStateManager.disableLighting();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.color(1.0F, 1.0F, 1.0F);
            
            GlStateManager.translate(0.5, 0.7, 0.5);
            float rotationAngel = (float) (720.0 * (System.currentTimeMillis() / 2 & 0x3FFFL) / 0x3FFFL);
            GlStateManager.rotate(rotationAngel, 0.0F, 1.0F, 0.0F);
            
            itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
            
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableLighting();
            
            GlStateManager.popMatrix();
        }
    }
}