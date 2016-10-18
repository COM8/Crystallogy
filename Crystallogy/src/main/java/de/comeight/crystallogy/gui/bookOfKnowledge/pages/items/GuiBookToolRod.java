package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookBaseInfusionPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookToolRod extends GuiBookBaseInfusionPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookToolRod() {
		super(new ItemStack(ItemHandler.toolRod));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookInfusionRecipe getRecipe() {
		BookButtonCrafting p = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.pureCrystallDust), PageRegistry.PURE_CRYSTAL_DUST_PAGE);
		p.disableFrame();
		BookButtonCrafting s = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.STICK), null);
		s.disableFrame();
		BookButtonCrafting e = BookButtonCrafting.EMPTY;
		e.disableFrame();
		
		BookButtonCrafting[] input = new BookButtonCrafting[]{s, p, p, e, e};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.toolRod), null);
		output.disableFrame();
		
		return new BookInfusionRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "Tool Rods are used for crafting tools in Crystallogy.";
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.pureCrystallDust), PageRegistry.PURE_CRYSTAL_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallHammer_red), 
																													new ItemStack(ItemHandler.crystallHammer_blue), 
																													new ItemStack(ItemHandler.crystallHammer_green), 
																													new ItemStack(ItemHandler.crystallHammer_yellow)}, 1000, PageRegistry.CRYSTAL_HAMMER_PAGE_1));

		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalSword_red), 
																													new ItemStack(ItemHandler.crystalSword_blue), 
																													new ItemStack(ItemHandler.crystalSword_green), 
																													new ItemStack(ItemHandler.crystalSword_yellow)}, 1000, PageRegistry.CRYSTAL_SWORD_PAGE_1));

		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystalPickaxe_red), 
																													new ItemStack(ItemHandler.crystalPickaxe_blue), 
																													new ItemStack(ItemHandler.crystalPickaxe_green), 
																													new ItemStack(ItemHandler.crystalPickaxe_yellow)}, 1000, PageRegistry.CRYSTAL_PICKAXE_PAGE_1));
		BookButtonCategory infusionButton = new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.infuserBlock), PageRegistry.INFUSION_CRAFTING_PAGE_1);
		infusionButton.setCustomDescription("Infusion Crafting");
		suggestionsList.addEntry(infusionButton);
	}
	
}
