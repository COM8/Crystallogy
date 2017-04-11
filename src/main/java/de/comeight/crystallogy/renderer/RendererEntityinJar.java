package de.comeight.crystallogy.renderer;

import de.comeight.crystallogy.tileEntitys.TileEntityEntityJar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class RendererEntityinJar extends TileEntitySpecialRenderer<TileEntityEntityJar> {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void renderTileEntityAt(TileEntityEntityJar tE, double x, double y, double z, float partialTicks, int destroyStage) {
		float partialTick = (float) (720.0 * (System.currentTimeMillis() / 2 & 0x3FFFL) / 0x3FFFL);
		renderEntity(tE.getEntity(), x, y, z, partialTick);
		renderIngredients(tE.getWorld(), x, y, z, partialTick);
	}
	
	private void renderEntity(EntityLivingBase entity, double posX, double posY, double posZ, float partialTicks){
		if(entity == null || entity.isDead){
			return;
		}
		float scale = 0.5F;
		Render<EntityLivingBase> rM = Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(entity);
		
		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, scale);
		GlStateManager.translate(posX + 0.5, posY, posZ + 0.5);
		GlStateManager.scale(scale, scale, scale);
		GlStateManager.translate(posX + 0.5, posY, posZ + 0.5);
		GlStateManager.scale(scale, scale, scale);
		GlStateManager.translate(posX + 0.5, posY, posZ + 0.5);
		rM.doRender(entity, posX + 0.5, posY, posZ + 0.5, 1.0F, partialTicks);
		rM.doRenderShadowAndFire(entity, posX + 0.5, posY, posZ + 0.5, 1.0F, partialTicks);
		GlStateManager.popMatrix();
	}
	
	private void renderIngredients(World worldIn, double x, double y, double z, float partialTicks){
		GlStateManager.pushMatrix();
		
		Tessellator te = Tessellator.getInstance();
		VertexBuffer buff = te.getBuffer();
		
		GlStateManager.popMatrix();
	}
	
}
