package de.comeight.crystallogy.blocks;

import de.comeight.crystallogy.util.RGBColor;

public class Crystall_yellow extends Crystall {
	//-----------------------------------------------Variabeln:---------------------------------------------
	public final static String ID = "crystall_yellow";

	//-----------------------------------------------Constructor:-------------------------------------------
	public Crystall_yellow() {
		super(ID);
		
		this.setParticleColor(new RGBColor(1.0F, 1.0F, 0.0F));
		this.chance = 5;
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
}
