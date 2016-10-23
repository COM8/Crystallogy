package de.comeight.crystallogy.renderer;

import de.comeight.crystallogy.blocks.machines.BaseMachine;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityDissectingTable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererDissectingTable extends TileEntitySpecialRenderer<TileEntityDissectingTable>
{
	//-----------------------------------------------Variabeln:---------------------------------------------
	private ItemStack brainStack;
	private double scale;
	private boolean growing;
	private final double FACTOR = 0.0005;
	private long lastScaling;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public RendererDissectingTable() {
		brainStack = new ItemStack(ItemHandler.entityBrain, 1, 1);
		scale = 2;
		growing = true;
		lastScaling = System.currentTimeMillis();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
    @Override
    public void renderTileEntityAt(TileEntityDissectingTable tE, double x, double y, double z, float partialTicks, int destroyStage)
    {
    	IBlockState state = getWorld().getBlockState(tE.getPos());
    	if(state == null || state.getBlock() != BlockHandler.dessectingTable){
    		return;
    	}
    	
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        renderItem(brainStack, (EnumFacing)state.getValue(BaseMachine.FACING));
        GlStateManager.popMatrix();
    }

    private void renderItem(ItemStack stack, EnumFacing enumfacing)
    {
    	if(enumfacing == null){
    		return;
    	}
    	if(lastScaling + 5 <= System.currentTimeMillis()){
    		scale();
    		lastScaling = System.currentTimeMillis();
    	}
    	
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        if (stack != null)
        {
            GlStateManager.pushMatrix();
            
            GlStateManager.disableLighting();
            RenderHelper.enableStandardItemLighting();
            translate(enumfacing);
            GlStateManager.scale(scale, scale, scale);
            
            rotate(enumfacing);
            
            itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
            
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableLighting();
            
            GlStateManager.popMatrix();
        }
    }
    
    private void translate(EnumFacing enumfacing) {
		switch (enumfacing) {
			case NORTH:
				GlStateManager.translate(0.5, 0.7, 0.2);
				break;
				
			case EAST:
				GlStateManager.translate(0.8, 0.7, 0.5);
				break;
				
			case SOUTH:
				GlStateManager.translate(0.5, 0.7, 0.8);
				break;
				
			case WEST:
				GlStateManager.translate(0.2, 0.7, 0.5);
				break;

			default:
				break;
		}
	}
    
	private void rotate(EnumFacing enumfacing) {
		switch (enumfacing) {
			case NORTH:
				GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
	            GlStateManager.rotate(-25, 1.0F, 0.0F, 0.0F);
				break;
				
			case EAST:
				GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
	            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
	            GlStateManager.rotate(-25, 1.0F, 0.0F, 0.0F);
				break;
				
			case SOUTH:
				GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
	            GlStateManager.rotate(-180.0F, 0.0F, 0.0F, 1.0F);
	            GlStateManager.rotate(-25, 1.0F, 0.0F, 0.0F);
				break;
				
			case WEST:
				GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
	            GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
	            GlStateManager.rotate(-25, 1.0F, 0.0F, 0.0F);
				break;

			default:
				break;
		}
	}
    
    private void scale(){
    	if(growing){
    		if(scale >= 2.1){
        		growing = false;
        	}
    		else{
    			scale += FACTOR;	
    		}
    	}
    	else{
    		if(scale <= 2.0){
        		growing = true;
        	}
    		else{
    			scale -= FACTOR;
    		}
    	}
    }
}