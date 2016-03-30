package de.comeight.crystallogy.renderer;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

import de.comeight.crystallogy.entity.PlayerClientDummy;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.world.World;

public class RendererPlayerinJar extends TileEntitySpecialRenderer<TileEntityPlayerJar>{
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void renderTileEntityAt(TileEntityPlayerJar tE, double x, double y, double z, float partialTicks, int destroyStage) {
		//GlStateManager.pushMatrix();
		renderPlayer(tE.getWorld(), tE.getPlayer(), x, y, z, partialTicks);
		//GlStateManager.popMatrix();
	}
	
	private void renderPlayer(World world, PlayerClientDummy player, double x, double y, double z, float partialTicks){
		if(player == null){
			return;
		}
		CustomRenderPlayer rP = new CustomRenderPlayer(Minecraft.getMinecraft().getRenderManager());
		GlStateManager.pushMatrix();
		
		GlStateManager.translate(0.5, 0.0, 0.5);
		//GlStateManager.scale(0.5, 0.5, 0.5);
		float rotationAngel = (float) (720.0 * (System.currentTimeMillis() / 2 & 0x3FFFL) / 0x3FFFL);
	    //GlStateManager.rotate(rotationAngel, 0.0F, 1.0F, 0.0F);
		GlStateManager.pushAttrib();
		rP.doRender(player, x, y, z, 0.0F, partialTicks);
		GlStateManager.popAttrib();
        //RenderHelper.disableStandardItemLighting();
        //GlStateManager.enableLighting();
		
		
        GlStateManager.popMatrix();
		
	}
	
}
