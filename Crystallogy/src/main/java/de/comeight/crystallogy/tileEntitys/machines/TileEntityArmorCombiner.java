package de.comeight.crystallogy.tileEntitys.machines;

import de.comeight.crystallogy.blocks.machines.Compressor;
import de.comeight.crystallogy.handler.ArmorCombinerRecipeHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import de.comeight.crystallogy.handler.SoundHandler;
import de.comeight.crystallogy.items.armor.Armor_combined;
import de.comeight.crystallogy.util.Utilities;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class TileEntityArmorCombiner extends BaseTileEntityMachine {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "tileEntityArmorCombiner";
    
	//-----------------------------------------------Constructor:-------------------------------------------
	public TileEntityArmorCombiner() {
		super(ArmorCombinerRecipeHandler.INSTANCE, 3, 0);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public String getName()
    {
		return "de.comeight.crystallogy.tileEntityArmorCombiner";
    }
	
	@Override
	public void setBlockState(){
		Compressor.setBlockState(crafting, worldObj, pos);
	}
	
	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		int[] ret = new int[slotsInput];
		for(int i = 0; i < slotsInput; i++){
			ret[i] = i;
		}
		return ret;
	}
	
	@Override
	public int getSoundIntervall() {
		return 80;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	public void craftItem()
    {
        if (canCraft())
        {
            ItemStack[] res = recipeHandler.getResults(getInputSlots());
            Armor_combined.addArmor(res[0], getInputSlots()[0]);
            inventory[2] = res[0].copy();

            int numCata = ((ArmorCombinerRecipeHandler) recipeHandler).getNumberOfCatalys(getInputSlots());
            inventory[1].stackSize -= numCata;
            if(inventory[1].stackSize <= 0){
        		inventory[1] = null;
        	}
            
            inventory[0] = null;
        }
    }

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(super.isItemValidForSlot(index, stack)){
			switch (index) {
				case 0:
					if(stack.getItem() instanceof ItemArmor){
						return true;
					}
					break;
					
				case 1:
					if(stack.getItem() == ItemHandler.armorCatalys){
						return true;
					}
					break;

				case 2:
					if(stack.getItem() instanceof Armor_combined){
						return true;
					}
					break;
			}
		}
		return false;
	}
	
	@Override
	public void playSound(World worldIn) {
		worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundHandler.MACHINE_BROKEN, SoundCategory.BLOCKS, Utilities.getRandFloat(0.1F, 0.2F), Utilities.getRandFloat(0.2F, 1.0F));
	}
}
