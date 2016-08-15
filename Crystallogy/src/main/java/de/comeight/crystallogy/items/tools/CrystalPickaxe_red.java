package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.blocks.materials.CustomToolMaterials;

public class CrystalPickaxe_red extends BaseCrystalPickaxe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "crystalPickaxe_red";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystalPickaxe_red() {
		super(CustomToolMaterials.CRYSTALL_RED, ID);
		setMaxDamage(getMaxDamage() * 3);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public int getAOE() {
		return 9;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
