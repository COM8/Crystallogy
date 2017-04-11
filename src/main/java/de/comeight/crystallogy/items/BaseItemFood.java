package de.comeight.crystallogy.items;

import de.comeight.crystallogy.CommonProxy;
import net.minecraft.item.ItemFood;

public class BaseItemFood extends ItemFood {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseItemFood(int amount, float saturation, boolean isWolfFood, String id) {
		super(amount, saturation, isWolfFood);
		setCreativeTab(CommonProxy.crystallogyMainTab);
		setUnlocalizedName(id);
		setRegistryName(id);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
