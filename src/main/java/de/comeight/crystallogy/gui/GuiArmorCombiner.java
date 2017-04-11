package de.comeight.crystallogy.gui;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.blocks.container.ContainerArmorCombiner;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityArmorCombiner;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiArmorCombiner extends BaseGuiCointainer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation rL = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/GuiArmorCombiner.png");
	private TileEntityArmorCombiner tileEntity;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiArmorCombiner(InventoryPlayer playerInventory, TileEntityArmorCombiner tileEntity) {
		super(new ContainerArmorCombiner(playerInventory, tileEntity), playerInventory, BlockHandler.armorCombiner.getLocalizedName());
		
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
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		drawTexturedModalRect(guiLeft + 81, guiTop + 37, 176, 0, (int)(tileEntity.fractionOfCookTimeComplete() * 14), 13);
		
		if(tileEntity.getStackInSlot(0) == null){
        	drawTexturedModalRect(guiLeft + 45, guiTop + 37, 176, 14, 14, 13);
        }
        if(tileEntity.getStackInSlot(2) == null){
        	drawTexturedModalRect(guiLeft + 117, guiTop + 37, 176, 14, 14, 13);
        }
    }
	
	@Override
	protected void refreshTileEntity(){
		if(tileEntity != null && tileEntity.isInvalid()){
			TileEntity tE = tileEntity.getWorld().getTileEntity(tileEntity.getPos());
			if(tE instanceof TileEntityArmorCombiner){
				tileEntity = (TileEntityArmorCombiner) tE;
			}
		}
	}
}
