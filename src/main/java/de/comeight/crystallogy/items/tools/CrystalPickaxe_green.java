package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.blocks.materials.CustomToolMaterials;

public class CrystalPickaxe_green extends BaseCrystalPickaxe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "crystalPickaxe_green";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystalPickaxe_green() {
		super(CustomToolMaterials.CRYSTALL_GREEN, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public int getAOE() {
		return 3;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
