package de.comeight.crystallogy.blocks;

import de.comeight.crystallogy.util.RGBColor;

public class Crystall_red extends Crystall {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public final static String ID = "crystall_red";

	//-----------------------------------------------Constructor:-------------------------------------------
	public Crystall_red() {
		super(ID);
		
		this.setParticleColor(new RGBColor(1.0F, 0.0F, 0.0F));
		this.chance = 5;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
