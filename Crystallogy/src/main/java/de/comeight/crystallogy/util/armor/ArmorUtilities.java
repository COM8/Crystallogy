package de.comeight.crystallogy.util.armor;

import java.util.LinkedList;

import de.comeight.crystallogy.items.armor.Armor_combined;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ArmorUtilities {
	//-----------------------------------------------Variabeln:---------------------------------------------

	
	//-----------------------------------------------Constructor:-------------------------------------------

	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------

	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	/**
	 * Checks wether the {@link ItemStack} contains the given {@link Item}.
	 * It also checks if the {@link ItemStack} contains a piece of {@link Armor_combined} and then checks wether this piece cointais the given {@link ItemArmor}
	 * 
	 * @param itemStackIn the {@link ItemStack} containing the {@link Armor_combined} or any other kind of {@link ItemArmor}.
	 * @param armor reference {@link ItemArmor}
	 * 
	 * @return Wether the itemStackIn contains the armor or not
	 */
	public static boolean hasArmor(ItemStack itemStackIn, ItemArmor armor){
		if(itemStackIn.getItem() instanceof Armor_combined){
			LinkedList<ItemStack> list = Armor_combined.getArmorList(itemStackIn);
			for (ItemStack itemStack : list) {
				if(itemStack.getItem() == armor){
					return true;
				}
			}
		}
		return itemStackIn.getItem() == armor;
	}
	
}
