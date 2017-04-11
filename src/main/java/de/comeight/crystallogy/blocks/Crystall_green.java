package de.comeight.crystallogy.blocks;

import de.comeight.crystallogy.util.RGBColor;

public class Crystall_green extends Crystall {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public final static String ID = "crystall_green";

	//-----------------------------------------------Constructor:-------------------------------------------
	public Crystall_green() {
		super(ID);
		
		this.setParticleColor(new RGBColor(0.0F, 1.0F, 0.0F));
		this.chance = 5;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------

}
