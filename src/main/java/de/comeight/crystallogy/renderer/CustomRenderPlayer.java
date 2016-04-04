package de.comeight.crystallogy.renderer;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
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
		//GlStateManager.scale(0.5F, 0.5F, 0.5F);
		GlStateManager.pushMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.popMatrix();
		
	}
	
}
