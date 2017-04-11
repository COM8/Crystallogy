package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.blocks.materials.CustomToolMaterials;

public class CrystalPickaxe_blue extends BaseCrystalPickaxe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "crystalPickaxe_blue";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystalPickaxe_blue() {
		super(CustomToolMaterials.CRYSTALL_BLUE, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public int getAOE() {
		return 5;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
