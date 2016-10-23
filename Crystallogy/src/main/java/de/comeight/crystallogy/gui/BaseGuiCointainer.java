package de.comeight.crystallogy.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class BaseGuiCointainer extends GuiContainer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	protected final InventoryPlayer playerInventory;
	
	protected final String NAME;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseGuiCointainer(Container container, InventoryPlayer playerInventory, String name) {
		super(container);
		
		this.playerInventory = playerInventory;
		this.NAME = name;
	}	

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	protected abstract ResourceLocation getBackground();

	//-----------------------------------------------Sonstige Methoden:-------------------------------------	
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        drawText();
    }
	
	protected void drawText(){
		this.fontRendererObj.drawString(NAME, xSize / 2 - fontRendererObj.getStringWidth(NAME) / 2, 6, 4210752);
        this.fontRendererObj.drawString(playerInventory.getDisplayName().getUnformattedText(), 8, ySize - 94, 4210752);
	}

	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
		refreshTileEntity();
		
        mc.getTextureManager().bindTexture(getBackground());
        int i = (width - xSize) / 2;
        int j = (height - ySize) / 2;
        drawTexturedModalRect(i, j, 0, 0, xSize, ySize);
    }
	
	protected abstract void refreshTileEntity();
}
