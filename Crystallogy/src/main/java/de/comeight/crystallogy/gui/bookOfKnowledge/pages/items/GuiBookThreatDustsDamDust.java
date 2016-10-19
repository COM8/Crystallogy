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
public class GuiBookThreatDustsDamDust extends GuiBookBaseInfusionPage {
	//-----------------------------------------------Variabeln:---------------------------------------------
	
	
	//-----------------------------------------------Constructor:-------------------------------------------
	public GuiBookThreatDustsDamDust() {
		super(new ItemStack(ItemHandler.damDust));
	}
	
	//-----------------------------------------------Set-, Get-Methoden:------------------------------------
	@Override
	protected BookInfusionRecipe getRecipe() {
		BookButtonCrafting f = new BookButtonCrafting(getNextButtonId(), new ItemStack(Items.FERMENTED_SPIDER_EYE), null);
		f.disableFrame();
		BookButtonCrafting g = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_green), PageRegistry.CRYSTAL_DUST_PAGE);
		g.disableFrame();
		BookButtonCrafting b = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.crystallDust_blue), PageRegistry.CRYSTAL_DUST_PAGE);
		b.disableFrame();
		
		BookButtonCrafting[] input = new BookButtonCrafting[]{f, b, b, g, g};
		
		BookButtonCrafting output = new BookButtonCrafting(getNextButtonId(), new ItemStack(ItemHandler.damDust), null);
		output.disableFrame();
		
		return new BookInfusionRecipe(input, output);
	}

	@Override
	protected String getDescription() {
		return "Damages the target eleven times one damage. The Damage Source is magic.";
	}
	
	@Override
	public GuiBookPage getNextPage() {
		return PageRegistry.THREAT_DUSTS_DROW_DUST_PAGE;
	}
	
	//-----------------------------------------------Sonstige Methoden:-------------------------------------

	
}
