package de.comeight.crystallogy.gui;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.blocks.container.ContainerCharger;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCharger;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCharger extends GuiContainer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final int ID = 2;
	
	private static final ResourceLocation rL = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/GuiCharger.png");
	private final InventoryPlayer playerInventory;
	private TileEntityCharger tileEntity;
	
	private final String NAME = BlockHandler.charger.getLocalizedName();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiCharger(InventoryPlayer playerInventory, TileEntityCharger tileEntity) {
		super(new ContainerCharger(playerInventory, tileEntity));
		
		this.playerInventory = playerInventory;
		this.tileEntity = tileEntity;
	}	

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public static ResourceLocation getGUIResourceLocation(){
		return rL;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------	
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRendererObj.drawString(NAME, xSize / 2 - fontRendererObj.getStringWidth(NAME) / 2, 6, 4210752);
        this.fontRendererObj.drawString(playerInventory.getDisplayName().getUnformattedText(), 8, ySize - 96 + 2, 4210752);
    }

	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(rL);
        int i = (width - xSize) / 2;
        int j = (height - ySize) / 2;
        drawTexturedModalRect(i, j, 0, 0, xSize, ySize);

        int l = getCookProgressScaled(24);
        drawTexturedModalRect(i + 79, j + 34, 176, 14, l + 1, 16);
        
        double progress = tileEntity.fractionOfCookTimeComplete();
        drawTexturedModalRect(guiLeft + 80, guiTop + 35, 176, 14, (int)(progress * 24), 17);
    }

    private int getCookProgressScaled(int pixels)
    {
        int i = this.tileEntity.getField(2);
        int j = this.tileEntity.getField(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
}
