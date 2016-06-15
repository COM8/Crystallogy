package de.comeight.crystallogy.gui;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.blocks.container.ContainerCompressor;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCompressor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCompressor extends GuiContainer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final int ID = 1;
	
	private static final ResourceLocation rL = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/GuiCompressor.png");
	private final InventoryPlayer playerInventory;
	private TileEntityCompressor tileEntity;
	
	private final String NAME = BlockHandler.compressor.getLocalizedName();
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiCompressor(InventoryPlayer playerInventory, TileEntityCompressor tileEntity) {
		super(new ContainerCompressor(playerInventory, tileEntity));
		
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
        
        this.fontRendererObj.drawString(String.valueOf((int)(tileEntity.fractionOfCookTimeComplete()*100)) + "%", 8, ySize - 106 + 2, 4210752);
    }

	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
		refreshTileEntity();
		
        mc.getTextureManager().bindTexture(rL);
        int i = (width - xSize) / 2;
        int j = (height - ySize) / 2;
        drawTexturedModalRect(i, j, 0, 0, xSize, ySize);
        
        double progress = tileEntity.fractionOfCookTimeComplete();
        drawTexturedModalRect(guiLeft + 80, guiTop + 35, 176, 14, (int)(progress * 24), 17);
    }

	private void refreshTileEntity(){
		if(tileEntity != null && tileEntity.isInvalid()){
			TileEntity tE = tileEntity.getWorld().getTileEntity(tileEntity.getPos());
			if(tE instanceof TileEntityCompressor){
				tileEntity = (TileEntityCompressor) tE;
			}
		}
	}
}
