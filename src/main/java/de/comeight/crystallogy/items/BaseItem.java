package de.comeight.crystallogy.items;

import de.comeight.crystallogy.CommonProxy;
import net.minecraft.item.Item;

public class BaseItem extends Item {
	//-----------------------------------------------Variabeln:---------------------------------------------


	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseItem(String ID) {
		setCreativeTab(CommonProxy.crystallogyMainTab);
		setUnlocalizedName(ID);
		setRegistryName(ID);
		
		//System.out.println("\"" + this.getUnlocalizedName() + "\" wurde initialisiert.");
	}

	//-----------------------------------------------Set-, Get-Methoden:------------------------------------


	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
}
