package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.CommonProxy;
import net.minecraft.item.ItemSword;

public class BaseItemSword extends ItemSword {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseItemSword(ToolMaterial material, String ID) {
		super(material);
		setCreativeTab(CommonProxy.crystallogyMainTab);
		setUnlocalizedName(ID);
		setRegistryName(ID);
		
		//System.out.println("\"" + this.getUnlocalizedName() + "\" wurde initialisiert.");
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

}
