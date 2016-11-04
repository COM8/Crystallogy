package de.comeight.crystallogy.gui;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.blocks.container.ContainerDissectingTable;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityDissectingTable;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDissectingTable extends BaseGuiCointainer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation rL = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/GuiDissectingTable.png");
	private TileEntityDissectingTable tileEntity;
	
	private GuiButton doItButton;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiDissectingTable(InventoryPlayer playerInventory, TileEntityDissectingTable tileEntity) {
		super(new ContainerDissectingTable(playerInventory, tileEntity), playerInventory, BlockHandler.dessectingTable.getLocalizedName());
		this.tileEntity = tileEntity;
	}	

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected ResourceLocation getBackground() {
		return rL;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------	
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        fontRendererObj.drawString(String.valueOf((int)(tileEntity.fractionOfCookTimeComplete() * 100)) + "%", 8, 5, 4210752);
    }
	
	@Override
	public void initGui() {
		super.initGui();
		doItButton = new GuiButton(0, 0, 0, "Do!");
		buttonList.add(doItButton);
	}
	
	@Override
	protected void drawText() {
		this.fontRendererObj.drawString(NAME, xSize / 2 - fontRendererObj.getStringWidth(NAME) / 2, 6, 4210752);
        this.fontRendererObj.drawString(playerInventory.getDisplayName().getUnformattedText(), 8, ySize - 92, 4210752);
	}
	
	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int progress = (int)(tileEntity.fractionOfCookTimeComplete() * 40);
        drawTexturedModalRect(guiLeft + 8, guiTop + 54 - progress, 176, 0, 16, progress);
		
    }

	@Override
	protected void refreshTileEntity(){
		if(tileEntity != null && tileEntity.isInvalid()){
			TileEntity tE = tileEntity.getWorld().getTileEntity(tileEntity.getPos());
			if(tE instanceof TileEntityDissectingTable){
				tileEntity = (TileEntityDissectingTable) tE;
			}
		}
	}
	
}
