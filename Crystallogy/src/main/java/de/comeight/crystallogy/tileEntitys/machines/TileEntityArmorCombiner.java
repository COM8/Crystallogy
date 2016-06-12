package de.comeight.crystallogy.tileEntitys.machines;

import de.comeight.crystallogy.blocks.machines.Compressor;
import de.comeight.crystallogy.handler.ArmorCombinerRecipeHandler;
import de.comeight.crystallogy.items.armor.Armor_combined;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

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
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	public void setBlockState(){
		Compressor.setBlockState(crafting, worldObj, pos);
	}
	
	@Override
	public void craftItem()
    {
        if (canCraft())
        {
            ItemStack[] res = recipeHandler.getResults(getInputSlots());
            Armor_combined.addArmor(res[0], (ItemArmor) getInputSlots()[0].getItem());
            inventory[2] = res[0];

            int numCata = ((ArmorCombinerRecipeHandler) recipeHandler).getNumberOfCatalys(getInputSlots());
            inventory[1].stackSize -= numCata;
            if(inventory[1].stackSize <= 0){
        		inventory[1] = null;
        	}
            
            inventory[0] = null;
        }
    }

}
