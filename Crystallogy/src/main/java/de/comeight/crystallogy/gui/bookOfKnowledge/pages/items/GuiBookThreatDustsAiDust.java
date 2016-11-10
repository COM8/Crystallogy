package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookBaseInfusionPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCategory;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookThreatDustsAiDust extends GuiBookBaseInfusionPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookThreatDustsAiDust() {
		super(new ItemStack(ItemHandler.aiRemoverDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.THREAT_DUSTS_BAD_LUCK_PAGE;
	}
	
	@Override
	protected BookInfusionRecipe getRecipe() {
		BookButtonCrafting b = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.blindDust), PageRegistry.THREAT_DUSTS_BLIND_DUST_PAGE);
		b.disableFrame();
		BookButtonCrafting f = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.fertilizerPotato), PageRegistry.FERTILIZER_POTATO_PAGE);
		f.disableFrame();
		BookButtonCrafting p = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.pureCrystallDust), PageRegistry.PURE_CRYSTAL_DUST_PAGE);
		p.disableFrame();
		BookButtonCrafting g = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_yellow), PageRegistry.CRYSTAL_DUST_PAGE);
		g.disableFrame();
		BookButtonCrafting y = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_green), PageRegistry.CRYSTAL_DUST_PAGE);
		y.disableFrame();
		
		BookButtonCrafting[] input = new BookButtonCrafting[]{b, f, p, g, y};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.aiRemoverDust), null);
		output.disableFrame();
		
		return new BookInfusionRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "Has a 60% chance to remove the ai from an entity. If it fails, the entity will die.\n"
				+ "It will not have any effect on a the player, if it gets eaten by one.";
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	@Override
	protected void populateSuggestionsList() {
		super.populateSuggestionsList();
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.pureCrystallDust), PageRegistry.PURE_CRYSTAL_DUST_PAGE));
		suggestionsList.addEntry(new BookButtonCategory(GuiBookPage.getNextButtonId(), 0, 0, null, new ItemStack(ItemHandler.fertilizerPotato), PageRegistry.FERTILIZER_POTATO_PAGE));
	}
	
}
