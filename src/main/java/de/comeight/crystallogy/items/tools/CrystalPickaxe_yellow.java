package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.blocks.materials.CustomToolMaterials;

public class CrystalPickaxe_yellow extends BaseCrystalPickaxe {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final String ID = "crystalPickaxe_yellow";
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public CrystalPickaxe_yellow() {
		super(CustomToolMaterials.CRYSTALL_YELLOW, ID);
		setMaxDamage(getMaxDamage() * 2);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public int getAOE() {
		return 7;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
