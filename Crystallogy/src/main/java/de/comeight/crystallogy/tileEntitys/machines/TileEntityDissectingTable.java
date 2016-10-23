package de.comeight.crystallogy.tileEntitys.machines;

import de.comeight.crystallogy.blocks.machines.DessectingTable;
import de.comeight.crystallogy.handler.CrystalCrusherRecipeHandler;
import de.comeight.crystallogy.handler.SoundHandler;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class TileEntityDissectingTable extends BaseTileEntityMachine {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "tileEntityDissectingTable";
	private int brainParts;
    
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityDissectingTable() {
		super(CrystalCrusherRecipeHandler.INSTANCE, 3, 0);
		this.brainParts = 0;
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
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void setBlockState(){
		DessectingTable.setBlockState(crafting, worldObj, pos);
	}
	
	@Override
	public void playSound(World worldIn) {
		worldIn.playSound((EntityPlayer)null, pos.getX(), pos.getY(), pos.getZ(), SoundHandler.CRUSHER, SoundCategory.BLOCKS, Utilities.getRandFloat(0.3F, 0.5F), Utilities.getRandFloat(0.2F, 1.0F));
	}
	
	@Override
	protected void writeAdditionalData(NBTTagCompound compound) {
		super.writeAdditionalData(compound);
		compound.setInteger("brainParts", brainParts);
	}
	
	@Override
	protected void readAdditionalData(NBTTagCompound compound) {
		super.readAdditionalData(compound);
		brainParts = compound.getInteger("brainParts");
	}
	
	@Override
	public double fractionOfCookTimeComplete() {
		return (double)brainParts / (double)40;
	}
	
	@Override
	public void update() {
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

}
