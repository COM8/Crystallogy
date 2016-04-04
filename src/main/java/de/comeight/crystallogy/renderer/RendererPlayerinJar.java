package de.comeight.crystallogy.renderer;

import de.comeight.crystallogy.entity.PlayerClientDummy;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.world.World;

public class RendererPlayerinJar extends TileEntitySpecialRenderer<TileEntityPlayerJar>{
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void renderTileEntityAt(TileEntityPlayerJar tE, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		//GlStateManager.scale(0.5, 0.5, 0.5);
		renderPlayer(tE.getWorld(), tE.getPlayer(), x, y, z, partialTicks);
		GlStateManager.popMatrix();
	}
	
	private void renderPlayer(World world, PlayerClientDummy player, double x, double y, double z, float partialTicks){
		if(player == null){
			return;
		}
		GlStateManager.pushMatrix();
		
		CustomRenderPlayer rP = new CustomRenderPlayer(Minecraft.getMinecraft().getRenderManager());
		float rotationAngel = (float) (720.0 * (System.currentTimeMillis() / 2 & 0x3FFFL) / 0x3FFFL);
		rP.doRender(player, x + 0.5, y, z + 0.5, 1.0F, rotationAngel);
		
		GlStateManager.popMatrix();
	}
	
}
