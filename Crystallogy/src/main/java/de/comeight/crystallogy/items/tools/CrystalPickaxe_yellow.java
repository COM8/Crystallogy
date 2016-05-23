package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.blocks.materials.CustomToolMaterials;

public class CrystalPickaxe_yellow extends BaseCrystalPickaxe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "crystalPickaxe_yellow";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystalPickaxe_yellow() {
		super(CustomToolMaterials.CRYSTALL_YELLOW, ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public int getAOE() {
		return 4;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
