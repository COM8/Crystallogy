package de.comeight.crystallogy.renderer;

import de.comeight.crystallogy.blocks.CrystalOfHolding;
import de.comeight.crystallogy.tileEntitys.TileEntityCrystalOfHolding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererEntityInCrystal extends TileEntitySpecialRenderer<TileEntityCrystalOfHolding> {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void renderTileEntityAt(TileEntityCrystalOfHolding tE, double x, double y, double z, float partialTicks, int destroyStage) {
		Entity entity = tE.getEntity();
		if(entity == null || !(entity instanceof EntityLivingBase)){
			return;
		}
		
		int state = tE.getWorld().getBlockState(tE.getPos()).getValue(CrystalOfHolding.AGE);
		
		renderEntity((EntityLivingBase) entity, state, x, y, z, partialTicks);
	}
	
	private void renderEntity(EntityLivingBase entity, int state, double posX, double posY, double posZ, float partialTicks){
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
		if(state <= 2){
			GlStateManager.scale(scale, scale, scale);
			GlStateManager.translate(posX + 0.5, posY, posZ + 0.5);
			if(state < 1){
				GlStateManager.scale(scale, scale, scale);
				GlStateManager.translate(posX + 0.5, posY, posZ + 0.5);
			}
		}
		rM.doRender(entity, posX + 0.5, posY, posZ + 0.5, 1.0F, 0);
		rM.doRenderShadowAndFire(entity, posX + 0.5, posY, posZ + 0.5, 1.0F, partialTicks);
		GlStateManager.popMatrix();
	}
	
}
