package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class GuiBookHuntersDreamArmor2 extends GuiBookBaseArmorPage2 {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookHuntersDreamArmor2() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected ItemStack getArmorMaterial() {
		return new ItemStack(ItemHandler.hunterArmorCompound);
	}

	@Override
	protected GuiBookPage getArmorMaterialPage() {
		return PageRegistry.HUNTERS_ARMOR_COMPOUND_PAGE;
	}

	@Override
	protected ItemArmor getHelmet() {
		return ItemHandler.armorHelmet_hunter;
	}

	@Override
	protected ItemArmor getChestplate() {
		return ItemHandler.armorChestplate_hunter;
	}

	@Override
	protected ItemArmor getLeggins() {
		return ItemHandler.armorLeggins_hunter;
	}

	@Override
	protected ItemArmor getBoots() {
		return ItemHandler.armorBoots_hunter;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.hunterArmorCompound), PageRegistry.HUNTERS_ARMOR_COMPOUND_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.energyCrystal), PageRegistry.ENERGY_CRYSTAL_PAGE));
	}
	
}
