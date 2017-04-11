package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.blocks.materials.CustomToolMaterials;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.item.ItemStack;

public class CrystallHammer_yellow extends BaseItemHammer {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "crystallHammer_yellow";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystallHammer_yellow() {
		super(CustomToolMaterials.CRYSTALL_YELLOW.setRepairItem(new ItemStack(BlockHandler.crystall_yellow, 4)), ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
