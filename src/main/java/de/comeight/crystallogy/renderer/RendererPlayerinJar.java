package de.comeight.crystallogy.renderer;

import de.comeight.crystallogy.entity.PlayerClientDummy;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class RendererPlayerinJar extends TileEntitySpecialRenderer<TileEntityPlayerJar>{
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void renderTileEntityAt(TileEntityPlayerJar tE, double x, double y, double z, float partialTicks, int destroyStage) {
		float partialTick = (float) (720.0 * (System.currentTimeMillis() / 2 & 0x3FFFL) / 0x3FFFL);
		GlStateManager.pushMatrix();
		//GlStateManager.scale(0.5, 0.5, 0.5);
		renderPlayer(tE.getPlayer(), x, y, z, partialTick);
		renderWater(tE.getWorld(), x, y, z, partialTick);
		GlStateManager.popMatrix();
	}
	
	private void renderPlayer(PlayerClientDummy player, double posX, double posY, double posZ, float partialTicks){
		if(player == null){
			return;
		}
		CustomRenderPlayer cRP = new CustomRenderPlayer(Minecraft.getMinecraft().getRenderManager());
		
		GlStateManager.pushMatrix();
		cRP.doRender(player, posX + 0.5, posY, posZ + 0.5, 1.0F, partialTicks);
		GlStateManager.popMatrix();
	}
	
	private void renderWater(World worldIn, double x, double y, double z, float partialTicks){
		GlStateManager.pushMatrix();
        
		GlStateManager.popMatrix();
	}
	
}
