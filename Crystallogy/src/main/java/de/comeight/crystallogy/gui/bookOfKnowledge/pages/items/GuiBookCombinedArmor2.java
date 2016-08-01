package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCombinedArmor2 extends GuiBookBaseArmorPage2 {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCombinedArmor2() {
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected ItemStack getArmorMaterial() {
		return new ItemStack(ItemHandler.combinedArmorCompound);
	}

	@Override
	protected GuiBookPage getArmorMaterialPage() {
		return PageRegistry.COMBINED_ARMOR_COMPOUND_PAGE;
	}

	@Override
	protected ItemArmor getHelmet() {
		return ItemHandler.armorHelmet_combined;
	}

	@Override
	protected ItemArmor getChestplate() {
		return ItemHandler.armorChestplate_combined;
	}

	@Override
	protected ItemArmor getLeggins() {
		return ItemHandler.armorLeggins_combined;
	}

	@Override
	protected ItemArmor getBoots() {
		return ItemHandler.armorBoots_combined;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.combinedArmorCompound), PageRegistry.COMBINED_ARMOR_COMPOUND_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.armorCombiner), PageRegistry.ARMOR_COMBINER_PAGE_1));
	}
	
}
