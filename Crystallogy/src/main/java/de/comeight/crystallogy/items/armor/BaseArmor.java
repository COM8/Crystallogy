package de.comeight.crystallogy.items.armor;

import de.comeight.crystallogy.CommonProxy;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public abstract class BaseArmor extends ItemArmor {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------
	public BaseArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn, String ID) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		
		setCreativeTab(CommonProxy.crystallogyMainTab);
		setUnlocalizedName(ID);
		setRegistryName(ID);
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	public abstract String getID();
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
