package de.comeight.crystallogy.renderer;

import de.comeight.crystallogy.blocks.BaseBlockEntityJar;
import de.comeight.crystallogy.entity.PlayerClientDummy;
import de.comeight.crystallogy.tileEntitys.TileEntityPlayerJar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RendererPlayerInJar extends TileEntitySpecialRenderer<TileEntityPlayerJar> {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	private float getRotation(World worldIn, BlockPos pos){
		IBlockState state = worldIn.getBlockState(pos);
		if(state == null){
			return 0.0F;
		}
		
		switch (state.getValue(BaseBlockEntityJar.FACING)) {
			case NORTH:
				return 180.0F;
				
			case EAST:	
				return 90.0F;
				
			case WEST:
				return 270.0F;
				
			case SOUTH:
				return 0.0F;

			default:
				return 0.0F;
		}
		
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void renderTileEntityAt(TileEntityPlayerJar tE, double x, double y, double z, float partialTicks, int destroyStage) {
		if(!tE.hasEntity()){
			return;
		}
		float partialTick = 0.0F;
		
		renderPlayer(new PlayerClientDummy(getWorld(), tE.getProfile()), x, y, z, partialTick, getRotation(getWorld(), tE.getPos()));
		renderIngredients(tE.getWorld(), x, y, z, partialTick);
	}
	
	private void renderPlayer(PlayerClientDummy player, double posX, double posY, double posZ, float partialTicks, float rotation){
		if(player == null){
			return;
		}
		CustomRenderPlayer cRP = new CustomRenderPlayer(Minecraft.getMinecraft().getRenderManager());
		
		GlStateManager.pushMatrix();
		cRP.doRender(player, posX + 0.5, posY, posZ + 0.5, 1.0F, partialTicks, rotation);
		GlStateManager.popMatrix();
	}
	
	private void renderIngredients(World worldIn, double x, double y, double z, float partialTicks){
		GlStateManager.pushMatrix();
		
		Tessellator te = Tessellator.getInstance();
		VertexBuffer buff = te.getBuffer();
		
		GlStateManager.popMatrix();
	}
	
}
