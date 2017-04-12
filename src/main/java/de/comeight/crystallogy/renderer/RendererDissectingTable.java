package de.comeight.crystallogy.renderer;

import de.comeight.crystallogy.blocks.machines.BaseMachine;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityDissectingTable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
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
	private double scaleBrain;
	private boolean growing;
	private final double FACTOR = 0.0005;
	private long lastScaling;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public RendererDissectingTable() {
		this.scaleBrain = 1.4;
		this.growing = true;
		this.lastScaling = System.currentTimeMillis();
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
    @Override
    public void renderTileEntityAt(TileEntityDissectingTable tE, double x, double y, double z, float partialTicks, int destroyStage)
    {
    	IBlockState state = getWorld().getBlockState(tE.getPos());
    	if(state == null || state.getBlock() != BlockHandler.dissectingTable){
    		return;
    	}
    	
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        
        renderBrain(tE.getStackInSlot(2), state.getValue(BaseMachine.FACING));
        renderKnife(tE.getStackInSlot(1), state.getValue(BaseMachine.FACING));
        GlStateManager.popMatrix();
    }

    private void renderBrain(ItemStack stack, EnumFacing enumfacing)
    {
        if (stack != null && enumfacing != null)
        {
        	if(lastScaling + 5 <= System.currentTimeMillis()){
        		scale();
        		lastScaling = System.currentTimeMillis();
        	}
        	
            GlStateManager.pushMatrix();
            
            GlStateManager.disableLighting();
            RenderHelper.enableStandardItemLighting();
            translate(enumfacing, 0.5, 0.75, 0.22);
            GlStateManager.scale(scaleBrain, scaleBrain, scaleBrain);
            
            rotateBrain(enumfacing);
            
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
            
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableLighting();
            
            GlStateManager.popMatrix();
        }
    }
    
    private void renderKnife(ItemStack stack, EnumFacing enumfacing)
    {
    	if (stack != null && enumfacing != null)
        {
            GlStateManager.pushMatrix();
            
            GlStateManager.disableLighting();
            RenderHelper.enableStandardItemLighting();
            translate(enumfacing, 0.1, 0.9, 0.5);
            GlStateManager.scale(1.5, 1.5, 1.5);
            
            rotateKnife(enumfacing);
            
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.GROUND);
            
            RenderHelper.disableStandardItemLighting();
            GlStateManager.enableLighting();
            
            GlStateManager.popMatrix();
        }
    }
    
    private void translate(EnumFacing enumfacing, double xOffset, double yOffset, double zOffset) {
		switch (enumfacing) {
			case NORTH:
				GlStateManager.translate(xOffset, yOffset, zOffset);
				break;
				
			case EAST:
				GlStateManager.translate(1.0 - zOffset, yOffset, xOffset);
				break;
				
			case SOUTH:
				GlStateManager.translate(1 - xOffset, yOffset, 1 - zOffset);
				break;
				
			case WEST:
				GlStateManager.translate(zOffset, yOffset, 1 - xOffset);
				break;

			default:
				break;
		}
	}
    
	private void rotateBrain(EnumFacing enumfacing) {
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
	
	private void rotateKnife(EnumFacing enumfacing) {
		switch (enumfacing) {
			case NORTH:
				GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
	            GlStateManager.rotate(-25.0F, 1.0F, 0.0F, 0.0F);
				break;
				
			case EAST:
				GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
	            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
	            GlStateManager.rotate(65.0F, 1.0F, 0.0F, 0.0F);
				break;
				
			case SOUTH:
				GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
	            GlStateManager.rotate(155.0F, 1.0F, 0.0F, 0.0F);
				break;
				
			case WEST:
				GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
	            GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
	            GlStateManager.rotate(65.0F, 1.0F, 0.0F, 0.0F);
				break;

			default:
				break;
		}
	}
    
    private void scale(){
    	if(growing){
    		if(scaleBrain >= 1.475){
        		growing = false;
        	}
    		else{
    			scaleBrain += FACTOR;	
    		}
    	}
    	else{
    		if(scaleBrain <= 1.4){
        		growing = true;
        	}
    		else{
    			scaleBrain -= FACTOR;
    		}
    	}
    }
}