package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookGreenCrystalArmor2 extends GuiBookBaseArmorPage2 {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookGreenCrystalArmor2() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected ItemStack getArmorMaterial() {
		return new ItemStack(ItemHandler.armorPlate, 1, 2);
	}

	@Override
	protected GuiBookPage getArmorMaterialPage() {
		return PageRegistry.ARMOR_PLATE_PAGE_1;
	}

	@Override
	protected ItemArmor getHelmet() {
		return ItemHandler.armorHelmet_green;
	}

	@Override
	protected ItemArmor getChestplate() {
		return ItemHandler.armorChestplate_green;
	}

	@Override
	protected ItemArmor getLeggins() {
		return ItemHandler.armorLeggins_green;
	}

	@Override
	protected ItemArmor getBoots() {
		return ItemHandler.armorBoots_green;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorPlate, 1, 0), 
																													new ItemStack(ItemHandler.armorPlate, 1, 1), 
																													new ItemStack(ItemHandler.armorPlate, 1, 2),
																													new ItemStack(ItemHandler.armorPlate, 1, 3),
																													new ItemStack(ItemHandler.armorPlate, 1, 4)}, 1000, PageRegistry.ARMOR_PLATE_PAGE_1));
	}
	
}
