package de.comeight.crystallogy.items;

import de.comeight.crystallogy.blocks.materials.CustomToolMaterials;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.item.ItemStack;

public class CrystallHammer_blue extends BaseItemHammer {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "CrystallHammer_blue";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystallHammer_blue() {
		super(CustomToolMaterials.CRYSTALL_BLUE.setRepairItem(new ItemStack(BlockHandler.crystall_blue, 4)), ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
