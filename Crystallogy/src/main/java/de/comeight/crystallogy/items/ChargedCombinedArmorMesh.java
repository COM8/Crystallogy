package de.comeight.crystallogy.items;

import net.minecraft.item.ItemStack;

public class ChargedCombinedArmorMesh extends BaseItem {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "chargedCombinedArmorMesh";

	//-----------------------------------------------Constructor:-------------------------------------------
	public ChargedCombinedArmorMesh() {
		super(ID);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	//-----------------------------------------------Sonstige Methoden:-------------------------------------

}
