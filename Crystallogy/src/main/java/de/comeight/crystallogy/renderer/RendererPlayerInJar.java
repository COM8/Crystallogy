package de.comeight.crystallogy.renderer;

import de.comeight.crystallogy.entity.PlayerClientDummy;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererPlayerInJar extends TileEntitySpecialRenderer<TileEntityPlayerJar> {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void renderTileEntityAt(TileEntityPlayerJar tE, double x, double y, double z, float partialTicks, int destroyStage) {
		if(!tE.hasEntity()){
			return;
		}
		float partialTick = 0.0F;
		//float partialTick = (float) (720.0 * (System.currentTimeMillis() / 2 & 0x3FFFL) / 0x3FFFL);
		renderPlayer((PlayerClientDummy)tE.getEntity(), x, y, z, partialTick);
		renderIngredients(tE.getWorld(), x, y, z, partialTick);
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
	
	private void renderIngredients(World worldIn, double x, double y, double z, float partialTicks){
		GlStateManager.pushMatrix();
		
		Tessellator te = Tessellator.getInstance();
		VertexBuffer buff = te.getBuffer();
		
		GlStateManager.popMatrix();
	}
	
}
