package de.comeight.crystallogy.gui;

import de.comeight.crystallogy.CrystallogyBase;
import de.comeight.crystallogy.blocks.container.ContainerDissectingTable;
import de.comeight.crystallogy.gui.parts.GuiButtonDissectingTable;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.tileEntitys.machines.TileEntityDissectingTable;
import de.comeight.crystallogy.util.NBTTags;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiCheckBox;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiDissectingTable extends BaseGuiCointainer{
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final ResourceLocation rL = new ResourceLocation(CrystallogyBase.MODID + ":" + "textures/guis/GuiDissectingTable.png");
	private TileEntityDissectingTable tileEntity;
	
	private GuiButton goButton;
	private GuiCheckBox checkboxes[];
	private ItemStack prevStack;
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiDissectingTable(InventoryPlayer playerInventory, TileEntityDissectingTable tileEntity) {
		super(new ContainerDissectingTable(playerInventory, tileEntity), playerInventory, BlockHandler.dissectingTable.getLocalizedName());
		this.tileEntity = tileEntity;
		this.prevStack = null;
	}	

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected ResourceLocation getBackground() {
		return rL;
	}
	
	private boolean areCheckboxesDifferent(){
		ItemStack brain;
		if((brain = tileEntity.getBrain()) != null && brain.hasTagCompound()){
			NBTTagCompound compound = brain.getTagCompound();
			return (checkboxes[0].isChecked() != compound.getBoolean(NBTTags.FORCE_MOVE_TO)) || (checkboxes[1].isChecked() != compound.getBoolean(NBTTags.RUN_CONTINUOUSLY));
		}
		return false;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------	
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        fontRendererObj.drawString(String.valueOf((int)(tileEntity.getBrainPartsFullnes() * 100)) + "%", 8, 5, 4210752);
    }
	
	@Override
	public void initGui() {
		super.initGui();
		goButton = new GuiButtonDissectingTable(0, guiLeft + 75, guiTop + 60, this);
		buttonList.add(goButton);
		
		checkboxes = new GuiCheckBox[3];
		checkboxes[0] = new GuiCheckBox(1, guiLeft + 120, guiTop + 35, "X25E", false);
		checkboxes[1] = new GuiCheckBox(1, guiLeft + 50, guiTop + 20, "X59U", false);
		checkboxes[2] = new GuiCheckBox(1, guiLeft + 35, guiTop + 50, "X99P", false);
		
		for (int i = 0; i < checkboxes.length; i++) {
			buttonList.add(checkboxes[i]);
		}
		manageCheckboxes();
	}
	
	@Override
	protected void drawText() {
		this.fontRendererObj.drawString(NAME, xSize / 2 - fontRendererObj.getStringWidth(NAME) / 2, 6, 4210752);
        this.fontRendererObj.drawString(playerInventory.getDisplayName().getUnformattedText(), 8, ySize - 92, 4210752);
	}
	
	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
		manageCheckboxes();
		goButton.enabled = tileEntity.isReady() && areCheckboxesDifferent();
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		int brainTank = (int) (tileEntity.getBrainPartsFullnes() * 40);
        drawTexturedModalRect(guiLeft + 8, guiTop + 54 - brainTank, 176, 0, 16, brainTank);
        int size = (int)(tileEntity.fractionOfCookTimeComplete() * 63);
        drawTexturedModalRect(guiLeft + 25, guiTop + 3, 25, 173, size, 80);
        drawTexturedModalRect(guiLeft + 25 + 126 - size, guiTop + 3, 152 - size, 173, size, 80);
    }
	
	private void manageCheckboxes(){
		for (int i = 0; i < checkboxes.length; i++) {
			checkboxes[i].enabled = !tileEntity.running;
		}
		ItemStack brain;
		if((brain = tileEntity.getBrain()) != null && brain.hasTagCompound()){
			NBTTagCompound compound = brain.getTagCompound();
			checkboxes[0].visible = compound.hasKey(NBTTags.FORCE_MOVE_TO);
			checkboxes[1].visible = compound.hasKey(NBTTags.RUN_CONTINUOUSLY);
			checkboxes[2].visible = false;
			
			if(prevStack == null){
				checkboxes[0].setIsChecked(compound.getBoolean(NBTTags.FORCE_MOVE_TO));
				checkboxes[1].setIsChecked(compound.getBoolean(NBTTags.RUN_CONTINUOUSLY));
				checkboxes[2].setIsChecked(false);
				prevStack = brain;
			}
			
		}
		else{
			prevStack = null;
			checkboxes[0].visible = false;
			checkboxes[1].visible = false;
			checkboxes[2].visible = false;
		}
	}
	
	public void go(){
		tileEntity.go(checkboxes);
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
