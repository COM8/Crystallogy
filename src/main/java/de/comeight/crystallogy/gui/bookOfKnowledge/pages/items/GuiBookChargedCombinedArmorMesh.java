package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookBaseInfusionPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookChargedCombinedArmorMesh extends GuiBookBaseInfusionPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookChargedCombinedArmorMesh() {
		super(new ItemStack(ItemHandler.chargedCombinedArmorMesh));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookInfusionRecipe getRecipe() {
		BookButtonCrafting c = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.combinedArmorMesh), PageRegistry.COMBINED_ARMOR_MESH_PAGE);
		c.disableFrame();
		BookButtonCrafting a = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.armorPlate, 1, 4), PageRegistry.ARMOR_PLATE_PAGE_1);
		a.disableFrame();
		BookButtonCrafting r = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.REDSTONE), null);
		r.disableFrame();
		BookButtonCrafting l = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.LEATHER), null);
		l.disableFrame();
		BookButtonCrafting d = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.DIAMOND), null);
		d.disableFrame();
		
		BookButtonCrafting[] input = new BookButtonCrafting[]{c, a, r, l, d};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.chargedCombinedArmorMesh), null);
		output.disableFrame();
		
		return new BookInfusionRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "The Charged Combined Armor Mesh is a crafting component for the Combined Armor Compound.";
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.combinedArmorMesh), PageRegistry.COMBINED_ARMOR_MESH_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.combinedArmorCompound), PageRegistry.COMBINED_ARMOR_COMPOUND_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.armorPlate, 1, 0),
																										new ItemStack(ItemHandler.armorPlate, 1, 1),
																										new ItemStack(ItemHandler.armorPlate, 1, 2),
																										new ItemStack(ItemHandler.armorPlate, 1, 3),
																										new ItemStack(ItemHandler.armorPlate, 1, 4),}, 1000, PageRegistry.ARMOR_PLATE_PAGE_1));
		BookButtonCategory infusionButton = new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.infuserBlock), PageRegistry.INFUSION_CRAFTING_PAGE_1);
		infusionButton.setCustomDescription("Infusion Crafting");
		suggestionsList.addEntry(infusionButton);
	}
	
}
