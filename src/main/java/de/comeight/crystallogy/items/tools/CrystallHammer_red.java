package de.comeight.crystallogy.items.Tools;

import de.comeight.crystallogy.blocks.materials.CustomToolMaterials;
import de.comeight.crystallogy.handler.BlockHandler;
import net.minecraft.item.ItemStack;

public class CrystallHammer_red extends BaseItemHammer {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "crystallHammer_red";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystallHammer_red() {
		super(CustomToolMaterials.CRYSTALL_RED.setRepairItem(new ItemStack(BlockHandler.crystall_red, 4)), ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
