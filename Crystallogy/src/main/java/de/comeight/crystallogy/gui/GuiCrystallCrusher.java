package de.comeight.crystallogy.gui;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.blocks.container.ContainerCrystallCrusher;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityCrystallCrusher;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCrystallCrusher extends BaseGuiCointainer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation rL = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/GuiCrystallCrusher.png");
	private TileEntityCrystallCrusher tileEntity;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiCrystallCrusher(InventoryPlayer playerInventory, TileEntityCrystallCrusher tileEntity) {
		super(new ContainerCrystallCrusher(playerInventory, tileEntity), playerInventory, BlockHandler.crystallCrusher.getLocalizedName());
		
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
        fontRendererObj.drawString(String.valueOf((int)(tileEntity.fractionOfCookTimeComplete() * 100)) + "%", 8, ySize - 104, 4210752);
    }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, (int)(tileEntity.fractionOfCookTimeComplete() * 24), 17);
	}

	@Override
	protected void refreshTileEntity(){
		if(tileEntity != null && tileEntity.isInvalid()){
			TileEntity tE = tileEntity.getWorld().getTileEntity(tileEntity.getPos());
			if(tE instanceof TileEntityCrystallCrusher){
				tileEntity = (TileEntityCrystallCrusher) tE;
			}
		}
	}
}
