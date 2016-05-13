package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.blocks.materials.CustomToolMaterials;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.item.ItemStack;

public class CrystallHammer_green extends BaseItemHammer {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "crystallHammer_green";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystallHammer_green() {
		super(CustomToolMaterials.CRYSTALL_GREEN.setRepairItem(new ItemStack(BlockHandler.crystall_green, 4)), ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
