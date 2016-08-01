package de.comeight.crystallogy.gui.bookOfKnowledge.pages.items;

import de.comeight.crystallogy.gui.bookOfKnowledge.BookInfusionRecipe;
import de.comeight.crystallogy.gui.bookOfKnowledge.GuiBookBaseInfusionPage;
import de.comeight.crystallogy.gui.bookOfKnowledge.PageRegistry;
import de.comeight.crystallogy.gui.bookOfKnowledge.buttons.BookButtonCrafting;
import de.comeight.crystallogy.gui.bookOfKnowledge.pages.GuiBookPage;
import de.comeight.crystallogy.handler.ItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBookThreatDusts2 extends GuiBookBaseInfusionPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookThreatDusts2() {
		super(new ItemStack(ItemHandler.badLuckDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookInfusionRecipe getRecipe() {
		BookButtonCrafting f = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.FISHING_ROD), null);
		f.disableFrame();
		BookButtonCrafting c = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.CARROT), null);
		c.disableFrame();
		BookButtonCrafting y = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_yellow), PageRegistry.CRYSTAL_DUST_PAGE);
		y.disableFrame();
		
		BookButtonCrafting[] input = new BookButtonCrafting[]{f, c, y, y, y};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.badLuckDust), null);
		output.disableFrame();
		
		return new BookInfusionRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "Gives the target the Bad Luck Potion Effect for 15 seconds.";
	}
	
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.THREAT_DUSTS_PAGE_3;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------
	
}
