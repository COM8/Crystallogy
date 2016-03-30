package de.comeight.crystallogy.materials;

import net.minecraft.block.material.MapColor;

public class Material extends net.minecraft.block.material.Material{

	//-----------------------------------------------Variabeln:---------------------------------------------
	public static final Material crystall = new Material(MapColor.iceColor);

	//-----------------------------------------------Constructor:-------------------------------------------
	public Material(MapColor color) {
		super(color);
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
}
