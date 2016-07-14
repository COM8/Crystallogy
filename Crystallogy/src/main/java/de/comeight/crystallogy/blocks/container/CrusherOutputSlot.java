package de.comeight.crystallogy.blocks.container;

import de.comeight.crystallogy.handler.CrystalCrusherRecipeHandler;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class CrusherOutputSlot extends BaseOutputSlot {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrusherOutputSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected float getXPFromRecipeHandler(ItemStack stack) {
		return CrystalCrusherRecipeHandler.INSTANCE.getExperience(new ItemStack[]{stack});
	}
	
}
