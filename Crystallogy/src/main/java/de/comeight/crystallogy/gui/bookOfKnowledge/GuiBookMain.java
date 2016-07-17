package de.comeight.crystallogy.gui.bookOfKnowledge;

import java.io.IOException;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiBookMain extends GuiScreen {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final int ID = 4;
	
	private static final ResourceLocation rL = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/GuiBookMain.png");
	/** The X size of the inventory window in pixels. */
	protected int xSize = 176;
    /** The Y size of the inventory window in pixels. */
    protected int ySize = 166;
	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void drawBackground(int tint) {
		mc.getTextureManager().bindTexture(rL);
		int i = (width - xSize) / 2;
		int j = (height - ySize) / 2;
		drawTexturedModalRect(i, j, 0, 0, xSize, ySize);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		//drawBackground(0);
		renderItem(new ItemStack(BlockHandler.crystall_red), 0, 0);
		renderItem(new ItemStack(BlockHandler.crystall_blue), 0, ySize);
		renderItem(new ItemStack(BlockHandler.crystall_green), xSize, ySize);
		renderItem(new ItemStack(BlockHandler.crystall_yellow), xSize, 0);
	}
	
	@SideOnly(Side.CLIENT)
	private void renderItem(ItemStack stack, int posX, int posY)
    {
        RenderItem itemRenderer = Minecraft.getMinecraft().getRenderItem();
        if (stack != null)
        {
        	GlStateManager.pushMatrix();
            GlStateManager.disableLighting();

            
            GlStateManager.translate(posX, posY, 0);
            GlStateManager.scale(5, 5, 5);
            
            RenderHelper.enableStandardItemLighting();
            itemRenderer.renderItemAndEffectIntoGUI(stack, 0, 0);
            itemRenderer.renderItemOverlayIntoGUI(this.fontRendererObj, stack, 0, 0, null);
            RenderHelper.disableStandardItemLighting();
            
            GlStateManager.enableLighting();
            GlStateManager.popMatrix();
        }
    }
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == 1 || this.mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode))
        {
            this.mc.thePlayer.closeScreen();
        }
    }
	
	
}
