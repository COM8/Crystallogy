package de.comeight.crystallogy.renderer;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class CustomRenderPlayer extends RenderPlayer {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CustomRenderPlayer(RenderManager renderManager) {
		super(renderManager);
	}

	public CustomRenderPlayer(RenderManager renderManager, boolean useSmallArms) {
		super(renderManager, useSmallArms);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void doRender(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks) {
		float scale = 0.5F;
		
		GlStateManager.pushMatrix();
		
		GlStateManager.scale(scale, scale, scale);
		GlStateManager.translate(x, y, z);
		GlStateManager.scale(scale, scale, scale);
		GlStateManager.translate(x, y, z);
		
		GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.popMatrix();
		
	}
	
}
