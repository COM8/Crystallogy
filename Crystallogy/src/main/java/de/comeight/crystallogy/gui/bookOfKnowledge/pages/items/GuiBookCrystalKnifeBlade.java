package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookBaseInfusionPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.handler.BlockHandler;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookCrystalKnifeBlade extends GuiBookBaseInfusionPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookCrystalKnifeBlade() {
		super(new ItemStack(ItemHandler.crystalKnifeBlade));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookInfusionRecipe getRecipe() {
		BookButtonCrafting d = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_red), PageRegistry.CRYSTAL_DUST_PAGE);
		d.disableFrame();
		BookButtonCrafting b = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallSwordBlade, 1, 0), PageRegistry.CRYSTAL_SWORD_BLADE_PAGE);
		b.disableFrame();
		BookButtonCrafting c = new BookButtonCrafting(getNextButtonId(), new ItemStack(BlockHandler.crystall_red), PageRegistry.CRYSTALS_PAGE);
		c.disableFrame();
		
		BookButtonCrafting[] input = new BookButtonCrafting[]{c, b, d, d, d};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystalKnifeBlade), null);
		output.disableFrame();
		
		return new BookInfusionRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "Crystal Knife Blades are crafting components for Entity / Player Crystal Knifes.";
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void populateSuggestionsList() {
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(),0, 0, null, new ItemStack[]{	new ItemStack(BlockHandler.crystall_red), 
																										new ItemStack(BlockHandler.crystall_blue),
																										new ItemStack(BlockHandler.crystall_green),
																										new ItemStack(BlockHandler.crystall_yellow)}, 1000, PageRegistry.CRYSTALS_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallDust_red), 
																										new ItemStack(ItemHandler.crystallDust_blue), 
																										new ItemStack(ItemHandler.crystallDust_green), 
																										new ItemStack(ItemHandler.crystallDust_yellow)}, 1000, PageRegistry.CRYSTAL_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack[]{	new ItemStack(ItemHandler.crystallSwordBlade, 1, 0),
																										new ItemStack(ItemHandler.crystallSwordBlade, 1, 1),
																										new ItemStack(ItemHandler.crystallSwordBlade, 1, 2),
																										new ItemStack(ItemHandler.crystallSwordBlade, 1, 3),}, 1000, PageRegistry.CRYSTAL_SWORD_BLADE_PAGE));
		BookButtonCategory infusionButton = new BookButtonCategory(getNextButtonId(), 0, 0, null, new ItemStack(BlockHandler.infuserBlock), PageRegistry.INFUSION_CRAFTING_PAGE);
		infusionButton.setCustomDescription("Infusion Crafting");
		suggestionsList.addEntry(infusionButton);
	}
	
}
