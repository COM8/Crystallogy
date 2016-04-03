package de.comeight.crystallogy.renderer;

import de.comeight.crystallogy.tileEntitys.TileEnityInfuserBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
        renderItem(tileInfuserBlock.getWorld(), inputStack);
        GlStateManager.popMatrix();
    }

    private void renderItem(World world, ItemStack stack)
    {
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        if (stack != null)
        {
            EntityItem entityitem = new EntityItem(world, 0.0D, 0.0D, 0.0D, stack);
            entityitem.getEntityItem().stackSize = 1;
            entityitem.hoverStart = 0.0F;
            GlStateManager.translate(0.5, 0.7, 0.5);
            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            float rotationAngel = (float) (720.0 * (System.currentTimeMillis() / 2 & 0x3FFFL) / 0x3FFFL);

            GlStateManager.rotate(rotationAngel, 0.0F, 1.0F, 0.0F);
            GlStateManager.pushAttrib();
            RenderHelper.enableStandardItemLighting();
            itemRenderer.renderItem(entityitem.getEntityItem(), ItemCameraTransforms.TransformType.GROUND);
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popAttrib();

            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
        else{
        }
    }
}