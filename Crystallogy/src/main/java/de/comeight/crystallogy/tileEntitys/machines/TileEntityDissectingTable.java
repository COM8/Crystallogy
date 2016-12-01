package de.comeight.crystallogy.tileEntitys.machines;

import java.util.Random;

import de.comeight.crystallogy.blocks.machines.DissectingTable;
import de.comeight.crystallogy.handler.AiHandler;
import de.comeight.crystallogy.handler.CrystalCrusherRecipeHandler;
import de.comeight.crystallogy.handler.SoundHandler;
import de.comeight.crystallogy.util.NBTTags;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.config.GuiCheckBox;

public class TileEntityDissectingTable extends BaseTileEntityMachine {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "tileEntityDissectingTable";
	private int brainParts;
	private boolean progressReverse;
	public boolean running;
	private GuiCheckBox checkboxes[];
    
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityDissectingTable() {
		super(CrystalCrusherRecipeHandler.INSTANCE, 3, 0);
		this.brainParts = 0;
		this.progressReverse = false;
		this.running = false;
		this.totalCookTime = 160;
		this.checkboxes = null;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public String getName()
    {
		return "de.comeight.crystallogy.tileEntityDissectingTable";
    }
	
	@Override
	public int getSoundIntervall() {
		return 40;
	}
	
	public double getBrainPartsFullnes(){
		return (double)brainParts / (double)40;
	}
	
	public void setBlockState(){
		DissectingTable.setBlockState(crafting, worldObj, pos);
	}
	
	public boolean isReady(){
		return getBrainPartsFullnes() >= 0.25 && getStackInSlot(1) != null && getStackInSlot(2) != null;
	}
	
	public ItemStack getBrain(){
		return getStackInSlot(2);
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void playSound(World worldIn) {
		worldIn.playSound((EntityPlayer)null, pos.getX(), pos.getY(), pos.getZ(), SoundHandler.CRUSHER, SoundCategory.BLOCKS, Utilities.getRandFloat(0.3F, 0.5F), Utilities.getRandFloat(0.2F, 1.0F));
	}
	
	@Override
	protected void writeAdditionalData(NBTTagCompound compound) {
		super.writeAdditionalData(compound);
		compound.setInteger("brainParts", brainParts);
		compound.setBoolean("progressReverse", progressReverse);
		compound.setBoolean("running", running);
	}
	
	@Override
	protected void readAdditionalData(NBTTagCompound compound) {
		super.readAdditionalData(compound);
		brainParts = compound.getInteger("brainParts");
		progressReverse = compound.getBoolean("progressReverse");
		running = compound.getBoolean("running");
	}
	
	private void incProgress(){
		if(getStackInSlot(1) == null || getStackInSlot(2) == null){
			done(false);
		}
		if(!running){
			return;
		}
		if(cookTime % 40 == 0){
			brainParts--;
		}
		if(progressReverse){
			if(cookTime < 1){
				done(true);
			}
			else{
				cookTime--;
			}
		}
		else{
			if(cookTime > totalCookTime){
				progressReverse = true;
			}
			else{
				cookTime++;
			}
		}
	}
	
	private void done(boolean successfully) {
		running = false;
		cookTime = 0;
		progressReverse = false;
		
		if(successfully && checkboxes != null){
			NBTTagCompound compound = getBrain().getTagCompound();
			if(checkboxes[0].visible){
				compound.setBoolean(NBTTags.FORCE_MOVE_TO, checkboxes[1].isChecked());
			}
			if(checkboxes[1].visible){
				compound.setBoolean(NBTTags.RUN_CONTINUOUSLY, checkboxes[0].isChecked());
			}
			if(checkboxes[2].visible){
				//compound.setBoolean(NBTTags.RUN_CONTINUOUSLY, checkboxes[2].isChecked());
			}
			getBrain().setTagCompound(compound);
			ItemStack knife = getStackInSlot(1);
			if(knife != null){
				knife.attemptDamageItem(2, new Random());
				if(knife.getItemDamage() >= knife.getMaxDamage()){
					setInventorySlotContents(1, null);
					worldObj.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
				}
			}
			sync();
		}
		else{
			
		}
	}
	
	
	@Override
	public void update() {
		incProgress();
		
		ItemStack stack = inventory[0];
		if (stack != null) {
			switch (stack.getMetadata()) {
				case 0:
					if(brainParts + 5 <= 40){
						brainParts += 5;
					}
					else{
						return;
					}
					break;

				case 1:
					if(brainParts + 3 <= 40){
						brainParts += 3;
					}
					else{
						return;
					}
					break;

				case 2:
					if(brainParts + 2 <= 40){
						brainParts += 2;
					}
					else{
						return;
					}
					break;

				default:
					if(brainParts + 1 <= 40){
						brainParts += 1;
					}
					else{
						return;
					}
					break;
			}
			if(stack.stackSize == 1){
				inventory[0] = null;
			}
			else{
				inventory[0].stackSize--;
			}
			if(!worldObj.isRemote){
				sync();
			}
		}
	}
	
	
	public void go(GuiCheckBox checkboxes[]){
		this.checkboxes = checkboxes;
		this.running = true;
		this.cookTime = 0;
		this.sync();
	}

}
