package de.comeight.crystallogy.blocks;

import de.comeight.crystallogy.util.RGBColor;

public class Crystall_blue extends Crystall {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public final static String ID = "crystall_blue";

	//-----------------------------------------------Constructor:-------------------------------------------
	public Crystall_blue() {
		super(ID);
		
		this.setParticleColor(new RGBColor(0.15F, 0.25F, 1.0F));
		this.chance = 5;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	

	//-----------------------------------------------Sonstige Methoden:-------------------------------------
}
