package de.comeight.crystallogy.items.tools;

import de.comeight.crystallogy.CommonProxy;
import net.minecraft.item.ItemPickaxe;

public abstract class BaseItemPickaxe extends ItemPickaxe {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseItemPickaxe(ToolMaterial material, String ID) {
		super(material);
		setCreativeTab(CommonProxy.crystallogyMainTab);
		setUnlocalizedName(ID);
		setRegistryName(ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
